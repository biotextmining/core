package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.DataProcessStatusAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.DataProcessStatus;

@Repository
public class DataProcessStatusManagerDao {
	
	private GenericDao<DataProcessStatus> dataProcessStatusDao;
	
	private DataProcessStatusAuxDao dataProcessStatusAuxDao;
	
	@Autowired
	public DataProcessStatusManagerDao(GenericDao<DataProcessStatus> dataProcessStatusDao,
			DataProcessStatusAuxDao dataProcessStatusAuxDao) {
		super();
		this.dataProcessStatusDao = dataProcessStatusDao;
		this.dataProcessStatusAuxDao = dataProcessStatusAuxDao;
	}

	public GenericDao<DataProcessStatus> getDataProcessStatusDao() {
		return dataProcessStatusDao;
	}

	public void setDataProcessStatusDao(GenericDao<DataProcessStatus> dataProcessStatusDao) {
		this.dataProcessStatusDao = dataProcessStatusDao;
	}

	public DataProcessStatusAuxDao getDataProcessStatusAuxDao() {
		return dataProcessStatusAuxDao;
	}

	public void setDataProcessStatusAuxDao(DataProcessStatusAuxDao dataProcessStatusAuxDao) {
		this.dataProcessStatusAuxDao = dataProcessStatusAuxDao;
	}
	
	
	
	
}
