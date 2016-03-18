package com.silicolife.textmining.core.datastructures.dataaccess.database.convert;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class ConvertDatabaseException extends ANoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConvertDatabaseException(Exception e) {
		super(e);
	}

	public ConvertDatabaseException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}

	public ConvertDatabaseException(String errorMessage) {
		super(errorMessage);
	}
}
