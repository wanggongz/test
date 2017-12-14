package com.shenpu.proxy.pay.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;

import com.google.common.base.Throwables;

/**
 * @author Levi.Wang 日期工具类（主要使用apache common-lang3的DateUtil，如该类无此功能，则再此类添加）
 */
public class DateTimeUtils extends DateUtils {

	/**
	 * 获取SimpleDateFormat
	 * 
	 * @param parttern
	 *            日期格式
	 * @return SimpleDateFormat对象
	 * @throws RuntimeException
	 *             异常：非法日期格式
	 */
	private static SimpleDateFormat getDateFormat(String parttern) {
		return new SimpleDateFormat(parttern);
	}

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @param parttern
	 *            日期格式
	 * @return 日期字符串
	 */
	public static String dateToString(Date date, String parttern) {
		String dateString = null;
		if (date != null) {
			try {
				dateString = getDateFormat(parttern).format(date);
			} catch (Exception e) {
				throw Throwables.propagate(e);
			}
		}
		return dateString;
	}

	/**
	 * @Title: getCurrentTime
	 * @Description: TODO(获取当前时间)
	 * @param format
	 * @return
	 */
	public static String getCurrentTime(String format) {
		return dateToString(new Date(), format);
	}

	/**
	 * 
	 * @Title: strToDatestr
	 * @Description: 将字符串转化为想要的格式
	 * @param datetime
	 * @return String
	 * @throws
	 */
	public static String strDateFormatConvert(String sourceDate, String sourceFormat, String destFormat) {

		Date date;
		try {
			date = DateUtils.parseDate(sourceDate, sourceFormat);
		} catch (ParseException e) {
			throw Throwables.propagate(e);
		}
		return dateToString(date, destFormat);
	}

	/**
	 * @Title: getTimeSubMS
	 * @Description: 计算两个时间之间的差值(单位ms)
	 * @param subtime1
	 * @param subtime2
	 * @return long
	 * @throws
	 */
	public static long getTimeSubMS(Date subtime1, Date subtime2) {

		long time1 = subtime1.getTime();
		long time2 = subtime2.getTime();

		return (time1 - time2);
	}

	/**
	 * 
	 * @Title: toDayDateChinese
	 * @Description: 求当日的第二日的日期且返回的类型精确到当天并且用汉字表示
	 * @param date
	 * @return String
	 * @throws
	 */
	public static String toDayDateChinese(Date date) {
		SimpleDateFormat sft = new SimpleDateFormat("yy年MM月dd日零时");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		String nextDate = null;
		nextDate = sft.format(calendar.getTime());
		return nextDate;
	}

	/**
	 * 
	 * @Title: plyStrTime
	 * @Description: 求精确到当天并且精确到时分秒(计算保险起期)
	 * @param date
	 * @return String
	 * @throws
	 */
	public static String plyStrTime(Date date) {
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		String nextDate = null;
		nextDate = sft.format(calendar.getTime());
		return nextDate;
	}

	/**
	 * 
	 * @Title: plyEndTime
	 * @Description: 求精确到当天并且精确到时分秒(计算保险止期)
	 * @param date
	 * @param period
	 * @param unit
	 * @return String
	 * @throws
	 */
	public static String plyEndTime(Date date, int period, int unit) {
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (unit == 1) {
			calendar.add(Calendar.YEAR, 1);
		}
		if (unit == 2) {
			calendar.add(Calendar.MONTH, 1);
		}
		if (unit == 3) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		String nextDate = null;
		nextDate = sft.format(calendar.getTime());
		return nextDate;
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
	 * 根据建议书的更新日期获取年龄
	 * 
	 * @return
	 */
	public static int getAge(String birthday, String endDate) {

		if (null != birthday && !"".equals(birthday)) {

			Date birDate;
			try {
				birDate = parseDate(birthday);
				return getAge(birDate, DateUtils.parseDate(endDate));
			} catch (ParseException e) {
				throw Throwables.propagate(e);
			}
		}
		return 0;
	}

	/**
	 * 根据建议书的更新日期获取年龄
	 * 
	 * @return
	 */
	public static int getAge(String birthday, Date endDate) {

		if (null != birthday && !"".equals(birthday)) {

			Date birDate;
			try {
				birDate = DateUtils.parseDate(birthday);
				return getAge(birDate, endDate);
			} catch (ParseException e) {
				throw Throwables.propagate(e);
			}
		}
		return 0;
	}

	/**
	 * 通过起始日期和终止日期计算以时间间隔单位为计量标准的时间间隔 起始日期
	 * 
	 * @param startDate
	 *            Date 终止日期
	 * @param endDate
	 *            Date 时间间隔单位，可用值("Y"--年 "M"--月 "D"--日 "H"--时 "m"--分 "S"--秒)
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
			long lInterval = (eCalendar.getTime().getTime() - sCalendar.getTime().getTime()) / 86400000;
			interval = (int) lInterval;
		}
		if (unit != null && unit.trim().equals("H")) {
			long lInterval = (endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60;
			interval = (int) lInterval;
		}
		if (unit != null && unit.trim().equals("m")) {
			long lInterval = (endDate.getTime() - startDate.getTime()) / 1000 / 60;
			interval = (int) lInterval;
		}
		if (unit != null && unit.trim().equals("S")) {

			long lInterval = (endDate.getTime() - startDate.getTime()) / 1000;
			interval = (int) lInterval;
		}
		return interval;
	}

	/**
	 * 根据年龄获取生日
	 * 
	 * @param age
	 * @return
	 */
	public static String getBirthdayByAge(String age) {

		if (null != age && !"".equals(age)) {

			int currentAge = Integer.valueOf(age);
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int birYear = year - currentAge;
			c.set(Calendar.YEAR, birYear);

			if (c.get(Calendar.MONTH) == Calendar.FEBRUARY && c.get(Calendar.DAY_OF_MONTH) == 29) {

				int day = c.getMaximum(Calendar.DAY_OF_MONTH);
				c.set(Calendar.DAY_OF_MONTH, day);
			}

			Date currentDate = c.getTime();
			return DateTimeUtils.dateToString(currentDate, "yyyy-MM-dd");
		}

		return null;
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

	// 此参数里datestr表示时间起期，time表示保险期间时长，type表示类型，1为年，2为月，3为日
	public static Date getBeforeAfterDate(String datestr, int time, int type) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date olddate = null;
		try {
			df.setLenient(false);
			olddate = new java.sql.Date(df.parse(datestr).getTime());
		} catch (Exception e) {
			Throwables.propagate(e);
		}
		Calendar cal = new GregorianCalendar();
		cal.setTime(olddate);

		int Year = cal.get(Calendar.YEAR);
		int Month = cal.get(Calendar.MONTH);
		int Day = cal.get(Calendar.DAY_OF_MONTH);

		int newYear = 0;
		int newMonth = 0;
		int newDay = 0;
		if (type == 1) {
			newYear = Year + time;
			cal.set(Calendar.YEAR, newYear);
			cal.set(Calendar.MONTH, Month);
			cal.set(Calendar.DAY_OF_MONTH, Day - 1);
		} else if (type == 2) {
			newMonth = Month + time;
			cal.set(Calendar.YEAR, Year);
			cal.set(Calendar.MONTH, newMonth);
			cal.set(Calendar.DAY_OF_MONTH, Day - 1);
		} else {
			newDay = Day + time;
			cal.set(Calendar.YEAR, Year);
			cal.set(Calendar.MONTH, Month);
			cal.set(Calendar.DAY_OF_MONTH, newDay - 1);
		}

		return new java.sql.Date(cal.getTimeInMillis());
	}
}
