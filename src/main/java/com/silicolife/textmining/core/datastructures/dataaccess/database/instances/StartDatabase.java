package com.silicolife.textmining.core.datastructures.dataaccess.database.instances;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.silicolife.textmining.core.interfaces.core.dataaccess.database.DataBaseTypeEnum;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;

public class StartDatabase implements IDatabase {

	public StartDatabase() {
		super();
	}

	@Override
	public Connection getNeWConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createDataBase() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateDatabase() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existDatabase() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openConnection() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDriverClassName(String driverName) {
		// TODO Auto-generated method stub

	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSchema() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPwd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataBaseTypeEnum getDataBaseType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isfill() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public String toString() {
		return new String();
	}

	@Override
	public boolean createDataBaseFromSqlScript(String filepath) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shutDownDatabase() throws  SQLException {

	}

	@Override
	public boolean checkIfTableExists(String tableName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dropTable(String tablename) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void fillDataBaseTables(String schemaFile) throws  SQLException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDataBaseURL() {
		// TODO Auto-generated method stub
		return null;
	}

}
