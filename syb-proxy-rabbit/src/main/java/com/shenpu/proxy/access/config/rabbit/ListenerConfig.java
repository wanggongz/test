package com.shenpu.proxy.access.config.rabbit;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.client.Channel;

/**
 * 消息接受
 * @author wang_sheng
 */
@Configuration
public class ListenerConfig {

	@Autowired
	private Queue simpleQueue;
	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(simpleQueue);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(10);
        container.setConcurrentConsumers(2);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {
			@Override
            public void onMessage(Message message, Channel channel) throws Exception {
                String string = new String(message.getBody());
                System.out.println("消息唯一标识："+string);
                try {
					MessageProperties messageProperties = message.getMessageProperties();
					
					System.out.println(messageProperties);
					
					long deliveryTag = messageProperties.getDeliveryTag();//传递信息（标签）
					
					String value = string.substring(string.indexOf("*")+1, string.length());
					System.out.println("传递的值为："+value);
					channel.basicAck(deliveryTag, false); //确认消息成功消费
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
        return container;
    }
	
}
