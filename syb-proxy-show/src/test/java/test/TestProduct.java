package test;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shenpu.base.utils.HttpUtil;

public class TestProduct {
	
	/**
	 * 测试    产品分页展示
	 * @throws JsonProcessingException
	 */
	@Test
	public void Test1() throws JsonProcessingException{
		//http://192.168.1.254:59999/show
		//http://localhost:7071
//		String post = HttpUtil.stringEntityPost("http://192.168.1.254:59999/show/product/queryByPage.htm", "{\"page\":1,\"rows\":1}","utf-8");
		String post = HttpUtil.stringEntityPost("http://peer1:8011/product/queryByPage.htm", "{\"page\":1,\"rows\":1}","utf-8");
		System.out.println(post);
	}
	
}
