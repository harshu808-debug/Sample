package com.mamathadigital.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO 
{
	   private String userId;
	   private String userName;
	   private Date userRegistrationDate;
	   private String role;
	   private String userEmail;
	   private Boolean active;
	   private String userPassword;
	   private String userProfileImage;
	   private String userGender;
	   private Long userPhone;
}
