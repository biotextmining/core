package com.silicolife.textmining.core.datastructures.dataaccess;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.DaemonAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.DaemonFactory;
import com.silicolife.textmining.core.datastructures.dataaccess.database.DatabaseAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.database.DatabaseFactory;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.server.ServerAccess;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;
import com.silicolife.textmining.core.interfaces.core.dataaccess.IDataAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.daemon.IDaemon;
import com.silicolife.textmining.core.interfaces.core.dataaccess.database.IDatabase;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class DataAccessManager {

	private IDataAccess current;

	public DataAccessManager(String propKey) {
		this.current = selectDataAccess(propKey);
	}

	public DataAccessManager(IDaemon daemon) {
		this.current = new DaemonAccess(daemon);
	}

	public DataAccessManager(IDatabase database) {
		String hibernateFilePath = GlobalOptions.hibernateConfigurationFile;
		this.current = new DatabaseAccess(database,hibernateFilePath);
	}

	public DataAccessManager() {
		current = new DataAccessStart();
	}

	private IDataAccess selectDataAccess(String propkey) {
		String[] values = propkey.split("\\|");
		String typeOfConnection = values[0];
		if (typeOfConnection.equals("daemon")) {
			
			IDaemon daemon = DaemonFactory.createDaemonConfigurations(propkey);
			return new DaemonAccess(daemon);
			
		} else if (typeOfConnection.equals("database")) {
			
			String hibernateFilePath = GlobalOptions.hibernateConfigurationFile;
			IDatabase database = DatabaseFactory.createDatabaseManager(propkey).getDatabase();
			return new DatabaseAccess(database,hibernateFilePath);
			
		} else if (typeOfConnection.equals("server")){
			
			return new ServerAccess();
			
		}
		return null;
	}

	public IDataAccess getCurrent() {
		return current;
	}

	@Override
	public String toString() {
		if (current != null)
			return current.toString();

		return "";
	}

	public void setUsersLogged(UsersLogged userLogged) throws ANoteException {
		current.setUser(userLogged);
	}
}
