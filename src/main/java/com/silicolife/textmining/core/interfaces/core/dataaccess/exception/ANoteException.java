package com.silicolife.textmining.core.interfaces.core.dataaccess.exception;

public class ANoteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exception e;

	public ANoteException(Exception e) {
		super(e);
		this.e = e;
	}

	public ANoteException(String errorMessage, Exception e) {
		super(errorMessage, e);
		this.e = e;
	}

	public ANoteException(String errorMessage) {
		super(errorMessage);
	}

	public Exception getExeption() {
		return e;
	}

}
