package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.system;

public interface ISystemService {
	
	public int getDataversion();

	public void addDatabaseVersion(int newversion, String commments);

}
