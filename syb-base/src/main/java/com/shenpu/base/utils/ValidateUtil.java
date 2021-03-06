package com.shenpu.base.utils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 校验工具类
 * 
 * @author jetty
 *
 */
public class ValidateUtil {
	/**
	 * Email正则表达式="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	 */
	public static final String REG_EMAIL = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";

	
	/**
	 * 电话号码正则表达式=
	 * (^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)
	 */
	public static final String REG_CALL = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";

	/**
	 * 手机号码正则表达式=^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$
	 */
	public static final String REG_MOBILE = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";

	/**
	 * Integer正则表达式 ^-?(([1-9]\d*$)|0)
	 */
	public static final String REG_INTEGER = "^-?(([1-9]\\d*$)|0)";

	/**
	 * 正整数正则表达式 >=0 ^[1-9]\d*|0$
	 */
	public static final String REG_INTEGER_POSITIVE = "^[1-9]\\d*|0$";

	/**
	 * 负整数正则表达式 <=0 ^-[1-9]\d*|0$
	 */
	public static final String REG_INTEGER_NEGATIVE = "^-[1-9]\\d*|0$";

	/**
	 * Double正则表达式 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$
	 */
	public static final String REG_DOUBLE = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";

	/**
	 * 正Double正则表达式 >=0 ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$
	 */
	public static final String REG_DOUBLE_POSITIVE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";

	/**
	 * 负Double正则表达式 <= 0 ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$
	 */
	public static final String REG_DOUBLE_NEGATIVE = "^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";

	/**
	 * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁
	 */
	public static final String REG_AGE = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";

	/**
	 * 邮编正则表达式 [0-9]\d{5}(?!\d) 国内6位邮编
	 */
	public static final String REG_CODE = "[0-9]\\d{5}(?!\\d)";

	/**
	 * 匹配由数字、26个英文字母或者下划线组成的字符串 ^\w+$
	 */
	public static final String REG_ENG_NUM_ = "^\\w+$";

	/**
	 * 匹配由数字和26个英文字母组成的字符串 ^[A-Za-z0-9]+$
	 */
	public static final String REG_ENG_NUM = "^[A-Za-z0-9]+";

	/**
	 * 匹配由26个英文字母组成的字符串 ^[A-Za-z]+$
	 */
	public static final String REG_ENG = "^[A-Za-z]+$";

