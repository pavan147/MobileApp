package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
@Table
public class AddresseModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long addressID;
	
	@Column
	@NotNull
	@NotBlank
	private String city;
	
	@Column
	@NotNull
	@NotBlank
	private String country;
	
	@Column
	private String street;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
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

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	

}
