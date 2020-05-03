package com.example.demo.exception;

import java.util.Date;

import javax.xml.ws.Response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.beans.ErrorMessaeBean;

@ControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceException(UserServiceException exception , WebRequest request){
		
		ErrorMessaeBean errorMessaeBean =  new ErrorMessaeBean(new Date(), exception.getMessage());
		
		return new ResponseEntity<>(errorMessaeBean , new HttpHeaders() , HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Handle Other all Exception
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleUserServiceException(Exception exception , WebRequest request){
		
		ErrorMessaeBean errorMessaeBean =  new ErrorMessaeBean(new Date(), exception.getMessage());
		
		return new ResponseEntity<>(errorMessaeBean , new HttpHeaders() , HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
