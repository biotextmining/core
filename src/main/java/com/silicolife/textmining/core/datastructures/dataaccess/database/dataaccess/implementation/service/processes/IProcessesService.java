package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.processes;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ProcessException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcessStatistics;

/**
 * Interface to define all methods of Service layer about processes
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public interface IProcessesService {
	/**
	 * Create a new process
	 * 
	 * @param processes
	 * @return
	 */
	public Boolean createIEProcess(IIEProcess processes_);

	/**
	 * Update process
	 * 
	 * @param process
	 * @return
	 * @throws ProcessException 
	 */
	public Boolean updateIEProcess(IIEProcess processes_) throws ProcessException;

	/**
	 * Get process by id
	 * 
	 * @param id
	 * @return
	 * @throws ProcessException 
	 */
	public IIEProcess getProcessByID(Long id) throws ProcessException;

	/**
	 * Get Process Statistics
	 * 
	 * @param processID
	 * @return 
	 * @throws ProcessException 
	 */
	public IIEProcessStatistics getProcessStatistics(long processID) throws ProcessException;

	/**
	 * Get All Processes with Owner privilges or admin
	 * 
	 * @return
	 */
	public List<IIEProcess> getPrivilegesAllProcessesAdminAccess();
	
	public void setUserLogged(UsersLogged userLogged);
	
	public List<IIEProcess> getProcessesByPublicationId(Long publicationId) throws ProcessException;

}
