package com.shenpu.proxy.access.config;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.rabbitmq.client.Channel;
import com.shenpu.proxy.access.util.RedisManager;

/**
 * 确认机制
 * **********************************************************
 * 消息发送：到达消费者时发送回执。
 * 消息消费：设置手动消费成功：
 * 		       	执行channel.basicAck(messageProperties.getDeliveryTag(), false);//确认消息成功消费
 * 		       	否则这个消息未被消费。
 * 		      可设置当前最大消费数量。
 * 				到达最大消费数量时，会出现阻塞，即不再处理消息，直到之前的消息处理结束。
 *  确认机制：confirm机制：发送到exchange即可返回成功。
 *  	   return机制：exchange和routing都存在时成功，但只有在失败时才返回信息。
 *  
 *  业务处理失败：1等待、2拒绝、3重新处理。
 *  **********************************************************
 *  
 *  
 *  基本原理
 *  **********************************************************
 *  exchange:消息的接受和转发，指定了按照什么规则（routing_key）,发送到那个queue
 *  
 *  **********************************************************
 */
@Configuration
public class AmqConfig {
	
	public static final String ACCESS_EXCHANGE = "access_exchange";

	public static final String ROUTINGKEY = "routing";
	
	public static final String PRINT_QUEUE = "print";
	
	public static final String ACCESS_QUEUE = "access";

	private static final Logger logger = Logger.getLogger(AmqConfig.class);
	
	//记录连续失败次数。
	AtomicLong totalCount = new AtomicLong();
	
	@Autowired
	private RedisManager redisManager;
	
	@Value("${spring.rabbitmq.host}")
	private String host;

	@Value("${spring.rabbitmq.port}")
	private Integer port;
	
	@Value("${spring.rabbitmq.username}")
	private String username;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Value("${spring.rabbitmq.virtual-host}")
	private String virtualHost;

	@Value("${spring.rabbitmq.publisher-confirms}")
	private boolean publisherConfirms;

	@Bean
	public ConnectionFactory connectionFactory(){
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setVirtualHost("/");
		connectionFactory.setPublisherConfirms(true);
		connectionFactory.setPublisherReturns(true);
		return connectionFactory;
	}

	@Bean
	/** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		return template;
	}
	

	/**
	 */
	@Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(AmqConfig.ACCESS_EXCHANGE);
    }
	
    @Bean
    public Queue accessQueue() {
        return new Queue(AmqConfig.ACCESS_QUEUE);
    }

    @Bean
    public Binding binding() {
    	return BindingBuilder.bind(accessQueue()).to(defaultExchange()).with(AmqConfig.ROUTINGKEY);
    }
    
    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(accessQueue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(10);
        container.setConcurrentConsumers(2);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {
			@Override
            public void onMessage(Message message, Channel channel) throws Exception {
                String string = new String(message.getBody());
                try {
                	int nextInt = RandomUtils.nextInt(10);
					logger.info("开始处理任务:"+nextInt+"....");
					
					Thread.sleep(3000L);
					MessageProperties messageProperties = message.getMessageProperties();
					long deliveryTag = messageProperties.getDeliveryTag();
					
					System.out.println("消息唯一标识："+string);
					String value = string.substring(string.indexOf("*")+1, string.length());
					System.out.println("传递的值为："+value);
					if(nextInt%2==1){
						channel.basicAck(deliveryTag, false); //确认消息成功消费
						logger.info("处理完成");
						totalCount.set(0);
					}else{
						//拒绝消息
						System.out.println("业务处理失败,重试开始--------------------");
						Object byStringKey = redisManager.getByStringKey(string);
						Integer count = 0;
						if(byStringKey==null||(count = (Integer)byStringKey)<3){
							System.out.println("当前byStringKey重试次数："+count);
							redisManager.incr(string);
							//ack返回false，并重新回到队列，重新处理。
							channel.basicNack(deliveryTag, false, true);
						}else{
							//连续三次处理失败，则形成阻塞。不在处理。
							long incrementAndGet = totalCount.incrementAndGet();
							System.out.println("当前连续处理失败次数："+incrementAndGet);
							if(incrementAndGet>2){
								System.out.println("服务器连续处理失败2次，暂停服务..........");
							}else{
								System.out.println("重试超过2次，拒绝消费，保存信息..........");
								channel.basicReject(deliveryTag, false);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
        return container;
    }
}
