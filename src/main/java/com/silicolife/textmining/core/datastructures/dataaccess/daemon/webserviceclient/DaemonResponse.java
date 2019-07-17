package com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.deserializes.DaemonContentJsonDeserializer;


public class DaemonResponse<T> {

	//@JsonDeserialize(using=DaemonContentJsonDeserializer.class)
	private T content;
	private ExceptionInfo exception;

	public DaemonResponse(T content, ExceptionInfo exception) {
		this.content = content;
		this.exception = exception;
	}

	public DaemonResponse(T content) {
		this.content = content;
	}

	public DaemonResponse() {
	}

	public T getContent() {
		return content;
	}

	public ExceptionInfo getException() {
		return exception;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public void setException(ExceptionInfo exception) {
		this.exception = exception;
	}
}
