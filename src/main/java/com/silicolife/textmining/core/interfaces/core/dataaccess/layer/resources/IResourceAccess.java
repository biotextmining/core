package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources;

import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;

public interface IResourceAccess extends IResourceElementAccess{

	/**
	 * Method to create a new resource
	 * 
	 * @param resource
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void createResource(IResource<IResourceElement> resource) throws ANoteException;
	
	
	/**
	 * MEthod to update resource
	 * 
	 * @param resource
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void updateResource(IResource<IResourceElement> resource) throws ANoteException;
	
	/**
	 * MEthod to inactivate resource
	 * 
	 * @param resource
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void inativeResource(IResource<IResourceElement> resource) throws ANoteException;
	
	/**
	 * Remove resource from user
	 * 
	 * @param resource
	 * @throws ANoteException
	 */
	public void removeResource(IResource<IResourceElement> resource) throws ANoteException;
		
	/**
	 * MEthod to return resource by type
	 * 
	 * @param type
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public List<IResource<IResourceElement>> getResourcesByType(String type)throws ANoteException;;
	
	/**
	 * Get Resources By ID
	 * 
	 * @param resourceID
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public IResource<IResourceElement> getResourceByID(long resourceID) throws ANoteException;
	
	/**
	 * Get Class content for a resource
	 * 
	 * @param resource
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public Set<IAnoteClass> getResourceClassContent(IResource<IResourceElement> resource) throws ANoteException;
	
	/**
	 * Test if resource has elements
	 * 
	 * @param resource
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public boolean checkResourceHasElements(IResource<IResourceElement> resource) throws ANoteException;
	
	/**
	 * Get Resource Content and main 
	 * 
	 * @return
	 * @throws ANoteException
	 */
	public IResourceContent getResourceContent(IResource<IResourceElement> resource) throws ANoteException;
}
