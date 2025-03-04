package com.mamathadigital.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mamathadigital.entities.User;
import com.mamathadigital.exceptions.userExceptions.UnSupportedFileTypeException;
import com.mamathadigital.exceptions.userExceptions.UserNotCreatedException;
import com.mamathadigital.exceptions.userExceptions.UserNotFoundException;
import com.mamathadigital.repositories.UserRepository;
import com.mamathadigital.util.FileStorageProperties;
import com.mamathadigital.util.idgenerators.ImageFileNameGenerator;

@Component
public class UserDAOImpl implements UserDAOI
{
    private UserRepository userRepository;
    
    private FileStorageProperties fileStorageProperties;
    
	public UserDAOImpl(UserRepository userRepository,FileStorageProperties fileStorageProperties) {
		super();
		this.userRepository = userRepository;
		this.fileStorageProperties=fileStorageProperties;
	}

	
	@Override
	public Optional<User> saveUser(User user) 
	{
		User byUserEmail = this.userRepository.findByUserEmail(user.getUserEmail());
		if(byUserEmail==null)
		{
			User byUserPhone = this.userRepository.findByUserPhone(user.getUserPhone());
			if(byUserPhone==null)
			{	
				User savedUser = this.userRepository.save(user); 
				return savedUser !=null ? Optional.of(savedUser) : Optional.empty();
			}
			else
			{
				throw new UserNotCreatedException("Already user existed with phoneno : "+user.getUserPhone());
			}
		}
		else
		{
			throw new UserNotCreatedException("Already user existed with email : "+user.getUserEmail());
		}
		
		
		
		
	}

	@Override
	public Optional<String> deleteUser(String userId)
	{
		Optional<User> byId = this.userRepository.findById(userId);
		if(byId.isPresent())
		{
			//this.userRepository.delete(byId.get());
			User user = byId.get();
			user.setActive(false);
			this.userRepository.save(user);
			return Optional.of("User deleted successfully with id : "+userId);
		}
		else
		{
			throw new UserNotFoundException("User not exist with id : "+userId);
		}
	}

	@Override
	public Optional<User> findUserById(String userId) 
	{
		//Optional<User> byId = this.userRepository.findById(userId);
		
		boolean isExist = this.userRepository.existsById(userId);
		if(isExist)
		{
			 User user = this.userRepository.getReferenceById(userId);
			 return Optional.of(user);
		}
		else
		{
			return Optional.empty();
		}
		
	}

	@Override
	public Optional<List<User>> findAllUser() 
	{
		List<User> users = this.userRepository.findAll();
		return users!=null ? Optional.of(users) : Optional.empty();
	}
	
	
	
	
	
	

	@Override
	public Optional<User> updateUser(User user) 
	{
		
		if(user!=null)
		{
			User updatedUser = this.userRepository.save(user);
			return updatedUser!=null ? Optional.of(updatedUser) : Optional.empty();
		}
		else		
		{
			throw new RuntimeException("User must not be null");
		}
			
			
		
	}

	
	
	
	
	
	
	
	
	
	
	

	@Override
	public Optional<User> editImage(MultipartFile file,String userId) 
	{
		System.out.println("UserDAOImpl.editImage()");
		String targetDirectory;
		String fileName=ImageFileNameGenerator.getNewFileName(file.getOriginalFilename());
		if(fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".png"))
		{
			
			targetDirectory=this.fileStorageProperties.getImageStoragePath();
			
			//create directories if not exist
			Path path = Paths.get(targetDirectory);
			
			//save the file
			Path targetLocation= path.resolve(fileName);
			
			System.out.println(targetLocation);
			try 
			{
				if(!Files.exists(path))
				{
					Files.createDirectories(path);
				}
				
				Optional<User> byId = this.userRepository.findById(userId);
				
				if(byId.isPresent())
				{
					 User user = byId.get();
					 
					 String oldImageFileName=user.getUserProfileImage();
					 user.setUserProfileImage(fileName);
					 User updatedUser = this.userRepository.save(user);
					 System.out.println(oldImageFileName);
					 
					 if(updatedUser!=null)
					 {
						 File oldFile = new File(targetDirectory.concat(oldImageFileName));
						 System.out.println(oldFile.getPath());
						 if(oldFile.exists())
						 {
							 oldFile.delete();
						 }
						 
						 Files.copy(file.getInputStream(), targetLocation);
						 System.out.println(updatedUser);
						 
						 return Optional.of(updatedUser);
						 
					 }
					 else
					 {
						return Optional.empty();
					 }
					
					
					 
				}
				else
				{
					throw new UserNotFoundException("User not exist with id : "+userId);
				}
				
			} 
			catch (IOException e) 
			{
				throw new RuntimeException("image not stored successfully !");
			}
			
		}
		else 
		{
			   
			   throw new UnSupportedFileTypeException("Unsupported file ! only support (.jpg or .png)");
		}
		
		
		
	}


	@Override
	public Optional<User> getUser(String userEmail, String userPassword) 
	{
		User user = this.userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
		if(user==null)
		{
			return Optional.empty();
		}
		else 
		{
		    return Optional.of(user);	
		}
		
		
	}


	@Override
	public Optional<User> getUserByEmail(String userEmail) 
	{
		User user = this.userRepository.findByUserEmail(userEmail);
		if(user==null)
		{
			return Optional.empty();
		}
		else
		{
			return Optional.of(user);
		}
		
	}


	@Override
	public Optional<User> changePassword(String email, String newPassword) 
	{
		Optional<User> userByEmail = this.getUserByEmail(email);
		if(userByEmail.isPresent())
		{
			User user = userByEmail.get();
			user.setUserPassword(newPassword);
			User updatedUser = this.userRepository.save(user);
			return Optional.of(updatedUser);
		}
		return Optional.empty();
	}


	@Override
	public boolean isUserExist(String userId)
	{
		return  this.userRepository.existsById(userId);
	}


	@Override
	public boolean findUserByEmail(String email) 
	{
		return this.userRepository.findByUserEmail(email)==null ? true : false;
	}


	@Override
	public boolean findUserByPhone(Long phone)
	{
		return this.userRepository.findByUserPhone(phone) ==null ? true : false;
	}
     
}
