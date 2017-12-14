package com.shenpu.base.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

public class ConvertMapUtil{
	
	public static Object ConvertMap(Map<String , Object> map ,Object obj){
		
		try {  
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  

	        for (PropertyDescriptor property : propertyDescriptors) {  
	            String key = property.getName();  
	            if (map.containsKey(key)) {  
	                Object value = map.get(key);  
	                // 得到property对应的setter方法  
	                Method setter = property.getWriteMethod();  
	                setter.invoke(obj, value);  
	            }  

	        }  
	    } catch (Exception e) {  
	        System.out.println("transMap2Bean Error " + e);  
	    }  
	    return obj;
	}
//	
//	public static void main(String[] args) {
//		Map<String, Object> map = new HashMap<String, Object>(); 
//		map.put("insuranceTime", "1");
//		Object obj = ConvertMapUtil(map,new ExcelModule());
//		System.out.println(obj);
//	}
}
