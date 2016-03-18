package com.silicolife.textmining.core.interfaces.core.dataaccess.layer;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public interface IUpdateAccess {
	
	public int getDatabaseVersion() throws ANoteException;
	
	public boolean addDatabaseVersion(int version, String commments) throws ANoteException;

}
