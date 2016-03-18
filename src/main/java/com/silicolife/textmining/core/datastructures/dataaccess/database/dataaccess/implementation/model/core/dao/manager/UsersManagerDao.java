package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroups;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserSettings;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;

/**
 * Class to handler with AuthUsers object DAO
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Repository
public class UsersManagerDao {

	private GenericDao<AuthUsers> authUsersDao;
	private GenericDao<AuthUserLogs> authUserLogsDao;
	private GenericDao<AuthUserDataObjects> authUserDataObjects;
	private GenericDao<AuthGroups> authGroup;
	private GenericDao<AuthUserSettings> authSettings;

	@Autowired
	public UsersManagerDao(GenericDao<AuthUsers> authUsersDao, GenericDao<AuthUserLogs> authUserLogsDao, GenericDao<AuthUserDataObjects> authUserDataObjects,
			GenericDao<AuthGroups> authGroup,GenericDao<AuthUserSettings> authSettings) {
		this.authUsersDao = authUsersDao;
		this.authUserLogsDao = authUserLogsDao;
		this.authUserDataObjects = authUserDataObjects;
		this.authGroup = authGroup;
		this.authSettings = authSettings;
	}

	public GenericDao<AuthUsers> getAuthUsersDao() {
		return authUsersDao;
	}

	public GenericDao<AuthUserLogs> getAuthUserLogsDao() {
		return authUserLogsDao;
	}

	public GenericDao<AuthUserDataObjects> getAuthUserDataObjectsDao() {
		return authUserDataObjects;
	}

	public GenericDao<AuthGroups> getAuthGroup() {
		return authGroup;
	}

	public GenericDao<AuthUserSettings> getAuthSettings() {
		return authSettings;
	}
}