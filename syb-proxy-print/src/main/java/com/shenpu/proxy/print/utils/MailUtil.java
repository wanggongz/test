package com.shenpu.proxy.print.utils;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;


/**
 * 邮件工具类
 * 
 * @author jetty
 *
 */
@Component
public class MailUtil {

	private static final Logger logger = Logger.getLogger(MailUtil.class);
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
    Configuration freemarkerConfiguration;
	
	
	@Bean
    public ThreadPoolTaskExecutor createThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(20);
        return
        		threadPoolTaskExecutor;
    }
	
	public boolean sendMail(String from, String to, String userName, String subject, String template) {
		String text;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			@SuppressWarnings("unchecked")
			Map<String, Object> model = new HashedMap();
			model.put("username", userName);
			text = FreeMarkerTemplateUtils.processTemplateIntoString(
					freemarkerConfiguration.getTemplate(template), model);
			helper.setText(text, true);
			mailSender.send(mimeMessage);
			return true;
		} catch (IOException | TemplateException e) {
			logger.error("发送邮件的I/O或者短信模板生成异常：{}", e);
			return false;
		} catch (MessagingException e) {
			logger.error("发送邮件生成异常：{}", e);
			return false;
		}
	}
}
