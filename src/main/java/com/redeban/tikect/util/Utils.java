package com.redeban.tikect.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

	public final static String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	
	private Utils() {
		
	}
	
	public static String nowTimestamp() {
		LocalDateTime nowDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);
		return nowDate.format(formatter);
	}
	
}
