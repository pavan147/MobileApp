package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.beans.UserBean;

public interface UserService extends UserDetailsService {
	
	public List<UserBean> getAllStudent();

	public void saveOrUpdate(UserBean userBean);
	
	public UserBean getUserByUsername(String username);
	
	public UserBean getUserById(Long userId);

}
