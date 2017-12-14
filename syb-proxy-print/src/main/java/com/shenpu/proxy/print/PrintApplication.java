package com.shenpu.proxy.print;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration  //获取配置
@EnableAutoConfiguration//开启自动配置
@EnableCircuitBreaker//开启断路路由功能
@EnableDiscoveryClient //服务发现并注册
@RefreshScope//自我修复注解
@EnableFeignClients
public class PrintApplication {
	
//	@Bean
//    public FilterRegistrationBean logFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new WebAopFilter());
//        registration.addUrlPatterns("/");
//        registration.addInitParameter("uuid", "0");
//        registration.setName("WebAopFilter");
//        return registration;
//    }
	
	public static void main(String[] args) {
        SpringApplication.run(PrintApplication.class, args);
    }
}
