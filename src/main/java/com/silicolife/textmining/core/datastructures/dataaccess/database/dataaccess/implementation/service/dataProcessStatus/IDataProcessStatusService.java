package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.dataProcessStatus;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.DataProcessStatusException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.DataProcessStatus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ProcessStatusResourceTypesEnum;
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

	public List<IDataProcessStatus> getAdminPrivilegesProcessStatusByTypeSortedPaginated(ProcessStatusResourceTypesEnum type,
			Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);

	public Integer countAdminPrivilegesProcessStatusByType(ProcessStatusResourceTypesEnum type);

	public IDataProcessStatus getDataProcessStatusById(int id);

	public List<IDataProcessStatus> getAdminPrivilegesProcessStatusSortedPaginated(Integer paginationIndex,
			Integer paginationSize, boolean asc, String sortBy);

	public Integer countAdminPrivilegesProcessStatus();

	public Integer countRunningOrStartDataProcessStatusforUser();

	public List<IDataProcessStatus> getUserRecentDataProcessStatusSortedOfTypeWLimit(ProcessStatusResourceTypesEnum type,
			Integer paginationSize);

	public List<IDataProcessStatus> getUserRecentDataProcessStatusSortedOfType(ProcessStatusResourceTypesEnum type);

}
