package com.shenpu.proxy.verify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = "com.shenpu")
@EnableFeignClients
@RefreshScope//自动刷新配置
@EnableHystrixDashboard
@EnableHystrix
@EnableCircuitBreaker
public class VerifyAppliaction extends WebMvcConfigurerAdapter{
//	
//    @Bean
//    public FilterRegistrationBean logFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new WebAopFilter());
//        registration.addUrlPatterns("/*");
//        registration.addInitParameter("uuid", "0");
//        registration.setName("WebAopFilter");
//        return registration;
//    }
	
	public static void main(String[] args) {
        SpringApplication.run(VerifyAppliaction.class, args);
    }
}
