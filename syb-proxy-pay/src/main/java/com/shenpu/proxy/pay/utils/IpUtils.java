package com.shenpu.proxy.pay.utils;


import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @desc ip工具
 *
 * @author hcy
 *
 * 2016年10月18日
 */
public final class IpUtils {

	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || "".equals(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getIPLocation(HttpServletRequest request) {
		String ip = getForwordedForIP(request.getHeader("x-forwarded-for"));
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
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
			// 可能有代理
			if (ipstr.indexOf(".") == -1) {
				// 没有“.”肯定是非IPv4格式
				ipstr = null;
			} else {
				if (ipstr.indexOf(",") != -1) {
					// 有","，估计多个代理。取第一个不是内网的IP。
					ipstr = ipstr.trim().replace("'", "");
					String[] temparyip = ipstr.split(",");
					for (int i = 0; i < temparyip.length; i++) {
						if (isIPAddress(temparyip[i]) && t == true
								&& temparyip[i].substring(0, 3) != "10."
								&& temparyip[i].substring(0, 7) != "192.168"
								&& temparyip[i].substring(0, 7) != "172.16.") {
							ipstr = temparyip[i]; // 找到不是内网的地址
							t = false;// 不继续找了
						}
					}
					ipstr = ipstr.split(",")[0];// 可能筛选后还是多个地址，取第一个
				} else if (!isIPAddress(ipstr)) {// 代理即是IP格式
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
