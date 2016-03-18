package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.RestClient;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.CredentialsAccessException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;

public abstract class RestClientAccess {

	protected RestClient webClient;

	protected RestClientAccess(RestClient webClient) {
		this.webClient = webClient;
	}

	protected RestClientAccess() {
	}

	public void setRestClient(RestClient webClient) {
		this.webClient = webClient;
	}
	
	public void checkAndForceLoginIfNecessary() throws DaemonException{
		Date now = new Date();
		Date interationServer = webClient.getLastOperationTime();
		long duration  = now.getTime() - interationServer.getTime();
		long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		if(diffInMinutes + 5 > RestClient.sessiontimeout){
			try {
				DataAccessForceLogin.forceLogin();
			} catch (CredentialsAccessException e) {
				throw new DaemonException(e);
			}
		}
	}
}
