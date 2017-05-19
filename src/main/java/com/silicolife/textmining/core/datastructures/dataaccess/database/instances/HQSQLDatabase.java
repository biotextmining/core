package com.silicolife.textmining.core.datastructures.dataaccess.database.instances;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.silicolife.textmining.core.datastructures.dataaccess.database.ADatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;

public class HQSQLDatabase extends ADatabase implements IDatabase{
	
	private Statement stmt;

	public HQSQLDatabase(String host, String port, String schema, String user,
			String pass) {
		super(host, port, schema, user, pass,DataBaseTypeEnum.MYSQL);
		this.setDriverClassName("org.hsqldb.jdbcDriver");
		this.setDialectClassName("org.hibernate.dialect.HSQLDialect");
	}

	@Override
	public Connection getNeWConnection(){
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
		String url_db_connection = "jdbc:hsqldb:file:" + getSchema();
		try {
			return DriverManager.getConnection(url_db_connection, this.getUser(), this.getPwd());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void openConnection() throws SQLException {
		if(!isLoadDriver())
		{
			try {
				this.loadDriver();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setLoadDriver(true);
		}
		String url_db_connection = "jdbc:hsqldb:file:" + getSchema();
		this.setConnection(DriverManager.getConnection(url_db_connection, this.getUser(), this.getPwd()));
	}

	@Override
	public boolean existDatabase() {
		File file = new File(getSchema());
		return file.exists();
	}
	
	public boolean createDataBase()
	{
		File file = new File(getSchema());
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void updateDatabase(String updateFolder,String databaseversionfile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean createDataBaseFromSqlScript(String sqlscript)
			throws SQLException {
        getStmt().execute(sqlscript);
		return false;
	}
	

 
    
	public void closeConnection() throws SQLException {
		if(getConnection()!=null)
		{
			getStmt().execute("SHUTDOWN");
            getConnection().close();
		}
	}

	@Override
	public void shutDownDatabase() throws SQLException {
        if (getConnection() != null) {
        	if(getConnection().isClosed())
        	{
        		openConnection();
            	getConnection().createStatement().execute("SHUTDOWN COMPACT");
                getConnection().close();
        	}

        }		
	}
	
    private Statement getStmt() throws SQLException{
    	if(stmt==null)
    		stmt = getConnection().createStatement();
		return stmt;
	}
    
	@Override
	public boolean isfill() throws SQLException {
		File file = new File(getSchema());
		if(file.exists())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void fillDataBaseTables(String schemaFile)
			throws SQLException,
			FileNotFoundException, IOException {
		
	}

	@Override
	public String getDataBaseURL() {
		return "jdbc:hsqldb:file:" + getSchema();
	}
}
