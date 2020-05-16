package com.example.demo.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.AuthorityModel;
import com.example.demo.model.RoleModel;
import com.example.demo.model.UserModel;
import com.example.demo.repo.AuthorityRepositary;
import com.example.demo.repo.RoleRepositary;
import com.example.demo.repo.UserRepository;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Component
public class InitialUserSetup {
	
	@Autowired
	private AuthorityRepositary authorityRepositary;
	
	@Autowired
	private RoleRepositary roleRepositary;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * @param applicationReadyEvent
	 */
	@EventListener
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		
		System.out.println("from Applicatiin event Event......");
		
		AuthorityModel readAuthority =  createAuthority("READ_AUTHORITY");
		AuthorityModel writrAuthority =  createAuthority("WRITE_AUTHORITY");
		AuthorityModel deleteAuthority =  createAuthority("DELETE_AUTHORITY");
		
		 RoleModel roleUser =  createRoles("ROLE_USER", Arrays.asList(readAuthority));
		 RoleModel roleAdmin = createRoles("ROLE_ADMIN", Arrays.asList(readAuthority , writrAuthority , deleteAuthority));
		 
		 if(roleAdmin == null) return;
			 
		/*
		 * UserModel userModel = new UserModel();
		 * 
		 * userModel.setName("Admin"); userModel.setEmail("admin@gmail.com");
		 * userModel.setPassword(bCryptPasswordEncoder.encode("admin"));
		 * userModel.setRoles(Arrays.asList(roleAdmin));
		 * 
		 * userRepository.save(userModel);
		 */
			 
	}
	
	@Transactional
	private AuthorityModel createAuthority(String name) {
		
		AuthorityModel authorityModel=authorityRepositary.findByName(name);
		
		if(authorityModel == null) {
			
			authorityModel = new AuthorityModel(name);
			authorityRepositary.save(authorityModel);
			
		}
		return authorityModel;
	}
	
	@Transactional
	private RoleModel createRoles(String role , Collection<AuthorityModel> authorityList) {
		
		RoleModel roleModel = roleRepositary.findByName(role);
		
		if(roleModel == null) {
			
			roleModel = new RoleModel(role);
			roleModel.setAuthority(authorityList);
			
			roleRepositary.save(roleModel);
			
		}
		return roleModel;
		
		
	}

}
