package com.shenpu.proxy.pay.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author zhugl
 */
public class DateUtils {

	public static SimpleDateFormat yearSdf = new SimpleDateFormat("yyyy");

	public static SimpleDateFormat mmSdf = new SimpleDateFormat("MM");

	public static SimpleDateFormat ddSdf = new SimpleDateFormat("dd");

	public static SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat timestampSdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/**
	 * 锟斤拷锟斤拷转锟斤拷锟斤拷式锟斤拷24小时锟斤拷
	 */
	public final static String date24Format = "yyyy-MM-dd HH:mm:ss"; 

	// private static SimpleDateFormat dateSdf2 = new
	// SimpleDateFormat("yyyyMMdd");
	/**
	 * 锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷锟�
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear(Date date) {
		if (date == null) {
			return "";
		} else {
			return yearSdf.format(date);
		}

	}

	/** 锟斤拷取锟斤拷锟斤拷锟叫碉拷锟铰凤拷 */
	public static String getMonth(Date date) {
		if (date == null) {
			return "";
		} else {
			return mmSdf.format(date);
		}
	}

	/** 锟斤拷取锟斤拷锟节碉拷锟斤拷 */
	public static String getDay(Date date) {
		if (date == null) {
			return "";
		} else {
			return ddSdf.format(date);
		}

	}

	/**
	 * 锟斤拷锟斤拷锟街凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷(默锟较革拷式yyyy-MM-dd)
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDate(String date) {
		return parseDate(date, null);
	}

	/**
	 * 锟斤拷锟斤拷锟街凤拷锟斤拷锟斤拷指锟斤拷锟斤拷式锟斤拷锟斤拷锟斤拷锟斤拷
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date parseDate(String date, String format) {
		if (date == null || date.equals("")) {
			return null;
		}
		try {
			if (format == null || format.equals("")) {
				return dateSdf.parse(date);
			} else {
				SimpleDateFormat dateSdf = new SimpleDateFormat(format);
				return dateSdf.parse(date);
			}
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷转锟斤拷锟斤拷锟街凤拷锟斤拷(默锟较革拷式yyyy-MM-dd)
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, null);
	}

	
	/**
	 * 锟斤拷锟斤拷锟斤拷转锟斤拷锟斤拷锟街凤拷锟斤拷(默锟较革拷式yyyy-MM-dd)
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(String date) {
		
		return formatDate(parseDate(date), null);
	}

	/**
	 * 锟斤拷锟斤拷默锟较革拷式锟斤拷式锟斤拷时锟斤拷
	 * 
	 * @param date
	 * @return
	 */
	public static String formatTime(Date date) {
		return formatTime(date, null);
	}

	/**
	 * 锟斤拷锟斤拷指锟斤拷锟斤拷format锟斤拷式锟斤拷时锟斤拷
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatTime(Date date, String format) {
		if (date == null) {
			return "";
		} else {
			if (format == null || format.equals("")) {
				return timestampSdf.format(date);
			} else {
				SimpleDateFormat dateSdf = new SimpleDateFormat(format);
				return dateSdf.format(date);
			}
		}
	}

	/**
	 * 锟斤拷锟斤拷指锟斤拷锟斤拷式锟斤拷锟斤拷锟斤拷转锟斤拷锟斤拷锟街凤拷锟斤拷
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		if (date == null) {
			return "";
		} else {
			if (format == null || format.equals("")) {
				return dateSdf.format(date);
			} else {
				SimpleDateFormat dateSdf = new SimpleDateFormat(format);
				return dateSdf.format(date);
			}
		}
	}

	/**
	 * 转锟斤拷java.util.Date为java.sql.Date
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date getSqlDate(Date date) {
		if (date == null) {
			return null;
		}
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 转锟斤拷java.sql.Date为java.util.Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date getUtilDate(java.sql.Date date) {
		if (date == null) {
			return null;
		}
		return new Date(date.getTime());
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟较硷拷锟斤拷指锟斤拷锟斤拷锟斤拷
	 * 
	 * @param oldDate
	 * @param addDays
	 * @return
	 */
	public static Date addDay(Date oldDate, int addDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.add(Calendar.DATE, addDays);
		return calendar.getTime();
	}

