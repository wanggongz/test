package com.shenpu.proxy.verify.annotation;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * 单例模式注解
 */
@Singleton
public class Database {

	@Named("name")
	@Inject
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
