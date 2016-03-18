package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.DataException;

public class ProcessException extends DataException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProcessException(Exception e) {
		super(e);
	}

	public ProcessException(String code, String errorMessage) {
		super(code, errorMessage);
	}

}
