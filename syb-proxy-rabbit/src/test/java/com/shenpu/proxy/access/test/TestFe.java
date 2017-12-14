package com.shenpu.proxy.access.test;

import org.junit.Test;

import com.shenpu.base.utils.HttpUtil;

public class TestFe {
	
	/**
	 * 测试承包接口
	 */
	@Test
	public void testAccess(){
		test(1);
	}
	
	public void test(int i){
		String stringEntityPost = "";
		if(i==1){
			stringEntityPost = HttpUtil.stringEntityPost("http://peer1:8089/access", "a0f93b46-cbef-44e5-be25-5ce867e3834c", "utf-8");
		}
		if(i==2){
			stringEntityPost = HttpUtil.stringEntityPost("http://localhost:9999/access/access", "a0f93b46-cbef-44e5-be25-5ce867e3834c", "utf-8");
		}
		if(i==3){
			stringEntityPost = HttpUtil.stringEntityPost("http://192.168.1.254:59999/access/access", "ab4ff798-0175-4024-8066-c85d2b42c9d7", "utf-8");
		}
		System.out.println("<<<<<<<<<<<<<<<<<<"+stringEntityPost);
	}
	
}
