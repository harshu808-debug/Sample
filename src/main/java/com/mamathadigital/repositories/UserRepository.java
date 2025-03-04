package com.mamathadigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mamathadigital.entities.User;

public interface UserRepository extends JpaRepository<User, String>
{
      User findByUserEmail(String userEmail);
      User findByUserEmailAndUserPassword(String email,String password);
      User findByUserPhone(Long phone);
      
      
      
}
