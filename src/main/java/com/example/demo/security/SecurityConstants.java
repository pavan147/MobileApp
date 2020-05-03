package com.example.demo.security;

import com.example.demo.SpringApplicationContext;

public class SecurityConstants {
	
	public static final long EXPIRATION_TIME = 86400000;
	public static final String TOKEN_PREFIX = "pavan ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/user";
	
	public static String getTockenSecret() {
		
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTockenSecret();
	}

}
