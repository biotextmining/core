package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.DataException;

public class AnnotationException extends DataException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AnnotationException(Exception e) {
		super(e);
	}

	public AnnotationException(String code, String errorMessage) {
		super(code, errorMessage);
	}

}
