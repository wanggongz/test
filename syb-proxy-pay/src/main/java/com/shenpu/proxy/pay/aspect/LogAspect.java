//package com.shenpu.proxy.pay.aspect;
//
//import org.aopalliance.intercept.Joinpoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class LogAspect {
//	
//	@Pointcut(value="execution(public * com.shenpu.proxy.pay.controller.*.*(..))")
//	public void log(){
//		
//	}
//	
//	@Before(value = "log()")
//	public void doBefore(Joinpoint joinpoint){
//		System.out.println("支付日志.........");
//	}
//	
//}
