package com.mamathadigital.util.idgenerators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

@SuppressWarnings("serial")
public class ProductIdGenerator implements IdentifierGenerator
{
	
	private Connection connection;
	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) 
	{
		
		String prefix="pid_0";
		String suffix="";
		
		//db call for getting sequence no
		try 
		{
			this.connection=session.getJdbcConnectionAccess().obtainConnection();
			Statement sequenceStatement = this.connection.createStatement();
			ResultSet rstSequence = sequenceStatement.executeQuery("select product_id_seq.nextval from dual");
		    if(rstSequence.next())
		    {
		    	int sequence = rstSequence.getInt(1);
		    	suffix=String .valueOf(sequence);
		    }
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
		return prefix+suffix;
	}
}
