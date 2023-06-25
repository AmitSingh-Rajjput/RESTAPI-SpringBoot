package com.training.SpringBootRestAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.SpringBootRestAPI.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	// Here if we want to use any field search which is in over table than fuction name should findBy<FieldName>. 
	// fieldname should be in camel case.
	
	 Optional<User> findByEmailId(String emailId);
}

