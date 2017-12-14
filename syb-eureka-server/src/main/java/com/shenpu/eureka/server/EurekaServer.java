package com.shenpu.eureka.server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心
 * @author jetty
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {
	
	private final static Logger logger = LogManager.getLogger(EurekaServer.class.getName()); 
	
	public static void main(String[] args) {
		logger.info("服务注册中心已启动...");
		SpringApplication.run(EurekaServer.class, args);
	}
}
