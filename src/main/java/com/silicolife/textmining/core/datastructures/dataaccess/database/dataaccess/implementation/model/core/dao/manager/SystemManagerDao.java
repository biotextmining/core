package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Versions;

public class SystemManagerDao {
	
	private GenericDao<Versions> versionsDao;

	@Autowired
	public SystemManagerDao(GenericDao<Versions> versionsDao) {
		this.setVersionsDao(versionsDao);
	}

	public GenericDao<Versions> getVersionsDao() {
		return versionsDao;
	}

	public void setVersionsDao(GenericDao<Versions> versionsDao) {
		this.versionsDao = versionsDao;
	}



}
