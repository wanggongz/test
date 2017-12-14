package com.shenpu.proxy.apigateway.test;

import com.shenpu.base.utils.HttpUtil;

public class TestVerify {

	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			System.out.println(HttpUtil.stringEntityGet("http://localhost:9999/verify/zipkintest"));
		}
	}
	
}
