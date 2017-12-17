package com.shenpu.proxy.access.simple;

import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息发送
 * 
 * 消息发送到【那个路由exchange】走【那条路线routing】
 * 
 * @author wang_sheng
 */
@RestController
public class SimpleSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * 直接交换器
	 */
	public void simpleDirectSend(String message){
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend("direct_exchange", "simple-routing", correlationData.getId().replace("-", "")+"*"+message, correlationData);
	}
	
	/**
	 * 广播式交换机
	 * 忽略路线，消息会发送到所有绑定到这个交换机上的队列
	 */
	public void simpleFanoutSend(String message){
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend("fanout_exchange", "all", correlationData.getId().replace("-", "")+"*"+message, correlationData);
	}
	
	
	/**
	 * 主题交换机
	 * 路线匹配（* 表是匹配一个任意词组，#表示匹配0个或多个词组），
	 */
	public void simpleTopicSend(String message){
		CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend("topic_exchange", "simple_#", correlationData.getId().replace("-", "")+"*"+message, correlationData);
	}
	
}
