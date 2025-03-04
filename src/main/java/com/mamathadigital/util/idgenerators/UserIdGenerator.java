package com.mamathadigital.util.idgenerators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
@SuppressWarnings("serial")
public class UserIdGenerator implements IdentifierGenerator
{
    private Connection connection;
	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) 
	{
		System.out.println("UserIdGenerator.generate()");
		String prefix="uid_0";
		String suffix="";
		
		//db call for getting sequence no
		try 
		{
			this.connection=session.getJdbcConnectionAccess().obtainConnection();
			Statement sequenceStatement = this.connection.createStatement();
			ResultSet rstSequence = sequenceStatement.executeQuery("select user_id_seq.nextval from dual");
		    if(rstSequence.next())
		    {
		    	int sequence = rstSequence.getInt(1);
		    	System.out.println(sequence);
		    	suffix=String .valueOf(sequence);
		    }
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
		System.out.println(prefix+suffix);
		return prefix+suffix;
	}

}

