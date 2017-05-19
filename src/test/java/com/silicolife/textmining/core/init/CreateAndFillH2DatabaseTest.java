package com.silicolife.textmining.core.init;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.silicolife.textmining.core.datastructures.dataaccess.database.DatabaseFactory;
import com.silicolife.textmining.core.datastructures.init.exception.InvalidDatabaseAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;

public class CreateAndFillH2DatabaseTest{
	
	
	@Test
	public void createUpdateDatabase() throws InvalidDatabaseAccess, FileNotFoundException, SQLException, IOException
	{
		IDatabase database = createDatabase("./","todelete","root","admin");
		if(database==null)
		{
	        assertTrue(false);
		}		
        assertTrue(fillDatabase(database));
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
			database.fillDataBaseTables("src/test/resources/h2_anote2databasescript.sql");
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
	public static IDatabase createDatabase(String databasePath,String schema,String username,String password) throws InvalidDatabaseAccess, SQLException
	{
		DataBaseTypeEnum databaseType = DataBaseTypeEnum.H2Embedded;
		if (databasePath.trim().equals("") || schema.trim().equals("")) {
			if(databasePath.isEmpty())
			{
				throw new InvalidDatabaseAccess("Host can not be empty ('./' by default)");
			}
			if(schema.isEmpty())
			{
				throw new InvalidDatabaseAccess("Schema can not be empty");
			}
			return null;
		} else {
			IDatabase databaseAdded = DatabaseFactory.createDatabase(databaseType, databasePath, null, schema, username, password);
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
