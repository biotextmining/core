package com.silicolife.textmining.core.init;

import static org.junit.Assert.assertTrue;

import java.net.Proxy;
import java.sql.SQLException;
import java.util.Properties;

import com.silicolife.textmining.core.datastructures.dataaccess.database.DatabaseAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.database.DatabaseFactory;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.init.dataaccess.DataAccessDefaultSettings;
import com.silicolife.textmining.core.datastructures.init.exception.InvalidDatabaseAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.IDataAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;


public class DatabaseConnectionInit {

	public static void init(DataBaseTypeEnum databaseType,String host,String port,String schema,String username,String password) throws InvalidDatabaseAccess, ANoteException
	{
		IDatabase dabaseAcess = factoryDatabase(databaseType, host,port,schema,username,password);
		Properties properties = new Properties();
		properties.put("Using-Title-In-Abstract", "true");
		properties.put("Free-Full-Text-Only", "true");
		properties.put(DataAccessDefaultSettings.LUCENEINDEXBASEDIRECTORY, "src/test/resources/");
		Proxy proxy = null;
		String hibernateFilePath = "src/test/resources/hibernate.cfg.xml";
		IDataAccess dataAccess = new DatabaseAccess(dabaseAcess,hibernateFilePath);
		InitConfiguration.init(dataAccess,proxy,properties );
		dataAccess.login("admin", "admin");
		InitConfiguration.getDataAccess().checkLogin("admin", "admin");
		assertTrue(true);
	}


	/**
	 * Create Database  
	 * 
	 * @return
	 * @throws InvalidDatabaseAccess
	 * @throws SQLException 
	 */
	private static IDatabase factoryDatabase(DataBaseTypeEnum databaseType,String host,String port,String schema,String username,String password) throws InvalidDatabaseAccess
	{

		if(host == null || host.isEmpty() || host.trim().isEmpty())
		{
			throw new InvalidDatabaseAccess("Host can not be empty (localhost by default)");
		}
		if(!databaseType.equals(DataBaseTypeEnum.H2Embedded) && (port == null || port.isEmpty() || port.trim().isEmpty()))
		{
			throw new InvalidDatabaseAccess("Port can not be empty (3306 by default)");
		}
		if(schema == null || schema.isEmpty() || schema.trim().isEmpty())
		{
			throw new InvalidDatabaseAccess("Schema can not be empty");
		}
		IDatabase databaseAdded = DatabaseFactory.createDatabase(databaseType, host, port, schema, username, password);
		return databaseAdded;
	}


}