	/**
	 * wll 锟斤拷锟街凤拷锟斤拷锟斤拷式锟斤拷锟斤拷锟斤拷锟较硷拷锟斤拷指锟斤拷锟斤拷锟斤拷
	 * 
	 * @param oldDate
	 * @param addDays
	 * @return
	 */
	public static String addDay(String oldDate, int addDays) {
		Date date = parseDate(oldDate);
		date = addDay(date, addDays);
		return formatDate(date);

	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷(锟斤拷锟斤拷)
	 * 
	 * @param birthday
	 * @param endDate
	 * @return
	 */
	public static int getAge(Date birthday, Date endDate) {
		return calInterval(birthday, endDate, "Y");
	}
	
	/**
	 * 锟斤拷锟捷斤拷锟斤拷锟斤拷母锟斤拷锟斤拷锟斤拷诨锟饺★拷锟斤拷锟�
	 * @return
	 */
	public static int getAge(String birthday,String endDate) {
		
		if(null != birthday && !"".equals(birthday)){
			
			Date birDate = DateUtils.parseDate(birthday);
			return DateUtils.getAge(birDate,DateUtils.parseDate(endDate) );
		}
		return 0;
	}
	/**
     * 锟斤拷锟捷斤拷锟斤拷锟斤拷母锟斤拷锟斤拷锟斤拷诨锟饺★拷锟斤拷锟�
     * @return
     */
    public static int getAge(String birthday,Date endDate) {
        
        if(null != birthday && !"".equals(birthday)){
            
            Date birDate = DateUtils.parseDate(birthday);
            return DateUtils.getAge(birDate,endDate);
        }
        return 0;
    }

	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷(锟斤拷位锟斤拷)
	 * 
	 * @param birthday
	 * @param endDate
	 * @return
	 */
	public static int getAgeByDay(Date birthday, Date endDate) {
		return calInterval(birthday, endDate, "D");
	}

	/**
	 * 锟角凤拷锟斤拷锟斤拷锟斤拷
	 * 
	 * @param nYear
	 * @return
	 */
	public static boolean isLeapYear(int nYear) {
		return (nYear % 400 == 0) || ((nYear % 100 != 0) && (nYear % 4 == 0));
	}

	/**
	 * 通锟斤拷锟斤拷始锟斤拷锟节猴拷锟斤拷止锟斤拷锟节硷拷锟斤拷锟斤拷时锟斤拷锟斤拷锟斤拷位为锟斤拷锟斤拷锟斤拷准锟斤拷时锟斤拷锟斤拷 锟斤拷始锟斤拷锟斤拷
	 * 
	 * @param startDate
	 *            Date 锟斤拷止锟斤拷锟斤拷
	 * @param endDate
	 *            Date 时锟斤拷锟斤拷锟斤拷位锟斤拷锟斤拷锟斤拷值("Y"--锟斤拷  "M"--锟斤拷   "D"--锟斤拷  "H"--时  "m"--锟斤拷  "S"--锟斤拷)
	 * @param unit
	 *            String 时锟斤拷锟斤拷,锟斤拷锟轿憋拷锟斤拷int
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
					if (eMonths == 1) { // 锟斤拷锟酵拷锟�2锟铰ｏ拷校锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
						if (isLeapYear(sYears) && isLeapYear(eYears)) { // 锟斤拷锟斤拷锟绞硷拷锟斤拷锟斤拷锟斤拷辏拷锟街癸拷瓴伙拷锟斤拷锟斤拷锟�
							if (eDays == 28) { // 锟斤拷锟斤拷锟街癸拷瓴伙拷锟斤拷锟斤拷辏拷锟�2锟铰碉拷锟斤拷锟揭伙拷锟�28锟秸ｏ拷锟斤拷么锟斤拷一
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
	 * 锟斤拷锟斤拷锟斤拷锟斤拷锟饺★拷锟斤拷锟�
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

			if (c.get(Calendar.MONTH) == Calendar.FEBRUARY
					&& c.get(Calendar.DAY_OF_MONTH) == 29) {

				int day = c.getMaximum(Calendar.DAY_OF_MONTH);
				c.set(Calendar.DAY_OF_MONTH, day);
			}

			Date currentDate = c.getTime();
			return DateUtils.formatDate(currentDate, null);
		}

		return null;
	}

	/**
	 * 锟斤拷取锟斤拷前时锟斤拷(java.util.Date)
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 锟斤拷取锟斤拷前时锟斤拷(java.sql.Date)
	 * 
	 * @return
	 */
	public static java.sql.Date getCurrentSqlDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷时锟斤拷锟诫构锟斤拷锟斤拷锟斤拷
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @param hour
	 * @param minuter
	 * @param second
	 * @return
	 */
	public static Date toDate(int year, int month, int date, int hour,
			int minuter, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date, hour, minuter, second);
		return calendar.getTime();
	}


	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷锟秸癸拷锟斤拷锟斤拷锟斤拷
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static Date toDate(int year, int month, int date) {
		return toDate(year, month, date, 0, 0, 0);
	}

	/**
	 * 锟斤拷锟斤拷小锟斤拷10锟斤拷锟斤拷锟街诧拷0
	 * 
	 * @param num
	 * @return
	 */
	public static String fillWithZero(int num) {
		if (num >= 1 && num <= 9) {
			return "0" + num;
		}

		return num + "";
	}

	/**
	 * 锟斤拷前锟斤拷锟斤拷锟斤拷锟斤拷锟杰碉拷一锟斤拷锟斤拷锟斤拷
	 * 
	 * @return
	 */
	public static String getFirstDayOfWeek() {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		Date date = new Date(c.getTimeInMillis());
		// LogUtils.logDebug(DateUtils.class, "锟斤拷锟杰碉拷一锟斤拷锟斤拷>>>"+formatDate(d1));
		return formatDate(date);
	}

