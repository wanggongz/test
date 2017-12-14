package com.shenpu.proxy.scheduler.task.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job1 implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("5秒执行一次-----------------"+new Date());
	}
}

