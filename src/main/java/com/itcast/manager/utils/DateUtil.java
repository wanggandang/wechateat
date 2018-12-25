/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename DateUtil.java
 * @package com.ratan.util
 * @author dandyzheng
 * @date 2012-6-7
 */
package com.itcast.manager.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author dandyzheng
 * 
 */
public class DateUtil {
	private static DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	private static final long MSECONDS_OF_ONE_DAY = 60 * 60 * 1000 * 24;

	private static final Calendar cal = Calendar.getInstance();
	
	private static final long EIGHT_HOUR_MSECONDS = 8*60*60*1000;


	/**
	 * 获得当前日期
	 * @return 当前日期
	 * @author dandyzheng
	 * @date 2012-6-7
	 * @see
	 */
	public static Date getCurrentDate() {
		return new Date();
	}
	
	/**
	 * 获取系统时间
	 * @return 系统时间
	 * @author dandyzheng
	 * @date 2012-6-7
	 * @see
	 */
	public final static Date getCurrentTime() {
		return new Date(System.currentTimeMillis());
	}
	
	public final static String getCurrentDateStr(){
		return dateFormatter.format(System.currentTimeMillis());
	}
	
	public final static String getCurrentDateTimeStr(){
		return dateTimeFormatter.format(System.currentTimeMillis());
	}
	
	public final static String getCurrentTimeStr(String format){
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		return simpledateformat.format(System.currentTimeMillis());
	}

