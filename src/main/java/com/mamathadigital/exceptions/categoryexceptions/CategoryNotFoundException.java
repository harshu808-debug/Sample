package com.mamathadigital.exceptions.categoryexceptions;

@SuppressWarnings("serial")
public class CategoryNotFoundException extends RuntimeException
{
	public CategoryNotFoundException(String msg)
    {
    	super(msg);
    }
}
