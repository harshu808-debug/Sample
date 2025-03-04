package com.mamathadigital.services.userservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mamathadigital.dao.UserDAOI;
import com.mamathadigital.dtos.UserRequestDTO;
import com.mamathadigital.dtos.UserResponseDTO;
import com.mamathadigital.entities.User;
import com.mamathadigital.exceptions.userExceptions.InvalideOTPException;
import com.mamathadigital.exceptions.userExceptions.UserNotCreatedException;
import com.mamathadigital.exceptions.userExceptions.UserNotFoundException;
import com.mamathadigital.mappers.DTOToEntityMapper;
import com.mamathadigital.mappers.EntityToDTOMapper;
import com.mamathadigital.util.EmailSender;
import com.mamathadigital.util.OTPOperation;
import com.mamathadigital.util.PasswordEncoding;

@Service
public class UserServiceImpl implements IUserService 
{
	
	
	private UserDAOI userDAOI;
	private OTPOperation otpOperation;
	

	public UserServiceImpl(UserDAOI userDAOI, OTPOperation otpOperation) {
		super();
		this.userDAOI = userDAOI;
		this.otpOperation = otpOperation;
	}

	@Override
	public UserResponseDTO addUser(UserRequestDTO userRequestDTO) 
	{
		if(userRequestDTO !=null)
		{
			 User user = DTOToEntityMapper.userRequestDTOToUser(userRequestDTO);
			 Optional<User> savedUser = this.userDAOI.saveUser(user);
			
			if(savedUser.isPresent())
			{
				UserResponseDTO userResponseDTO = EntityToDTOMapper.toUserResponseDTO(savedUser.get());
				if(userResponseDTO !=null)
				{
					return userResponseDTO;
				}
				else
				{
					throw new RuntimeException("Something went wrong !");
				}
				
			}
			else
			{
				throw new UserNotCreatedException("User not created !");
			}
			
			
			
		}
		else
		{
			throw new RuntimeException("User must not be null");
		}
		
		
	}
	
	@Override
	public List<UserResponseDTO>  getAllUser()
	{
		Optional<List<User>> allUser = this.userDAOI.findAllUser();
		if(allUser.isPresent())
		{
			List<User> listOfUsers = allUser.get();
			if(listOfUsers!=null)
			{
				if(listOfUsers.size() > 0)
				{
					List<UserResponseDTO> allUserResponseDTOs=new ArrayList<>();
					
					for(User user : listOfUsers)
					{
						UserResponseDTO userResponseDTO = EntityToDTOMapper.toUserResponseDTO(user);
						allUserResponseDTOs.add(userResponseDTO);
					}
					
					return allUserResponseDTOs;
				}
				else 
				{
					throw new UserNotFoundException("No user is present !");
				}
			}
			else
			{
				throw new RuntimeException("Something went wrong !");
			}
			
		}
		else
		{
			throw new UserNotFoundException("No user is present !");
		}
		
	}




	@Override
	public UserResponseDTO getSingleUser(String userId) 
	{
		if(userId!=null)
		{
			if(!userId.isEmpty())
			{
				Optional<User> OptionalUser = this.userDAOI.findUserById(userId);
				if(OptionalUser.isPresent())
				{
					UserResponseDTO userResponseDTO = EntityToDTOMapper.toUserResponseDTO(OptionalUser.get());
				    return userResponseDTO;
				}
				else
				{
					throw new UserNotFoundException("User not exist with id : "+userId);
				}
			}
			else
			{
				throw new RuntimeException("UserId must not be empty !");
			}
		}
		else 
		{
			 throw new RuntimeException("UserId must not be null !");
		}
	}




	@Override
	public UserResponseDTO loginByEmailAndPassword(String email, String password) 
	{
		if(email!=null && !email.isEmpty())
		{
			if(password!=null && !password.isEmpty())
			{
				Optional<User> loginUser = this.userDAOI.getUser(email, password);
				
				if(loginUser.isPresent())
				{
					UserResponseDTO userResponseDTO = EntityToDTOMapper.toUserResponseDTO(loginUser.get());
					return userResponseDTO;
				}
				else
				{
					throw new UserNotFoundException("Invalid Credentials");
				}
			}
			else
			{
				throw new RuntimeException("password must not be null or empty");
			}
		}
		else 
		{
			  throw new RuntimeException("Email must not be null or empty");
		}
		
	}




	@Override
	public UserResponseDTO loginByEmail(String email) 
	{
		
		if(email!=null && !email.isEmpty())
		{
			 Optional<User> optionalUser = this.userDAOI.getUserByEmail(email);
			 if(optionalUser.isPresent())
			 {
				 String otp = this.otpOperation.generateOTP();
				 System.out.println(otp);
				 otpOperation.storeOTP(email, otp);
				 EmailSender.sendOtpEmail(email, otp);
				 return EntityToDTOMapper.toUserResponseDTO(optionalUser.get());
			 }
			 else
			 {
				 throw new UserNotFoundException("User not exist with email : "+email);
			 }
		}
		else
		{
			throw new RuntimeException("Email must not be null or empty !");
		}
	}

