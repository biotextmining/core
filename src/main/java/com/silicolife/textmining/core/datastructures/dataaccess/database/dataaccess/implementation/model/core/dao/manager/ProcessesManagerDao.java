package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.ProcessesAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessOrigins;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;

@Repository
public class ProcessesManagerDao {

	private GenericDao<Processes> processesDao;
	private GenericDao<ProcessTypes> processTypesDao;
	private GenericDao<ProcessOrigins> processOriginsDao;
	private GenericDao<ProcessProperties> processPropertiesDao;
	private ProcessesAuxDao processAuxDao;

	@Autowired
	public ProcessesManagerDao(GenericDao<Processes> processesDao, GenericDao<ProcessTypes> processTypesDao, GenericDao<ProcessOrigins> processOriginsDao,
			GenericDao<ProcessProperties> processPropertiesDao,ProcessesAuxDao processAuxDao) {
		super();
		this.processesDao = processesDao;
		this.processTypesDao = processTypesDao;
		this.processOriginsDao = processOriginsDao;
		this.processPropertiesDao = processPropertiesDao;
		this.processAuxDao = processAuxDao;
	}

	public GenericDao<Processes> getProcessesDao() {
		return processesDao;
	}

	public GenericDao<ProcessTypes> getProcessTypesDao() {
		return processTypesDao;
	}

	public GenericDao<ProcessOrigins> getProcessOriginsDao() {
		return processOriginsDao;
	}

	public GenericDao<ProcessProperties> getProcessPropertiesDao() {
		return processPropertiesDao;
	}
	
	public ProcessesAuxDao getProcessesAuxDao()
	{
		return processAuxDao;
	}
}