	public static String getDateString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(date==null){
			return null;
		}
		return df.format(date);
	}

	public static String getDateString(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static String getDateTimeString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(date==null){
			return null;
		}
		return df.format(date);
	}
	
	public static String getDateTimeString(Long date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(date==null){
			return null;
		}
		return df.format(date);
	}

	public static String getDateTimeString(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	
		public static Date parseDate(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(date);
	}

	public static Date parseDate(String date, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(date);
	}

	public static Date parseDateTime(String date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.parse(date);
	}

	public static Date parseDateTime(String date, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(date);
	}
	
	/**
	 * @param date
	 * @return
	 * @author Administrator
	 * @date 2012-6-18
	 * @see
	 */
	public static Date getFirstDayOfMonth(Date date) {
		String year = getYear(date);
		String month = getMonth(date);
		month = month.substring(4, month.length());
		return getFirstDayOfMonth(Integer.parseInt(year), 
				Integer.parseInt(month));
	}

	//
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, year);
		cl.set(Calendar.MONTH, month - 1);
		cl.set(Calendar.DAY_OF_MONTH, 1);
		return cl.getTime();
	}

	public static Date getLastDayOfMonth(int year, int month) {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, year);
		cl.set(Calendar.MONTH, month - 1);
		cl.set(Calendar.DAY_OF_MONTH,
				cl.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cl.getTime();
	}
	
	/*
	 * 获取月份的第一天
	 */
	public static int getFirstDateOfMonth(int year, int month) {

		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, year);
		cl.set(Calendar.MONDAY, month - 1);
		return cl.getActualMinimum(Calendar.DAY_OF_MONTH);
	}

	/*
	 * 获取月份的最后一天
	 */
	public static int getLastDateOfMont(int year, int month) {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.YEAR, year);
		cl.set(Calendar.MONDAY, month - 1);
		return cl.getActualMaximum(Calendar.DAY_OF_MONTH);
	}


	public static int getDays(Date fromDate, Date endDate) {

		long from = fromDate.getTime();
		long end = endDate.getTime();

		return (int) ((end - from) / (24 * 60 * 60 * 1000)) + 1;
	}

	public static long getTimes(Date fromDate, Date endDate) {

		long from = fromDate.getTime();
		long end = endDate.getTime();

		return (end - from)/1000;
	}
	
	/**
	 * 算两个日期相差天数
	 * @param fromDate
	 * @param endDate
	 * @return
	 * @author zhangtao
	 * @date 2014-8-29
	 * @see
	 */
	public static int getDays(long fromDate, long endDate) {
		long from = (fromDate+EIGHT_HOUR_MSECONDS)/(24*60*60*1000);
		long end = (endDate+EIGHT_HOUR_MSECONDS)/(24*60*60*1000);
		return (int) (end - from);
	}

	public static String getTakeTime(Date startDate, Date endDate) {
		int minute = 0;
		try {
			minute = (int) (endDate.getTime() - startDate.getTime())
					/ (1000 * 60);
			return String.valueOf(minute);
		} catch (Exception e) {
			return "";
		}

	}

	

	public static java.sql.Date convertUtilDateToSQLDate(Date date) {
		if (date == null)
			return null;
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		java.sql.Date jd = new java.sql.Date(cl.getTimeInMillis());
		return jd;
	}

	public static java.sql.Date convertObjToSQLDate(Object obj) {
		if (obj == null || "".equals(obj.toString().trim()))
			return null;

		Calendar cl = Calendar.getInstance();
		cl.setTime((Date) obj);
		java.sql.Date jd = new java.sql.Date(cl.getTimeInMillis());
		return jd;
	}

	public static java.sql.Timestamp convertUtilDateToSQLDateWithTime(
			Date date) {
		if (date != null) {
			return new java.sql.Timestamp(date.getTime());
		} else {
			return null;
		}
	}

	public static java.sql.Date convertStringToSQLDate(String dateString) {
		return (convertObjToSQLDate(dateString));
	}

	public static java.sql.Date convertToSQLDateWithoutTime(Date date) {
		String dateString = dateFormatter.format(date);
		return convertStringToSQLDate(dateString);
	}

	

	/**
	 * get day index of a week for the specific date
	 * 
	 * @param date
	 *            the specific date
	 * @return day index of a week,Mon. is 1,Tues. is 2,Wed. is 3,Thurs. is
	 *         4,Fri. is 5,Sat. is 6,Sun. is 7
	 * @throws ParseException
	 */
	public static int getDayOfWeek(Date date) {
		if (date == null)
			return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int result = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (result == 0)
			result = 7;
		return result;
	}

	/**
	 * get day index of a week for the specific date
	 * 
	 * @param date
	 *            the specific date
	 * @return day index of a week,Sun. is 1,Mon. is 2,Tues. is 3,Wed. is
	 *         4,Thurs. is 5,Fri. is 6,Sat. is 7
	 * @throws ParseException
	 */
	public static int getDayOfWeek2(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * add days to the specific date
	 * 
	 * @param SourceDate
	 *            the specific date
	 * @param days
	 *            day count to be added
	 * @return java.util.Date object after add days
	 * @throws ParseException
	 */
	public static Date addDate(Date sourceDate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * add days to the specific date
	 * 
	 * @param SourceDate
	 *            the specific date
	 * @param days
	 *            day count to be added
	 * @return java.util.Date object after add days
	 * @throws ParseException
	 */
	public static Date addDate(String stringDate, int days)  throws ParseException{
		Date sourceDate = parseDate(stringDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * @param stringDate
	 * @return
	 */
	public static String addOneDay(Date sourceDate) {
		Date newDate = addDate(sourceDate, 1);
		return getDateTimeString(newDate);

	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 * @throws ParseException
	 */
	public static long subDate(Date from, Date to) throws ParseException {
		long value = Math.abs(to.getTime() - from.getTime());
		return value / MSECONDS_OF_ONE_DAY;
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 * @throws ParseException
	 */
	public static long subDate(String from, String to) throws ParseException {
		return subDate(parseDateTime(from), parseDateTime(to));
	}


	public static int compareDate(Date firstDate, Date secondDate) {
		return firstDate.compareTo(secondDate);
	}

	

	

	/**
	 * 取年份
	 * 
	 * @param date
	 * @return
	 */
	public static final String getYear(Date date) {
		cal.setTime(date);
		return String.valueOf(cal.get(1));
	}

	/**
	 * 取月份
	 * 
	 * @param date
	 * @return
	 */
	public static final String getMonth(Date date) {
		String s = "";
		cal.setTime(date);
		if (cal.get(2) < 9)
			s = "0";
		return String.valueOf(cal.get(1)) + s + String.valueOf(cal.get(2) + 1);
	}

	/**
	 * 取日
	 * 
	 * @param date
	 * @return
	 */
	public static final String getDay(Date date) {
		String s = "";
		String s1 = "";
		cal.setTime(date);
		if (cal.get(2) < 9)
			s = "0";
		if (cal.get(5) < 10)
			s1 = "0";
		return String.valueOf(cal.get(1)) + s + String.valueOf(cal.get(2) + 1)
				+ s1 + String.valueOf(cal.get(5));
	}

	/**
	 * 取星期X
	 * 
	 * @param date
	 * @return
	 */
	public static final String getWeek(Date date) {
		String s = "";
		cal.setTime(date);
		if (cal.get(3) < 10)
			s = "0";
		return String.valueOf(cal.get(1)) + s + String.valueOf(cal.get(3));
	}

	/**
	 * 取季节
	 * 
	 * @param date
	 * @return
	 */
	public static final String getSeason(Date date) {
		cal.setTime(date);
		int i = cal.get(2);
		byte byte0 = 1;
		if (i >= 3 && i <= 5)
			byte0 = 2;
		if (i >= 6 && i <= 8)
			byte0 = 3;
		if (i >= 9 && i <= 11)
			byte0 = 4;
		return String.valueOf(cal.get(1)) + "0" + String.valueOf(byte0);
	}


	/**
	 * 得到指定日期前后n天的日期
	 * 
	 * @param oriDate
	 * @param n
	 *            后n天的参数应该是正数，前n天的参数应该是负数
	 * @return
	 */
	public static Date getNDayFromCur(Date oriDate, int n) {
		Date date = new Date(oriDate.getTime() + 24 * 60 * 60 * 1000 * n);
		return date;
	}

	

	/**
	 * yanglei 按日期得到星期几的英文简写 如：周一为Mon，周二为Tues
	 * 
	 * @param date
	 * @return
	 */
	public static String getWekByDate(String dateStr) {
		return getWekByDate(dateStr);
	}

	public static String getWekByDate(Date date) {
		int week_no = getDayOfWeek(date);
		String wek = null;
		if (week_no == 1) {
			wek = "Mon";
		} else if (week_no == 2) {
			wek = "Tues";
		} else if (week_no == 3) {
			wek = "Wed";
		} else if (week_no == 4) {
			wek = "Thurs";
		} else if (week_no == 5) {
			wek = "Fri";
		} else if (week_no == 6) {
			wek = "Sat";
		} else if (week_no == 7) {
			wek = "Sun";
		} else {
			wek = "";
		}
		return wek;

	}


	/**
	 * 获取当天的零点时间(最早时间：0点0分0秒)
	 * @param startDate
	 * @return
	 * @author Administrator
	 * @date 2012-6-26
	 * @see
	 */
	public static Date get0Point(Date startDate) {
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取当天最晚时间（23点59分59秒）
	 * @param endDate
	 * @return
	 * @author Administrator
	 * @date 2012-6-26
	 * @see
	 */
	public static Date get24Point(Date endDate) {
		cal.setTime(endDate);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		System.out.println(cal.getTime());
		return cal.getTime();
	}
	
	/**
	 * "EEE MMM dd HH:mm:ss zzz yyyy" String类型----> String类型 (yyyy-MM-dd)
	 */
	public static String convertS2S(String date,String format) throws ParseException{
		Date d  = convertStr2Date(date);
		return getDateString(d, format);
	}
	
	
	
	/**
	 * "EEE MMM dd HH:mm:ss zzz yyyy" String类型----> Date类型 
	 * @throws ParseException 
	 */
	public static Date  convertStr2Date(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		return sdf.parse(date);
	}
	
	/**
	 * 将日期时间格式化字符串转换为日期类型
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDatetime(String str) throws ParseException {
		return datetimeFormat.parse(str);
	}
	
	/**
	 * 返回中文星期几
	 * @param weekday
	 * @return weekNo
	 */
	public static String getChineseWeek(int weekday) {
		String[] cns = {"日","一","二","三","四","五","六"};
		String weekNo = "";
		switch(weekday) {
			case 1 : weekNo = cns[0]; break;
			case 2 : weekNo = cns[1]; break;
			case 3 : weekNo = cns[2]; break;
			case 4 : weekNo = cns[3]; break;
			case 5 : weekNo = cns[4]; break;
			case 6 : weekNo = cns[5]; break;
			case 7 : weekNo = cns[6]; break;			
		}	
		return weekNo;
	}
	
	//昨天
	public static String getYesterdayTimeStr(){
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}
	
	//根据当前输入时间,往前取三天
	public static String getNDaysTimeStr(Date date, int n){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,-n);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}
	
	
	//前三天
	public static String getThreeDaysTimeStr(){
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,-3);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}

	public static int compareByInterval(Calendar cal1,Calendar cal2,int period){
		int intervalYear = cal1.get(Calendar.YEAR)-cal2.get(Calendar.YEAR);
		int compareValue = 0;
		
		if(period==Calendar.YEAR){
			compareValue = intervalYear;
		}else {
			int intervalMonth = cal1.get(Calendar.MONTH)-cal2.get(Calendar.MONTH);
			if(period==Calendar.MONTH){
				compareValue = intervalYear*12+intervalMonth;
			}else if(period==Calendar.WEEK_OF_YEAR){
				if(intervalYear==0){
					compareValue = cal1.get(Calendar.WEEK_OF_YEAR)-cal2.get(Calendar.WEEK_OF_YEAR);
				}else{
					compareValue = intervalYear;
				}
			}else if(period==Calendar.DATE){
				if(intervalYear+intervalMonth!=0){
					compareValue = intervalYear*12+intervalMonth;
				}else{
					compareValue = cal1.get(Calendar.DATE)-cal2.get(Calendar.DATE);
				}
			}
		}
		if(compareValue>0){
			return 1;
		}else if(compareValue<0){
			return -1;
		}
		return compareValue;
	}
	
	public static String getChineseDate(Date date){
		if(date==null){
			return "";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR)+"年"+(cal.get(Calendar.MONTH)+1)+"月"+cal.get(Calendar.DATE)+"日";
	}
	
	public static String getChineseDateTime(Date date){
		if(date==null){
			return "";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		String hourString = hour+"";
		if(hour<10){
			hourString = "0"+hour;
		}
		int min = cal.get(Calendar.MINUTE);
		String minString = min+"";
		if(min<10){
			minString = "0"+min;
		}
		return year+"年"+month+"月"+day+"日"+hourString+":"+minString;
	}
	
	public static String getDate(long updateTime){
		long currentTime = System.currentTimeMillis();
		int day = DateUtil.getDays(updateTime, currentTime);
		if(0==day){
			return "今天";
		}else if(1==day){
			return "昨天";
		}else if(2==day){
			return "前天";
		}
		
		return getDateString(new Date(updateTime));
	}
	
	public static void main(String[] args) throws ParseException {
		Date value = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	        String dateValue = sdf.format(value);
	        dateValue = dateValue.replace(" ", "T");
	        System.out.println(dateValue);
	}
}
