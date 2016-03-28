package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * 日期字符串工具
 * 
 * @author rengl
 *
 */
public class StringDateUtil {

	public static final String FMT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String FMT_YYYYMMDD = "yyyyMMdd";
	public static final String FMT_YYYYMMDDHHmmss = "yyyyMMddHHmmss";
	public static final String FMT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String FMT_HHMMSS = "HHmmss";
	public static final String FMT_HH_MM_SS = "HH:mm:ss";

	/**
	 * 数据库的日期字符串转换为有年月日的字符串 如 20131212 转换 2013年12月12日
	 * 
	 * @param date
	 * @return
	 * @author lupp
	 * @date 2014-4-28 下午3:39:30
	 */
	public static String ToDate(String date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		if (date.length() > 8) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(date.substring(0, 4)).append("年");
		sb.append(date.substring(4, 6)).append("月");
		sb.append(date.substring(6, 8)).append("日");
		return sb.toString();
	}

	/**
	 * 数据库的日期字符串转换为有年月日的字符串 如 20131212 转换 2013.12.12
	 * 
	 * @param date
	 * @return
	 * @author lupp
	 * @date 2014-4-28 下午3:39:46
	 */
	public static String ToDateDian(String date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		if (date.length() > 8) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(date.substring(0, 4)).append(".");
		sb.append(date.substring(4, 6)).append(".");
		sb.append(date.substring(6, 8));
		return sb.toString();
	}

	/**
	 * 得到当前时间字符串格式问14为 yyyyMMddHHmmss"
	 * 
	 * @return
	 * @author lupp
	 * @date 2014-4-28 下午3:39:58
	 */
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(FMT_YYYYMMDDHHmmss);
		return sdf.format(new Date());
	}
	
	/**
	 * 得到当前时间字符串格式问14为 yyyyMMdd"
	 * 
	 * @return
	 * @author lupp
	 * @date 2014-4-28 下午3:39:58
	 */
	public static String getDateTimeToYMD() {
		SimpleDateFormat sdf = new SimpleDateFormat(FMT_YYYYMMDD);
		return sdf.format(new Date());
	}

	/**
	 * 当前时间一段时间后的时间字符串
	 * 
	 * @param time
	 * @return
	 * @author shigj
	 * @date 2014-4-28 下午3:40:55
	 */
	public static String formatDate(Integer time) {
		DateFormat format = new SimpleDateFormat(FMT_HHMMSS);
		return format.format(new Date(Math.round(time * 1000 + 16 * 60 * 60
				* 1000)));
	}

	/**
	 * 时间格式化为yyyyMMddHHmmss字符串"
	 * 
	 * @param date
	 * @return
	 * @author Lawrence.Sun
	 * @date 2014-4-28 下午3:41:23
	 */
	public static String formatDate(Date date) {
		DateFormat format = new SimpleDateFormat(FMT_YYYYMMDDHHmmss);
		return format.format(date);
	}

	public static String formatDate2(Date date) {
		DateFormat format2 = new SimpleDateFormat("yyyyMMdd");
		return format2.format(date);
	}

	public static long getQuot(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 时间字符串转换为时间
	 * 
	 * @param date
	 *            时间字符串
	 * @param pattern
	 *            时间字符串的格式
	 * @return
	 * @author Lawrence.Sun
	 * @date 2014-4-28 下午5:04:59
	 */
	public static Date toData(String date, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间字符串化为formatStr字符串
	 * 
	 * @param dateTime
	 * @param formatStr
	 *            时间字符串时间格式
	 * @param formatStr
	 * @return
	 * @author Lawrence.Sun
	 * @date 2014-4-28 下午3:42:12
	 */
	public static String formatDate(String dateTime, String pattern,
			String formatStr) {
		Date d = toData(dateTime, pattern);
		if (null == d) {
			return null;
		}
		DateFormat format = new SimpleDateFormat(formatStr);
		return format.format(d);
	}

	/**
	 * 时间字符串化以pattern为格式为yyyy-MM-dd字符串
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return
	 * @author Lawrence.Sun
	 * @date 2014-4-28 下午5:10:53
	 */
	public static String formatDate(String dateTime, String pattern) {
		return formatDate(dateTime, pattern);
	}

	/**
	 * 时间字符串化以yyyyMMdd为格式为yyyy-MM-dd字符串
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return
	 * @author Lawrence.Sun
	 * @date 2014-4-28 下午5:10:53
	 */
	public static String formatDate(String dateTime) {
		return formatDate(dateTime, FMT_YYYYMMDD, FMT_YYYY_MM_DD);
	}

	public static void main(String[] args) {
		String str = "20140424";
		DateFormat format = new SimpleDateFormat(FMT_YYYYMMDD);
		try {
			Date d = format.parse(str);
			DateFormat format2 = new SimpleDateFormat(FMT_YYYY_MM_DD);
			System.out.println(format2.format(d));
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// System.out.println(formatDate(str, "yyyy-MM-dd"));
	}

	/**
	 * 将年月日时分秒(格式：yyyyMMddHHmmss)转换为以秒为单位的数字
	 * 
	 * @param dateTime
	 * @return
	 * @author shigj
	 * @date 2014-4-28 下午3:42:24
	 */
	public static Long parseDateTime(String dateTime) {
		int year = Integer.parseInt(dateTime.substring(0, 4));
		int month = Integer.parseInt(dateTime.substring(4, 6)) - 1;
		int day = Integer.parseInt(dateTime.substring(6, 8));
		int hour = Integer.parseInt(dateTime.substring(8, 10));
		int minute = Integer.parseInt(dateTime.substring(10, 12));
		int second = Integer.parseInt(dateTime.substring(12));

		Date date = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.YEAR, year);// 设置年
		gc.set(Calendar.MONTH, month);// 这里0是1月..以此向后推
		gc.set(Calendar.DAY_OF_MONTH, day);// 设置天
		gc.set(Calendar.HOUR_OF_DAY, hour);// 设置小时
		gc.set(Calendar.MINUTE, minute);// 设置分
		gc.set(Calendar.SECOND, second);// 设置秒
		date = gc.getTime();
		return date.getTime() / 1000;
	}
}
