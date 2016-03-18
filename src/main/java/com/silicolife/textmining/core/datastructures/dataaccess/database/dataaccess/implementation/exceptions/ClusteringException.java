package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.DataException;

public class ClusteringException extends DataException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClusteringException(Exception e) {
		super(e);
	}

	public ClusteringException(String code, String errorMessage) {
		super(code, errorMessage);
	}

}
