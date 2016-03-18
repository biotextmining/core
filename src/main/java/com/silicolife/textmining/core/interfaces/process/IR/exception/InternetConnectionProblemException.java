package com.silicolife.textmining.core.interfaces.process.IR.exception;


public class InternetConnectionProblemException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exception e;
	private Object object;
	
	public InternetConnectionProblemException(Exception e)
	{
		super(e);
		this.setE(e);
	}
	
	public InternetConnectionProblemException(Exception e,Object object)
	{
		super(e);
		this.setE(e);
		this.setObject(object);
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
