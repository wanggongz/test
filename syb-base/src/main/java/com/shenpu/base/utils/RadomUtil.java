package com.shenpu.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 随机命名规则类
 * 
 * @author jetty
 *
 */
public class RadomUtil {

	/**
	 * 当前年月日时分秒+五位随机数
	 * 
	 * @return
	 */
	public static String getTime5Bit() {
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		return str + rannum;
	}

	/**
	 * 当前年月日时分秒+8位随机数
	 * 
	 * @return
	 */
	public static String getTime10Bit() {
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String str = simpleDateFormat.format(date);
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999999 - 10000000 + 1)) + 10000;
		return str + rannum;
	}

	/**
	 * 获取32位uuid
	 * 
	 * @return
	 */
	public static String get32BitUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

	/**
	 * 获取64位uuid
	 * 
	 * @return
	 */
	public static String get64BitUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
}
