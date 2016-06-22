package com.silicolife.textmining.core.init;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.silicolife.textmining.core.datastructures.dataaccess.database.DatabaseAccess;
import com.silicolife.textmining.core.datastructures.init.exception.InvalidDatabaseAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class UpdateDatabaseTest {

	@Test
	public void update() throws InvalidDatabaseAccess, SQLException, FileNotFoundException, IOException, ANoteException {
		IDatabase database = CreateAndFillMysqlDatabaseTest.createDatabase("localhost","3306","todelete","root","admin");
		DatabaseConnectionInit.init("localhost","3306","todelete","root","admin");
		database.updateDatabase("src/test/resources/dbupdatefolder","src/test/resources/anote2_db_version.xml");	
	    assertTrue(true);
	}
	
	
//	@Test
	public void createandupdate() throws InvalidDatabaseAccess, SQLException, FileNotFoundException, IOException, ANoteException {
		IDatabase database = CreateAndFillMysqlDatabaseTest.createDatabase("localhost","3306","todelete","root","admin");
        String hibernateFilePath = "src/test/resources/hibernate.cfg.xml";
		if(database==null)
		{
	        assertTrue(false);
		}		
        assertTrue(fillDatabase(database));
		DatabaseAccess dbAccess = new DatabaseAccess(database, hibernateFilePath);
		if(!dbAccess.isFill() || dbAccess.isDatabaseOutOfDate("src/test/resources/anote2_db_version.xml"))
		{
			DatabaseConnectionInit.init("localhost","3306","todelete","root","admin");
			database.updateDatabase("src/test/resources/dbupdatefolder","src/test/resources/anote2_db_version.xml");
			
	        assertTrue(true);
		}
	}
	
	
//	@Test
	public void testOutOffdate() throws InvalidDatabaseAccess, SQLException, FileNotFoundException, IOException, ANoteException {
		IDatabase database = CreateAndFillMysqlDatabaseTest.createDatabase("localhost","3306","todelete2","root","admin");
        String hibernateFilePath = "src/test/resources/hibernate.cfg.xml";
		if(database==null)
		{
	        assertTrue(false);
		}		
        assertTrue(fillDatabase(database));
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
	 */
	private static boolean fillDatabase(IDatabase database) throws SQLException, FileNotFoundException, IOException
	{
		if(!database.isfill())
		{
			database.fillDataBaseTables("src/test/resources/anote2databasescript_old.sql");
			return true;
		}
		return false;
	}

}
