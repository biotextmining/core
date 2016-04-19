package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.DataException;

public class RunServerProcessesException extends DataException {

	private static final long serialVersionUID = 1L;

	public RunServerProcessesException(Exception e) {
		super(e);
	}

	public RunServerProcessesException(String code, String errorMessage) {
		super(code, errorMessage);
	}

}
