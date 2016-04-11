package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.runserverprocesses;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.process.IConfiguration;

/**
 * 
 * Interface to represent processes that could be done in server side
 * 
 * @author Hugo Costa
 *
 */
public interface IRunServerProcesses {
	
	
	public Boolean runServerProcesses(IConfiguration configuration) throws ANoteException;

}
