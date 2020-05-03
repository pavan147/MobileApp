package com.example.demo.beans;

import java.util.Date;

public class ErrorMessaeBean {

	private Date date;
	private String errorMessage;

	public ErrorMessaeBean(Date date, String errorMessage) {
		this.date = date;
		this.errorMessage = errorMessage;
	}

	public Date getDate() {
		return date;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
