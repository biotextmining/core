package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.DataException;

public class PublicationManagerException extends DataException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PublicationManagerException(Exception e) {
		super(e);
	}

	public PublicationManagerException(String code, String errorMessage) {
		super(code, errorMessage);
	}
}
