package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.DataException;

public class CorpusException extends DataException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CorpusException(Exception e) {
		super(e);
	}

	public CorpusException(String code, String errorMessage) {
		super(code, errorMessage);
	}
}
