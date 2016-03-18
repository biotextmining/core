package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.user;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.PermissionsUtilsEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.user.IUser;

/**
 * 
 * 
 * @author Utilizador
 *
 */
public interface IPrivilegesAccess {
	/**
	 * 
	 * @param user
	 * @param resourceId
	 * @param resource
	 * @param privilege
	 * @return
	 * @throws ANoteException
	 */
	public void addUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource, PermissionsUtilsEnum privilege) throws ANoteException;
	/**
	 * 
	 * @param user
	 * @param resourceId
	 * @param Resource
	 * @param privilege
	 * @return
	 * @throws ANoteException
	 */
	public void updateUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils Resource, PermissionsUtilsEnum privilege) throws ANoteException;

	/**
	 * 
	 * @param user
	 * @param resourceId
	 * @param resource
	 * @return
	 * @throws ANoteException
	 */
	public void removeUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource) throws ANoteException;
}
