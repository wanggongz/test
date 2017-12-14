package com.shenpu.proxy.blacklist.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import com.shenpu.proxy.blacklist.service.GlobalService;

public class ApplicationEventListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		System.out.println(applicationContext);
		GlobalService globalService = applicationContext.getBean(GlobalService.class);
		System.out.println(globalService);
		globalService.saveAllToES();
	}
}



