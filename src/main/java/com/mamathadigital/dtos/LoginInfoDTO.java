package com.mamathadigital.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoDTO 
{
	
	private String userEmail;
	
	private String userPassword;
	
	private String otp;
	

}
