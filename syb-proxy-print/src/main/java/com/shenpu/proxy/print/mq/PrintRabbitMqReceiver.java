//package com.shenpu.proxy.print.mq;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.shenpu.base.utils.FieldUtil;
//import com.shenpu.proxy.print.client.VerifyClient;
//import com.shenpu.proxy.print.domain.Appnt;
//import com.shenpu.proxy.print.domain.Bene;
//import com.shenpu.proxy.print.domain.Insured;
//import com.shenpu.proxy.print.domain.Policy;
//import com.shenpu.proxy.print.utils.PdfUtil;
//
//
///**
// * 承保消息队列接收类
// */
//@Component
//@RabbitListener(queues = "print")
//public class PrintRabbitMqReceiver {
//
//	private static final Logger logger = Logger.getLogger(PrintRabbitMqReceiver.class);
//	
////	@Autowired MailUtil mailUtil;
//
//	@Autowired
//	private VerifyClient verifyClient;
//	
//	@SuppressWarnings("unchecked")
//	@RabbitHandler
//	public void process(String applyNo) {
//		//根据applyNo获取投保人，受益人，被保人。
//		Map<String, Object> map = verifyClient.queryAllByApplyNo(applyNo);
//		if(map!=null){
//			Map<String,Object> appntMap = (Map<String,Object>)map.get("appnt");
//			List<Map<String,Object>> insuredsMap = (List<Map<String,Object>>)map.get("insureds");
//			List<Map<String,Object>> benesMap = (List<Map<String,Object>>)map.get("benes");
//			Map<String,Object> policyMap = (Map<String,Object>)map.get("policy");
//			
//			List<Insured> insureds = new ArrayList<Insured>();
//			List<Bene> benes = new ArrayList<Bene>();
//			try {
//				Appnt appnt = (Appnt)FieldUtil.map2Entity(appntMap, Appnt.class);
//				Policy policy = (Policy)FieldUtil.map2Entity(policyMap, Policy.class);
//				for(Map<String,Object> ma: insuredsMap ){
//					Insured insured = (Insured)FieldUtil.map2Entity(ma, Insured.class);
//					insureds.add(insured);
//				}
//				for(Map<String,Object> ma: benesMap){
//					Bene bene = (Bene)FieldUtil.map2Entity(ma, Bene.class);
//					benes.add(bene);
//				}
//				PdfUtil pdfUtil = new PdfUtil();
//				pdfUtil.createPolicyToPdf(applyNo, appnt, insureds, benes,policy);
//				
//				logger.info("生成保单成功.....");
////				boolean sendMail = mailUtil.sendMail("1764614623@qq.com", appnt.getEmail(), appnt.getAppntName(), "保单生成成功", "");
////				if(sendMail){
////					logger.info("流水号："+policy.getApplyNo()+"邮件发送成功。");
////				}else{
////					logger.info("流水号："+policy.getApplyNo()+"邮件发送失败。");
////				}
//			} catch (Exception e) {
//				logger.info("生成保单失败：{}",e);
//				e.printStackTrace();
//				logger.error("打印消息处理异常<<<<<<<<<<<<<<<<<<<<", e);
//			}
//		}
//	}
//	
//}
