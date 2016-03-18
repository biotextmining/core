package com.silicolife.textmining.core.datastructures.exceptions;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

/**
 * Classe that implements a Query Exception
 * 
 * @author Hugo Costa
 * 
 * @version 1.0 (16 Junho 2009)
 *
 */
public class QueryException extends ANoteException {

	private static final long serialVersionUID = -4574335219818517049L;

	public QueryException(Exception e) {
		super(e);
	}

	public QueryException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}

	public QueryException(String errorMessage) {
		super(errorMessage);
	}
}
