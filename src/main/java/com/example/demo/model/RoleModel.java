package com.example.demo.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
@Table
public class RoleModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private Collection<UserModel> userModels;
	
	
	@ManyToMany(cascade = {CascadeType.PERSIST} , fetch = FetchType.EAGER)
	@JoinTable(name = "role_authority" , joinColumns=@JoinColumn(name = "role_id" , referencedColumnName = "id"),
			                         inverseJoinColumns = @JoinColumn(name = "aiuthority_id", referencedColumnName = "id"))
     private Collection<AuthorityModel> authority;
	
	public RoleModel() {}

	public RoleModel(String role) {
		this.name = role;
	}

	public Collection<AuthorityModel> getAuthority() {
		return authority;
	}

	public void setAuthority(Collection<AuthorityModel> authority) {
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Collection<UserModel> getUserModels() {
		return userModels;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserModels(Collection<UserModel> userModels) {
		this.userModels = userModels;
	}
	
	

}
