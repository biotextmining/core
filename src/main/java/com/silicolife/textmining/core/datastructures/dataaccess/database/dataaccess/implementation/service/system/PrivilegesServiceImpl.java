package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.PrivilegesException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjectsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.utils.GenericPairImpl;
import com.silicolife.textmining.core.interfaces.core.user.IUser;
import com.silicolife.textmining.core.interfaces.core.user.IUserDataObject;
import com.silicolife.textmining.core.interfaces.core.utils.IGenericPair;

/**
 * 
 * 
 * 
 * 
 * @author Utilizador
 *
 */
@Service
@Transactional(readOnly = true)
public class PrivilegesServiceImpl implements PrivilegesService {

	private UsersManagerDao usersManagerDao;
	private UsersLogged userLogged;

	@Autowired
	public PrivilegesServiceImpl(UsersManagerDao usersManagerDao, UsersLogged userLogged) {
		this.usersManagerDao = usersManagerDao;
		this.userLogged = userLogged;
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean addPrivilege(Long userId, Long resourceId, String resource, String privilege) throws PrivilegesException {

		AuthUserDataObjectsId objectId = new AuthUserDataObjectsId(userId, resourceId, resource);
		AuthUserDataObjects dataObject = usersManagerDao.getAuthUserDataObjectsDao().findById(objectId);
		if (dataObject != null)
			throw new PrivilegesException(ExceptionsCodes.codePrivilegeExist, ExceptionsCodes.msgPrivilegeExist);

		dataObject = new AuthUserDataObjects(objectId, null, privilege);

		usersManagerDao.getAuthUserDataObjectsDao().save(dataObject);

		AuthUsers userLog = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(userLog, new Date(), "create", "auth_user_data_objects", null, "Add priviles to a resource");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean updatePrivilege(Long userId, Long resourceId, String resource, String privilege) throws PrivilegesException {

		AuthUserDataObjectsId objectId = new AuthUserDataObjectsId(userId, resourceId, resource);
		AuthUserDataObjects dataObject = usersManagerDao.getAuthUserDataObjectsDao().findById(objectId);
		if (dataObject == null)
			throw new PrivilegesException(ExceptionsCodes.codePrivilegeNoExist, ExceptionsCodes.msgPrivilegeNoExist);

		dataObject.setAudoPermission(privilege);
		usersManagerDao.getAuthUserDataObjectsDao().update(dataObject);

		AuthUsers userLog = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(userLog, new Date(), "update ", "auth_user_data_objects", null, "edit privile from a resource");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean removePrivilege(Long userId, Long resourceId, String resource) throws PrivilegesException {

		AuthUserDataObjectsId objectId = new AuthUserDataObjectsId(userId, resourceId, resource);
		AuthUserDataObjects dataObject = usersManagerDao.getAuthUserDataObjectsDao().findById(objectId);
		if (dataObject == null)
			throw new PrivilegesException(ExceptionsCodes.codePrivilegeNoExist, ExceptionsCodes.msgPrivilegeNoExist);

		usersManagerDao.getAuthUserDataObjectsDao().delete(dataObject);

		AuthUsers userLog = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(userLog, new Date(), "delete ", "auth_user_data_objects", null, "remove privile from a resource");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean removePrivilegeLoggedUser(Long resourceId, String resource) {

		AuthUsers userLog = userLogged.getCurrentUserLogged();

		AuthUserDataObjectsId objectId = new AuthUserDataObjectsId(userLog.getAuId(), resourceId, resource);
		AuthUserDataObjects dataObject = usersManagerDao.getAuthUserDataObjectsDao().findById(objectId);
		if (dataObject != null) {
			usersManagerDao.getAuthUserDataObjectsDao().delete(dataObject);
			AuthUserLogs log = new AuthUserLogs(userLog, new Date(), "delete ", "auth_user_data_objects", null, "remove privile from a resource");
			usersManagerDao.getAuthUserLogsDao().save(log);
		}

		return true;

	}

	@Override
	public IUserDataObject getPrivilege(Long userId, Long resourceId, String resource) {
		AuthUserDataObjectsId objectId = new AuthUserDataObjectsId(userId, resourceId, resource);
		IUserDataObject dataObject = usersManagerDao.getAuthUserDataObjectsDao().findById(objectId);
		return dataObject;
	}

	@Override
	public List<IGenericPair<IUser, String>> getUsersAndPermissions(Long resourceId, String resource) {

		List<IGenericPair<IUser, String>> list = new ArrayList<IGenericPair<IUser, String>>();
		List<AuthUsers> allUsers = usersManagerDao.getAuthUsersDao().findAll();
		for (AuthUsers user : allUsers) {
			AuthUserDataObjectsId objectId = new AuthUserDataObjectsId(user.getAuId(), resourceId, resource);
			AuthUserDataObjects dataObject = usersManagerDao.getAuthUserDataObjectsDao().findById(objectId);
			if (dataObject != null) {
				list.add(new GenericPairImpl<IUser, String>(user, dataObject.getAudoPermission()));
			} else {
				list.add(new GenericPairImpl<IUser, String>(user, null));
			}
		}
		return list;
	}

	@Override
	public Boolean hasPermission(Long resourceId, String resource, List<String> permission) {
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserDataObjectsId idDataObject = new AuthUserDataObjectsId(user.getAuId(), resourceId, resource);
		AuthUserDataObjects dataObject = usersManagerDao.getAuthUserDataObjectsDao().findById(idDataObject);
		if (dataObject == null || !permission.contains(dataObject.getAudoPermission())) {
			return false;
		}
		return true;
	}

}