	@Override
	public String validateOTP(String email, String otp) 
	{
		if(email!=null && !email.isEmpty())
		{
			
			if(otp!=null && !otp.isEmpty())
			{
				Optional<User> optionalUser = this.userDAOI.getUserByEmail(email);
				if(optionalUser.isPresent())
				{
					Optional<String> optionalMessage = this.otpOperation.validateOTP(email, otp);
					return optionalMessage.orElseThrow(()-> new InvalideOTPException("Invalid OTP !"));
				}
				else 
				{
					throw new UserNotFoundException("User not exist with email : "+email);
				}
				
			}
			else 
			{
				throw new RuntimeException("OTP must not be null or empty !");
			}
		      
		
		}
		else
		{
			throw new RuntimeException("Email must not be null or empty !");
		}
		
		
	}

	@Override
	public UserResponseDTO updateUser(UserRequestDTO userRequestDTO,String userId) 
	{
		if(userRequestDTO !=null)
		{
			if(userId!=null && !userId.isEmpty())
			{
				
				
				//boolean userExist = this.userDAOI.isUserExist(userId);
				Optional<User> isExistUser = this.userDAOI.findUserById(userId);
				
				if(isExistUser.isPresent())
				{
					
					if(this.userDAOI.findUserByEmail(userRequestDTO.getUserEmail()))
					{
					
						if(this.userDAOI.findUserByPhone(userRequestDTO.getUserPhone()))
						{
							
							//get the old user by id
							User user = isExistUser.get();
							user.setUserEmail(userRequestDTO.getUserEmail());
							user.setUserName(userRequestDTO.getUserName());
							user.setUserPassword(PasswordEncoding.passwordEncode(userRequestDTO.getUserPassword()));
							user.setUserPhone(userRequestDTO.getUserPhone());
							
							Optional<User> updatedUser = this.userDAOI.updateUser(user);
							
							if(updatedUser.isPresent())
							{
								UserResponseDTO userResponseDTO = EntityToDTOMapper.toUserResponseDTO(updatedUser.get());
								return userResponseDTO;
							}
							else 
							{
								throw new RuntimeException("Something went wrong ! try again ");
							}
						}
						else
						{
							throw new RuntimeException("User exist with phone : "+userRequestDTO.getUserPhone());
						}
					}
					else
					{
						throw new RuntimeException("User exist with email : "+userRequestDTO.getUserEmail());
					}
					
					
				}
				else 
				{
					throw new UserNotFoundException("User not exist with id : "+userId);
				}
				
			}
			else
			{
				 throw new RuntimeException("User id must not be null or empty !");
			}
			
		}
		else
		{
			throw new RuntimeException("UserRequestDTO can't be null");
		}
	}

	@Override
	public UserResponseDTO editProfileImage(MultipartFile file, String userId) 
	{
		Optional<User> optionalUser = this.userDAOI.editImage(file, userId);
		if(optionalUser.isPresent())
		{
			
			 UserResponseDTO userResponseDTO = EntityToDTOMapper.toUserResponseDTO(optionalUser.get());
			 return userResponseDTO;
		}
		else
		{
			throw new RuntimeException("Something went wrong !");
		}
	
	}

	@Override
	public UserResponseDTO resetPassword(String email)
	{
		if(email!=null && !email.isEmpty())
		{
			 Optional<User> optionalUser = this.userDAOI.getUserByEmail(email);
			 if(optionalUser.isPresent())
			 {
				 User user = optionalUser.get();
				 
				 String otp = this.otpOperation.generateOTP();
				 System.out.println(otp);
				 otpOperation.storeOTP(email, otp);
				 EmailSender.sendOtpEmail(email, otp);
				 
				 return EntityToDTOMapper.toUserResponseDTO(user);
				 
			 }
			 else
			 {
				 throw new UserNotFoundException("User not existed with email : "+email);
			 }
			 
		}
		else
		{
			throw new RuntimeException("Email must not be null or empty !");
		}
	}

	@Override
	public UserResponseDTO updatePassword(String email, String newPassword) 
	{
		if(email !=null && !email.isEmpty())
		{
			if(newPassword!=null && !newPassword.isEmpty())
			{
				Optional<User> optionalUser = this.userDAOI.changePassword(email, newPassword);
				
                return EntityToDTOMapper
						.toUserResponseDTO(optionalUser.orElseThrow(
								()->new UserNotFoundException("User not exist with id : "+email)
								           )
								);
				
			}
			else
			{
				throw new RuntimeException("Password must not be null and empty");
			}
		}
		else
		{
			throw new RuntimeException("Email must not be null and empty");
		}
	}




	
	

}
