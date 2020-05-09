package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserModel;

@Repository
//public interface UserRepository extends CrudRepository<UserModel, Long> 
//public interface UserRepository extends JpaRepository<UserModel , Long>
public interface UserRepository extends PagingAndSortingRepository<UserModel, Long>
{

	public UserModel findByEmail(String email);
	
}