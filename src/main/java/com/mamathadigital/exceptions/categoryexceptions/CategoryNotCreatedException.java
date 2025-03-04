package com.mamathadigital.exceptions.categoryexceptions;

@SuppressWarnings("serial")
public class CategoryNotCreatedException extends RuntimeException
{
    public CategoryNotCreatedException(String msg)
    {
    	super(msg);
    }
}
