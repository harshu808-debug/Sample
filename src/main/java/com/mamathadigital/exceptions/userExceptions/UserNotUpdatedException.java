package com.mamathadigital.exceptions.userExceptions;

@SuppressWarnings("serial")
public class UserNotUpdatedException extends RuntimeException
{
   public UserNotUpdatedException(String msg)
   {
	   super(msg);
   }
}
