package com.mamathadigital.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.mamathadigital.entities.User;

public interface UserDAOI 
{
     Optional<User> saveUser(User user);
     Optional<String> deleteUser(String userId);
     Optional<User> findUserById(String userId);
     Optional<List<User>> findAllUser();
     Optional<User> updateUser(User user);
     Optional<User> editImage(MultipartFile file,String userId);
     Optional<User> getUser(String userEmail,String userPassword);
     Optional<User>  getUserByEmail(String userEmail);
     Optional<User> changePassword(String email,String newPassword );
     boolean isUserExist(String userId);
     boolean findUserByEmail(String email);
     boolean  findUserByPhone(Long phone);
     
     
     
     
     
}
