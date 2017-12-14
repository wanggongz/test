package com.shenpu.proxy.scheduler.dto;

public class SchedulerJob {
	private String jobName;
	
	private String jobGroupName;
	
	private String jobClass;
	
	private String jobData;
	
	private String triggerName;
	
	private String triggerGroupName;
	
	private String expression;

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getJobData() {
		return jobData;
	}

	public void setJobData(String jobData) {
		this.jobData = jobData;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroupName() {
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroupName() {
		return triggerGroupName;
	}

	public void setTriggerGroupName(String triggerGroupName) {
		this.triggerGroupName = triggerGroupName;
	}
	
	
}
