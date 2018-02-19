package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.DataException;

public class DataProcessStatusException extends DataException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataProcessStatusException(Exception e) {
		super(e);
	}

	public DataProcessStatusException(String code, String errorMessage) {
		super(code, errorMessage);
	}

}