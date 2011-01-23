package com.infoservice.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA. Date: 2008-4-6 Time: 20:07:36 To change
 * this template use File | Settings | File Templates.
 */

public class RequestUtil {
	/**
	 * 获取request中变量值
	 * 
	 * @param request
	 * @param name
	 * @param defval
	 *            默认值
	 * @return String
	 */
	public static final String getParam(HttpServletRequest request,
			String name, String defval) {
		String param = request.getParameter(name);
		return (param != null ? param : defval);
	}

	/**
	 * 获取request中变量值
	 * 
	 * @param request
	 * @param name
	 * @return int
	 */
	public static final int getParam(HttpServletRequest request, String name,
			int defval) {
		String param = request.getParameter(name);
		int value = defval;
		if (param != null) {
			try {
				value = Integer.parseInt(param);
			} catch (NumberFormatException ignore) {
			}
		}
		return value;
	}

	/**
	 * @param request
	 * @param name
	 * @param defval
	 *            默认值
	 * @return Integer
	 */
	public static final Integer getParam(HttpServletRequest request,
			String name, Integer defval) {
		String param = request.getParameter(name);
		Integer value = defval;
		if (param != null) {
			try {
				value = Integer.valueOf(param);
			} catch (NumberFormatException ignore) {
			}
		}
		return value;
	}

	public static String changeEncoding(String input, String sourceEncoding,
			String targetEncoding) {
		if (input == null || input.equals("")) {
			return input;
		}

		try {
			byte[] bytes = input.getBytes(sourceEncoding);
			return new String(bytes, targetEncoding);
		} catch (Exception ex) {
		}
		return input;
	}


}
