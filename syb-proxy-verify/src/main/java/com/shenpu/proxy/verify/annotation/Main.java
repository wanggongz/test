package com.shenpu.proxy.verify.annotation;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

	public static void main(String[] args) {
		DatabaseModule database = new DatabaseModule();
		
		Injector createInjector = Guice.createInjector(database);
		
		System.out.println(createInjector.getInstance(Database.class).getName());
	}
	
}
