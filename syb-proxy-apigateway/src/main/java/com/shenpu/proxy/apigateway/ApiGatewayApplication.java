package com.shenpu.proxy.apigateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * apigateway
 * @author jetty
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableZuulProxy
public class ApiGatewayApplication {
	
//	@Bean
//	@LoadBalanced
//	RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
//	
//	
//	@Bean
//    public FilterRegistrationBean logFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new WebAopFilter());
//        registration.addUrlPatterns("/*");
//        registration.addInitParameter("uuid", "0");
//        registration.setName("WebAopFilter");
//        return registration;
//    }
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(ApiGatewayApplication.class).web(true).run(args);
	}
	
}
