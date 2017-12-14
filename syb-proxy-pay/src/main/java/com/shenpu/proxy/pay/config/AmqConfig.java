package com.shenpu.proxy.pay.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqConfig {

	@Bean(name="access")
    public Queue AccessMessage() {
        return new Queue("access");
    }
}
