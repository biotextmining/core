package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;

public interface IResourcesElementLuceneService {
	
	// find by exact term
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByExactTerm(String term);
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsFromResourceByExactTerm(String term, Long resourceId) throws ResourcesExceptions;
	
	// find by partial string in term
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByPartialTerm(String partialString);
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsFromResourceByPartialTerm(String partialString, Long resourceId) throws ResourcesExceptions;
	
	// find by partial string in term paginated
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermPaginated(String partialString, int index, int paginationSize);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTermPaginated(String partialString, Long resourceId, int index, int paginationSize) throws ResourcesExceptions;
	
	// find by exact synonym
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByExactSynonym(String synonym);
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsFromResourceByExactSynonym(String term, Long resourceId) throws ResourcesExceptions;
	
	// find by partial string in synonym

	public IResourceElementSet<IResourceElement> getAllResourceElementsByPartialSynonym(String partialString);
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsFromResourceByPartialSynonym(String partialString, Long resourceId) throws ResourcesExceptions;
	
	// find by partial string in synonym paginated
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonymPaginated(String partialString, int index, int paginationSize);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonymPaginated(String partialString, Long resourceId, int index, int paginationSize) throws ResourcesExceptions;
	
	// find by exact external id
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByExactExternalID(String externalId);
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsFromSourceByExactExternalID(String externalId, Long sourceId) throws ResourcesExceptions;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsFromResourceByExactExternalID(String externalId, Long resourceId) throws ResourcesExceptions;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsFromResourceAndSourceByExactExternalID(String externalId, Long sourceId, Long resourceId) throws ResourcesExceptions;
	
	// find by partial string in external id
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByPartialExternalID(String partialString);
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsFromSourceByPartialExternalID(String partialString, Long sourceId) throws ResourcesExceptions;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsFromResourceByPartialExternalID(String partialString, Long resourceId) throws ResourcesExceptions;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsFromResourceAndSourceByPartialExternalID(String partialString, Long sourceId, Long resourceId) throws ResourcesExceptions;
	
	// find by partial string in external id paginated
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIDPaginated(String partialString, int index, int paginationSize);
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByPartialExternalIDPaginated(String partialString, Long sourceId, int index, int paginationSize) throws ResourcesExceptions;
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalIDPaginated(String partialString, Long resourceId, int index, int paginationSize) throws ResourcesExceptions;
	
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceAndSourceByPartialExternalIDPaginated(String partialString, Long sourceId, Long resourceId, int index, int paginationSize) throws ResourcesExceptions;

}
