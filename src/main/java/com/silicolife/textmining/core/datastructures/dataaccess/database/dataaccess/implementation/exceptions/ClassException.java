package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.DataException;

public class ClassException extends DataException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClassException(Exception e) {
		super(e);
	}

	public ClassException(String code, String errorMessage) {
		super(code, errorMessage);
	}

}
