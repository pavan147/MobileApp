package com.example.demo.model;

import java.util.Collection;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
@Table
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@Column(unique = true)
	@NotNull
	@NotBlank
	private String email;

	@Column
	private String password;
	
	@OneToMany(mappedBy = "userModel" ,cascade =CascadeType.ALL)
	List<AddresseModel> addresses;
	
	@ManyToMany(cascade = {CascadeType.PERSIST} , fetch = FetchType.EAGER)
	@JoinTable(name = "user_role" , joinColumns=@JoinColumn(name = "user_id" , referencedColumnName = "id"),
			                         inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
 private Collection<RoleModel> roles;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<AddresseModel> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddresseModel> addresses) {
		this.addresses = addresses;
	}

	public Collection<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleModel> roles) {
		this.roles = roles;
	}
	
	

}