	/**
	 * 过滤特殊字符串正则
	 * regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]";
	 */
	public static final String REG_SPECIAL = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]";

	/***
	 * 日期正则 支持： YYYY-MM-DD YYYY/MM/DD YYYY_MM_DD YYYYMMDD YYYY.MM.DD的形式
	 */
	public static final String REG_DATE_ALL = "((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)"
			+ "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)"
			+ "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)"
			+ "([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)"
			+ "|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)"
			+ "(0?2)([-\\/\\._]?)(29)$)"
			+ "|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)"
			+ "([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|"
			+ "(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))";
	/***
	 * 日期正则 支持： YYYY-MM-DD
	 */
	public static final String REG_DATE_FORMAT1 = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

	/**
	 * URL正则表达式 匹配 http www ftp
	 */
	public static final String REG_URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?"
			+ "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*"
			+ "(\\w*:)*(\\w*\\+)*(\\w*\\.)*" + "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";

	/**
	 * 身份证正则表达式
	 */
	public static final String REG_IDCARD = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})"
			+ "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" + "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";

	/**
	 * 匹配数字组成的字符串 ^[0-9]+$
	 */
	public static final String REG_NUMBER = "^[0-9]+$";
	
	/**
	 * 匹配这样的日期时间格式 YYYY-MM-dd HH:MM:SS
	 */
	public static final String REG_LEGAL_FORMATE_DATE_TIME = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

	
	/**
	 * 匹配这样的日期格式 YYYY-MM-dd
	 */
	public static final String REG_LEGAL_FORMATE_DATE = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";

	
	/**
	 * 判断字段是否为Email 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmail(String str) {
		return regular(str, REG_EMAIL);
	}

	/**
	 * 判断是否为电话号码 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isCall(String str) {
		return regular(str, REG_CALL);
	}

	/**
	 * 判断是否为手机号码 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isMobile(String str) {
		return regular(str, REG_MOBILE);
	}

	/**
	 * 判断是否为Url 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isUrl(String str) {
		return regular(str, REG_URL);
	}

	/**
	 * 判断字段是否为INTEGER 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isInteger(String str) {
		return regular(str, REG_INTEGER);
	}

	/**
	 * 判断字段是否为正整数正则表达式 >=0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isIntegerPositive(String str) {
		return regular(str, REG_INTEGER_NEGATIVE);
	}

	/**
	 * 判断字段是否为负整数正则表达式 <=0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isIntegerNegative(String str) {
		return regular(str, REG_INTEGER_NEGATIVE);
	}

	/**
	 * 判断字段是否为DOUBLE 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDouble(String str) {
		return regular(str, REG_DOUBLE);
	}

	/**
	 * 判断字段是否为正浮点数正则表达式 >=0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDoublePositive(String str) {
		return regular(str, REG_DOUBLE_POSITIVE);
	}

	/**
	 * 判断字段是否为负浮点数正则表达式 <=0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDoubleNegative(String str) {
		return regular(str, REG_DOUBLE_NEGATIVE);
	}

	/**
	 * 判断字段是否为日期 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDate(String str) {
		return regular(str, REG_DATE_ALL);
	}

	/**
	 * 验证2016-12-17
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDate1(String str) {
		return regular(str, REG_DATE_FORMAT1);
	}

	/**
	 * 判断字段是否为年龄 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isAge(String str) {
		return regular(str, REG_AGE);
	}

	/**
	 * 判断字段是否超长 字串为空返回fasle, 超过长度{length}返回ture 反之返回false
	 * 
	 * @param str
	 * @param leng
	 * @return boolean
	 */
	public static boolean isLengthOut(String str, int length) {
		return StringUtils.isBlank(str) ? false : str.trim().length() > length;
	}

	/**
	 * 判断字段是否为身份证 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isIDCard(String str) {
		if (StringUtils.isBlank(str))
			return false;
		if (str.trim().length() == 15 || str.trim().length() == 18) {
			return regular(str, REG_IDCARD);
		} else {
			return false;
		}

	}

	/**
	 * 判断字段是否为邮编 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isCode(String str) {
		return regular(str, REG_CODE);
	}

	/**
	 * 判断字符串是不是全部是英文字母
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEnglish(String str) {
		return regular(str, REG_ENG);
	}

	/**
	 * 判断字符串是不是全部是英文字母+数字
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEngNum(String str) {
		return regular(str, REG_ENG_NUM);
	}

	/**
	 * 判断字符串是不是全部是英文字母+数字+下划线
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEngNum_(String str) {
		return regular(str, REG_ENG_NUM_);
	}

	/**
	 * 过滤特殊字符串 返回过滤后的字符串
	 * 
	 * @param str
	 * @return boolean
	 */
	public static String filterStr(String str) {
		Pattern p = Pattern.compile(REG_SPECIAL);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 判断字符串是不是数字组成
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumber(String str) {
		return regular(str, REG_NUMBER);
	}

	/**
	 * 判断日期时间字符串是不是YYYY-MM-dd HH:MM:SS
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isLegalFormatDateTime(String str) {
		Pattern pattern = Pattern.compile(REG_LEGAL_FORMATE_DATE_TIME);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
			matcher = pattern.matcher(str);
			if (matcher.matches()) {
				int y = Integer.valueOf(matcher.group(1));
				int m = Integer.valueOf(matcher.group(2));
				int d = Integer.valueOf(matcher.group(3));
				if (d > 28) {
					Calendar c = Calendar.getInstance();
					c.set(y, m-1, 1);
					int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
					return (lastDay >= d);
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 判断日期时间字符串是不是YYYY-MM-dd
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isLegalFormatDate(String str) {
		Pattern pattern = Pattern.compile(REG_LEGAL_FORMATE_DATE);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
			matcher = pattern.matcher(str);
			if (matcher.matches()) {
				int y = Integer.valueOf(matcher.group(1));
				int m = Integer.valueOf(matcher.group(2));
				int d = Integer.valueOf(matcher.group(3));
				if (d > 28) {
					Calendar c = Calendar.getInstance();
					c.set(y, m-1, 1);
					int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
					return (lastDay >= d);
				}
			}
			return true;
		}
		return false;
	}
	
	
	/**
	 * 匹配是否符合正则表达式pattern 匹配返回true
	 * 
	 * @param str
	 *            匹配的字符串
	 * @param pattern
	 *            匹配模式
	 * @return boolean
	 */
	private static boolean regular(String str, String pattern) {
		if (null == str || str.trim().length() <= 0)
			return false;
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}

}
