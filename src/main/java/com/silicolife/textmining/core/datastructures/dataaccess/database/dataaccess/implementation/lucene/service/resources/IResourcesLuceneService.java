package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IResourcesLuceneService {

	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties);

	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuth(ISearchProperties searchProperties, int index,
			int paginationSize);

	public void setUserLogged(UsersLogged userLogged);

	public Integer countActiveResourcesFromSearch(ISearchProperties searchProperties);

	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties, String permission);

	public List<IResource<IResourceElement>> getActiveResourcesFromSearchPaginatedWSort(ISearchProperties searchProperties,
			int index, int paginationSize, boolean asc, String sortBy);

	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuthAndSort(ISearchProperties searchProperties,
			int index, int paginationSize, boolean asc, String sortBy);

	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuthAndSort(ISearchProperties searchProperties,
			int index, int paginationSize, boolean asc, String sortBy, String permission);

	public List<IResource<IResourceElement>> getPrivilegesResourcesAdminAccessFromSearchPaginated(
			ISearchProperties searchProperties, Integer paginationIndex, Integer paginationSize, boolean asc,
			String sortBy);

	public Integer countPrivilegesResourcesAdminAccessFromSearch(ISearchProperties searchProperties);

}
