package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroups;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.user.IGroup;
import com.silicolife.textmining.core.interfaces.core.user.IUser;

/**
 * Implementation of User Access Operations
 * 
 * @author Utilizador
 *
 */
public class UserAccessImpl extends RestClientAccess {


	/**
	 * Login to daemon server operation
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws DaemonException
	 */
	public void logout() throws DaemonException {
		ResponseEntity<DaemonResponse<Object>> response = webClient.logout();
		if (response.getStatusCode() != HttpStatus.OK && response.getStatusCode() != HttpStatus.FOUND) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		}
	}
	
	
	/**
	 * Login to daemon server operation
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws DaemonException
	 */
	public IUser login(String username, String password) throws DaemonException {
		ResponseEntity<DaemonResponse<AuthUsers>> response = webClient.login(username, password);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			return response.getBody().getContent();
		}
	}

	/**
	 *  Only validate credentials. Nothing more.
	 *  
	 * @param username
	 * @param password
	 * @return
	 * @throws DaemonException
	 */
	public Boolean checkLogin(String username, String password) throws DaemonException {
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {
		};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("username", username);
		uriVariables.add("password", password);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("users/checkLogin", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			return response.getBody().getContent();
		}
	}

	/**
	 * Get all groups from the daemon server
	 * 
	 * @return
	 * @throws DaemonException
	 */
	public List<IGroup> getAllGroups() throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<AuthGroups>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<AuthGroups>>>() {
		};
		ResponseEntity<DaemonResponse<List<AuthGroups>>> response = webClient.get("users/getAllGroups", responseType);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {

			List<IGroup> returned = new ArrayList<IGroup>();
			List<AuthGroups> groups = response.getBody().getContent();
			for (AuthGroups igroup : groups) {
				returned.add(igroup);
			}
			return returned;
		}
	}

	/**
	 * Get all users
	 * 
	 * @return
	 * @throws DaemonException
	 */
	public List<IUser> getAllUsers() throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<AuthUsers>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<AuthUsers>>>() {
		};
		ResponseEntity<DaemonResponse<List<AuthUsers>>> response = webClient.get("users/getAllUsers", responseType);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			List<IUser> returned = new ArrayList<IUser>();
			List<AuthUsers> users = response.getBody().getContent();
			for (AuthUsers iuser : users) {
				returned.add(iuser);
			}
			return returned;
		}
	}

	/**
	 * Get user by email
	 * 
	 * @param email
	 * @return
	 * @throws DaemonException
	 */
	public IUser getUserByEmail(String email) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<AuthUsers>> responseType = new ParameterizedTypeReference<DaemonResponse<AuthUsers>>() {
		};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("email", email);

		ResponseEntity<DaemonResponse<AuthUsers>> response = webClient.post("users/getUserByEmail", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IUser user = response.getBody().getContent();
			return user;
		}
	}

	/**
	 * Get user by username
	 * 
	 * @param username
	 * @return
	 * @throws DaemonException
	 */
	public IUser getUserByUsername(String username) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<AuthUsers>> responseType = new ParameterizedTypeReference<DaemonResponse<AuthUsers>>() {
		};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("username", username);

		ResponseEntity<DaemonResponse<AuthUsers>> response = webClient.post("users/getUserByUsername", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IUser user = response.getBody().getContent();
			return user;
		}
	}

	/**
	 * Create a new user
	 * 
	 * @param user
	 * @return
	 * @throws DaemonException
	 */
	public Boolean createUser(IUser user) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {
		};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("users/createUser", responseType, user);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Update an user
	 * 
	 * @param user
	 * @return
	 * @throws DaemonException
	 */
	public Boolean updateUser(IUser user) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {
		};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.put("users/updateUser", responseType, user);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Delete an user
	 * 
	 * @param userId
	 * @return
	 * @throws DaemonException
	 */
	public Boolean deleteUser(Long userId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {
		};
		MultiValueMap<String, String> variables = new LinkedMultiValueMap<String, String>();
		variables.add("userId", String.valueOf(userId));

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("users/updateUser", responseType, variables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * 
	 * @param rolesCodes
	 * @return
	 * @throws DaemonException
	 */
	public Boolean hasPermissionRole(List<String> rolesCodes) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {
		};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("users/hasPermissionRole", responseType, rolesCodes);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
	
	
	/**
	 * Load properties from user
	 * 
	 * @param propertiesIdentifiers
	 * @return
	 * @throws DaemonException
	 */
	public Properties loadProperties(Set<String> propertiesIdentifiers) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Properties>> responseType = new ParameterizedTypeReference<DaemonResponse<Properties>>() {};
		ResponseEntity<DaemonResponse<Properties>> response = webClient.post("users/loadProperties", responseType, propertiesIdentifiers);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Properties properties = response.getBody().getContent();
			return properties;
		}
	}
	
	
	/**
	 * Save properties from user
	 * 
	 * @param properties
	 * @return
	 * @throws DaemonException
	 */
	public Boolean saveProperties(Properties properties) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("users/saveProperties", responseType, properties);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
}
