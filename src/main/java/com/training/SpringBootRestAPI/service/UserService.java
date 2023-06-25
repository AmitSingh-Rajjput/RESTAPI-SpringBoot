package com.training.SpringBootRestAPI.service;

import java.util.List;

import com.training.SpringBootRestAPI.dto.UserDto;
import com.training.SpringBootRestAPI.entity.User;

public interface UserService {
	
	public UserDto createUser(UserDto user);
	
	public UserDto getUserById(int userId);
	
	public List<UserDto> getAllUser();
	
	public String deleteUserById(int userId);
	
	public UserDto updateUser(UserDto user,int userId);

}
