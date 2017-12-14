package com.shenpu.proxy.blacklist;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableFeignClients //feign调用
@Configuration//获取配置
@EnableAutoConfiguration//开启自动配置 
@ComponentScan(value="com.shenpu")
@EnableCircuitBreaker//开启断路路由功能
@EnableDiscoveryClient //服务发现并注册
//@RefreshScope//自我修复注解implements CommandLineRunner
public class BlacklistApplication extends WebMvcConfigurerAdapter {
	public static ConfigurableApplicationContext ctx;

	private static final Logger logger = Logger.getLogger(BlacklistApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BlacklistApplication.class, args);
		logger.info("黑名单启动...");  
	}
}
