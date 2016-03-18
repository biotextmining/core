package com.silicolife.textmining.core.datastructures.exceptions.evaluation;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class EvaluationException extends ANoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EvaluationException(Exception e) {
		super(e);
	}

	public EvaluationException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}

	public EvaluationException(String errorMessage) {
		super(errorMessage);
	}
}
