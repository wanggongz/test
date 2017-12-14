package com.shenpu.proxy.scheduler.controller;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shenpu.proxy.scheduler.dto.SchedulerJob;
import com.shenpu.proxy.scheduler.task.job.Job1;


/**
 * 
 * @author wang_sheng
 */
@RestController
public class TaskController {

	@Autowired
	private Scheduler scheduler;
	
	/**
	 * 启动任务
	 * @param jobName
	 * @return
	 */
	@RequestMapping(value = "/start",method = RequestMethod.POST)
	public String start(@RequestParam String jobName){
		try {
			scheduler.resumeJob(jobName, Scheduler.DEFAULT_GROUP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
	
	/**
	 * 停止任务
	 * @param jobName
	 * @return
	 */
	@RequestMapping(value = "/stop",method = RequestMethod.POST)
	public String stop(@RequestParam String jobName){
		try {
			scheduler.pauseJob(jobName, Scheduler.DEFAULT_GROUP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
	
	/**
	 * 添加job
	 * @param schedulerJob
	 * @throws Exception
	 */
	@RequestMapping(value = "/addJob",method = RequestMethod.POST)
	public void addJob(SchedulerJob schedulerJob) throws Exception{
//		//jobDetail 
//		JobDetail jobDetail = new JobDetail();
//		 jobDetail.setName(schedulerJob.getJobName());
//		 jobDetail.setGroup(schedulerJob.getJobGroupName());
//		 Class<?> forName = Class.forName(schedulerJob.getJobClass());
//		 jobDetail.setJobClass(forName);
//		 //trigger
//		CronTrigger trigger = new CronTrigger();
//		trigger.setName(schedulerJob.getTriggerName());
//		trigger.setGroup(schedulerJob.getTriggerGroupName());
//		trigger.setCronExpression(schedulerJob.getExpression());
		
		
		//创建jobDetail对象；调度员包含调度的方案和策略
		JobDetail jobDetail = new JobDetail();
		//设置工作项
		jobDetail.setJobClass(Job1.class);
		jobDetail.setGroup(Scheduler.DEFAULT_GROUP);
		jobDetail.setName("jobDetail_1");
		//创建trigger，什么时间调
		CronTrigger trigger = new CronTrigger();
		trigger.setCronExpression("5/5 * * * * ?");
		trigger.setName("触发器1");
		
		scheduler.scheduleJob(jobDetail, trigger);
	 }
	
	/**
	 * 启动scheduler
	 * @throws SchedulerException 
	 */
	@RequestMapping(value = "/runScheduler",method = RequestMethod.POST)
	public void runScheduler() throws SchedulerException{
		scheduler.start();
	}
	
	
	/**
	 * 关闭scheduler
	 * @throws SchedulerException
	 */
	public void shutDown() throws SchedulerException {
		scheduler.shutdown();
	}
	
	
	
}
