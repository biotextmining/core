package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.queries;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.PublicationManagerException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.relevance.RelevanceTypeEnum;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

/**
 * Interface to define all methods of Service Layer Queries
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 * 
 */
public interface IQueriesService {

	/**
	 * Get query by id
	 * 
	 * @param id
	 * @return
	 */
	public IQuery getById(Long id);

	/**
	 * Get all queries associated from a user
	 * 
	 * @return
	 */
	public List<IQuery> getAllQueries();

	/**
	 * Get all queries existed in system. If "role_admin" all queries are
	 * returned. If has other role is returned the queries with owner permission
	 * 
	 * @return
	 */
	public List<IQuery> getAllPrivilegesQueriesAdminAccess();

	/**
	 * Get all publications from a query
	 * 
	 * @param id
	 * @return
	 * @throws ANoteException 
	 */
	public List<IPublication> getQueryPublications(Long id) throws PublicationManagerException;

	/**
	 * Create a query
	 * 
	 * @param query
	 * @return
	 */
	public Boolean create(IQuery query);

	/**
	 * Update a Query
	 * 
	 * @param query
	 * @return
	 * @throws ANoteException 
	 */
	public Boolean update(IQuery query) throws PublicationManagerException;

	/**
	 * Inactive query if user is owner. Other user, remove from the association
	 * 
	 * @param queryId
	 * @return
	 */
	public Boolean inactiveQuery(Long queryId);

	/**
	 * Associate publications to a query
	 * 
	 * @param id
	 * @param publicationsIds
	 * @return
	 * @throws ANoteException 
	 */
	public Boolean addPublicationsToQuery(Long id, Set<Long> publicationsIds) throws PublicationManagerException;

	/**
	 * Update the relevance from a publication in a query
	 * 
	 * @param queryId
	 * @param publicationId
	 * @param relevance
	 * @return
	 */
	public Boolean updateRelevance(Long queryId, Long publicationId, String relevance);

	/**
	 * Get relevance from a publications from a query
	 * 
	 * 
	 * @param queryId
	 * @return
	 * @throws ANoteException 
	 */
	public Map<Long, RelevanceTypeEnum> getQueryPublicationsRelevance(Long queryId) throws PublicationManagerException;

	/**
	 * Get All internalSource ids for the SOurce on Query
	 * 
	 * @param queryId
	 * @param source
	 * @return
	 */
	public Set<String> getQueryPublicationsExternalIDFromSource(Long queryId, String source);

	public void setUserLogged(UsersLogged userLogged);

	public Long getQueryPublicationsCount(Long queryId) throws PublicationManagerException;
	
	/**
	 * Returns publications of a query paginated
	 * 
	 * @param queryId
	 * @param paginationIndex
	 * @param paginationSize
	 * @param asc
	 * @param sortBy
	 * @return
	 */
	public List<IPublication> getQueryPublicationsPaginated(Long queryId, Integer paginationIndex, Integer paginationSize,
			boolean asc, String sortBy) throws PublicationManagerException;

	/**
	 * Returns queries of a user paginated
	 * 
	 * @param paginationIndex
	 * @param paginationSize
	 * @param asc
	 * @param sortBy
	 * @return
	 */
	public List<IQuery> getAllQueriesPaginated(Integer paginationIndex, Integer paginationSize, boolean asc, String sortBy);
	
	/**
	 * Counts all queries of a user
	 */
	public Integer countAllQueries();
}
