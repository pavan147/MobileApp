package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {

	public UserModel findByEmail(String email);
}