package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.user;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.user.IGroup;
import com.silicolife.textmining.core.interfaces.core.user.IUser;
import com.silicolife.textmining.core.interfaces.core.user.IUserDataObject;
import com.silicolife.textmining.core.interfaces.core.utils.IGenericPair;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IUserPrivilegesAccess extends IPrivilegesAccess {

	/**
	 * 
	 * 
	 * @param user
	 * @return
	 * @throws ANoteException
	 */
	public void createUser(IUser user) throws ANoteException;

	/**
	 * 
	 * @param email
	 * @return
	 * @throws ANoteException
	 */
	public IUser getUserByEmail(String email) throws ANoteException;

	/**
	 * 
	 * 
	 * @param username
	 * @return
	 * @throws ANoteException
	 */
	public IUser getUserByUsername(String username) throws ANoteException;

	/**
	 * 
	 * 
	 * @param user
	 * @return
	 * @throws ANoteException
	 */
	public void updateUser(IUser user) throws ANoteException;

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws ANoteException
	 */
	public void removeUser(IUser user) throws ANoteException;

	/**
	 * 
	 * 
	 * @return
	 * @throws ANoteException
	 */
	public List<IGroup> getAllGroups() throws ANoteException;

	/**
	 * 
	 * 
	 * @return
	 * @throws ANoteException
	 */
	public List<IUser> getAllUsers() throws ANoteException;

	/**
	 * 
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @return
	 * @throws ANoteException
	 */
	public IUserDataObject getUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource) throws ANoteException;

	/**
	 * 
	 * 
	 * @param roles
	 * @return
	 * @throws ANoteException
	 */
	public Boolean hasPermissionRole(List<String> roles) throws ANoteException;

	/**
	 * 
	 * @param query
	 * @param permission
	 * @return
	 * @throws ANoteException
	 */
	public Boolean hasPermission(IQuery query, List<String> permission) throws ANoteException;
	
	
	/**
	 * 
	 * @param query
	 * @param permission
	 * @return
	 * @throws ANoteException
	 */
	public Boolean hasPermission(IResource<IResourceElement> resource, List<String> permission) throws ANoteException;
	
	/**
	 * 
	 * 
	 * @param corpus
	 * @param ownergrant
	 * @return
	 * @throws ANoteException 
	 */
	public Boolean hasPermission(ICorpus corpus,List<String> ownergrant) throws ANoteException;
	
	/**
	 * 
	 * 
	 * @param obj
	 * @param fullgrant
	 * @return
	 * @throws ANoteException 
	 */
	public Boolean hasPermission(IIEProcess obj, List<String> fullgrant) throws ANoteException;



	/**
	 * 
	 * @param resourceId
	 * @param resource
	 * @return
	 * @throws ANoteException
	 */
	public List<IGenericPair<IUser, String>> getUsersAndPermissions(Long resourceId, ResourcesTypeUtils resource) throws ANoteException;
}
