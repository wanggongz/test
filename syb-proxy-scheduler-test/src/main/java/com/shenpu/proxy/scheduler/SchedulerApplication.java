package com.shenpu.proxy.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(value = "com.shenpu")//扫描包
//@EnableJpaRepositories(basePackages="com.shenpu.proxy.underwrite.repository")//jpa包
@EnableScheduling//定时任务
public class SchedulerApplication{
    
	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}
	
}
