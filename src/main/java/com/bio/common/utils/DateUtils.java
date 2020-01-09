package com.bio.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 */
public class DateUtils {
	/**
	 * 时间格式(yyyy-MM-dd)
	 */
	public final static String DATE_PATTERN_10 = "yyyy-MM-dd";
	/**
	 * 时间格式(yyyy-MM-dd HH:mm:ss)
	 */
	public final static String DATE_TIME_PATTERN_19 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 时间格式(yyyyMMddHHmmss)
	 */
	public final static String DATE_TIME_PATTERN_14 = "yyyyMMddHHmmss";

	/**
	 * 时间格式(yyyyMMdd)
	 */
	public final static String DATE_TIME_PATTERN_8 = "yyyyMMdd";

	public static String format(Date date) {
		return format(date, DATE_PATTERN_10);
	}

	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}

	/**
	 * 计算距离现在多久，非精确
	 *
	 * @param date
	 * @return
	 */
	public static String getTimeBefore(Date date) {
		Date now = new Date();
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String r = "";
		if (day > 0) {
			r += day + "天";
		} else if (hour > 0) {
			r += hour + "小时";
		} else if (min > 0) {
			r += min + "分";
		} else if (s > 0) {
			r += s + "秒";
		}
		r += "前";
		return r;
	}

	/**
	 * 计算距离现在多久，精确
	 *
	 * @param date
	 * @return
	 */
	public static String getTimeBeforeAccurate(Date date) {
		Date now = new Date();
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String r = "";
		if (day > 0) {
			r += day + "天";
		}
		if (hour > 0) {
			r += hour + "小时";
		}
		if (min > 0) {
			r += min + "分";
		}
		if (s > 0) {
			r += s + "秒";
		}
		r += "前";
		return r;
	}

	public static Date getLastWeekMondayStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMondayStart(date));
		cal.add(Calendar.DATE, -7);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static Date getLastWeekSundayEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getLastWeekMondayStart(date));
		cal.add(Calendar.DATE, 6);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		return cal.getTime();
	}

	public static Date getThisWeekMondayStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static Date getNextWeekMondayStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMondayStart(date));
		cal.add(Calendar.DATE, 7);
		return cal.getTime();
	}


	public static Date getThisWeekSundayEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMondayStart(date));
		cal.add(Calendar.DATE, 6);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 59);

		return cal.getTime();
	}

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = new Date();
			System.out.println("今天是" + sdf.format(date));
			System.out.println("上周一" + sdf.format(getLastWeekMondayStart(date)));
			System.out.println("本周一" + sdf.format(getThisWeekMondayStart(date)));
			System.out.println("本周日" + sdf.format(getThisWeekSundayEnd(date)));

			System.out.println("下周一" + sdf.format(getNextWeekMondayStart(date)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
