package com.test.web.Util;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期转换
 * 
 */
public class DateUtil {

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String FORMAT_STYLE_1 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd
	 */
	public static String FORMAT_STYLE_2 = "yyyy-MM-dd";

	/**
	 * yyyy年MM月dd日
	 */
	public static String FORMAT_STYLE_3 = "yyyy年MM月dd日";

	/**
	 * yyyyMM
	 */
	public static String FORMAT_STYLE_4 = "yyyyMM";

	/**
	 * yyyyMMdd
	 */
	public static String FORMAT_STYLE_5 = "yyyyMMdd";

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static String FORMAT_STYLE_6 = "yyyy-MM-dd HH:mm";

	/**
	 * yyyyMMddhhmmss
	 */
	public static String FORMAT_STYLE_7 = "yyyyMMddHHmmss";

	/**
	 * yyMMdd
	 */
	public static String FORMAT_STYLE_8 = "yyMMdd";
	
	/**
	 * yyMM
	 */
	public static String FORMAT_STYLE_9 = "yyMM";
	
	/**
	 * yyyy-MM
	 */
	public static String FORMAT_STYLE_10 = "yyyy-MM";

	/**
	 * yyyy-MM
	 */
	public static String FORMAT_STYLE_11 = "HHmmss";

	/**
	 * yyyyMMddhhmm
	 */
	public static String FORMAT_STYLE_12 = "yyyyMMddHHmm";

