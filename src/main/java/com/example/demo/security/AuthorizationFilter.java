package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.demo.model.UserModel;
import com.example.demo.repo.UserRepository;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {
	
	private final UserRepository userRepositary;

	public AuthorizationFilter(AuthenticationManager authenticationManager , UserRepository userRepositary) {
		super(authenticationManager);
		
		this.userRepositary = userRepositary;
	}
	
	@Override
	public void doFilterInternal(HttpServletRequest request , HttpServletResponse response ,FilterChain chain) throws IOException, ServletException {
		
		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = getAuthontication(request);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
		
	}
	
	private UsernamePasswordAuthenticationToken getAuthontication(HttpServletRequest request) {
		
		String tocken = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if(tocken!= null) {
			
			tocken = tocken.replace(SecurityConstants.TOKEN_PREFIX, "");
			
			String user = Jwts.parser()
					.setSigningKey(SecurityConstants.getTockenSecret())
					.parseClaimsJws(tocken)
					.getBody()
					.getSubject();
			
			if(user != null) {
				
				UserModel userModel = userRepositary.findByEmail(user);
				
				if(userModel == null) return null;
					
				UserPrincipal userPrincipal = new UserPrincipal(userModel);
				
				return new UsernamePasswordAuthenticationToken(userPrincipal, null , userPrincipal.getAuthorities());
			}
			
		}
		return null;
		
		
	}

}
