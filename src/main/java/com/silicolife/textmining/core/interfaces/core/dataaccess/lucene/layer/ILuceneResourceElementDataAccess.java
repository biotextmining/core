package com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;

public interface ILuceneResourceElementDataAccess {
	
	//  find resource element by term
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByExactTermUsingLucene(String term) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByExactTermFromResourceUsingLucene(String term, IResource<IResourceElement> resource) throws ANoteException;
	
	//  find resource element by partial term
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByPartialTermUsingLucene(String term) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermUsingLucenePaginated(String term, int index, int paginationSize) throws ANoteException;
	
	//  find resource element by partial term paginated
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByPartialTermFromResourceUsingLucene(String partialTerm, IResource<IResourceElement> resource) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermFromResourceUsingLucenePaginated(String partialTerm, IResource<IResourceElement> resource, int index, int paginationSize) throws ANoteException;
	
	// find resource element by synonym
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByExactSynonymUsingLucene(String synonym) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByExactSynonymFromResourceUsingLucene(String synonym, IResource<IResourceElement> resource) throws ANoteException;
	
	// find resource element by partial synonym
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByPartialSynonymUsingLucene(String synonym) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByPartialSynonymFromResourceUsingLucene(String partialSynonym, IResource<IResourceElement> resource) throws ANoteException;

	// find resource element by partial synonym paginated
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonymUsingLucenePaginated(String synonym, int index, int paginationSize) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonymFromResourceUsingLucenePaginated(String partialSynonym, IResource<IResourceElement> resource, int index, int paginationSize) throws ANoteException;
	
	// find resource element by external id
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByExactExternalIdUsingLucene(String externalId) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByExactExternalIdFromResourceUsingLucene(String externalId, IResource<IResourceElement> resource) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByExactExternalIdFromSourceUsingLucene(String externalId, ISource source) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByExactExternalIdFromResourceAndSourceUsingLucene(String externalId, IResource<IResourceElement> resource, ISource source) throws ANoteException;
	
	// find resource element by partial synonym
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByPartialExternalIdUsingLucene(String partialExternalId) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByPartialExternalIdFromResourceUsingLucene(String partialExternalId, IResource<IResourceElement> resource) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByPartialExternalIdFromSourceUsingLucene(String partialExternalId, ISource source) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getAllResourceElementsByPartialExternalIdFromResourceAndSourceUsingLucene(String partialExternalId, IResource<IResourceElement> resource, ISource source) throws ANoteException;

	// find resource element by partial synonym paginated
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIdUsingLucenePaginated(String partialExternalId, int index, int paginationSize) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIdFromResourceUsingLucenePaginated(String partialExternalId, IResource<IResourceElement> resource, int index, int paginationSize) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIdFromSourceUsingLucenePaginated(String partialExternalId, ISource source, int index, int paginationSize) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIdFromResourceAndSourceUsingLucenePaginated(String partialExternalId, IResource<IResourceElement> resource, ISource source, int index, int paginationSize) throws ANoteException;

	
	
}
