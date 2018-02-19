package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.runserverprocesses;

import java.util.Date;
import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;
import com.silicolife.textmining.core.interfaces.core.user.IUser;

public interface IDataProcessStatusAcess {
	
	
	public void addDataProcessStatus(IDataProcessStatus dataprocessStatus) throws ANoteException;
	
	public void updateDataProcessStatus(IDataProcessStatus dataProcessStatus) throws ANoteException;
	
	public List<IDataProcessStatus> getAllDataProcessStatus() throws ANoteException;
	
	public List<IDataProcessStatus> getDataProcessStatusByUserAndDateRange(IUser user,Date startDateRange,Date endDateRange) throws ANoteException;


}
