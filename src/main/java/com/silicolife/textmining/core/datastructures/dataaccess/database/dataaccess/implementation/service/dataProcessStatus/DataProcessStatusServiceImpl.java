package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.dataProcessStatus;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.DataProcessStatusException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.DataProcessStatusManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.DataProcessStatus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.general.DataProcessStatusWrapper;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;

@Service
@Transactional(readOnly = true)
public class DataProcessStatusServiceImpl implements IDataProcessStatusService {
	
	final static Logger creationLogger = LoggerFactory.getLogger(DataProcessStatus.class);
	
	private DataProcessStatusManagerDao dataProcessStatusManagerDao;
	private UsersManagerDao usersManagerDao;
	private UsersLogged userLogged;
	
	@Autowired
	public DataProcessStatusServiceImpl(DataProcessStatusManagerDao dataProcessStatusManagerDao,
			UsersManagerDao usersManagerDao, UsersLogged userLogged) {
		super();
		this.dataProcessStatusManagerDao = dataProcessStatusManagerDao;
		this.usersManagerDao = usersManagerDao;
		this.userLogged = userLogged;
	}

	@Override
	public void addDataProcessStatus(IDataProcessStatus dataProcessStatus_) {		
		DataProcessStatus dataProcessStatus = DataProcessStatusWrapper.convertToDaemonStructure(dataProcessStatus_);
		this.dataProcessStatusManagerDao.getDataProcessStatusDao().save(dataProcessStatus);
	}

	@Override
	public void updateDataProcessStatus(IDataProcessStatus dataProcessStatus) throws DataProcessStatusException {
		DataProcessStatus dataProcess = this.dataProcessStatusManagerDao.getDataProcessStatusDao().findById(dataProcessStatus.getId());
		if(dataProcess==null)
			throw new DataProcessStatusException(ExceptionsCodes.codeNoDataProcessStatus,ExceptionsCodes.msgNoDataProcessStatus);
		dataProcess.setDpsFinishDate(dataProcessStatus.getFinishDate());
		dataProcess.setDpsUpdateDate(dataProcessStatus.getUpdateDate());
		dataProcess.setDpsProgress(dataProcessStatus.getProgress());
		dataProcess.setDpsReport(dataProcessStatus.getReport());
		dataProcess.setDpsStatus(dataProcessStatus.getStatus().name());
		this.dataProcessStatusManagerDao.getDataProcessStatusDao().saveOrUpdate(dataProcess);		
	}

	@Override
	public List<IDataProcessStatus> getAllDataProcessStatus() {
		List<IDataProcessStatus> out = new ArrayList<>();
		List<DataProcessStatus> dataProcessesStatusInDatabase = this.dataProcessStatusManagerDao.getDataProcessStatusDao().findAll();
		for(DataProcessStatus dataProcessStatusInDatabase:dataProcessesStatusInDatabase)
		{
			out.add(DataProcessStatusWrapper.convertToAnoteStructure(dataProcessStatusInDatabase));
		}
		return out;
	}



}
