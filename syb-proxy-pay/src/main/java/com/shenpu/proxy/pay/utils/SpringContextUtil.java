package com.shenpu.proxy.pay.utils;
//package cn.util;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//
///**
// * @author Levi.Wang
// * SpringContext工具类
// */
//public class SpringContextUtil implements ApplicationContextAware {  
//	  
//    // Spring应用上下文环境  
//    private static ApplicationContext applicationContext;  
//  
//    /** 
//     * 实现ApplicationContextAware接口的回调方法，设置上下文环境 
//     * @param applicationContext 
//     */  
//    @SuppressWarnings("static-access")
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext)
//			throws BeansException {
//		this.applicationContext =  applicationContext;
//	}  
//
//    /** 
//     * 获取对象 
//     *  
//     * @param name 
//     * @return Object
//     * @throws BeansException 
//     */  
//    public static<T> T getBean(String name,Class<T> clz) throws BeansException {  
//        return applicationContext.getBean(name,clz);  
//    }
//
//    /** 
//     * 获取对象 
//     *  
//     * @return Object
//     * @throws BeansException 
//     */  
//    public static<T> T getBean(Class<T> clz) throws BeansException {  
//        return applicationContext.getBean(clz);  
//    }
//}
