package com.shenpu.proxy.pay.utils;
//package cn.util;
//
//public class UserSessionUtil {
//	
//	
//	// Redis key前缀
//	private final static String WXopenIdSession = "SessionWxOpenId";
//	
//	// 默认会话过期时间
//	private final static int sessionExpiresSecs = 7200;
//	// 用户会话过期时间(用户登陆状态超时时间)
//	private final static int userSessionExpireSecs = 7200;
//
//	private static RedisUtil redisUtil = SpringContextUtil.getBean("redisUtil", RedisUtil.class);
//
//	public static void saveSessionWxOpenId(String sessionId,String openId) {
//		redisUtil.setString(getRedisKey(WXopenIdSession, sessionId), openId, sessionExpiresSecs);
//	}
//	
//	private static String getRedisKey(String prefix, String key) {
//		StringBuilder strbuilder = new StringBuilder(prefix);
//		return strbuilder.append(key).toString();
//	}
//	
//}
