package com.shenpu.proxy.access.mq;

import java.util.UUID;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shenpu.proxy.access.config.AmqConfig;

@Component  
public class AccessRabbitMqSender implements RabbitTemplate.ConfirmCallback {
	  
    private static final Logger logger = Logger.getLogger(AmqConfig.class);
    
    private RabbitTemplate rabbitTemplate;

	@Autowired
	public AccessRabbitMqSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.rabbitTemplate.setMandatory(true);//设置为true:如果根据exchange和routingkey未找到对应的queue(至少一个)，则将消息返回ReturnCallback。
		this.rabbitTemplate.setConfirmCallback(this);
		this.rabbitTemplate.setReturnCallback(new ReturnCallback(){
			@Override
			public void returnedMessage(Message arg0, int arg1, String arg2, String arg3, String arg4) {
				System.out.println("returned信息********************");
				System.out.println(arg0);System.out.println(arg1);System.out.println(arg2);System.out.println(arg3);System.out.println(arg4);
				System.out.println("********************");
			}
		});
	}

	public void send(String msg) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(AmqConfig.ACCESS_EXCHANGE, AmqConfig.ROUTINGKEY, correlationData.getId().replace("-", "")+"*"+msg, correlationData);
    }

	public void send2(String msg) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend("unknow", "unknow", correlationData.getId().replace("-", "")+"*"+msg, correlationData);
    }
	
	public void send3(String msg) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(AmqConfig.ACCESS_EXCHANGE, "unknow", correlationData.getId().replace("-", "")+"*"+msg, correlationData);
    }
	
	/** 回调方法 */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
        	System.out.println("成功。");
        }else{
        	System.out.println("失败处理....");
        }
    }
}
