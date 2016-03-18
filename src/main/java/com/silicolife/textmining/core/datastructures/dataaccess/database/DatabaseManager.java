package com.silicolife.textmining.core.datastructures.dataaccess.database;

import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;

public class DatabaseManager {
	private IDatabase database;
	
	public DatabaseManager(IDatabase database)
	{
		this.setDatabase(database);
	}

	public IDatabase getDatabase() {
		return database;
	}

	public void setDatabase(IDatabase database) {
		this.database = database;
	}
	
	public String toString()
	{
		return database.toString();
	}
}
