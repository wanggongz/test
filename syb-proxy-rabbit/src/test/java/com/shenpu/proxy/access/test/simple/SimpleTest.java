package com.shenpu.proxy.access.test.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.shenpu.proxy.access.simple.SimpleSender;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SimpleTest {

	@Autowired
	private SimpleSender sender;
	
	@Test
	public void test1(){
		for(int i=0;i<10;i++){
			sender.simpleDirectSend("simple message!");
		}
	}
	
}
