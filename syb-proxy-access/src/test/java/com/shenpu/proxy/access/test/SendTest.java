package com.shenpu.proxy.access.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SendTest {

	@Autowired
	private RabbitTemplate amqpTemplate;
	
	/**
	 * 测试发送打印消息队列
	 */
	@Test
	public void test1(){
		amqpTemplate.convertAndSend("print", "703db6ae-5fc9-486f-a1c5-3f4325db2cb1");
	}
	
}
