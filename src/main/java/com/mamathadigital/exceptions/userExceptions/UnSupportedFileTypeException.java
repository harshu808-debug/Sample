package com.mamathadigital.exceptions.userExceptions;

@SuppressWarnings("serial")
public class UnSupportedFileTypeException extends RuntimeException
{
    public UnSupportedFileTypeException(String msg)
    {
    	super(msg);
    }
}
