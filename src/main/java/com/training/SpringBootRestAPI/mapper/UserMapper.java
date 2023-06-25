package com.training.SpringBootRestAPI.mapper;

import com.training.SpringBootRestAPI.dto.UserDto;
import com.training.SpringBootRestAPI.entity.User;

public class UserMapper {

	public static UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmailId());
		return userDto;
				
	}
	
	public static User mapToUser(UserDto userDto) {
		User user = new User(
				userDto.getId(),
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmailId());
		return user;
				
	}
}
