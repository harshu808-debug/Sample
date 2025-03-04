package com.mamathadigital.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoding {
	
	private static PasswordEncoder passwordEncoder;
	
	
	
	public PasswordEncoding(PasswordEncoder passwordEncoder) {
		super();
		PasswordEncoding.passwordEncoder = passwordEncoder;
	}



	public static String passwordEncode(String password)
	{
		return PasswordEncoding.passwordEncoder.encode(password);
				
	}

}
