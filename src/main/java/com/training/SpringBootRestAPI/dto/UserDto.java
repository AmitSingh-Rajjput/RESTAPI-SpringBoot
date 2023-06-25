package com.training.SpringBootRestAPI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(
		description="User Dto")
public class UserDto {
	
	private int id;
	
	@Schema(
			description= "User first name")
	// if client want firstname not to be empty 
	@NotEmpty(message = "FirstName should not be empty")
	private String firstName;
	
	
	@Schema(
			description= "User last name")
	//Last name not should be empty
	@NotEmpty(message = "LastName should not be empty")
	private String lastName;
	
	
	@Schema(
			description= "User email")
	//Email not should be empty and valid email address
	@NotEmpty(message = "Email should not be empty")
	@Email(message="Email should be in proper format")
	private String emailId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(int id, String firstName, String lastName, String emailId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}
	
	

}
