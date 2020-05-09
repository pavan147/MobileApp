package com.example.demo.beans;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.example.demo.model.UserModel;
import com.sun.istack.NotNull;

public class AddresseBean {

	private Long addressID;
	private String city;

	private String country;

	private String street;

	private UserModel userModel;

	public Long getAddressID() {
		return addressID;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getStreet() {
		return street;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setAddressID(Long addressID) {
		this.addressID = addressID;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

}
