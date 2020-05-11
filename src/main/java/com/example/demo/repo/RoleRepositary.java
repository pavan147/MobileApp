package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RoleModel;

@Repository
public interface RoleRepositary extends CrudRepository<RoleModel, Long> {
	
	public RoleModel findByName(String name);

}
