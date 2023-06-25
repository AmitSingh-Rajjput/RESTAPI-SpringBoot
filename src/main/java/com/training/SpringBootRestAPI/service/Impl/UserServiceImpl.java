package com.training.SpringBootRestAPI.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.SpringBootRestAPI.dto.UserDto;
import com.training.SpringBootRestAPI.entity.User;
import com.training.SpringBootRestAPI.exception.EmailAlreadyExist;
import com.training.SpringBootRestAPI.exception.resourceNotFound;
import com.training.SpringBootRestAPI.mapper.UserMapper;
import com.training.SpringBootRestAPI.repository.UserRepository;
import com.training.SpringBootRestAPI.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		//Convert userDto into user jpa entity
		//User user = UserMapper.mapToUser(userDto);
		Optional<User> optionalUser = userRepository.findByEmailId(userDto.getEmailId());
		
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExist("Email Already Exists");
	    }
		User user = modelMapper.map(userDto, User.class);
		
		User savedUser = userRepository.save(user);
		
		// Convert User JPA entity to user Dto
		//UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
		UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
		return savedUserDto;
	}
	@Override
	public UserDto getUserById(int userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(
				()-> new resourceNotFound("User","id",userId));
//		return UserMapper.mapToUserDto(user);
		return modelMapper.map(user, UserDto.class);
	}
	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findAll();
//		return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
		return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}
	@Override
	public String deleteUserById(int userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(
				()-> new resourceNotFound("User","id",userId));
		userRepository.deleteById(userId);
		return "User deleted Sucessfully";
	}
	@Override
	public UserDto updateUser(UserDto user,int userId) {
		// TODO Auto-generated method stub
		User existingUser = userRepository.findById(userId).orElseThrow(
				() -> new resourceNotFound("User","id",user.getId())
				);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmailId(user.getEmailId());
		User updateUser = userRepository.save(existingUser);
//		return UserMapper.mapToUserDto(updateUser);
		return modelMapper.map(updateUser, UserDto.class);
	}
}
