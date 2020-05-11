package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.SpringApplicationContext;
import com.example.demo.beans.LoginBean;
import com.example.demo.beans.UserBean;
import com.example.demo.service.UserService;
import com.example.demo.serviceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {

		this.authenticationManager = authenticationManager;

	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request , HttpServletResponse response) {
		
		try {
			
			LoginBean creds = new ObjectMapper().readValue(request.getInputStream(), LoginBean.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
					(creds.getEmail(),
							creds.getPassword(), 
							      new ArrayList<>()));
			
			
		}catch(IOException exception) {
			
			throw new RuntimeException(exception);
		}
		
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest request , 
			                              HttpServletResponse response ,
			                              FilterChain filterChain ,
			                              Authentication authentication) {
		
		String username =  ((UserPrincipal)authentication.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTockenSecret())
				.compact();
		
		UserService UserService =(UserService)SpringApplicationContext.getBean("userServiceImpl");
		UserBean userBean = UserService.getUserByUsername(username);
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX +token);
		response.addHeader("UserID", userBean.getId().toString());
		
	}
}
