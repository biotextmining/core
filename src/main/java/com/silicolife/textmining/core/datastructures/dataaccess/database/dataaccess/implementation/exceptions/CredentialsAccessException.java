package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class CredentialsAccessException extends ANoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;

	public CredentialsAccessException(Exception e) {
		super(e);
	}

	public CredentialsAccessException(String code, String errorMessage, Exception e) {
		super(errorMessage, e);
		this.code = code;
	}

	public CredentialsAccessException(String code, String errorMessage) {
		super(errorMessage);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
