package com.silicolife.textmining.core.datastructures.exceptions;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;


/**
 * Class that implements a Pubmed Search Exception
 */
public class PubmedException extends ANoteException{

	private static final long serialVersionUID = 1L;

	public PubmedException(Exception e) {
		super(e);
	}

	public PubmedException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}

	public PubmedException(String errorMessage) {
		super(errorMessage);
	}	
}
