package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.runserverprocesses;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

/**
 * 
 * Interface to represent processes that could be done in server side
 * 
 * @author Hugo Costa
 *
 */
public interface IRunServerProcesses {
	
	
	public Boolean runServerProcesses(String klass, String configuration) throws ANoteException;

}
