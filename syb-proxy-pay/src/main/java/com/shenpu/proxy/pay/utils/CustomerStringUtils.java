package com.shenpu.proxy.pay.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomerStringUtils{
	
	public static boolean isChineseChar(String str){
        boolean temp = false;
        Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
        Matcher m=p.matcher(str); 
        if(m.find()){ 
            temp =  true;
        }
        return temp;
    }
	
	/**
	 * 根据身份证   字符串   返回 相关信息
	 * @param idCard [0]性别  M:male  F:female	  [1]出生日期  yyyy-MM-dd
	 * @return
	 */
	public static String[] formatIDCard(String idCard){
		String[] returnStr = new String[2];
		char[] str = idCard.toCharArray();
		//倒数第二位  判断性别  男单女双
		if(str[16]%2==0){
			returnStr[0] = "F";
		}else{
			returnStr[0] = "M";
		}
		//拼接 出生年月日
		returnStr[1] = str[6]+""+str[7]+str[8]+str[9]+"-"+str[10]+str[11]+"-"+str[12]+str[13];
		return returnStr;
	}

	/**
	 * 按长度 生成  随机 数字 组合
	 * @param len
	 * @return
	 */
	public static String generateIntNum(int len) {
		Random r = new Random();
		String str = "";
		for (int i = 0; i < len; i++) {
			str+=r.nextInt(10);
		}
		return str;
	}
	
	/**
	 * 按长度 生成  随机 字符 组合
	 * @param len
	 * @return
	 */
	public static String generateString(int len) {
		String source = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			sb.append(source.charAt(random.nextInt(source.length())));
		}
		return sb.toString();
	}
	public static String replaceBlank(String str) {
	        String dest = "";
	        if (str!=null) {
	            Pattern p = Pattern.compile("\n");
	            Matcher m = p.matcher(str);
	            dest = m.replaceAll("");
	        }
	        return dest;
	}
//	public static String processStrings(String str)
//	    {
//	        if(str != null)
//	        {
//	            str = StringUtils.replace(str, "&", "&amp;");
//	            str = StringUtils.replace(str, " ", "&nbsp;");
//	            str = StringUtils.replace(str, "<", "&lt;");
//	            str = StringUtils.replace(str, ">", "&gt;");
//	            str = StringUtils.replace(str, "\r\n", "<br>");
//	            str = StringUtils.replace(str, "\"", "&quot;");
//
//	            return (str);
//	        }
//	        else
//	            return (str);
//	}
}
