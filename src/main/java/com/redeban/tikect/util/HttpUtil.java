package com.redeban.tikect.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletResponse;

public class HttpUtil {

	private static final String HEADER_TOTAL_COUNT = "X-Total-Count";
	
	public static void setHeaderTotalCountResponse(String value) {
		setHeaderResponse(HEADER_TOTAL_COUNT, value);
	}

	public static void setHeaderResponse(String name, String value) {
		HttpServletResponse currentResponse = getCurrentHttpResponse();
		if (currentResponse != null) {
			currentResponse.setHeader(name, value);
		}
	}
	
	public static HttpServletResponse getCurrentHttpResponse() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null && requestAttributes instanceof ServletRequestAttributes) {
			HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
			return response;
		}
		return null;
	}
	
}
