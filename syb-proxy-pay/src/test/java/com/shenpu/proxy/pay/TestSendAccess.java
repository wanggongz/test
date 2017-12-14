package com.shenpu.proxy.pay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.shenpu.proxy.pay.mq.AccessRabbitMqSender;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestSendAccess {

	@Autowired
	private AccessRabbitMqSender accessRabbitMqSender;
	
	/**
	 * 测试发送承包消息队列
	 */
	@Test
	public void test1(){
		accessRabbitMqSender.send("a0f93b46-cbef-44e5-be25-5ce867e3834c");
	}
	
}
