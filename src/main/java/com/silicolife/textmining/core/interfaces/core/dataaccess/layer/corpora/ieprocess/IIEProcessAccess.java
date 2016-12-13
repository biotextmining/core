package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.corpora.ieprocess;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcessStatistics;

public interface IIEProcessAccess extends IIEStatisticsAccess,IIEProcessPrivilegesAccess{


	/**
	 * Create a new process
	 * 
	 * @param process
	 * @param processType
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void createIEProcess(IIEProcess processe) throws ANoteException;


	/**
	 * Update process information
	 * 
	 * @param process
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void updateIEProcess(IIEProcess process) throws ANoteException;
	
	/**
	 * Inactivate IE Process
	 * 
	 * @param process
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void inactivateProcess(IIEProcess process) throws ANoteException;
	
	/**
	 * Get Process given ID
	 * 
	 * @param processID
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public IIEProcess getProcessByID(long processID) throws ANoteException;
	
	/**
	 * Get IEProcess Statitics
	 * 
	 * @param ieProcessImpl
	 * @return
	 * @throws ANoteException 
	 */
	public IIEProcessStatistics getIEProcessStatistics(IIEProcess ieProcessImpl) throws ANoteException;

	public List<IIEProcess> getProcessesByPublication(IPublication publication) throws ANoteException;

}
