package com.shenpu.proxy.print.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.client.Channel;
import com.shenpu.base.utils.FieldUtil;
import com.shenpu.proxy.print.client.VerifyClient;
import com.shenpu.proxy.print.domain.Appnt;
import com.shenpu.proxy.print.domain.Bene;
import com.shenpu.proxy.print.domain.Insured;
import com.shenpu.proxy.print.domain.Policy;
import com.shenpu.proxy.print.utils.MailUtil;
import com.shenpu.proxy.print.utils.PdfUtil;

@Configuration
public class AmqConfig {

	public static final String ACCESS_EXCHANGE = "access_exchange";

	public static final String ROUTINGKEY = "routing";
	
	public static final String PRINT_QUEUE = "print";
	
	private static final Logger logger = Logger.getLogger(AmqConfig.class);
	
	@Autowired MailUtil mailUtil;

	@Autowired
	private PdfUtil pdfUtil;
	
	
	@Autowired
	private VerifyClient verifyClient;
	
	@Bean
	public ConnectionFactory connectionFactory(){
		logger.info("ConnectionFactory创建------------");
		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setAddresses("localhost:5672");
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setVirtualHost("/");
		factory.setPublisherConfirms(true);
		return factory;
	}
	
	
    /**
     * @return
     */
    @Bean  
    public Queue queue() {  
    	return new Queue("print", true); //队列持久  
    }
	
	
    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                try {
					logger.info("开始处理:"+body);
					logger.info("处理中....");
//					boolean nextInt = (RandomUtils.nextInt(10)%2)==1;
//					if(nextInt){
//						throw new Exception("异常！");
//					}
					MessageProperties messageProperties = message.getMessageProperties();
					pro(new String(body));
					logger.info("处理完成");
					channel.basicAck(messageProperties.getDeliveryTag(), false); //确认消息成功消费
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
        return container;
    }
    
    
    @SuppressWarnings("unchecked")
	public void pro(String applyNo){
    	//根据applyNo获取投保人，受益人，被保人。
		Map<String, Object> map = verifyClient.queryAllByApplyNo(applyNo);
		if(map!=null){
			Map<String,Object> appntMap = (Map<String,Object>)map.get("appnt");
			List<Map<String,Object>> insuredsMap = (List<Map<String,Object>>)map.get("insureds");
			List<Map<String,Object>> benesMap = (List<Map<String,Object>>)map.get("benes");
			Map<String,Object> policyMap = (Map<String,Object>)map.get("policy");
			
			List<Insured> insureds = new ArrayList<Insured>();
			List<Bene> benes = new ArrayList<Bene>();
			try {
				Appnt appnt = (Appnt)FieldUtil.map2Entity(appntMap, Appnt.class);
				Policy policy = (Policy)FieldUtil.map2Entity(policyMap, Policy.class);
				for(Map<String,Object> ma: insuredsMap ){
					Insured insured = (Insured)FieldUtil.map2Entity(ma, Insured.class);
					insureds.add(insured);
				}
				for(Map<String,Object> ma: benesMap){
					Bene bene = (Bene)FieldUtil.map2Entity(ma, Bene.class);
					benes.add(bene);
				}
				pdfUtil.createPolicyToPdf(applyNo, appnt, insureds, benes,policy);
				
				logger.info("生成保单成功.....");
//    					boolean sendMail = mailUtil.sendMail("1764614623@qq.com", appnt.getEmail(), appnt.getAppntName(), "保单生成成功", "");
//    					if(sendMail){
//    						logger.info("流水号："+policy.getApplyNo()+"邮件发送成功。");
//    					}else{
//    						logger.info("流水号："+policy.getApplyNo()+"邮件发送失败。");
//    					}
			} catch (Exception e) {
				logger.info("生成保单失败：{}",e);
				e.printStackTrace();
				logger.error("打印消息处理异常<<<<<<<<<<<<<<<<<<<<", e);
			}
		}
    }
    
}
