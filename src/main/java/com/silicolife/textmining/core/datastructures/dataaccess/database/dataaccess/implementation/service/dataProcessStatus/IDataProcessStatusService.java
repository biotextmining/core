package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.dataProcessStatus;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.DataProcessStatusException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;

public interface IDataProcessStatusService {
	
	public void addDataProcessStatus(IDataProcessStatus dataProcessStatus);
	
	public void updateDataProcessStatus(IDataProcessStatus dataProcessStatus) throws DataProcessStatusException;

	public List<IDataProcessStatus> getAllDataProcessStatus();

	public void setUserLogged(UsersLogged userLogged);

	public List<IDataProcessStatus> getUserDataProcessStatus();

	public List<IDataProcessStatus> getUserRecentDataProcessStatus();

	public List<IDataProcessStatus> getUserRecentDataProcessStatusSorted();

	public List<IDataProcessStatus> getUserRecentDataProcessStatusSortedWLimit(Integer paginationSize);

}
