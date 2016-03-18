package com.silicolife.textmining.core.datastructures.exceptions.process.manualcuration;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class ApplyManualCurationToSchemaException extends ANoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplyManualCurationToSchemaException(Exception e) {
		super(e);
	}

	public ApplyManualCurationToSchemaException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}

	public ApplyManualCurationToSchemaException(String errorMessage) {
		super(errorMessage);
	}
}