	public static String getNowYYYYMMDDHHMMSS() {
		String result = "";
		SimpleDateFormat l_sdf = new SimpleDateFormat(DateUtil.FORMAT_STYLE_7);
		result = l_sdf.format(new Date());
		return result;
	}
	/**
	 * 获得本月1号的日期
	 * */
	public static Date getThisMonthBeginDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 将Date转换成String
	 * 
	 * @param date
	 * @return
	 */
	public static String getString(Date date) {
		// 日期格式
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STYLE_1);
		return sdf.format(date);
	}

	/**
	 * 将当前时间转换成String
	 * 
	 * @return
	 */
	public static String getNowDateString(String format) {
		// 日期格式
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * 将Date按一定的格式转成成String
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getStringDate(Date date, String format) {
		// 日期格式
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 将Date转换成String
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getString(Date date, String format) {
		// 日期格式
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}


	/**
	 * 字符转成日期
	 * 
	 * @param dateString
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateString, String format)
			 {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 获取系统当前时间戳String
	 * 
	 * @return
	 */
	public static String getCurrentTimeMillis2String() {
		return new Long(System.currentTimeMillis()).toString();
	}

	/**
	 * 获取系统当前时间----Date
	 * 
	 * @return Date
	 */
	public static Date getNowDate() {
		return new Date();
	}
 

	/**
	 * 获取系统当前时间----Timestamp
	 * 
	 * @return
	 */
	public static Timestamp getNowTimestamp() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		Timestamp buydate = Timestamp.valueOf(nowTime);
		return buydate;
	}

	/**
	 * @param mins
	 * @return
	 * 
	 *         根据指定分钟数获取距离当前时间的Date时间 -mins 指前几分钟， +mins 后几分钟
	 */
	@SuppressWarnings("deprecation")
	public static Date getSystemTime4AppointMins(int mins) {
		Date nowDate = getNowDate();
		nowDate.setMinutes(nowDate.getMinutes() + mins);
		return nowDate;
	}

	/**
	 * @param hours
	 * @return
	 * 
	 *         根据指定小时数获取距离当前时间的Date时间 -hours 指前几小时， +hours 后几小时
	 */
	@SuppressWarnings("deprecation")
	public static Date getSystemTime4AppointHours(int hours) {
		Date nowDate = getNowDate();
		nowDate.setHours(nowDate.getHours() + hours);
		return nowDate;
	}

	/**
	 * @param hours
	 * @return
	 * 
	 *         根据指定小时数获取距离当前时间的Date时间 -days 指前几天， +days 后几天
	 */
	@SuppressWarnings("deprecation")
	public static Date getSystemTime4AppointDays(int days) {
		Date nowDate = getNowDate();
		nowDate.setDate(nowDate.getDate() + days);
		return nowDate;
	}

	/**
	 * 获取2个时间之间的分钟差
	 * 
	 * @param startday
	 * @param endday
	 * @return
	 */
	public static int getIntervalMins(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60));
	}

	/**
	 * 获取2个时间之间的天数差
	 * 
	 * @param startday
	 * @param endday
	 * @return
	 */
	public static int getIntervalDays(Timestamp startday, Timestamp endday) {
		if (startday.after(endday)) {
			Timestamp cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}
	
	/**
	 * 获取2个时间之间的小时差
	 * 
	 * @param startday
	 * @param endday
	 * @return
	 */
	public static int getIntervalHours(Timestamp startday, Timestamp endday) {
		if (startday.after(endday)) {
			Timestamp cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 ));
	}
	public static float getIntervalHours_float(Timestamp startday, Timestamp endday) {
		if (startday.after(endday)) {
			Timestamp cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (float) (ei / (1000 * 60 * 60 ));
	}

	/**
	 * 返回某天的0点
	 * */
	public static Timestamp getZeroTime(Timestamp time) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(time);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 * 返回某天的23点
	 * */
	public static Timestamp getBigTime(Timestamp time) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(time);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 * 当前时间的字符串
	 * 
	 * @return
	 */
	public static String getNowTimeString() {
		return getString(getNowDate());
	}
	
	public static Timestamp getTimestampByStr(String time){
		Timestamp ts = new Timestamp(System.currentTimeMillis());   
        try {   
            ts = Timestamp.valueOf(time);   
            return ts;
        } catch (Exception e) {   
            e.printStackTrace();   
        }  
        return null;
	}
	
	/**
	 * 获取相差指定日期
	 * 
	 * @param time
	 *            ：指定日期
	 * @param days
	 *            ： days可正可负，负为指定日期前，正为指定日期后
	 * @return
	 */
	public static Timestamp getTimeAdd(Timestamp time, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(time);
		cal.add(Calendar.DAY_OF_YEAR, days);
		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 * 获取距离指定时间的指定日期
	 * 
	 * @param date
	 *            指定时间
	 * @param days
	 *            days可正可负，负为指定日期前，正为指定日期后
	 * @return
	 */
	public static Date getDayChange(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}

	/**
	 * 获取相差指定日期
	 * 
	 * @param time
	 *            ：指定日期
	 * @param days
	 *            ： days可正可负，负为指定日期前，正为指定日期后
	 * @return
	 */
	public static Timestamp getMonthAdd(Timestamp time, int months) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(time);
		cal.add(Calendar.MONTH, months);
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 * 判断给定日期是否已经超时 判断年，月，日
	 * 
	 * @param time
	 *            ：指定日期
	 * @return 超时=true，未超时=false
	 */
	public static Boolean judgeTime(Timestamp time) {
		Timestamp nowTime = new Timestamp((new Date()).getTime());
		nowTime = getBigTime(nowTime);
		time = getBigTime(time);
		/*
		 * GregorianCalendar calStart = new GregorianCalendar();
		 * calStart.setTime(time); GregorianCalendar calNow = new
		 * GregorianCalendar(); calNow.setTime(nowTime);
		 */
        return nowTime.after(time);
	}

	/**
	 * 判断给定日期是否已经超时 判断年，月，日
	 * 
	 * @param date
	 * @return
	 */
	public static Boolean judgeTimeByDate(Date date) {
		Date now = getNowDate();
        return now.after(date);
	}

	/**
	 * 获得上月1号的日期
	 * */
	public static Date getLastMonthBeginDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获得上个月最后一天的日期
	 * */
	public static Date getLastMonthEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 获得这个月最后一天的日期
	 * */
	public static Date getThisMonthEndDate() {
		Calendar cal = Calendar.getInstance();
		// 当月最大天数
		int a = cal.getActualMaximum(Calendar.DATE);
		cal.set(Calendar.DAY_OF_MONTH, a);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 获得时间中年，月，日单个值
	 * */
	public static int getMonthFromDate(Date date, int type) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		int monthNow = cal.get(type);

		return monthNow;
	}

	/**
	 * 取得年龄
	 * 
	 * @param year出生年
	 * @return
	 */
	public static int getAge(int year, int month, int day) {
		try {
			// 年龄
			int age = getNowYear();
			age = age - year;
			// 日本年龄满一年才是一岁
			age = age - 1;
			if (0 > age) {
				age = 0;
			}
			if (getNowMonth() > month) {
				// 当前月大于出生月，年龄加1
				age++;
			} else if (getNowMonth() == month) {
				// 当前日等于出生月,并且日大于生日
				if (getNowDay() > day) {
					age++;
				}
			}
			return age;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 取得当前年
	 * 
	 * @return
	 */
	public static int getNowYear() {
		try {
			// 当前月
			return Calendar.getInstance().get(Calendar.YEAR);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 取得当前月
	 * 
	 * @return
	 */
	public static int getNowMonth() {
		try {
			// 当前月
			return Calendar.getInstance().get(Calendar.MONTH) + 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 取得当前日
	 * 
	 * @return
	 */
	public static int getNowDay() {
		try {
			// 当前日
			return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 现在距离指定时间还有多少小时
	 * 
	 * @param appointedTime
	 * @return
	 */
	public static int getHourByAppointedTime(Date appointedTime) {
		long now = getNowDate().getTime();
		long at = appointedTime.getTime();
		long ei = at - now;
		return (int) (ei / (1000 * 60 * 60));
	}

	/**
	 * 与现在的时间相差几分钟
	 * 
	 * @param appointedTime
	 * @return
	 */
	public static int getminsByAppointedTime(Date appointedTime) {
		long now = getNowDate().getTime();
		long at = appointedTime.getTime();
		long ei = now - at;
		return (int) (ei / (1000 * 60));
	}
	
	
	public static String formatToYYYYMMDD(Date date) {
		if (date == null)
			return "";
		String result = "";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			result = format.format(date);
		} catch (Exception e) {
			return "";
		}
		return result;
	}
	
	
	public static Date parseDate(String dateStr, String pattern) {
		Date date = null;
		if (StringUtils.isNotBlank(dateStr)) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e) {
			}
		}
		return date;
	}
	
	public static Date addMonth(String dateStr, int month) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		Calendar cal = null;
		try {
			date = df.parse(dateStr);
			cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, month);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cal.getTime();
	}



	public static String  StrToStr(String strDate,String formatBefore,String formateaft){

		SimpleDateFormat sdf = new SimpleDateFormat(formatBefore);
		try {
			Date dt =  sdf.parse(strDate);

			SimpleDateFormat sdf2 = new SimpleDateFormat(formateaft);

			return sdf2.format(dt);

		} catch (ParseException e) {
			e.printStackTrace();

		}


		return null;
	}

	/**
	 * date转换为yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String convertToYYYYMMDDHHMMSS(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowTime = df.format(date);
		return nowTime;
	}

	/**
	 * 本周开始日
	 * @return
	 */
	public static String getStartweek(){

		Calendar calender = Calendar.getInstance();

		int week = calender.get(Calendar.DAY_OF_WEEK);

		int offset = 0;
		if(week == 1){
			offset = -6;
		} else if(week == 2){
			offset = 0;
		} else {
			offset = 2-week;
		}

		calender.add(Calendar.DAY_OF_MONTH, offset);

		return Util.toStrDateYYYYMMDD(calender.getTime());
	}

	/**
	 * 本周结束日
	 * @return
	 */
	public static String getEndweek(){

		Calendar calender = Calendar.getInstance();

		int week = calender.get(Calendar.DAY_OF_WEEK);

		int offset = 0;
		if(week == 1){
			offset = 0;
		} else if(week == 2){
			offset = 6;
		} else {
			offset = 8-week;
		}

		calender.add(Calendar.DAY_OF_MONTH, offset);

		return Util.toStrDateYYYYMMDD(calender.getTime());
	}


	/**
	 * 取得当月天数
	 * */
	public static int getCurrentMonthLastDay()
	{
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);//把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 得到指定月的天数
	 * */
	public static int getMonthLastDay(int year, int month)
	{
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);//把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	public static List<String> getMonthDayList(int year, int month){
		List<String> daylist=new ArrayList<>();
		int maxDate=DateUtil.getMonthLastDay(year,month);
		String date="";
		for(int i=1;i<=maxDate;i++){
			if(month<=9){
				date=year+"0"+month;
			}else{
				date=year+""+month;
			}
			if(i<10){
				date=date+"0"+i;
			}else {
				date=date+""+i;
			}
			daylist.add(date);
		}
		return daylist;
	}

}
