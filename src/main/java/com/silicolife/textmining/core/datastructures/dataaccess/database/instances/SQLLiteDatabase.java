package com.silicolife.textmining.core.datastructures.dataaccess.database.instances;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.silicolife.textmining.core.datastructures.dataaccess.database.ADatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;

public class SQLLiteDatabase extends ADatabase implements IDatabase{

	
	
	public SQLLiteDatabase(String host, String port, String schema, String user, String pwd)
	{
		super(host,port,schema,user,pwd,DataBaseTypeEnum.MYSQL);
		this.setDriverClassName("org.sqlite.JDBC");
		this.setDialectClassName("org.hibernate.dialect.SQLiteDialect");
	}
	
	
	public void openConnection()
	{
		if(!isLoadDriver())
		{
			try {
				this.loadDriver();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setLoadDriver(true);
		}
		try {
				String url_db_connection = "jdbc:sqlite:" + this.getSchema();
				this.setConnection(DriverManager.getConnection(url_db_connection));
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}


	public Connection getNeWConnection() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean createDataBase() {
		return false;
		
	}


	@Override
	public boolean existDatabase() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void updateDatabase(String updateFolder,String databaseversionfile) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean createDataBaseFromSqlScript(String filepath)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shutDownDatabase() throws SQLException {
		super.closeConnection();
		
	}


	@Override
	public void fillDataBaseTables(String schemaFile)
			throws SQLException,
			FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getDataBaseURL() {
		return "jdbc:sqlite:" + this.getSchema();
	}

}
