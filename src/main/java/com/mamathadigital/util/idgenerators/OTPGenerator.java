package com.mamathadigital.util.idgenerators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mamathadigital.util.ConnectionProvider;


@Component
public class OTPGenerator 
{
	private  Connection connection;
	
	@Autowired
	private ConnectionProvider connectionProvider;
	
	public  Optional<String> getOTP()
	{
		long otp=0000;
		try 
		{
			this.connection=this.connectionProvider.getConnection();
			Statement sequenceStatement = this.connection.createStatement();
			ResultSet rstSequence = sequenceStatement.executeQuery("select otp_generator_seq.nextval from dual");
		    if(rstSequence.next())
		    {
		    	otp= rstSequence.getInt(1);
		    	
		    }
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
		return Optional.of(String.valueOf(otp));
		
	}

}
