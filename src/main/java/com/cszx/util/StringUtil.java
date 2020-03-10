package com.cszx.util;

public class StringUtil {

	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static Integer parseToInteger(String s) {
		if (!"".equals(s) && s != null) {
			return Integer.parseInt(s);
		}
		return null;
	}

	public static String doNullString(String s) {
		if (s == null) {
			return "";
		} else {
			return s;
		}
	}
}
