package com.silicolife.textmining.core.datastructures.dataaccess.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.queriessql.QueriesGeneral;
import com.silicolife.textmining.core.datastructures.dataaccess.database.schema.DatabaseTablesName;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;


public abstract class ADatabase implements IDatabase {

	private String host;
	private String port;
	private String schema;
	private String user;
	private String pwd;
	private Connection connection;
	private String driverClassName;
	private String dialectClassName;
	public static final long timeout = 10000000;
	public static long starttime = 0;
	private DataBaseTypeEnum databaseType;
	public static String databaseSplitFields = "|";
	
	private boolean loadDriver = false;

	
	public ADatabase(String host, String port, String schema, String user, String pass,DataBaseTypeEnum databseType) {
		this.host = host;
		this.port = port;
		this.schema = schema;
		this.user = user;
		this.pwd = pass;
		this.databaseType = databseType;
		this.connection=null;
	}

	public abstract void openConnection() throws SQLException;
	
	protected void loadDriver() throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{	
			Class.forName(this.getDriverClassName()).newInstance();				
	}
	
	public boolean equals(Object database)
	{
		if(database == null)
			return false;
		if(database instanceof IDatabase)
		{
			IDatabase db = (IDatabase) database;
			if(!databaseType.equals(db.getDataBaseType()))
			{
				return false;
			}
			else if(!host.equals(db.getHost()))
			{
				return false;
			}
			else if(!port.equals(db.getPort()))
			{
				return false;
			}
			else if(!schema.equals(db.getSchema()))
			{
				return false;
			}
			else if(!user.equals(db.getUser()))
			{
				return false;
			}
			else if(!pwd.equals(db.getPwd()))
			{
				return false;
			}
			return true;
		}
		else
			return false;
	}

	public void closeConnection() throws SQLException{

	}
	
	public boolean equals(ADatabase session){
		if(this.host.compareTo(session.getHost())==0
				&& this.port.compareTo(session.getPort())==0
				&& this.schema.compareTo(session.getSchema())==0
				&& this.user.compareTo(session.getUser())==0
				&& this.pwd.compareTo(session.getPwd())==0
			)
		{
			return true;
		}
		return false;
	}

	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append("database" + databaseSplitFields);
		str.append(this.databaseType.toString() + databaseSplitFields);
		str.append(this.host + databaseSplitFields);
		str.append(this.port + databaseSplitFields);
		str.append(this.schema + databaseSplitFields);
		str.append(this.user + databaseSplitFields);
		str.append(this.pwd);
		return str.toString();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Connection getConnection() throws SQLException {
		if(connection==null || connection.isClosed())
		{
			this.openConnection();
			starttime = GregorianCalendar.getInstance().getTimeInMillis();
		} else{
			try {
				if(connection.prepareStatement("")==null)
				{
					this.openConnection();
				}
			} catch (SQLException e) {
//				e.printStackTrace();
			}

		}
		long timenow = GregorianCalendar.getInstance().getTimeInMillis();
		if(timenow-starttime>timeout)
		{
			starttime = GregorianCalendar.getInstance().getTimeInMillis();
			this.openConnection();
		}
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	
	public String getDialectClassName() {
		return dialectClassName;
	}
	
	public void setDialectClassName(String dialectClassName){
		this.dialectClassName = dialectClassName;
	}
	
	public DataBaseTypeEnum getDataBaseType()
	{
		return this.databaseType;
	}
	
	@Override
	public boolean isfill() throws SQLException {
			PreparedStatement fillDatabase = getConnection().prepareStatement(QueriesGeneral.showDatabaseListOfTable);
			fillDatabase.setString(1, getSchema());
			ResultSet rs = fillDatabase.executeQuery();
			Set<String> listOfTables = new HashSet<String>();
			while(rs.next())
			{
				listOfTables.add(rs.getString(1));
			}
			if(listOfTables.contains(DatabaseTablesName.resources)
					&& listOfTables.contains(DatabaseTablesName.version)
					&& listOfTables.contains(DatabaseTablesName.publicationsTable))
			{
				fillDatabase.close();
				rs.close();
				return true;
			}
			else
			{
				fillDatabase.close();
				rs.close();
				return false;
			}
	}

	public boolean isLoadDriver() {
		return loadDriver;
	}

	public void setLoadDriver(boolean loadDriver) {
		this.loadDriver = loadDriver;
	}
	
    /**
     * Check if a table already exists
     * @param tableName
     * @return: true if exists
     * @throws DatabaseLoadDriverException 
     * @throws SQLException 
     */
	public boolean checkIfTableExists(String tableName) throws SQLException {

		DatabaseMetaData meta = getConnection().getMetaData();
		ResultSet rs = meta.getTables(null, null, null, new String[]{"TABLE"});
		while (rs.next()) {
			String Name = rs.getString("TABLE_NAME");
			if(Name.contentEquals(tableName)||Name.contentEquals(tableName.toUpperCase())){
				rs.close();
				return true ;
			}
		}
		rs.close();
		return false;
	}
	
    public void dropTable(String tablename) throws SQLException{
    	getConnection().createStatement().executeUpdate("DROP TABLE "+tablename +" IF EXISTS");
    }
}
