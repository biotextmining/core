package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IResourcesService {
	
	
	/**
	 * 
	 * @param type
	 * @return
	 * @throws ResourcesExceptions
	 */
	public List<IResource<IResourceElement>> getResourcesByType(String type) throws ResourcesExceptions;

	/**
	 * 
	 * @param id
	 * @return
	 */
	public IResource<IResourceElement> getResourcesById(Long id);

	/**
	 * 
	 * @param resource
	 * @return
	 */
	public Boolean create(IResource<IResourceElement> resource_);

	/**
	 * 
	 * @param resource
	 * @return
	 * @throws ResourcesExceptions
	 */
	public Boolean update(IResource<IResourceElement> resource_) throws ResourcesExceptions;
	
	/**
	 * Get all resources existed in system. If "role_admin" all resources are
	 * returned. If has other role is returned the resources with owner permission
	 * 
	 * @return
	 */
	public List<IResource<IResourceElement>> getAllPrivilegesResourcesAdminAccess() throws ResourcesExceptions;

	public void setUserLogged(UsersLogged userLogged);

	public List<IResource<IResourceElement>> getResourcesByTypePaginated(String type, Integer paginationIndex,
			Integer paginationSize, boolean asc, String sortBy) throws ResourcesExceptions;

	public Integer countResourcesByType(String type) throws ResourcesExceptions;
}
