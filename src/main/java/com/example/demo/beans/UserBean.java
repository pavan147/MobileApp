package com.example.demo.beans;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserBean {
	
 
	private Long id;
	
 
	private String name;
 
	private String email;
	
 
	private String password;


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
