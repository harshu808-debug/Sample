package com.mamathadigital.util;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public  class ConnectionProvider 
{
	 
	@Autowired 
	private  DataSource dataSource;
	 
     private  Connection connection;
	 
	 
	 private ConnectionProvider() {}
	 
	 
	 public  Connection getConnection()
	 {
		 try 
		 {
			 if(connection==null)
			 {
				 this.connection=this.dataSource.getConnection();
			 }
		 } 
		 catch (Exception e) 
		 {
			throw new RuntimeException(e.getMessage());
		 }
		 
		 return connection;
		 
	 }
     
     
}
