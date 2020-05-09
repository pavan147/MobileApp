package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.UserBean;
import com.example.demo.enums.ErrorMessageExceptions;
import com.example.demo.exception.UserServiceException;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/{userID}" ,
			produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public UserBean getUser(@PathVariable Long userID) {
		
		UserBean userBean = userService.getUserById(userID);
		
		//get XMl format respoince you need to add one Header Accept = application/xml in POST_MAN
		return userBean;
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE} ,
			produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public  UserBean saveOrUpdateUser(@RequestBody UserBean userBean) throws Exception {
		
		if(userBean.getEmail().isEmpty() || userBean.getEmail() == null) {
			throw new UserServiceException(ErrorMessageExceptions.MISSING_REQUARED_FIELD.getErrorMessage());
		}
		
		userService.saveOrUpdate(userBean);
		return userBean;
		
		
	}
	

	@PutMapping(path = "/{userID}" ,
			consumes = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE} ,
			produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public  UserBean updateUser(@PathVariable  Long userID , @RequestBody UserBean userBean) throws Exception {
		
		
		
		userService.updateUser(userBean , userID );
		return userBean;
		
		
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public List<UserBean> getAllUserPagination(@RequestParam(value = "page" , defaultValue = "1") int page , @RequestParam(value = "size" , defaultValue = "25") int size) {
		
		 List<UserBean> userBeanList = userService.getUserListPagination(page , size);
		
		return userBeanList;
		
	}
	
	



}
