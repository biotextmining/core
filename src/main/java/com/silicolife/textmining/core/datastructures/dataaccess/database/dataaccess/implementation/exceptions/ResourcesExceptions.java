package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.DataException;

public class ResourcesExceptions extends DataException {

	private static final long serialVersionUID = 1L;

	public ResourcesExceptions(Exception e) {
		super(e);
	}

	public ResourcesExceptions(String code, String errorMessage) {
		super(code, errorMessage);
	}
}