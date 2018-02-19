package com.silicolife.textmining.core.init;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.silicolife.textmining.core.datastructures.dataaccess.database.DatabaseAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.database.instances.MySQLDatabase;
import com.silicolife.textmining.core.datastructures.init.exception.InvalidDatabaseAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class UpdateDatabaseTest {

//	@Test	
	public void update() throws InvalidDatabaseAccess, SQLException, FileNotFoundException, IOException, ANoteException {
		String dbName= "todelete";
		String databaseuser = "databaseuser";
		String databaseuserpassword = "databaseuserpassword";
		String host = "localhost";
		String port = "3306";
		IDatabase database = new MySQLDatabase(host,port,dbName,databaseuser,databaseuserpassword);
		DatabaseConnectionInit.initWithouLogIn(DataBaseTypeEnum.MYSQL, host,port,dbName,databaseuser,databaseuserpassword);
		database.updateDatabase("src/test/resources/dbupdatefolder","src/test/resources/anote2_db_version.xml");	
	    assertTrue(true);
	}
	
	
//	@Test
	public void createandupdate() throws InvalidDatabaseAccess, SQLException, FileNotFoundException, IOException, ANoteException {
		String dbName= "todelete";
		String databaseuser = "databaseuser";
		String databaseuserpassword = "databaseuserpassword";
		String host = "localhost";
		String port = "3306";
		DataBaseTypeEnum databaseType = DataBaseTypeEnum.MYSQL;
		IDatabase database = CreateAndFillMysqlDatabaseTest.createDatabase(host,port,dbName,databaseuser,databaseuserpassword);
        String hibernateFilePath = "src/test/resources/hibernate.cfg.xml";
		if(database==null)
		{
	        assertTrue(false);
		}		
        fillDatabase(database);
		DatabaseConnectionInit.initWithouLogIn(databaseType,host,port,dbName,databaseuser,databaseuserpassword);
		DatabaseAccess dbAccess = new DatabaseAccess(database, hibernateFilePath);
		if(!dbAccess.isFill() || dbAccess.isDatabaseOutOfDate("src/test/resources/anote2_db_version.xml"))
		{
			database.updateDatabase("src/test/resources/dbupdatefolder","src/test/resources/anote2_db_version.xml");			
	        assertTrue(true);
		}
	}
	
	
//	@Test
	public void testOutOffdate() throws InvalidDatabaseAccess, SQLException, FileNotFoundException, IOException, ANoteException {
		IDatabase database = CreateAndFillMysqlDatabaseTest.createDatabase("localhost","3306","maria","root","admin");
		String hibernateFilePath = "src/test/resources/hibernate.cfg.xml";
		if(database==null)
		{
			assertTrue(false);
		}		
		fillDatabase(database);
		DatabaseConnectionInit.initWithouLogIn(DataBaseTypeEnum.MYSQL,"localhost","3306","todelete3","root","admin");
		DatabaseAccess dbAccess = new DatabaseAccess(database, hibernateFilePath);
		if(!dbAccess.isFill() || dbAccess.isDatabaseOutOfDate("src/test/resources/anote2_db_version.xml"))
		{
			assertTrue(true);
		}
		else
		{
			assertTrue(false);
		}
	}

	/**
	 * Fill Database
	 * 
	 * @param database
	 * @return
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ANoteException 
	 */
	private static boolean fillDatabase(IDatabase database) throws SQLException, FileNotFoundException, IOException, ANoteException
	{
		if(!database.isfill())
		{
			database.fillDataBaseTables("src/test/resources/anote2databasescript_old.sql");
			return true;
		}
		return false;
	}

}
