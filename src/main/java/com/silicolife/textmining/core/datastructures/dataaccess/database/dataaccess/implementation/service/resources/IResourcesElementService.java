package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources;

import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IResourceElementsRelation;

public interface IResourcesElementService {

	public IResourceManagerReport addResourceElements(Long id, List<IResourceElement> elem) throws ResourcesExceptions; //

	public IResourceElementSet<IResourceElement> getResourceElements(Long resourceId) throws ResourcesExceptions;  //

	public IResourceElementSet<IResourceElement> getResourceElementsByClass(Long resourceId, String termClass) throws ResourcesExceptions; //
	
	public IResourceContent getResourceContent(Long resourceId) throws ResourcesExceptions; //
	
	public Set<IAnoteClass> getResourceClassContent(Long id) throws ResourcesExceptions;  //

	public List<IExternalID> getResourceElementExternalIDs(Long resourceElementID) throws ResourcesExceptions; // 

	public IResourceManagerReport addResourceElementsWithoutValidation(Long id,List<IResourceElement> element)  throws ResourcesExceptions; //

	public Boolean checkResourceElementExistsInResource(Long id, String term) throws ResourcesExceptions; //

	public Boolean removeResourceClass(Long id, Long classID) throws ResourcesExceptions; // 
 
	public IResourceManagerReport addResourceElementSynonyms(Long resourseID,Long resourceElmentID,List<String> newSynonyms) throws ResourcesExceptions; //

	public IResourceManagerReport addResourceElementExternalIDs(Long resourseID,Long resourceElmentID, List<IExternalID> externalIDs) throws ResourcesExceptions; //

	public IResourceManagerReport updateResourceElement(IResourceElement elem) throws ResourcesExceptions; // 

	public Boolean inactivateResourceElement(Long resourceElmentID) throws ResourcesExceptions; //////////////////////////

	public Boolean removeResourceElementSynonyms(Long resourceElmentID) throws ResourcesExceptions; //////////////////////
	
	public IResourceManagerReport updateResourceElementSynonym(Long resourceID,Long resourceElmentID,String oldSynonym, String newSynonym) throws ResourcesExceptions; // 

	public Boolean removeResourceElementSynonym(Long resourceElmentID, String synonym) throws ResourcesExceptions; //

	public Boolean removeResourceElementExternalID(Long resourceElmentID,IExternalID extID) throws ResourcesExceptions; //

	public Boolean removeResourceElementAllExternalID(Long resourceElmentID) throws ResourcesExceptions; //

	public int getResourceMaxPriorety(Long resourceElmentID)throws ResourcesExceptions; //

	public Boolean addResourceElementsRelation(Long resourceElmentIDa, Long resourceElmentIDb,String relationType) throws ResourcesExceptions; // 

	public List<IResourceElementsRelation> getResourceElementsRelations(Long resourceID) throws ResourcesExceptions; //

	public IResource<IResourceElement> getResourceFromResourceElement(Long resourceElementID) throws ResourcesExceptions; //

	public IResourceElement getResourceElemenByID(Long resourceElementID) throws ResourcesExceptions; //

	public IResourceElementSet<IResourceElement> getResourceElementsByName(Long id, String name) throws ResourcesExceptions; //

	public void setUserLogged(UsersLogged userLogged);
}
