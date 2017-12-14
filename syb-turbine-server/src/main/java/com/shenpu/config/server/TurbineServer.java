package com.shenpu.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 
 */
@SpringBootApplication
@EnableTurbine//包含了@EnableDiscoveryCLient注解
@EnableHystrixDashboard 
@EnableEurekaClient
public class TurbineServer {
	
    public static void main(String[] args) {
    	SpringApplication.run(TurbineServer.class,args);
    }
}