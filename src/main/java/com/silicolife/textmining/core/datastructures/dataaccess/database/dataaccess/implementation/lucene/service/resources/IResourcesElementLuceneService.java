package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementsFilter;

public interface IResourcesElementLuceneService {
	
	// find by exact term
	
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTerm(String term);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactTerm(IResourceElementsFilter filter, String term) throws ResourcesExceptions;
	
	// find by partial string in term
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTerm(String partialString);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTerm(IResourceElementsFilter filter, String partialString) throws ResourcesExceptions;
	
	// find by partial string in term paginated
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermPaginated(String partialString, int index, int paginationSize);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTermPaginated(IResourceElementsFilter filter, String partialString, int index, int paginationSize) throws ResourcesExceptions;
	
	public Integer getResourceElementsCountByPartialTerm(String partialString);
	
	public Integer getResourceElementsFilteredCountByPartialTerm(IResourceElementsFilter filter, String partialString) throws ResourcesExceptions;
	
	// find by exact synonym
	
	public IResourceElementSet<IResourceElement> getResourceElementsByExactSynonym(String synonym);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactSynonym(IResourceElementsFilter filter, String term) throws ResourcesExceptions;
	
	// find by partial string in synonym

	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonym(String partialString);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonym(IResourceElementsFilter filter, String partialString) throws ResourcesExceptions;
	
	// find by partial string in synonym paginated
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonymPaginated(String partialString, int index, int paginationSize);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonymPaginated(IResourceElementsFilter filter, String partialString, int index, int paginationSize) throws ResourcesExceptions;
	
	public Integer getResourceElementsCountByPartialSynonym(String partialString);
	
	public Integer getResourceElementsFilteredCountByPartialSynonym(IResourceElementsFilter filter, String partialString) throws ResourcesExceptions;
	
	// find by exact external id
	
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalID(String externalId);

	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactExternalID(IResourceElementsFilter filter, String externalId) throws ResourcesExceptions;
	
	// find by partial string in external id
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalID(String partialString);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalID(IResourceElementsFilter filter, String partialString) throws ResourcesExceptions;
	
	// find by partial string in external id paginated
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIDPaginated(String partialString, int index, int paginationSize);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalIDPaginated(IResourceElementsFilter filter, String partialString, int index, int paginationSize) throws ResourcesExceptions;
	
	public Integer getResourceElementsCountByPartialExternalID(String partialString);
	
	public Integer getResourceElementsFilteredCountByPartialExternalID(IResourceElementsFilter filter, String partialString) throws ResourcesExceptions;
	
	public void setUserLogged(UsersLogged userLogged);
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermOrPartialSynonymPaginated(String partialString, int index, int paginationSize);

	public Integer getResourceElementsCountByPartialTermOrPartialSynonym(String partialString);

}
