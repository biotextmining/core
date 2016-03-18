package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.general;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.general.AnoteClass;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

/**
 * Class which implements all classes daemon access methods
 * 
 * @author Joel Azevedo Costa
 *
 */
public class ClassesAccessImpl extends RestClientAccess {

	public ClassesAccessImpl() {
		super();
	}


	/**
	 * Create a new class
	 * 
	 * @param classes
	 * @return
	 * @throws DaemonException
	 */
	public Boolean updateClass(IAnoteClass classes) throws DaemonException {
		checkAndForceLoginIfNecessary();
		
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.put("classes/updateClass", responseType, classes);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
	
	/**
	 * Create a new class
	 * 
	 * @param classes
	 * @return
	 * @throws DaemonException
	 */
	public Boolean insertNewClass(IAnoteClass classes) throws DaemonException {
		checkAndForceLoginIfNecessary();
		
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("classes/insertNewClass", responseType, classes);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
	
	/**
	 * Get all Classes
	 * 
	 * @return
	 * @throws DaemonException
	 */
	public Set<IAnoteClass> getClasses() throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Set<AnoteClass>>> responseType = new ParameterizedTypeReference<DaemonResponse<Set<AnoteClass>>>() {};
		ResponseEntity<DaemonResponse<Set<AnoteClass>>> response = webClient.get("classes/getClasses", responseType);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			
			Set<IAnoteClass> iClasses = new HashSet<IAnoteClass>();
			Set<AnoteClass> classes = response.getBody().getContent();
			
			for (AnoteClass iClass : classes) {
				iClasses.add(iClass);
			}

			return iClasses;
		}
	}
	
	/**
	 * Get class by id
	 * 
	 * @param classId
	 * @return
	 * @throws DaemonException
	 */
	public IAnoteClass getClassById(Long classId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("classId", classId);
		
		ParameterizedTypeReference<DaemonResponse<AnoteClass>> responseType = new ParameterizedTypeReference<DaemonResponse<AnoteClass>>() {};
		ResponseEntity<DaemonResponse<AnoteClass>> response = webClient.get("classes/getClasses", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			
			AnoteClass anoteClass = response.getBody().getContent();
			return anoteClass;
		}
	}
}
