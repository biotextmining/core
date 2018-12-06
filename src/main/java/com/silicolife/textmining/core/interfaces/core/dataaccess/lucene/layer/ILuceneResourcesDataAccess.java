package com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface ILuceneResourcesDataAccess {
	
	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties) throws ANoteException;
	
	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuth(ISearchProperties searchProperties, int index,
			int paginationSize) throws ANoteException;

	public Integer countActiveResourcesFromSearch(ISearchProperties searchProperties) throws ANoteException;

	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties, String permission) throws ANoteException;

	public List<IResource<IResourceElement>> getActiveResourcesFromSearchPaginatedWSort(ISearchProperties searchProperties,
			int index, int paginationSize, boolean asc, String sortBy) throws ANoteException;

	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuthAndSort(ISearchProperties searchProperties,
			int index, int paginationSize, boolean asc, String sortBy) throws ANoteException;

	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuthAndSort(ISearchProperties searchProperties,
			int index, int paginationSize, boolean asc, String sortBy, String permission) throws ANoteException;

	public List<IResource<IResourceElement>> getPrivilegesResourcesAdminAccessFromSearchPaginated(
			ISearchProperties searchProperties, Integer paginationIndex, Integer paginationSize, boolean asc,
			String sortBy) throws ANoteException;

	public Integer countPrivilegesResourcesAdminAccessFromSearch(ISearchProperties searchProperties) throws ANoteException;

}
