package com.shenpu.proxy.access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@ComponentScan(value = "com.shenpu")
public class AccessApplication {
	
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
        SpringApplication.run(AccessApplication.class, args);
    }
	
}
