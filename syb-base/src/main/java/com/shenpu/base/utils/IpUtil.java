package com.shenpu.base.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * ip工具类
 * @author jetty
 */
public final class IpUtil {

	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || "".equals(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getIPLocation(HttpServletRequest request) {
		String ip = getForwordedForIP(request.getHeader("x-forwarded-for"));
		@SuppressWarnings("unused")
		int ipsrc = 0;
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			ipsrc = 1;
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			ipsrc = 2;
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			ipsrc = 3;
		}
		if (ip == null) {
			ip = "";
		} else {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	private static String getForwordedForIP(String ipstr) {
		boolean t = true;
		if (ipstr != null && ipstr.trim().length() > 0) {
			if (ipstr.indexOf(".") == -1) {
				ipstr = null;
			} else {
				if (ipstr.indexOf(",") != -1) {
					ipstr = ipstr.trim().replace("'", "");
					String[] temparyip = ipstr.split(",");
					for (int i = 0; i < temparyip.length; i++) {
						if (isIPAddress(temparyip[i]) && t == true
								&& temparyip[i].substring(0, 3) != "10."
								&& temparyip[i].substring(0, 7) != "192.168"
								&& temparyip[i].substring(0, 7) != "172.16.") {
							ipstr = temparyip[i];
							t = false;
						}
					}
					ipstr = ipstr.split(",")[0];
				} else if (!isIPAddress(ipstr)) {
					ipstr = null;
				}
			}
		}
		return ipstr;
	}

	/**
	 * 判断是否是IP地址格式
	 * 
	 * @param str1
	 * @return
	 */
	private static boolean isIPAddress(String str1) {
		boolean isIP = true;
		if (str1 == null || str1.trim().length() < 7
				|| str1.trim().length() > 15) {
			isIP = false;
		}
		return isIP;
	}
}
