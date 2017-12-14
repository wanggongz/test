package com.shenpu.proxy.verify.dto;

import java.util.List;

import com.shenpu.proxy.verify.check.msg.Message;

public abstract class PersonDTO {

	/**
	 * 校验生日格式 yyyy-MM-dd
	 * @param nillable 可以为空：true-可以；false-不可以
	 * @return
	 */
	public abstract Message validatBirthDay(boolean nillable);
	
	/**
	 * 校验手机号码
	 * @param nillable 可以为空：true-可以；false-不可以
	 * @return
	 */
	public abstract Message validatMobile(boolean nillable);
	
	/**
	 * 校验email
	 * @param nillable 可以为空：true-可以；false-不可以
	 * @return
	 */
	public abstract Message validatEmail(boolean nillable);
	
	/**
	 * 校验身份证
	 * @param nillable 可以为空：true-可以；false-不可以
	 * @return
	 */
	public abstract Message validatIDCard(boolean nillable);
	
	/**
	 * 校验报文内部内容是否一致，比如身份证读取的年龄、性别是否一致
	 * @param nillable 可以为空：true-可以；false-不可以
	 * @return
	 */
	public abstract List<Message> accordanceValidate();
}
