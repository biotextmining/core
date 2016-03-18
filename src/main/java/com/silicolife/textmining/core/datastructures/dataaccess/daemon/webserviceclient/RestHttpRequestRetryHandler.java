package com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;

public class RestHttpRequestRetryHandler implements HttpRequestRetryHandler {

	private int maxTry = 4; 
	@Override
	public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
		if (executionCount >= maxTry) {
			return false;
		}

		if (exception instanceof NoHttpResponseException) {
			return true;
		}
		if (exception instanceof ConnectException) {
			return true;
		}
		
		if (exception instanceof SocketTimeoutException) {
			return true;
		}
		
		if (exception instanceof SocketException) {
			return true;
		}
		

		Boolean b = (Boolean) context.getAttribute(HttpCoreContext.HTTP_REQ_SENT);
		boolean sent = (b != null && b.booleanValue());
		if (!sent) {
			return true;
		}
		return false;
	}
	
	public void setMaxTry(int maxTry) {
		this.maxTry = maxTry;
	}
}
