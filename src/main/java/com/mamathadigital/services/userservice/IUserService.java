package com.mamathadigital.services.userservice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mamathadigital.dtos.UserRequestDTO;
import com.mamathadigital.dtos.UserResponseDTO;

public interface IUserService 
{
      UserResponseDTO addUser(UserRequestDTO userDTO);
      List<UserResponseDTO> getAllUser();
      UserResponseDTO getSingleUser(String userId);
      UserResponseDTO loginByEmailAndPassword(String email,String password);
      UserResponseDTO loginByEmail(String email);
      String validateOTP(String email,String otp);
      UserResponseDTO updateUser(UserRequestDTO userRequestDTO,String userId);
      UserResponseDTO editProfileImage(MultipartFile file,String userId);
      UserResponseDTO resetPassword(String email);
      UserResponseDTO updatePassword(String email,String newPassword );
}
