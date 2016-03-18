package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

/**
 * Anote2Daemon exceptions
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 */
public class DataException extends ANoteException {

	private static final long serialVersionUID = 1L;
	private String code;

	public DataException(Exception e) {
		super(e);
	}

	public DataException(String code, String errorMessage) {
		super(errorMessage);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
