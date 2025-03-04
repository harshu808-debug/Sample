package com.mamathadigital.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.mamathadigital.dtos.Message;
import com.mamathadigital.exceptions.categoryexceptions.CategoryNotCreatedException;
import com.mamathadigital.exceptions.categoryexceptions.CategoryNotFoundException;
import com.mamathadigital.exceptions.productexceptions.ProductNotCreatedException;
import com.mamathadigital.exceptions.userExceptions.InvalideOTPException;
import com.mamathadigital.exceptions.userExceptions.UnSupportedFileTypeException;
import com.mamathadigital.exceptions.userExceptions.UserNotCreatedException;
import com.mamathadigital.exceptions.userExceptions.UserNotFoundException;
import com.mamathadigital.exceptions.userExceptions.UserNotUpdatedException;

@ControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(exception = CategoryNotCreatedException.class)
     public ResponseEntity<?> categoryNotCreatedException(CategoryNotCreatedException e)
     {
    	 return new ResponseEntity<Message>( new  Message(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
     }
	@ExceptionHandler(exception = CategoryNotFoundException.class)
    public ResponseEntity<?> categoryNotFoundException(CategoryNotFoundException e)
    {
   	 return new ResponseEntity<Message>( new  Message(e.getMessage()),HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(exception = UserNotCreatedException.class)
    public ResponseEntity<Message>  userNotCreatedException(UserNotCreatedException e)
    {
   	 return new ResponseEntity<Message>(new Message(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
	@ExceptionHandler(exception = UserNotFoundException.class)
    public ResponseEntity<Message>  userNotFoundException(UserNotFoundException e)
    {
   	 return new ResponseEntity<Message>(new Message(e.getMessage()),HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(exception = UserNotUpdatedException.class)
    public ResponseEntity<Message>  userNotFoundException(UserNotUpdatedException e)
    {
   	 return new ResponseEntity<Message>(new Message(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(exception = UnSupportedFileTypeException.class)
    public ResponseEntity<Message>  unSupportedFileTypeException(UnSupportedFileTypeException e)
    {
   	 return new ResponseEntity<Message>(new Message(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(exception = InvalideOTPException.class)
	public ResponseEntity<Message> invalidOTPException(InvalideOTPException e)
	{
		return new ResponseEntity<Message>(new Message(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//Product related exception handling
	@ExceptionHandler(exception = ProductNotCreatedException.class)
	public ResponseEntity<Message> productNotCreatedException(ProductNotCreatedException e)
	{
		return new ResponseEntity<Message>(new Message(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@ExceptionHandler(exception = NoResourceFoundException.class)
	public ResponseEntity<Message> noResourceFoundException(NoResourceFoundException e)
	{
		return new ResponseEntity<Message>(new Message(e.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(exception = NoSuchMethodError.class)
	public ResponseEntity<Message> HandleAnyException(Error e)
	{
		return new ResponseEntity<Message>(new Message(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(exception = RuntimeException.class)
	public ResponseEntity<Message> runtimeException(RuntimeException e)
	{
		return new ResponseEntity<Message>(new Message(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
