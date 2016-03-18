package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.DataException;

public class HyperLinkMenuException extends DataException{


	private static final long serialVersionUID = 1L;

	public HyperLinkMenuException(Exception e) {
		super(e);
	}
	
	public HyperLinkMenuException(String code, String errorMessage) {
		super(code, errorMessage);
	}

}
