package com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient;

import java.io.Serializable;

/**
 * Class to represent the exception. It is sent in JSON format
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ExceptionInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private String rootCause;
	private String otherInfo;

	public ExceptionInfo() {

	}

	public ExceptionInfo(String code, String message, String rootCause) {
		this.code = code;
		this.message = message;
		this.rootCause = rootCause;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRootCause() {
		return rootCause;
	}

	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getCompletedMessage() {
		String newLine = System.getProperty("line.separator");
		StringBuilder builder = new StringBuilder();
		if (message != null) {
			builder.append(message);
			builder.append(newLine);
		}

		if (rootCause != null) {
			builder.append(rootCause);
			builder.append(newLine);
		}

		if (otherInfo != null) {
			builder.append(otherInfo);
			builder.append(newLine);
		}

//		if (builder.length() > 0)
//			builder.delete(builder.length() - 1, builder.length());

		return builder.toString();
	}
}
