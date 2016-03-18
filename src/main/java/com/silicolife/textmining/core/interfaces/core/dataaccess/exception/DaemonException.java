package com.silicolife.textmining.core.interfaces.core.dataaccess.exception;

public class DaemonException extends ANoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	public DaemonException(Exception e) {
		super(e);
	}

	public DaemonException(String code, String errorMessage) {
		super(errorMessage);
		this.code = code;
	}

	public DaemonException(String code, String errorMessage, Exception e) {
		super(errorMessage, e);
		this.code = code;
	}

	public DaemonException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}

	public DaemonException(String errorMessage) {
		super(errorMessage);
	}

	public String getCode() {
		return code;
	}
}
