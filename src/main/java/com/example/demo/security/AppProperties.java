package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
	
	@Autowired
	private Environment environment;
	
	public String getTockenSecret() {
		return environment.getProperty("tocken.secret");
	}

}
