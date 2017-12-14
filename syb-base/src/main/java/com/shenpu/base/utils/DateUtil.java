package com.shenpu.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * 日期工具类
 * @author jetty
 *
 */
public class DateUtil {
	
	public static Date parse(String string) {
		if (null == string || "".equals(string)) {
			return null;
		}
		try {
			String formateStr = null;
			if (string.indexOf(':') > 0) {
				formateStr = "yyyy-MM-dd HH:mm:ss";
			} else {
				formateStr = "yyyy-MM-dd";
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formateStr, Locale.UK);
			return simpleDateFormat.parse(string);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String formate(Date date, String formateStr) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formateStr);
		return simpleDateFormat.format(date);
	}
	
	/**
     * 根据建议书的更新日期获取年龄
     * @return
     */
    public static int getAge(String birthday,Date endDate) {
        if(null != birthday && !"".equals(birthday)){
            Date birDate = DateUtil.parse(birthday);
            return DateUtil.getAge(birDate,endDate);
        }
        return 0;
    }

	/**
	 * 计算生日(单位天)
	 * 
	 * @param birthday
	 * @param endDate
	 * @return
	 */
	public static int getAgeByDay(Date birthday, Date endDate) {
		return calInterval(birthday, endDate, "D");
	}
	
	/**
	 * 计算生日(周岁)
	 * 
	 * @param birthday
	 * @param endDate
	 * @return
	 */
	public static int getAge(Date birthday, Date endDate) {
		return calInterval(birthday, endDate, "Y");
	}
	
	
	/**
	 * 通过起始日期和终止日期计算以时间间隔单位为计量标准的时间间隔 起始日期
	 * 
	 * @param startDate
	 *            Date 终止日期
	 * @param endDate
	 *            Date 时间间隔单位，可用值("Y"--年  "M"--月   "D"--日  "H"--时  "m"--分  "S"--秒)
	 * @param unit
	 *            String 时间间隔,整形变量int
	 * @return int
	 */
	public static int calInterval(Date startDate, Date endDate, String unit) {
		if (startDate == null || endDate == null) {
			return 0;
		}
		int interval = 0;
		GregorianCalendar sCalendar = new GregorianCalendar();
		sCalendar.setTime(startDate);
		int sYears = sCalendar.get(Calendar.YEAR);
		int sMonths = sCalendar.get(Calendar.MONTH);
		int sDays = sCalendar.get(Calendar.DAY_OF_MONTH);
		GregorianCalendar eCalendar = new GregorianCalendar();
		eCalendar.setTime(endDate);
		int eYears = eCalendar.get(Calendar.YEAR);
		int eMonths = eCalendar.get(Calendar.MONTH);
		int eDays = eCalendar.get(Calendar.DAY_OF_MONTH);
		if (unit != null && unit.trim().equals("Y")) {
			interval = eYears - sYears;
			if (eMonths < sMonths) {
				interval--;
			} else {
				if (eMonths == sMonths && eDays < sDays) {
					interval--;
					if (eMonths == 1) { // 如果同是2月，校验润年问题
						if (isLeapYear(sYears) && isLeapYear(eYears)) { // 如果起始年是润年，终止年不是润年
							if (eDays == 28) { // 如果终止年不是润年，且2月的最后一天28日，那么补一
								interval++;
							}
						}
					}
				}
			}
		}
		if (unit != null && unit.trim().equals("M")) {
			interval = eYears - sYears;
			interval *= 12;
			interval += eMonths - sMonths;

			if (eDays < sDays) {
				interval--;
				int maxDate = eCalendar.getActualMaximum(Calendar.DATE);
				if (eDays == maxDate) {
					interval++;
				}
			}
		}
		if (unit != null && unit.trim().equals("D")) {
			sCalendar.set(sYears, sMonths, sDays);
			eCalendar.set(eYears, eMonths, eDays);
			long lInterval = (eCalendar.getTime().getTime() - sCalendar
					.getTime().getTime()) / 86400000;
			interval = (int) lInterval;
		}
		if (unit != null && unit.trim().equals("H")) {
		    long lInterval = (endDate.getTime() - startDate.getTime())/1000/60/60;
		    interval = (int) lInterval;
		}
		if (unit != null && unit.trim().equals("m")) {
		    long lInterval = (endDate.getTime() - startDate.getTime())/1000/60;
		    interval = (int) lInterval;
		}
		if (unit != null && unit.trim().equals("S")) {
		    
		    long lInterval = (endDate.getTime() - startDate.getTime())/1000;
		    interval = (int) lInterval;
		}
		return interval;
	}
	
	/**
	 * 是否是闰年
	 * 
	 * @param nYear
	 * @return
	 */
	public static boolean isLeapYear(int nYear) {
		return (nYear % 400 == 0) || ((nYear % 100 != 0) && (nYear % 4 == 0));
	}
}
