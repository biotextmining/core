package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.PublicationManagerException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;

/**
 * Interface to define all methods of Service Layer publications
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public interface IPublicationsService {

	/**
	 * Get publication by id (without fulltext)
	 * 
	 * @param id
	 * @return
	 */
	public IPublication getById(Long id);

	/**
	 * Create publication
	 * 
	 * @param publication
	 * @return
	 */
	public Boolean create(Set<IPublication> publications_);

	/**
	 * Update publication
	 * 
	 * @param publication
	 * @return
	 */
	public Boolean update(IPublication publication_);

	/**
	 * Get all SourceId from a publications
	 * 
	 * 
	 * @param source
	 * @return
	 */
	public Map<String, Long> getAllPublicationsIdFromSource(String source);

	/**
	 * Get full text from a publication
	 * 
	 * @param id
	 * @return
	 * @throws ANoteException 
	 */
	public String getFullText(Long id) throws ANoteException;

	/**
	 * 
	 * 
	 * @param pub
	 * @return
	 * @throws ANoteException 
	 */
	public Boolean updatePublicationAvailableFreeFullText(long pubid, boolean freeFullText) throws PublicationManagerException;

	/**
	 * Get all publications labels
	 * 
	 * @return
	 */
	public List<IPublicationLabel> getAllPublicationLabels();

	
	/**
	 * Update publications full text content
	 * 
	 * @param publicationID
	 * @param fullTextContent
	 * @throws PublicationManagerException 
	 */
	public Boolean updatePublicationAFullTextContent(Long publicationID,String fullTextContent) throws PublicationManagerException;
	
	public void setUserLogged(UsersLogged userLogged);

	public Long getPublicationIdFromSourceId(String source, String sourceId);

	public IPublication getPublicationFromSourceId(String source, String sourceId);
}
