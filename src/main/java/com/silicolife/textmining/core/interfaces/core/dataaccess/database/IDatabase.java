package com.silicolife.textmining.core.interfaces.core.dataaccess.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;

/**
 * This interface implements a general database credentials
 * 
 * @author Hugo Costa
 * 
 * @version 1.0 (16 Junho 2009)
 *
 */
public interface IDatabase {

	/**
	 * Get the URL from database
	 * 
	 * @return
	 */
	public String getDataBaseURL();

	/**
	 * Method that open a database Connection
	 * 
	 * @throws DatabaseLoadDriverException
	 * @throws SQLException
	 * @throws DatabaseCredentialsException
	 */
	public void openConnection() throws SQLException;

	/**
	 * Method that close a database connection
	 */
	public void closeConnection() throws SQLException;

	/**
	 * Method that shutdown database
	 */
	public void shutDownDatabase() throws SQLException;

	/**
	 * Method that change driver database
	 */
	public void setDriverClassName(String driverName);

	/**
	 * Method that return a Connection at database
	 * 
	 * @return
	 * @throws DatabaseLoadDriverException
	 * @throws SQLException
	 * @throws DatabaseCredentialsException
	 */
	public Connection getConnection() throws SQLException;

	/**
	 * Method that return a Schema database connection
	 * 
	 * @return Schema
	 */

	public String getSchema();

	/**
	 * Method that return Host of database connection
	 * 
	 * @return Host
	 */
	public String getHost();

	/**
	 * Method that return Port of database connection
	 * 
	 * @return Port
	 */
	public String getPort();

	/**
	 * Method that return User of database connection
	 * 
	 * @return User
	 */
	public String getUser();

	/**
	 * Method that return Password of database connection
	 * 
	 * @return Password
	 */
	public String getPwd();

	/**
	 * Method for create a new connection
	 * 
	 * @return {@link Connection}
	 * @throws DatabaseLoadDriverException
	 * @throws SQLException
	 * @throws DatabaseCredentialsException
	 */
	public Connection getNeWConnection() throws SQLException;

	/**
	 * 
	 * Fill database tables
	 * 
	 * @throws DatabaseLoadDriverException
	 * @throws SQLException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws DatabaseCredentialsException
	 */
	public void fillDataBaseTables(String schemaFile) throws SQLException, FileNotFoundException, IOException;;

	/**
	 * 
	 * Create a Database
	 * 
	 * @param user
	 * @param pwd
	 * @return
	 * @throws DatabaseLoadDriverException
	 * @throws SQLException
	 * @throws DatabaseCredentialsException
	 */
	public boolean createDataBase() throws SQLException;

	/**
	 * 
	 * Create a Database
	 * 
	 * @param user
	 * @param pwd
	 * @return
	 * @throws DatabaseLoadDriverException
	 * @throws SQLException
	 * @throws DatabaseCredentialsException
	 */
	public boolean createDataBaseFromSqlScript(String filepath) throws SQLException;

	/**
	 * Update database
	 * 
	 * @throws SQLException
	 * @throws DaemonException 
	 * @throws ANoteException 
	 * @throws IOException
	 */
	public void updateDatabase(String updateFolder,String databaseversionfile) throws SQLException, ANoteException;

	/**
	 * Test if database exists
	 * 
	 * @return
	 * @throws DatabaseLoadDriverException
	 * @throws DatabaseCredentialsException
	 * @throws SQLException
	 */
	public boolean existDatabase() throws SQLException;

	/**
	 * GetDatabaseType
	 * 
	 * @return {@link DataBaseTypeEnum}
	 */
	public DataBaseTypeEnum getDataBaseType();

	/**
	 * Return if databse tables has fill in database
	 * 
	 * @return
	 * @throws DatabaseLoadDriverException
	 * @throws DatabaseCredentialsException
	 * @throws SQLException
	 */
	public boolean isfill() throws SQLException;

	/**
	 * Method that check if database table @param tableName exists
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 * @throws DatabaseLoadDriverException
	 */
	public boolean checkIfTableExists(String tableName) throws SQLException;

	/**
	 * Method that drop table from database. Be careful this method shoulbe be
	 * use only for experts
	 * 
	 * @param tablename
	 * @throws SQLException
	 * @throws DatabaseLoadDriverException
	 */
	public void dropTable(String tablename) throws SQLException;
}
