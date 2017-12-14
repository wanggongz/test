package com.shenpu.proxy.pay.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessRabbitMqSender {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(String applyNo){
		rabbitTemplate.convertAndSend("access", applyNo);
	}
	
	
	
}
