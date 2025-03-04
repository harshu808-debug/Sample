package com.mamathadigital.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mamathadigital.util.idgenerators.OTPGenerator;

@Component
public class OTPOperation 
{
	 @Autowired
	 private OTPGenerator otpGenerator;
	
     private Map<String, String> otpStore;

	 public OTPOperation() 
	 {
		this.otpStore=new HashMap<>();
	 }
	 
	 
	 public  void storeOTP(String email,String OTP)
	 {
		this.otpStore.put(email,OTP);
		System.out.println(this.otpStore);
	 }
	 
	 public Optional<String> validateOTP(String email,String OTP)
	 {
		 String storedOTP = this.otpStore.get(email);
		 if(storedOTP!=null)
		 {
			 if(storedOTP.equals(OTP))
			 {
				 this.otpStore.remove(email);
				 return  Optional.of("OTP Validation Successfull");
			 }
			 else
			 {
				 return Optional.empty();
			 }
			 
			 
		 }
		 else 
		 {
			return Optional.empty();
		 }
	 }
	 
	 public String generateOTP()
	 {
		 Optional<String> otp = this.otpGenerator.getOTP();
		 
		 return otp.orElseThrow(()->new RuntimeException("OTP generation failed"));
	 }
     
     
     
}
