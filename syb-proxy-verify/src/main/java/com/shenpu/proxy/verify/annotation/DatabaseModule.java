package com.shenpu.proxy.verify.annotation;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;


public class DatabaseModule extends AbstractModule{
	@Override
	protected void configure() {
		bind(String.class).annotatedWith(Names.named("name")).toInstance("ws");
	}
}
