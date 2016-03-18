package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.DataException;

public class UserExceptions extends DataException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserExceptions(Exception e) {
		super(e);
	}

	public UserExceptions(String code, String errorMessage) {
		super(code, errorMessage);
	}
}
