package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.users;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.CredentialsAccessException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.UserExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.user.IGroup;
import com.silicolife.textmining.core.interfaces.core.user.IUser;

/**
 * 
 * 
 * 
 * @author Utilizador
 *
 */
public interface IUserService {

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Boolean checkLogin(String username, String password);

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws ANoteException 
	 */
	public IUser login(String username, String password) throws CredentialsAccessException;

	/**
	 * 
	 * @param roleCodes
	 * @return
	 */
	public Boolean hasPermissionRole(List<String> roleCodes);

	/**
	 * 
	 * 
	 * @return
	 */
	public List<IGroup> getAllGroups();

	/**
	 * 
	 * 
	 * @return
	 */
	public List<AuthUsers> getAllUsers();

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public IUser getById(Long id);

	/**
	 * 
	 * @param email
	 * @return
	 */
	public IUser getByEmail(String email);

	/**
	 * 
	 * @param username
	 * @return
	 */
	public IUser getByUsername(String username);

	/**
	 * 
	 * 
	 * @param user
	 * @return
	 */
	public Boolean createUser(IUser user);

	/**
	 * 
	 * 
	 * @param user
	 * @return
	 */
	public Boolean updateUser(IUser user);

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws ANoteException 
	 */
	public Boolean removeUser(Long id) throws UserExceptions;
	

	public Boolean saveProperties(Properties properties);

	
	public Properties loadProperties(Set<String> propertiesIdentifiers);
	
	public void setUserLogged(UsersLogged userLogged);

}
