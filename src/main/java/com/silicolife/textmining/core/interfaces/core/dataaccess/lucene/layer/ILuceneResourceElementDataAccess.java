package com.silicolife.textmining.core.interfaces.core.dataaccess.lucene.layer;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementsFilter;


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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactTerm(IResourceElementsFilter filter, String term) throws ANoteException;
	
	
	
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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTerm(IResourceElementsFilter filter, String partialTerm) throws ANoteException;

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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTermPaginated(IResourceElementsFilter filter, String partialTerm,  int index, int paginationSize) throws ANoteException;

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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactSynonym(IResourceElementsFilter filter, String synonym) throws ANoteException;
	
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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonym(IResourceElementsFilter filter, String partialSynonym) throws ANoteException;

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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonymPaginated(IResourceElementsFilter filter, String partialSynonym, int index, int paginationSize) throws ANoteException;
	
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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactExternalId(IResourceElementsFilter filter, String externalId) throws ANoteException;
	
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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalId(IResourceElementsFilter filter, String partialExternalId) throws ANoteException;
	
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
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalIdPaginated(IResourceElementsFilter filter, String partialExternalId , int index, int paginationSize) throws ANoteException;
	
	/**
	 * Get Number of results for the partial search in Resource Element
	 * 
	 * @param partialTerm
	 * @return
	 * @throws ANoteException
	 */
	public Integer getResourceElementsCountByPartialTerm(String partialTerm) throws ANoteException;
	
	/**
	 * Get Number of results for the partial search in Synonyms
	 * 
	 * @param partialSynonym
	 * @return
	 * @throws ANoteException
	 */
	
	public Integer getResourceElementsFilteredCountByPartialTerm(IResourceElementsFilter filter, String partialString) throws ANoteException;
	
	public Integer getResourceElementsCountByPartialSynonym(String partialString) throws ANoteException;
	
	public Integer getResourceElementsFilteredCountByPartialSynonym(IResourceElementsFilter filter, String partialString) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalID(String externalId) throws ANoteException;

	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactExternalID(IResourceElementsFilter filter, String externalId) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalID(String partialString) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalID(IResourceElementsFilter filter, String partialString) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIDPaginated(String partialString, int index, int paginationSize) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalIDPaginated(IResourceElementsFilter filter, String partialString, int index, int paginationSize) throws ANoteException;
	
	public Integer getResourceElementsCountByPartialExternalID(String partialString) throws ANoteException;
	
	public Integer getResourceElementsFilteredCountByPartialExternalID(IResourceElementsFilter filter, String partialString) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermOrPartialSynonymPaginated(String partialString, int index, int paginationSize)  throws ANoteException;

	public Integer getResourceElementsCountByPartialTermOrPartialSynonym(String partialString)  throws ANoteException;

	public IResourceElementSet<IResourceElement> getResourceElementsByExactTermOrExactSynonymPaginated(String exactString,
			int index, int paginationSize) throws ANoteException;

	public Integer getCountResourceElementsByExactTermOrExactSynonymPaginated(String exactString) throws ANoteException;

	public IResourceElementSet<IResourceElement> getResourceElementsPaginated(ISearchProperties searchProperties, int index,
			int paginationSize, boolean asc, String sortBy) throws ANoteException;

	public Integer countResourceElements(ISearchProperties searchProperties) throws ANoteException;
	
}
