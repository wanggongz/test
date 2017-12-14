package com.shenpu.proxy.scheduler.comfig;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.shenpu.proxy.scheduler.task.job.Job1;

@Configuration
public class SchedulerConfig {
	
	@Bean
    public Scheduler scheduler() throws Exception{
    	System.out.println("创建schedulerFactory对象");
    	SchedulerFactory factory = new StdSchedulerFactory("quartz.properties");
    	Scheduler scheduler = factory.getScheduler();
    	scheduler.start();
		return  scheduler;
//    	try {
//    		
//    		//创建jobDetail对象；调度员包含调度的方案和策略
//    		JobDetail jobDetail = new JobDetail();
//    		//设置工作项
//    		jobDetail.setJobClass(Job1.class);
//    		jobDetail.setGroup(Scheduler.DEFAULT_GROUP);
//    		jobDetail.setName("jobDetail_1");
//    		//创建trigger，什么时间调
//    		CronTrigger trigger = new CronTrigger();
//    		trigger.setCronExpression("5/5 * * * * ?");
//    		trigger.setName("触发器1");
//    		
//    		scheduler.scheduleJob(jobDetail, trigger); 
//    		
//    		scheduler.start();
//    		scheduler.pauseAll();
//    		return scheduler;
//		} catch (SchedulerException e) {
//			e.printStackTrace();
//			return null;
//		}
    }
}
