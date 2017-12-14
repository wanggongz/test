package com.shenpu.proxy.scheduler.task;
//package com.shenpu.proxy.underwrite.task;
//
//import java.util.Date;
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.TriggerContext;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.web.bind.annotation.RestController;
//
//@EnableScheduling
//@RestController
//public class ChangeCron implements SchedulingConfigurer{
//
//	private String cron = "0/5 * * * * *";
//
//	@Override
//	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//		Runnable task = new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("run。。。。。。。。。。。。。。");
//			}
//		};
//		
//		Trigger trigger = new Trigger() {
//			@Override
//			public Date nextExecutionTime(TriggerContext triggerContext) {
//				CronTrigger cronTrigger = new CronTrigger(cron);
//				return cronTrigger.nextExecutionTime(triggerContext);
//			}
//		};
//		taskRegistrar.addTriggerTask(task, trigger);
//		
//	}
//	
//}
