package com.shenpu.proxy.access.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列，消息队列需要绑定到路由和路线中
 * @author wang_sheng
 */
@Configuration
public class QueueConfig {

	@Autowired
	private DirectExchange directExchange;
	
	@Bean
	public Queue simpleQueue(){
		return new Queue("simple");
	}
	
	@Bean
	public Binding simpleBingding(){
		return BindingBuilder.bind(simpleQueue()).to(directExchange).with("simple-routing");
	}
}
