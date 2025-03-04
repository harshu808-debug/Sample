package com.mamathadigital.contoller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mamathadigital.dtos.LoginInfoDTO;
import com.mamathadigital.dtos.UserRequestDTO;
import com.mamathadigital.dtos.UserResponseDTO;
import com.mamathadigital.services.userservice.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000")
public class UserController 
{
	private IUserService userService;
	  
	  
	
	public UserController(IUserService userService) {
		super();
		this.userService = userService;
	}
	



	@PostMapping("/adduser")
	public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO)
	{
		UserResponseDTO user = this.userService.addUser(userRequestDTO);
		return new ResponseEntity<UserResponseDTO>(user,HttpStatus.OK);
	}
	
	@GetMapping("/allusers")
	public ResponseEntity<List<UserResponseDTO>>  getAllUser()
	{
		List<UserResponseDTO> allUser = this.userService.getAllUser();
		return new ResponseEntity<List<UserResponseDTO>>(allUser,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userId}")
	public ResponseEntity<UserResponseDTO> getSingleUser(@PathVariable String userId)
	{
		System.out.println(userId);
		return new ResponseEntity<UserResponseDTO>(this.userService.getSingleUser(userId.trim()),HttpStatus.OK);
	}
	
	
	@PutMapping(value ="/updateUser/{userId}")
	public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO,@PathVariable String userId)
	{
		UserResponseDTO userResponseDTO = this.userService.updateUser(userRequestDTO, userId);
		return new ResponseEntity<UserResponseDTO>(userResponseDTO,HttpStatus.OK);
	}
	
	
	@PutMapping("/editProfileImage/{userid}")
	public ResponseEntity<UserResponseDTO> editProfileImage(@RequestParam MultipartFile file,
			                                        @PathVariable("userid") String userId
			)
	{
		 UserResponseDTO userResponseDTO = this.userService.editProfileImage(file,userId);
		 
		return new ResponseEntity<UserResponseDTO>(userResponseDTO,HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "/loginbyemailandpassword")
	public ResponseEntity<UserResponseDTO> loginUserByEmailAndPassword(@RequestBody LoginInfoDTO loginInfoDTO)
	{
		if(loginInfoDTO!=null)
		{
			UserResponseDTO userResponseDTO = this.userService.loginByEmailAndPassword(loginInfoDTO.getUserEmail(), loginInfoDTO.getUserPassword());
		    return new ResponseEntity<UserResponseDTO>(userResponseDTO,HttpStatus.OK);
		
		}
		else 
		{
			throw new RuntimeException("Credentials can't be null");
		}
	}
	
	@PostMapping(value = "/byemail")
	public ResponseEntity<UserResponseDTO> loginByEmail(@RequestBody LoginInfoDTO loginInfoDTO)
	{
		if(loginInfoDTO !=null)
		{
		UserResponseDTO userResponseDTO = this.userService.loginByEmail(loginInfoDTO.getUserEmail());
		return new ResponseEntity<UserResponseDTO>(userResponseDTO,HttpStatus.OK);
		}
		else {
			throw new RuntimeException("something went wrong");
		}
	}
	
	@PostMapping(value="/otp")
	public ResponseEntity<String> validateOTP(@RequestBody LoginInfoDTO loginInfoDTO)
	{
		if(loginInfoDTO !=null)
		{
		String msg = this.userService.validateOTP(loginInfoDTO.getUserEmail(),loginInfoDTO.getOtp());
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		else {
			throw new RuntimeException("something went wrong");
		}
	}
	
	@PostMapping(value = "/resetPassword")
	public ResponseEntity<UserResponseDTO> resetPassword(@RequestBody LoginInfoDTO loginInfoDTO)
	{
		UserResponseDTO userResponseDTO = this.userService.resetPassword(loginInfoDTO.getUserEmail());
		return new ResponseEntity<UserResponseDTO>(userResponseDTO,HttpStatus.OK);
	}
	
	@PostMapping(value="/updatePassword")
	public ResponseEntity<UserResponseDTO> updatePassword(@RequestBody LoginInfoDTO loginInfoDTO)
	{
		if(loginInfoDTO != null)
		{
			UserResponseDTO updatedUserResponseDTO = this.userService.updatePassword(loginInfoDTO.getUserEmail(), loginInfoDTO.getUserPassword());
		    return new ResponseEntity<UserResponseDTO>(updatedUserResponseDTO,HttpStatus.OK);
		}
		else
		{
			throw new RuntimeException("LoginInfoDTO must not be null");
		}
	}
	
	
	
	
	
	

}
