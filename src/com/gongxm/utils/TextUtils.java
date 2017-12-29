package com.gongxm.utils;

public class TextUtils {

	/**
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean notEmpty(String... strings) {
		return !isEmpty(strings);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isEmpty(String... strings) {
		for (int i = 0; i < strings.length; i++) {
			if (strings[i] == null || strings[i].trim().length() == 0) {
				return true;
			}
		}
		return false;
	}

	

	
}
