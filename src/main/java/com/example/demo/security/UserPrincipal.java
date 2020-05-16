package com.example.demo.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.AuthorityModel;
import com.example.demo.model.RoleModel;
import com.example.demo.model.UserModel;

public class UserPrincipal implements UserDetails {

	private UserModel userModel;
	private Long userId;
	
	public UserPrincipal(UserModel userModel){
		this.userModel = userModel;
		this.userId = userModel.getId();
	}
	
	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		Collection<AuthorityModel> authorityList = new ArrayList<>();
		
		Collection<RoleModel> roleList =userModel.getRoles();
		
		if(roleList == null)  return grantedAuthorities;
		
		roleList.forEach((role) ->{
			
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
			authorityList.addAll(role.getAuthority());
		});
		
		
		authorityList.forEach((authorityModel) ->{
			
			grantedAuthorities.add(new SimpleGrantedAuthority(authorityModel.getName()));
		});
		
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		 
		return  userModel.getPassword();
	}

	@Override
	public String getUsername() {
		
		return  userModel.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
