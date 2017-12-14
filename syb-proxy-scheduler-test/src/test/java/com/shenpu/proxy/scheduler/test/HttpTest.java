package com.shenpu.proxy.scheduler.test;

import org.junit.Test;

import com.shenpu.base.utils.HttpUtil;

import junit.framework.TestCase;

public class HttpTest extends TestCase{

	
	@Test
	public void test1(){
//		String data = "{\"schedulerJob\":}"
//				"{\"jobName\":\"jobName\","
//						+ "\"jobGroupName\":\"jobGroupName\","
//						+ "\"jobClass\":\"jobClass\","
//						+ "\"triggerName\":\"triggerName\","
//						+ "\"triggerGroupName\":\"triggerGroupName\","
//						+ "\"expression\":\"5/5 * * * * ?\""
//						+ "}";
		String stringEntityPost = HttpUtil.stringEntityPost("http://localhost:10101/addJob?"
				+ "jobName=jobName&jobGroupName=jobGroupName&jobClass=jobClass&triggerName=triggerName&triggerGroupName=triggerGroupName&expression='5/5 * * * * ?'", "" ,"utf-8");
		System.out.println(stringEntityPost);
	}
	
	
	public static void main(String[] args) {
		
	}
	
	
	
}
