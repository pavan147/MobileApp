package com.example.demo.enums;

public enum ErrorMessageExceptions {
	
	MISSING_REQUARED_FIELD("Missing requared field, Please check");
	
	
	private String errorMessage;

	private ErrorMessageExceptions(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
