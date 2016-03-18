package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.publicationmanager;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IQueryPrivilegesAccess {
	
	/**
	 * Get queries from user (Admin) and ownership queries
	 * 
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public List<IQuery> getPrivilegesAllQueriesAdminAccess() throws ANoteException;
	
	/**
	 * Get Resources from user (admin) and ownership resources
	 * 
	 * @return
	 * @throws ANoteException
	 */
	public List<IResource<IResourceElement>> getPrivilegesAllResourcesAdminAccess() throws ANoteException;
}
