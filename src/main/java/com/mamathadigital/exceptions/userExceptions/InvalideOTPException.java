package com.mamathadigital.exceptions.userExceptions;

@SuppressWarnings("serial")
public class InvalideOTPException extends RuntimeException
{
     public InvalideOTPException(String msg)
     {
    	 super(msg);
     }
}
