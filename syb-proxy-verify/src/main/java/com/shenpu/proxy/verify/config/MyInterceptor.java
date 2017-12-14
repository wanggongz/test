//package com.shenpu.proxy.verify.config;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//
///**
// * 拦截器
// */
//public class MyInterceptor implements HandlerInterceptor{
//
//	//用户登录拦截
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		
//		Object user = request.getSession().getAttribute("/user");
//		if(user==null){
//			response.sendRedirect("/tologin");
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
