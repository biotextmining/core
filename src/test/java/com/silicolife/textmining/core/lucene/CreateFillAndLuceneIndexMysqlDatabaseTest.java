package com.silicolife.textmining.core.lucene;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.silicolife.textmining.core.datastructures.dataaccess.database.DatabaseFactory;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.init.exception.InvalidDatabaseAccess;
import com.silicolife.textmining.core.init.DatabaseConnectionInit;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class CreateFillAndLuceneIndexMysqlDatabaseTest{
	
	
	@Test
	public void createUpdateDatabase() throws InvalidDatabaseAccess, FileNotFoundException, SQLException, IOException, ANoteException
	{
		IDatabase database = createDatabase("localhost","3306","todelete","root","admin");
		if(database==null)
		{
	        assertTrue(false);
		}		
        assertTrue(fillDatabase(database));
        DatabaseConnectionInit.init("localhost","3306","todelete","root","admin");
        InitConfiguration.getDataAccess().getResourceElementsByExactSynonym("batatas");
	}
	
	/**
	 * Fill Database
	 * 
	 * @param database
	 * @return
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean fillDatabase(IDatabase database) throws SQLException, FileNotFoundException, IOException
	{
		if(!database.isfill())
		{
			database.fillDataBaseTables("src/test/resources/anote2databasescript.sql");
			return true;
		}
		return false;
	}
	
	/**
	 * Create Database  
	 * 
	 * @return
	 * @throws InvalidDatabaseAccess
	 * @throws SQLException 
	 */
	public static IDatabase createDatabase(String host,String port,String schema,String username,String password) throws InvalidDatabaseAccess, SQLException
	{
		DataBaseTypeEnum databaseType = DataBaseTypeEnum.MYSQL;
		if (host.trim().equals("") || port.trim().equals("") || schema.trim().equals("")) {
			if(host.isEmpty())
			{
				throw new InvalidDatabaseAccess("Host can not be null (localhost by default)");
			}
			if(port.isEmpty())
			{
				throw new InvalidDatabaseAccess("Port can not be null (3306 by default)");
			}
			if(schema.isEmpty())
			{
				throw new InvalidDatabaseAccess("Schema can not be null");
			}
			return null;
		} else {
			IDatabase databaseAdded = DatabaseFactory.createDatabase(databaseType, host, port, schema, username, password);
			databaseAdded.createDataBase();
			checkConnection(databaseAdded);
			return databaseAdded;
		}
	}
	
	/**
	 * Check databse connection
	 * 
	 * @param databaseAdded
	 * @return
	 */
	private static boolean checkConnection(IDatabase databaseAdded) {
		try {
			databaseAdded.openConnection();
			if (databaseAdded.getConnection() != null) {
				return true;
			}
		}catch (SQLException e) {
			return false;
		}
		return false;
	}

}
