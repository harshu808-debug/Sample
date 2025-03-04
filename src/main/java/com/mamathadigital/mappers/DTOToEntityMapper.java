package com.mamathadigital.mappers;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.mamathadigital.dtos.UserRequestDTO;
import com.mamathadigital.entities.User;
import com.mamathadigital.util.PasswordEncoding;


public class DTOToEntityMapper 
{
	
	public static User userRequestDTOToUser(UserRequestDTO dto)
	{
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		user.setUserPassword(PasswordEncoding.passwordEncode(dto.getUserPassword()));
		user.setRole("USER");
		user.setUserRegistrationDate(new Date());
		user.setUserProfileImage("default.png");
		user.setActive(true);
		
		System.out.println(user);
		return user;
		
	}

}
