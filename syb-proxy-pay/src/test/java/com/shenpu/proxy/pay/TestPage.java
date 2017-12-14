package com.shenpu.proxy.pay;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenpu.proxy.pay.domain.User;
import com.shenpu.proxy.pay.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PayApplication.class)
public class TestPage {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void test1(){
		
		PageHelper.startPage(1,1);
		List<User> list = userMapper.list();
		
		System.out.println(list.size());
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		System.out.println(pageInfo.getTotal());
		
	}
	
}
