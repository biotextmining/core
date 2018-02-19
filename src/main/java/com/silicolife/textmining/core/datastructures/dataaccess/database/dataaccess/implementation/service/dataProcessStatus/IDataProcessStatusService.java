package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.dataProcessStatus;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.DataProcessStatusException;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;

public interface IDataProcessStatusService {
	
	public void addDataProcessStatus(IDataProcessStatus dataProcessStatus);
	
	public void updateDataProcessStatus(IDataProcessStatus dataProcessStatus) throws DataProcessStatusException;

	public List<IDataProcessStatus> getAllDataProcessStatus();

}
