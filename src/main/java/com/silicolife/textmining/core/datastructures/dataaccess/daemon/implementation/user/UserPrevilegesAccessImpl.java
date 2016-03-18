package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.user;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.utils.GenericPairImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.user.IUser;
import com.silicolife.textmining.core.interfaces.core.user.IUserDataObject;
import com.silicolife.textmining.core.interfaces.core.utils.IGenericPair;

/**
 * 
 * 
 * @author Utilizador
 *
 */
public class UserPrevilegesAccessImpl extends RestClientAccess {


	/**
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @param privilege
	 * @return
	 * @throws DaemonException
	 */
	public Boolean addPrivileges(Long userId, Long resourceId, String resource, String privilege) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {
		};
		MultiValueMap<String, String> variables = new LinkedMultiValueMap<String, String>();
		variables.add("userId", String.valueOf(userId));
		variables.add("resourceId", String.valueOf(resourceId));
		variables.add("resource", resource);
		variables.add("privilege", privilege);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("privileges/addPrivileges", responseType, variables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @param privilege
	 * @return
	 * @throws DaemonException
	 */
	public Boolean updatePrivileges(Long userId, Long resourceId, String resource, String privilege) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {
		};
		MultiValueMap<String, String> variables = new LinkedMultiValueMap<String, String>();
		variables.add("userId", String.valueOf(userId));
		variables.add("resourceId", String.valueOf(resourceId));
		variables.add("resource", resource);
		variables.add("privilege", privilege);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("privileges/updatePrivileges", responseType, variables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			return true;
		}
	}

	/**
	 * 
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @return
	 * @throws DaemonException
	 */
	public Boolean deletePrivilegesForUser(Long userId, Long resourceId, String resource) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {
		};
		MultiValueMap<String, String> variables = new LinkedMultiValueMap<String, String>();
		variables.add("userId", String.valueOf(userId));
		variables.add("resourceId", String.valueOf(resourceId));
		variables.add("resource", resource);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("privileges/deletePrivilegesForUser", responseType, variables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			return true;
		}
	}

	/**
	 * 
	 * 
	 * @param resourceId
	 * @param resource
	 * @return
	 * @throws DaemonException
	 */
	public Boolean deletePrivileges(Long resourceId, String resource) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {
		};
		MultiValueMap<String, String> variables = new LinkedMultiValueMap<String, String>();
		variables.add("resourceId", String.valueOf(resourceId));
		variables.add("resource", resource);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("privileges/deletePrivileges", responseType, variables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			return true;
		}
	}

	/**
	 * 
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @return
	 * @throws DaemonException
	 */
	public IUserDataObject getUserDataObjectPrivilege(Long userId, Long resourceId, String resource) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<AuthUserDataObjects>> responseType = new ParameterizedTypeReference<DaemonResponse<AuthUserDataObjects>>() {};

		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("userId", String.valueOf(userId));
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("resource", resource);
	
		ResponseEntity<DaemonResponse<AuthUserDataObjects>> response = webClient.get("privileges/getUserDataObjectPrivilege", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IUserDataObject userDataObject = response.getBody().getContent();
			return userDataObject;
		}
	}

	/**
	 * 
	 * @param resourceId
	 * @param resource
	 * @return
	 * @throws DaemonException
	 */
	public List<IGenericPair<IUser, String>> getUsersAndPrivilegers(Long resourceId, String resource) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ArrayList<GenericPairImpl<AuthUsers, String>>>> responseType = new ParameterizedTypeReference<DaemonResponse<ArrayList<GenericPairImpl<AuthUsers, String>>>>() {};

		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("resource", resource);

		ResponseEntity<DaemonResponse<ArrayList<GenericPairImpl<AuthUsers, String>>>> response = webClient.get("privileges/getUsersAndPrivilegers", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {

			List<IGenericPair<IUser, String>> returned = new ArrayList<IGenericPair<IUser, String>>();
			ArrayList<GenericPairImpl<AuthUsers, String>> usersAndPermissions = response.getBody().getContent();
			for (GenericPairImpl<AuthUsers, String> userPerm : usersAndPermissions) {
				IUser user = userPerm.getX();
				String permission = userPerm.getY();
				returned.add(new GenericPairImpl<IUser, String>(user, permission));
			}
			return returned;
		}
	}

	/**
	 * Check if user has permission
	 * 
	 * @param resourceId
	 * @param resource
	 * @param permissions
	 * @return
	 * @throws DaemonException
	 */
	public Boolean hasPermission(Long resourceId, String resource, List<String> permissions) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		Map<String, String> uriVariables = new LinkedHashMap<String, String>();
		uriVariables.put("resourceId", String.valueOf(resourceId));
		uriVariables.put("resource", resource);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("privileges/hasPermission", responseType, permissions, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean hasPermission = response.getBody().getContent();
			return hasPermission;
		}
	}
}
