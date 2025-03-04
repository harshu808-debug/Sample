package com.mamathadigital.exceptions.userExceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String msg)
    {
    	super(msg);
    }
}
