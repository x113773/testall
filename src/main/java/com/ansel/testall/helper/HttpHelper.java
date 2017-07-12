package com.ansel.testall.helper;

import javax.servlet.http.HttpServletRequest;

public class HttpHelper {
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
		return isAjax;
	}
}
