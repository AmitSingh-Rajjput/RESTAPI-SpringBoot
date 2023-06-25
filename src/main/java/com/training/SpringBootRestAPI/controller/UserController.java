package com.training.SpringBootRestAPI.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.training.SpringBootRestAPI.dto.UserDto;
import com.training.SpringBootRestAPI.exception.ErrorDetails;
import com.training.SpringBootRestAPI.exception.resourceNotFound;
import com.training.SpringBootRestAPI.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name="CRUD Rest API for User resource",
description="CRUD REST APIs - Create User, Update User, Get User,Get User by id,Get all User,Delete user")
@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Operation(
			summary="Create User Rest API",
			description="Create User Rest Api is used to save user")
	@ApiResponse(
			responseCode="201",
			description="HTTP Status 201 Created")
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto user){
		UserDto savedUser = userService.createUser(user);
		return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
	}

	
	@Operation(
			summary="Get User by Id Rest API",
			description="Get User by Id Rest Api is used to fetch the user details")
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 sucess")
	@GetMapping("/getUser/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") int userId){
		UserDto getUser = userService.getUserById(userId);
		return new ResponseEntity<>(getUser,HttpStatus.OK);
	}
	
	
	@Operation(
			summary="Get all User Rest API",
			description="Get all User Rest Api is used to fetch all user details")
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 Sucess")
	@GetMapping("/getAllUser")
	public ResponseEntity<List<UserDto>> getAllUSer(){
		return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
	}
	
	
	@Operation(
			summary="Delete User Rest API",
			description="Delete User Rest Api is used to delete user details")
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 Sucess")
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int userId){
		return new ResponseEntity<>(userService.deleteUserById(userId),HttpStatus.OK);
	}
	
	@Operation(
			summary="Update User Rest API",
			description="Udate User Rest Api is used to update the user details")
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 Sucess")
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user,@PathVariable("id") int userId){
		return new ResponseEntity<>(userService.updateUser(user,userId),HttpStatus.OK);
	}
	
//	@ExceptionHandler(resourceNotFound.class)
//	public ResponseEntity<ErrorDetails> handleResourceNotFound(resourceNotFound expection,WebRequest webrequest){
//
//		ErrorDetails errorDetails = new ErrorDetails(
//				LocalDateTime.now(),expection.getMessage(),webrequest.getDescription(false),"USER_NOT_FOUND");
//		
//		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//	}
}
