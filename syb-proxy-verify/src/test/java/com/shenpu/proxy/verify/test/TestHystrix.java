package com.shenpu.proxy.verify.test;

import com.shenpu.base.utils.HttpUtil;

public class TestHystrix {

	public static void main(String[] args) {
		for(int i=0;i<10;i++){
//			System.out.println(HttpUtil.stringEntityGet("http://localhost:9015/get2?message=1"));
//			HttpUtil.stringEntityGet("http://localhost:9511/pay");
			HttpUtil.stringEntityPost("http://localhost:9210/shutdown", "", "utf-8");
		}
	}
	
}
