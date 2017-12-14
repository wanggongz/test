package com.shenpu.proxy.show;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration  //获取配置
@EnableAutoConfiguration//开启自动配置 
@ComponentScan(value="com.shenpu")
@EnableCircuitBreaker//开启断路路由功能
@EnableDiscoveryClient //服务发现并注册
@RefreshScope//自我修复注解
public class ShowApplication {
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
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
        SpringApplication.run(ShowApplication.class, args);
    }
}
