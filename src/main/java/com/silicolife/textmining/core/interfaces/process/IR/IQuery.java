package com.silicolife.textmining.core.interfaces.process.IR;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.relevance.IQueryPublicationRelevance;

public interface IQuery extends Serializable{

	/**
	 * Method that return a queryID
	 * 
	 * @return queryID
	 */
	public long getId();

	/**
	 * return query Origin Type
	 * 
	 * @return QueryType
	 */
	public IQueryOriginType getQueryOriginType();

	/**
	 * Method that return date of query
	 * 
	 * @return Date
	 */
	public Date getDate();

	public void setDate(Date date);

	/**
	 * Method that return keyword of Query
	 * 
	 * @return keywords "" -- if keywords don't exist
	 */
	public String getKeywords();

	/**
	 * Method that return the organism in searching
	 * 
	 * @return organism "" -- if organism don't exist
	 */
	public String getOrganism();

	/**
	 * Method that resturn completed query peformed to IR Process (like 1235[ui]
	 * OR "Stringent response")
	 * 
	 * @return
	 */
	public String getCompleteQuery();

	/**
	 * Method that return a Query Properties
	 * 
	 * @return query properties
	 */

	public int getPublicationsSize();

	public void setPublicationsSize(int newQuerySize);

	/**
	 * Get number of available abstracts
	 * 
	 * @return
	 */
	public int getAvailableAbstracts();

	public void setAvailableAbstracts(int newAvailableAbstracts);

	/**
	 * Return Query Name
	 * 
	 * @return
	 * @throws DatabaseLoadDriverException
	 */
	public String getName();

	/**
	 * set query name
	 * 
	 * @param name
	 * @throws DatabaseLoadDriverException
	 */
	public void setName(String name);

	/**
	 * Method that return a Query Properties
	 * 
	 * @return query properties
	 */
	public Properties getProperties();

	/**
	 * 
	 * 
	 * @return
	 */
	public String getNotes();

	/**
	 * Get All Query Publciations
	 * 
	 * @return
	 * @throws DatabaseLoadDriverException
	 * @throws DaemonException
	 */
	public List<IPublication> getPublications() throws ANoteException;

	/**
	 * Get All Publication Relevance for A Query
	 * 
	 * @return
	 * @throws DatabaseLoadDriverException
	 * @throws DaemonException
	 */
	public Map<Long, IQueryPublicationRelevance> getPublicationsRelevance();

}
