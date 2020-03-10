package com.cszx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date parseDateTime(String str, String formatStr) throws ParseException {
		if (StringUtil.isNotEmpty(str)) {
			if (str.length() == 8) {
				if (str.contains(":")) {
					formatStr = "HH:mm:ss";
				} else {
					formatStr = "yyyyMMdd";
				}
			} else if (str.contains("-")) {
				if (str.contains(":")) {
					formatStr = "yyyy-MM-dd HH:mm:ss";
				} else {
					formatStr = "yyyy-MM-dd";
				}
			} else if (str.contains("/")) {
				if (str.contains(":")) {
					formatStr = "yyyy/MM/dd HH:mm:ss";
				} else {
					formatStr = "yyyy/MM/dd";
				}
			}
		}
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);// 小写的mm表示的是分钟
		date = sdf.parse(str);
		return date;
	}

	public static String dateToStr(Date date, String formatStr) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		String str = sdf.format(date);
		return str;
	}

	@SuppressWarnings("deprecation")
	public static int getDutyDays(String strStartDate, String strEndDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;

		try {
			startDate = df.parse(strStartDate);
			endDate = df.parse(strEndDate);
		} catch (ParseException e) {
			System.out.println("非法的日期格式,无法进行转换");
			e.printStackTrace();
		}
		int result = 0;
		while (startDate.compareTo(endDate) <= 0) {
			if (startDate.getDay() != 6 && startDate.getDay() != 0)
				result++;
			startDate.setDate(startDate.getDate() + 1);
		}

		return result;
	}
}
