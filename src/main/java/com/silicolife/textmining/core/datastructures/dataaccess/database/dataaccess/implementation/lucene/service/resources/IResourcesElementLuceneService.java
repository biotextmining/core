package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;

public interface IResourcesElementLuceneService {
	
	// find by exact term
	
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTerm(String term);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactTerm(String term, Long resourceId) throws ResourcesExceptions;
	
	// find by partial string in term
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTerm(String partialString);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTerm(String partialString, Long resourceId) throws ResourcesExceptions;
	
	// find by partial string in term paginated
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermPaginated(String partialString, int index, int paginationSize);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTermPaginated(String partialString, Long resourceId, int index, int paginationSize) throws ResourcesExceptions;
	
	public Integer getCountResourceElementsByPartialTerm(String partialString);
	
	public Integer getCountResourceElementsFromResourceByPartialTerm(String partialString, Long resourceId) throws ResourcesExceptions;
	
	// find by exact synonym
	
	public IResourceElementSet<IResourceElement> getResourceElementsByExactSynonym(String synonym);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactSynonym(String term, Long resourceId) throws ResourcesExceptions;
	
	// find by partial string in synonym

	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonym(String partialString);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonym(String partialString, Long resourceId) throws ResourcesExceptions;
	
	// find by partial string in synonym paginated
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonymPaginated(String partialString, int index, int paginationSize);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonymPaginated(String partialString, Long resourceId, int index, int paginationSize) throws ResourcesExceptions;
	
	public Integer getCountResourceElementsByPartialSynonym(String partialString);
	
	public Integer getCountResourceElementsFromResourceByPartialSynonym(String partialString, Long resourceId) throws ResourcesExceptions;
	
	// find by exact external id
	
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalID(String externalId);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByExactExternalID(String externalId, Long sourceId) throws ResourcesExceptions;
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactExternalID(String externalId, Long resourceId) throws ResourcesExceptions;
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceAndSourceByExactExternalID(String externalId, Long sourceId, Long resourceId) throws ResourcesExceptions;
	
	// find by partial string in external id
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalID(String partialString);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByPartialExternalID(String partialString, Long sourceId) throws ResourcesExceptions;
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalID(String partialString, Long resourceId) throws ResourcesExceptions;
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceAndSourceByPartialExternalID(String partialString, Long sourceId, Long resourceId) throws ResourcesExceptions;
	
	// find by partial string in external id paginated
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIDPaginated(String partialString, int index, int paginationSize);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByPartialExternalIDPaginated(String partialString, Long sourceId, int index, int paginationSize) throws ResourcesExceptions;
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalIDPaginated(String partialString, Long resourceId, int index, int paginationSize) throws ResourcesExceptions;
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceAndSourceByPartialExternalIDPaginated(String partialString, Long sourceId, Long resourceId, int index, int paginationSize) throws ResourcesExceptions;
	
	public Integer getCountResourceElementsByPartialExternalID(String partialString);
	
	public Integer getCountResourceElementsFromSourceByPartialExternalID(String partialString, Long sourceId) throws ResourcesExceptions;
	
	public Integer getCountResourceElementsFromResourceByPartialExternalID(String partialString, Long resourceId) throws ResourcesExceptions;
	
	public Integer getCountResourceElementsFromResourceAndSourceByPartialExternalID(String partialString, Long sourceId, Long resourceId) throws ResourcesExceptions;
	
	public void setUserLogged(UsersLogged userLogged);
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermOrPartialSynonymPaginated(String partialString, int index, int paginationSize);

	public Integer getResourceElementsCountByPartialTermOrPartialSynonym(String partialString);

}
