package com.silicolife.textmining.core.interfaces.core.dataaccess.layer;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public interface IGeneralDataAccess {
	/**
	 * 
	 * 
	 * @param documents
	 * @param documentType
	 * @throws DatabaseLoadDriverException
	 */
	public void addPublications(Set<IPublication> documents) throws ANoteException;

	/**
	 * Get Publication details
	 * 
	 * @param documentID
	 * @return
	 * @throws DatabaseLoadDriverException
	 */
	public IPublication getPublication(long documentID) throws ANoteException;

	/**
	 * Update publication
	 * 
	 * @param publication
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void updatePublication(IPublication publication) throws ANoteException;

	/**
	 * Update PublicationAvailableFreeFullText to true
	 * 
	 * @param pub
	 */
	public void updatePublicationAvailableFreeFullText(IPublication pub) throws ANoteException;

	/**
	 * Method that return a Map External Publciation Indentifier -> Publciation
	 * ID given source ( Example PUBMED, DOI, EPODOC)
	 * 
	 * @param source
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public Map<String, Long> getAllPublicationsExternalIDFromSource(String source) throws ANoteException;

	/**
	 * Get Full text publication
	 * 
	 * @param publication
	 * @return
	 */
	public String getPublicationFullText(IPublication publication) throws ANoteException;

	/**
	 * Insert new class
	 * 
	 * @param className
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void insertNewClass(IAnoteClass klass) throws ANoteException;

	/**
	 * Get all classes
	 * 
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public Set<IAnoteClass> getClasses() throws ANoteException;
	
	/**
	 * Change class name
	 * 
	 * @param oldclassName
	 * @param newCLass
	 * @return
	 * @throws ANoteException 
	 */
	public boolean updateClassName(IAnoteClass oldclassName,String newCLass) throws ANoteException;
	
	/**
	 * Update Class
	 * 
	 * @param klass
	 * @throws ANoteException 
	 */
	public void updateAnoteKlass(IAnoteClass klass) throws ANoteException;

	/**
	 * Get All Labels
	 * 
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public List<IPublicationLabel> getAllLabels() throws ANoteException;
	
	/**
	 * Update Publication Full Text Content
	 * 
	 * @param publication
	 * @throws ANoteException 
	 */
	public void updatePublicationFullTextContent(IPublication publication) throws ANoteException;
	

}
