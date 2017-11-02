package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.system;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.PrivilegesException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.user.IUser;
import com.silicolife.textmining.core.interfaces.core.user.IUserDataObject;
import com.silicolife.textmining.core.interfaces.core.utils.IGenericPair;

/**
 * 
 * 
 * @author Utilizador
 *
 */
public interface IPrivilegesService {
	/**
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @param privilege
	 * @return
	 * @throws ANoteException 
	 */
	public Boolean addPrivilege(Long userId, Long resourceId, String resource, String privilege) throws PrivilegesException;

	/**
	 * 
	 * @param userId
	 * @param resourceId
	 * @param newResource
	 * @param privilege
	 * @return
	 * @throws ANoteException 
	 */
	public Boolean updatePrivilege(Long userId, Long resourceId, String resource, String privilege) throws PrivilegesException;

	/**
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @return
	 * @throws PrivilegesException 
	 */
	public Boolean removePrivilege(Long userId, Long resourceId, String resource) throws PrivilegesException;

	/**
	 * 
	 * @param resourceId
	 * @param resource
	 * @return
	 */
	public Boolean removePrivilegeLoggedUser(Long resourceId, String resource);

	/**
	 * 
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @return
	 */
	public IUserDataObject getPrivilege(Long userId, Long resourceId, String resource);

	/**
	 * 
	 * @param resourceId
	 * @param resource
	 * @return
	 */
	public List<IGenericPair<IUser, String>> getUsersAndPermissions(Long resourceId, String resource);

	/**
	 * 
	 * @param resourceId
	 * @param resource
	 * @param permission
	 * @return
	 */
	public Boolean hasPermission(Long resourceId, String resource, List<String> permission);
	
	public void setUserLogged(UsersLogged userLogged);

	public List<IGenericPair<IUser, String>> getUsersAndPermissionsPaginated(Long resourceId, String resource,
			Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

	public Integer countUsersAndPermissions(Long resourceId, String resource);
}
