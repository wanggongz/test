package com.shenpu.proxy.access.mq;

import java.util.UUID;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.shenpu.proxy.access.config.AmqConfig;

@Component  
public class PrintRabbitMqSender implements RabbitTemplate.ConfirmCallback {
	  
    private static final Logger logger = Logger.getLogger(AmqConfig.class);
    
    private RabbitTemplate rabbitTemplate;

	@Autowired
	public PrintRabbitMqSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.rabbitTemplate.setConfirmCallback(this);
	}

	public void send(String msg) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(AmqConfig.ACCESS_EXCHANGE, AmqConfig.ROUTINGKEY, msg, correlationData);

    }

	/** 回调方法 */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("confirm: " + correlationData.getId()+",ack:"+ack+",cause:"+cause);
    }
}
