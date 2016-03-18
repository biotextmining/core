package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao;

import java.io.Serializable;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;

/**
 * Manage the user logged in the system
 * 
 * @author Joel Azevedo Costa
 *
 */
public class UsersLoggedImpl implements UsersLogged, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AuthUsers currentUserLogged;
	private final String separator = "\t";

	public UsersLoggedImpl() {
	}

	public UsersLoggedImpl(AuthUsers currentUserLogged) {
		this.currentUserLogged = currentUserLogged;
	}

	public UsersLoggedImpl(String propKey) {
		this.currentUserLogged = getUserByProperties(propKey);
	}

	public UsersLoggedImpl(String username, String password) {
		this.currentUserLogged = getUserByCredentials(username, password);
	}

	private AuthUsers getUserByCredentials(String username, String password) {
		AuthUsers user = new AuthUsers();
		user.setAuUsername(username);
		user.setAuPassword(password);
		return user;
	}

	private AuthUsers getUserByProperties(String propkey) {
		String[] userCredentials = propkey.split(separator);
		return getUserByCredentials(userCredentials[0], userCredentials[1]);
	}

	/**
	 * return the user logged
	 * 
	 * @return
	 */
	@Override
	public AuthUsers getCurrentUserLogged() {
		return currentUserLogged;
	}

	@Override
	public void setCurrentUserLogged(AuthUsers currentUserLogged) {
		this.currentUserLogged = currentUserLogged;
	}

	@Override
	public String toString() {
		if (currentUserLogged == null) {
			return "";
		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append(currentUserLogged.getAuUsername());
			buffer.append(separator);
			buffer.append(currentUserLogged.getAuPassword());

			return buffer.toString();
		}
	}
}
