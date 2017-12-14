package com.shenpu.proxy.scheduler.task.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job2 implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
//		System.out.println("1秒一次------");
	}
	
}
