package com.shenpu.proxy.access.config.rabbit;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息路由
 * @author wang_sheng
 */
@Configuration
public class ExchangeConfig {

	/**
	 * 
	 */
	@Bean
	public DirectExchange directExchange(){
		return new DirectExchange("direct_exchange");
	}
	
}
