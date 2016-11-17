package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IResourceElementsRelation;

public interface IResourceElementAccess {

	/**
	 * Add Term synonyms and External IDs
	 * Return report with conflits and well-done tasks
	 * 
	 * @param resource
	 * @param element
	 * @return 
	 * @throws ANoteException
	 */
	public IResourceManagerReport addResourceElements(IResource<IResourceElement> resource,List<IResourceElement> element) throws ANoteException;
	
	/**
	 * Add Term synonyms and External IDs without validation
	 * Return report with conflits and well-done tasks
	 * 
	 * @param resource
	 * @param element
	 * @return 
	 * @throws ANoteException
	 */
	public IResourceManagerReport addResourceElementsWithouValidation(IResource<IResourceElement> resource,List<IResourceElement> element) throws ANoteException;

	
	/**
	 * Add Resource Element Synonyms
	 * 
	 * @param dicElement
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public IResourceManagerReport addResourceElementSynomyns(IResource<IResourceElement> resource,IResourceElement elem,List<String> synonyms) throws ANoteException;

	/**
	 * Add resource Element Synonyms without Validation
	 * 
	 * @param destiny
	 * @param originalElem
	 * @param synonymsToAdd
	 * @return 
	 * @throws ANoteException
	 */
	public IResourceManagerReport addResourceElementSynomynsWithoutValidation(IResource<IResourceElement> destiny,IResourceElement originalElem, List<String> synonymsToAdd) throws ANoteException;
	
	
	/**
	 * Add Resource Element External Ids
	 * 
	 * @param elem
	 * @param externalIDs
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public IResourceManagerReport addResourceElementExternalIds(IResource<IResourceElement> resource,IResourceElement elem,List<IExternalID> externalIDs) throws ANoteException;
	
	
	/**
	 * Update resource element (Like class)
	 * 
	 * @param elem
	 * @throws DatabaseLoadDriverException
	 */
	public IResourceManagerReport updateResourceElement(IResourceElement elem) throws ANoteException;

	/**
	 * Remove element
	 * 
	 * @param elem
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void inactivateResourceElement(IResourceElement elem) throws ANoteException;
	
	
	/**
	 * Get All Resource Elements
	 * 
	 * @param resource
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public IResourceElementSet<IResourceElement> getResourceElements(IResource<IResourceElement> resource) throws ANoteException;
	
	public IResourceElementSet<IResourceElement> getResourceElementsInBatchWithLimit(IResource<IResourceElement> resource, Integer index, Integer pagination) throws ANoteException;
	
	/**
	 * Get All Resource Elements Relation
	 * 
	 * @param resource
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public List<IResourceElementsRelation> getResourceElementsRelations(IResource<IResourceElement> resource) throws ANoteException;

	/**
	 * Method that removes all class term form a resource
	 * 
	 * @param resource
	 * @param classID
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void removeResourceClass(IResource<IResourceElement> resource, long classID) throws ANoteException;
	
	/**
	 * 
	 * Get Resource Element ID that matching in resource for text 
	 * 
	 * @param handRule
	 * @param resource
	 * @param text
	 * @param casesensitive 
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public long getResourceElementIDMathingByText(IResource<IResourceElement> resource,IResourceElement elemennt,String text, boolean casesensitive) throws ANoteException;
	
	/**
	 * Get all resource element that word matching ( with case sensitive)
	 * 
	 * @param name
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByName(IResource<IResourceElement> resource,String name) throws ANoteException;
	
	/**
	 * Get Resource Element for ID
	 * 
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public IResourceElement getResourceElementByID(long resourceID)  throws ANoteException;
	
	/**
	 * Remove all Resource Element Synonyms
	 * 
	 * @param elem
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void removeResourceElementSynonyms(IResourceElement elem) throws ANoteException;
	
	
	/**
	 * Remove Resource Element Synonym
	 * 
	 * @param elem
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void removeResourceElementSynonym(IResourceElement elem,String synonym) throws ANoteException;
	
	/**
	 * Get Max Priority for a Resource
	 * 
	 * @param resource
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public int getResourceMaxPriority(IResource<IResourceElement> resource) throws ANoteException;

	/**
	 * Add A relation between two resource elements
	 * 
	 * @param a
	 * @param b
	 * @param relationType
	 * @return
	 * @throws ANoteException
	 */
	public void addResourceElementsRelation(IResourceElement a,IResourceElement b, String relationType) throws ANoteException;
	
	/**
	 * 
	 * @param resourceImpl
	 * @param klass
	 * @return
	 * @throws ANoteException 
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByClass(IResource<IResourceElement> resource, IAnoteClass klass) throws ANoteException;
	
	/**
	 * 
	 * @param resourceElementImpl
	 * @return
	 * @throws ANoteException 
	 */
	public List<IExternalID> getResourceElementExternalIds(IResourceElement resourceElementImpl) throws ANoteException;

	/**
	 * Check if term exists
	 * 
	 * @param term
	 * @return
	 * @throws ANoteException 
	 */
	public boolean checkResourceElementExistsInResource(IResource<IResourceElement> resource,String term) throws ANoteException;
	
	/**
	 * Update Synonym name
	 *  
	 * @param elem
	 * @param oldSynonym
	 * @param newSynonym
	 * @return
	 * @throws ANoteException 
	 */
	public IResourceManagerReport updateResourceElementSynonym(IResource<IResourceElement> resource,IResourceElement elem, String oldSynonym, String newSynonym) throws ANoteException;
	
	/**
	 * Remove resource element external id
	 * 
	 * @param element
	 * @param extID
	 * @throws ANoteException
	 */
	public void removeResourceElementExternalID(IResourceElement element,IExternalID extID) throws ANoteException;
	
	/**
	 * Remove all external ids from resource element
	 * 
	 * @param element
	 */
	public void removeResourceElementAllExternalID(IResourceElement element) throws ANoteException;;
	
	/**
	 * Get Resource for a Resource Element ID
	 * 
	 * @param resourceElementID
	 * @return
	 * @throws ANoteException 
	 */
	public IResource<IResourceElement> getResourceFromResourceElementByID(Long resourceElementID) throws ANoteException;
	
	/**
	 * 
	 * Get Resource Elements by External ID
	 * 
	 * @param externalId
	 * @return
	 * @throws ANoteException
	 */
	public IResourceElementSet<IResourceElement> getResourceElementsByExternalID(IExternalID externalId) throws ANoteException;


}
