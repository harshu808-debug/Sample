package com.mamathadigital.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
	
	 private String userName;
	 private String userEmail;
	 private String userPassword;
	 private String userGender;
	 private Long userPhone;
	 private Boolean termsAndCondition;

}
