//package com.shenpu.proxy.access.mq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.shenpu.proxy.access.dto.DataInfo;
//import com.shenpu.proxy.access.service.PolicyService;
//
///**
// * 承保消息队列接收类
// */
//@Component
//@RabbitListener(queues = "access")
//public class AccessRabbitMqReceiver {
//
//	private final static Logger logger = LoggerFactory.getLogger(AccessRabbitMqReceiver.class);
//	
//	@Autowired PolicyService policyService;
//	
//	@RabbitHandler
//	public void process(String applyNo) {
//		DataInfo result = policyService.modifyByPrimaryKeySelective(applyNo);
//		logger.info("applyNo="+applyNo+",result="+result);
//	}
//}
