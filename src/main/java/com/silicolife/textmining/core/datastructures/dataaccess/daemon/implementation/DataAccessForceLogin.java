package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.CredentialsAccessException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class DataAccessForceLogin {

	public static void forceLogin() throws CredentialsAccessException {
		try {
			InitConfiguration.getDataAccess().logout();
			InitConfiguration.getDataAccess();
		} catch (ANoteException e) {
			throw new CredentialsAccessException(ExceptionsCodes.codeWrongCredentials, e.getMessage(), e);
		}
	}
}
