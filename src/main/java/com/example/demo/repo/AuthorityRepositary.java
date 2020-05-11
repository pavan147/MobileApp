package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AuthorityModel;

@Repository
public interface AuthorityRepositary extends CrudRepository<AuthorityModel , Long> {
	
	public AuthorityModel  findByName(String name);

}
