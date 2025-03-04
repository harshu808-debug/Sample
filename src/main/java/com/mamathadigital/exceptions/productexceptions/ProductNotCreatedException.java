package com.mamathadigital.exceptions.productexceptions;

@SuppressWarnings("serial")
public class ProductNotCreatedException extends RuntimeException
{
    public ProductNotCreatedException(String msg)
    {
    	super(msg);
    }
}
