package com.shenpu.proxy.access.config;

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
import com.shenpu.proxy.access.dto.DataInfo;
import com.shenpu.proxy.access.service.PolicyService;

@Configuration
public class AmqConfig {

	@Autowired 
	private PolicyService policyService;
	
	public static final String ACCESS_EXCHANGE = "access_exchange";

	public static final String ROUTINGKEY = "routing";
	
	public static final String PRINT_QUEUE = "print";
	
	public static final String ACCESS_QUEUE = "access";

	private static final Logger logger = Logger.getLogger(AmqConfig.class);
	
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
		return connectionFactory;
	}

	@Bean
	/** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		return template;
	}
	
	@Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(AmqConfig.ACCESS_EXCHANGE);
    }
	
    @Bean
    public Queue printQueue() {
        return new Queue(AmqConfig.PRINT_QUEUE);
    }
    
    @Bean
    public Queue accessQueue() {
        return new Queue(AmqConfig.ACCESS_QUEUE);
    }

    @Bean
    public Binding binding() {
    	return BindingBuilder.bind(printQueue()).to(defaultExchange()).with(AmqConfig.ROUTINGKEY);
    }
    
    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(accessQueue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                logger.info("开始处理消息:"+body);
				MessageProperties messageProperties = message.getMessageProperties();
				String applyNo = new String(body);
				long deliveryTag = messageProperties.getDeliveryTag();
                try {
					DataInfo result = policyService.modifyByPrimaryKeySelective(applyNo);
					logger.info("applyNo="+applyNo+",result="+result);
					logger.info("处理完成");
					channel.basicAck(deliveryTag, false); //确认消息成功消费
				} catch (Exception e) {
					e.printStackTrace();
					channel.basicNack(deliveryTag, false,true); //重新回到队列
//					channel.basicReject(deliveryTag, false);//拒绝消费
				}
            }
        });
        return container;
    }
    
}