	/**
	 * 锟斤拷前锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟�
	 * 
	 * @return
	 */
	public static String getLastDayOfWeek() {

		Calendar c = Calendar.getInstance();
		int lastDayofweek = c.getFirstDayOfWeek() + 6;
		c.set(Calendar.DAY_OF_WEEK, lastDayofweek);
		Date date = new Date(c.getTimeInMillis());
		return formatDate(date);
	}

	/**
	 * 锟斤拷取锟斤拷锟斤拷锟铰碉拷锟斤拷锟揭伙拷锟�
	 * 
	 * @param date
	 * @return
	 */
	public static int getMaxDayOfMonth(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Description: 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * 
	 * @param oldDate
	 *            java.util.Date 锟斤拷锟斤拷
	 * @param addYears
	 *            int 锟斤拷锟接碉拷锟斤拷锟斤拷
	 * @return java.util.Date 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
	 */
	public static java.util.Date addYear(java.util.Date oldDate, int addYears) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.add(Calendar.YEAR, addYears);
		return calendar.getTime();
	}

	/**
	 * Description: 锟斤拷时锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * 
	 * @param oldDate
	 *            Timestamp 时锟斤拷
	 * @param addYears
	 *            int 锟斤拷锟接碉拷锟斤拷锟斤拷
	 * @return Timestamp 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞憋拷锟�
	 */
	public static Timestamp addYear(Timestamp oldDate, int addYears) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date(oldDate.getTime()));
		calendar.add(Calendar.YEAR, addYears);
		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * Description: 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * 
	 * @param oldDate
	 *            java.util.Date 锟斤拷锟斤拷
	 * @param addMonths
	 *            int 锟斤拷锟接碉拷锟斤拷锟斤拷
	 * @return java.util.Date 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
	 */
	public static java.util.Date addMonth(java.util.Date oldDate, int addMonths) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.add(Calendar.MONTH, addMonths);
		return calendar.getTime();
	}

	/**
	 * Description: 锟斤拷时锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * 
	 * @param oldDate
	 *            Timestamp 时锟斤拷
	 * @param addMonths
	 *            int 锟斤拷锟接碉拷锟斤拷锟斤拷
	 * @return Timestamp 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞憋拷锟�
	 */
	public static Timestamp addMonth(Timestamp oldDate, int addMonths) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date(oldDate.getTime()));
		calendar.add(Calendar.MONTH, addMonths);
		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * Description: 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * 
	 * @param oldDate
	 *            java.util.Date 锟斤拷锟斤拷
	 * @param addWeeks
	 *            int 锟斤拷锟接碉拷锟斤拷锟斤拷
	 * @return java.util.Date 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
	 */
	public static java.util.Date addWeek(java.util.Date oldDate, int addWeeks) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.add(Calendar.WEEK_OF_YEAR, addWeeks);
		return calendar.getTime();
	}

	/**
	 * Description: 锟斤拷时锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * 
	 * @param oldDate
	 *            Timestamp 时锟斤拷
	 * @param addWeeks
	 *            int 锟斤拷锟接碉拷锟斤拷锟斤拷
	 * @return Timestamp 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞憋拷锟�
	 */
	public static Timestamp addWeek(Timestamp oldDate, int addWeeks) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date(oldDate.getTime()));
		calendar.add(Calendar.WEEK_OF_YEAR, addWeeks);
		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * wll 锟饺斤拷锟斤拷锟斤拷锟斤拷锟节的达拷小锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷诘诙锟斤拷锟斤拷锟斤拷锟絫rue锟斤拷锟斤拷锟津返伙拷false
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(Date date1, Date date2) {

		Long time1 = date1.getTime();
		Long time2 = date2.getTime();

		if (time1 > time2) {

			return true;
		} else {

			return false;
		}

	}
	/**
     * Description: 锟斤拷锟斤拷锟斤拷转锟斤拷锟斤拷锟斤拷"**锟斤拷**锟斤拷**锟斤拷"锟斤拷式
     * 
     * 说锟斤拷锟斤拷锟斤拷锟斤拷潞锟斤拷斩锟斤拷锟斤拷锟斤拷锟�9锟斤拷锟斤拷锟斤拷为****锟斤拷*锟斤拷*锟秸ｏ拷锟斤拷锟斤拷****锟斤拷0*锟斤拷0*锟斤拷
     * 
     * @param date
     *            java.util.Date 锟斤拷转锟斤拷锟斤拷锟斤拷锟斤拷
     * @return String 转锟斤拷锟斤拷锟斤拷锟斤拷锟�
     */
    public static String getChineseDate(java.util.Date date) {
        String result = "";

        if (date == null) {
            result = " 锟斤拷 锟斤拷 锟斤拷";
        } else {
            result = "" + getYear(date) + "锟斤拷" + getMonth(date) + "锟斤拷"
                    + getDay(date) + "锟斤拷";
        }

        return result;
    }

}
