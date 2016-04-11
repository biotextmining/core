package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.runserverprocesses;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.process.IConfiguration;

/**
 * Class which implements all run server processes daemon access methods
 * 
 * @author Joel Azevedo Costa
 *
 */
public class RunSercerProcessesAccessImpl extends RestClientAccess {

	public RunSercerProcessesAccessImpl() {
		super();
	}

	
	/**
	 * Run processes according to IConfigurations
	 * 
	 * @param resource (They are datatypes)
	 * @return
	 * @throws DaemonException
	 */
	public Boolean runSercerProcesses(IConfiguration configuration) throws DaemonException {
		checkAndForceLoginIfNecessary();
		
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("runserverprocesses/configuration",responseType, configuration);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}	
	


}