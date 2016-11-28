package com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;


/**
 * Interface with methods for using with Hibernate search (Lucene)
 * To do the lucene index use InitConfiguration.getDataAccess().rebuildLuceneIndex();
 *
 */
public interface ILuceneResourceElementDataAccess {
	
	/**
	 * Get Resource Element from all Resources with exact term match in primary term
	 * 
	 * @param term
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTerm(String term) throws ANoteException;
	
	
	/**
	 * Get Resource Element from @param resource with exact term match in primary term
	 * 
	 * @param term
	 * @param resource
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactTerm(IResource<IResourceElement> resource,String term) throws ANoteException;
	
	
	
	/**
	 * Get Resource Element from all Resources with partial or exact term match in primary term
	 * 
	 * @param term
	 * @return
	 * @throws ANoteException
	 * 
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTerm(String term) throws ANoteException;
	
	/**
	 * Get Resource Element from all Resources with partial or exact term match in primary term sliced by pages
	 * 
	 * @param term
	 * @param index -  page number
	 * @param paginationSize - pagination size
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermPaginated(String term, int index, int paginationSize) throws ANoteException;
	
	/**
	 * Get Resource Element from @param resource with partial or exact term match in primary term
	 * 
	 * @param partialTerm
	 * @param resource
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTerm(IResource<IResourceElement> resource,String partialTerm) throws ANoteException;

	/**
	 * Get Resource Element from @param resource with exact term match in primary term sliced by pages
	 * 
	 * @param partialTerm
	 * @param resource
	 * @param index -  page number
	 * @param paginationSize - pagination size
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTermPaginated(IResource<IResourceElement> resource,String partialTerm,  int index, int paginationSize) throws ANoteException;

	/**
	 * Get Resource Element from all Resource with exact synonym match in primary term
	 * 
	 * @param synonym
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByExactSynonym(String synonym) throws ANoteException;
	
	/**
	 * Get Resource Element from @param resource with exact synonym match in primary term
	 * 
	 * @param synonym
	 * @param resource
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactSynonym(IResource<IResourceElement> resource,String synonym) throws ANoteException;
	
	/**
	 * Get Resource Element from all Resource with partial or exact synonym match in primary term
	 * 
	 * @param synonym
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonym(String synonym) throws ANoteException;
	
	/**
	 * Get Resource Element from all Resource with partial or exact synonym match in primary term sliced by pages 
	 * 
	 * @param synonym
	 * @param index -  page number
	 * @param paginationSize - pagination size
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonymPaginated(String synonym, int index, int paginationSize) throws ANoteException;
	
	/**
	 * Get Resource Element from @param resource with partial or exact synonym match in primary term
	 * 
	 * @param partialSynonym
	 * @param resource
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonym(IResource<IResourceElement> resource,String partialSynonym) throws ANoteException;

	/**
	 * Get Resource Element from @param resource with partial or exact synonym match in primary term sliced by pages
	 * 
	 * @param partialSynonym
	 * @param resource
	 * @param index
	 * @param paginationSize
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonymPaginated(String partialSynonym, IResource<IResourceElement> resource, int index, int paginationSize) throws ANoteException;
	
	/**
	 * Get Resource Element with exact external identifier in any source
	 * 
	 * @param externalId
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalId(String externalId) throws ANoteException;
	
	/**
	 * Get Resource Element from @param resource with exact external identifier in any source
	 * 
	 * @param externalId
	 * @param resource
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactExternalId(String externalId, IResource<IResourceElement> resource) throws ANoteException;
	
	/**
	 * Get Resource Element from all resources with exact external identifier in @param source
	 * 
	 * @param externalId
	 * @param source
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalIdFromSource(String externalId, ISource source) throws ANoteException;
	
	
	/**
	 * Get Resource Element from @param resource with exact external identifier in @param source
	 * 
	 * @param externalId
	 * @param resource
	 * @param source
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactExternalIdAndSource(IResource<IResourceElement> resource, ISource source,String externalId) throws ANoteException;
	
	/**
	 * Get Resource Element with exact or partial external identifier in any source
	 * 
	 * @param externalId
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalId(String partialExternalId) throws ANoteException;
	
	/**
	 * Get Resource Element from @param resource with exact external identifier in any source
	 * 
	 * @param externalId
	 * @param resource
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalId( IResource<IResourceElement> resource,String partialExternalId) throws ANoteException;
	
	/**
	 * Get Resource Element from @param resource with exact or similar external identifier in @param source
	 * 
	 * @param partialExternalId
	 * @param source
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByPartialExternalId(String partialExternalId, ISource source) throws ANoteException;
	
	/**
	 * Get Resource Element from @param resource with exact external identifier in @param source
	 * 
	 * @param externalId
	 * @param resource
	 * @param source
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalIdAndSource(IResource<IResourceElement> resource, ISource source,String partialExternalId) throws ANoteException;

	/**
	 * Get Resource Element with exact or partial external identifier in any source sliced by pages
	 * 
	 * @param externalId
	 * @return
	 * @throws ANoteException
	 */	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIdPaginated(String partialExternalId, int index, int paginationSize) throws ANoteException;
	
	/**
	 * Get Resource Element from @param resource with exact external identifier in any source sliced by pages
	 * 
	 * @param externalId
	 * @param resource
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalIdPaginated(IResource<IResourceElement> resource,String partialExternalId , int index, int paginationSize) throws ANoteException;
	
	/**
	 * Get Resource Element from @param resource with exact or similar external identifier in @param source sliced by pages
	 * 
	 * @param partialExternalId
	 * @param source
	 * @param index
	 * @param paginationSize
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIdFromSourcePaginated(ISource source,String partialExternalId,  int index, int paginationSize) throws ANoteException;
	
	/**
	 * Get Resource Element from @param resource with exact external identifier in @param source sliced by pages
	 * 
	 * @param partialExternalId
	 * @param resource
	 * @param source
	 * @param index
	 * @param paginationSize
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalIdAndSourcePaginated(IResource<IResourceElement> resource, ISource source,String partialExternalId,  int index, int paginationSize) throws ANoteException;

	
	
}
