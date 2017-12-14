//package com.shenpu.proxy.verify.config;
//
//import java.io.IOException;
//import java.util.Properties;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//import org.springframework.stereotype.Component;
//
//
///**
// * 配置文件获取（只能获取自定义）：
// * 	可使用@configurationProperties(prefix="配置文件的前缀")
// */
//@Component
//public class SystemConfig {
//
//	private static Properties props;
//	
//	public SystemConfig(){
//		try {
//			ClassPathResource classPathResource = new ClassPathResource("/application.yml");
//			props = PropertiesLoaderUtils.loadProperties(classPathResource);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static String getProperty(String key){
//		return props==null?null:props.getProperty(key);
//	}
//	
//	/**
//     * 获取properyies属性
//     * @return
//     */
//    public static Properties getProperties(){
//        return props;
//    }
//	
//}
