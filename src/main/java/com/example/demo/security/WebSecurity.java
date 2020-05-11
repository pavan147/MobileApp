package com.example.demo.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private final UserService userDetailService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserRepository userRepository;
	
	
	public WebSecurity(UserService userDetailService, BCryptPasswordEncoder bCryptPasswordEncoder ,UserRepository userRepository) {
		 
		this.userDetailService = userDetailService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository =userRepository;
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST , SecurityConstants.SIGN_UP_URL)
		.permitAll()
		.antMatchers("/h2-console/**")
        .permitAll()
		
		//Default URL
		//  .anyRequest().authenticated().and().addFilter(new  AuthenticationFilter(authenticationManager()));
		
		//Custome Url
		 .anyRequest().authenticated().and()
		 .addFilter(getAuthenticationFilter())//this method created getAuthenticationFilter() same class
		 .addFilter(new AuthorizationFilter(authenticationManager() , userRepository))
		 .sessionManagement()
		 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		
		/*
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/console/**" , "/h2/**").permitAll().anyRequest().authenticated();
         httpSecurity.headers().frameOptions().disable();
        */ 
		httpSecurity.headers().frameOptions().disable();
		//
		//.anyRequest().permitAll();
	}
	

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		
		authenticationManagerBuilder.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	//custum login url
	public AuthenticationFilter getAuthenticationFilter() throws Exception {
		
		final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
		authenticationFilter.setFilterProcessesUrl("/user/login");
		return authenticationFilter;
	}
	

}
