//package com.shenpu.proxy.verify.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * springmvc静态资源路径设置：
// * 静态资源映射
// * 添加拦截器
// */
//@Configuration
//public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter{
//
//	/**
//	 * 拦截器添加
//	 */
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new MyInterceptor())
//				.addPathPatterns("/**")//添加要拦截的路径
//				.excludePathPatterns("/login","/tologin");//配置不拦截的路径
//		
//		super.addInterceptors(registry);
//	}
//
//	/**
//	 * controller解析
//	 * 	通常访问一个页面需要写一个controller类，然后跳转
//	 * 	重写一下方法即可达到相同的效果
//	 */
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/tologin").setViewName("login");
//		super.addViewControllers(registry);
//	}
//
//	/**
//	 * 配置静态文件路径
//	 */
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		
//		registry.addResourceHandler("/")//指定访问路径
//				.addResourceLocations("classpath:/mystatic/");//指定静态资源目录
//		//指定外部目录
//		registry.addResourceHandler("/").addResourceLocations("file:e://mystatic/");
//		super.addResourceHandlers(registry);
//	}
//	
//	
//	
//
//}
