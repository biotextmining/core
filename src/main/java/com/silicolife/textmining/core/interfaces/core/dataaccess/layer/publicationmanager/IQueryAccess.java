package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.publicationmanager;

import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.relevance.IQueryPublicationRelevance;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

public interface IQueryAccess extends IQueryPrivilegesAccess {

	/**
	 * Return all {@link IPublication} for Query
	 * 
	 * @param query
	 * @return
	 * @throws DatabaseLoadDriverException
	 */
	public List<IPublication> getQueryPublications(IQuery query) throws ANoteException;

	/**
	 * Update a relevance from a publication
	 * 
	 * @param publication
	 * @param iQueryPublicationRelevance
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void updateQueryDocumentRelevance(IPublication publication, IQuery query, IQueryPublicationRelevance iQueryPublicationRelevance) throws ANoteException;

	/**
	 * Associate query to a publication
	 * 
	 * @param publication
	 * @throws DatabaseLoadDriverException
	 */
	public void addQueryPublications(IQuery query, List<IPublication> publications) throws ANoteException;

	/**
	 * Create a new Query
	 * 
	 * @param query
	 * @return
	 * @throws DatabaseLoadDriverException
	 */
	public void createQuery(IQuery query) throws ANoteException;

	/**
	 * Update query* (Insert all fileds to update)
	 * 
	 * @param query
	 * @throws DatabaseLoadDriverException
	 */
	public void updateQuery(IQuery query) throws ANoteException;

	/**
	 * Inactive query
	 * 
	 * @param queryID
	 * @throws DatabaseLoadDriverException
	 */
	public void inactiveQuery(IQuery query) throws ANoteException;

	/**
	 * remove Query Privileges for logged user
	 * 
	 * @param queryID
	 * @throws DatabaseLoadDriverException
	 */
	public void removeQuery(IQuery query) throws ANoteException;

	/**
	 * Get all queries from user logged
	 * 
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public List<IQuery> getAllQueries() throws ANoteException;

	/**
	 * Get Query given the id
	 * 
	 * @param queryID
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public IQuery getQueryByID(long queryID) throws ANoteException;

	/**
	 * Method that return a External Publication Set for a Query
	 * 
	 * @param source
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public Set<String> getQueryPublicationsExternalIDFromSource(IQuery query, String source) throws ANoteException;

}
