package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.layer.ResourceManagerReport;
import com.silicolife.textmining.core.datastructures.general.AnoteClass;
import com.silicolife.textmining.core.datastructures.general.ExternalIDImpl;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementImpl;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementSetImpl;
import com.silicolife.textmining.core.datastructures.resources.ResourceImpl;
import com.silicolife.textmining.core.datastructures.resources.content.ResourceContentImpl;
import com.silicolife.textmining.core.datastructures.resources.dictionary.loaders.DictionaryImpl;
import com.silicolife.textmining.core.datastructures.resources.lexiacalwords.LexicalWordsImpl;
import com.silicolife.textmining.core.datastructures.resources.lookuptable.LookupTableImpl;
import com.silicolife.textmining.core.datastructures.resources.ontology.OntologyImpl;
import com.silicolife.textmining.core.datastructures.resources.ontology.ResourceElementsRelationImpl;
import com.silicolife.textmining.core.datastructures.resources.rule.RuleSetImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.ResourcesTypeEnum;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IResourceElementsRelation;

/**
 * Class which implements all resources elements daemon access methods
 * 
 * @author Joel Azevedo Costa
 *
 */
public class ResourcesElementsAccessImpl extends RestClientAccess {

	public ResourcesElementsAccessImpl() {
		super();
	}

	/**
	 * Get classes by resource
	 * 
	 * @param resourceElementId
	 * @return
	 * @throws DaemonException
	 */
	public Set<IAnoteClass> getResourceClassContent(Long resourceId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Set<AnoteClass>>> responseType = new ParameterizedTypeReference<DaemonResponse<Set<AnoteClass>>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("resourceId", resourceId);

		ResponseEntity<DaemonResponse<Set<AnoteClass>>> response = webClient.get("resourceElements/getResourceClassContent", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			Set<IAnoteClass> anoteClasses = new HashSet<IAnoteClass>();
			Set<AnoteClass> anoteClassImpl = response.getBody().getContent();
			for (AnoteClass anoteClass : anoteClassImpl) {
				anoteClasses.add(anoteClass);
			}

			return anoteClasses;
		}	
	}

	/**
	 * Add elements to a resource
	 * 
	 * @param resourceId
	 * @param elem
	 * @return
	 * @throws DaemonException
	 */
	public IResourceManagerReport addResourceElements(Long resourceId,  List<IResourceElement> elem) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceManagerReport>> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceManagerReport>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("resourceId", resourceId);

		ResponseEntity<DaemonResponse<ResourceManagerReport>> response = webClient.post("resourceElements/addResourceElements", responseType, elem, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IResourceManagerReport report = response.getBody().getContent();
			return report;
		}
	}

	/**
	 * Get resources elements by resource id
	 * 
	 * @param resourceId
	 * @return
	 * @throws DaemonException
	 */
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElements(Long resourceId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("resourceId", resourceId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElements", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}


	/**
	 * Get resource elements by a class
	 * 
	 * @param resourceId
	 * @param termClass
	 * @return
	 * @throws DaemonException
	 */
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByClass(Long resourceId, String termClass) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("termClass",termClass);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsByClass", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}

	/**
	 * Get resource content by resource id
	 * 
	 * @param resourceId
	 * @return
	 * @throws DaemonException
	 */
	public IResourceContent getResourceContent(Long resourceId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceContentImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceContentImpl>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("resourceId",resourceId);

		ResponseEntity<DaemonResponse<ResourceContentImpl>> response = webClient.get("resourceElements/getResourceContent", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceContent resourceContent = response.getBody().getContent();
			return resourceContent;
		}
	}

	/**
	 * Get resource element external id
	 * 
	 * @param resourceElementId
	 * @return
	 * @throws DaemonException
	 */
	public List<IExternalID> getResourceElementExternalIDs(Long resourceElementId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<ExternalIDImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<ExternalIDImpl>>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("resourceElementId", resourceElementId);

		ResponseEntity<DaemonResponse<List<ExternalIDImpl>>> response = webClient.get("resourceElements/getResourceElementExternalIds", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			List<IExternalID> externalIds = new ArrayList<IExternalID>();
			List<ExternalIDImpl> externalIdsImpl = response.getBody().getContent();
			for (ExternalIDImpl resourceImpl : externalIdsImpl) {
				externalIds.add(resourceImpl);
			}

			return externalIds;
		}
	}

	/**
	 * Add resource element wothout validation
	 * 
	 * @param resourceId
	 * @param elem
	 * @return
	 * @throws DaemonException
	 */
	public IResourceManagerReport addResourceElementsWithoutValidation(Long resourceId, List<IResourceElement> elem) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceManagerReport>> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceManagerReport>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("resourceId", resourceId);

		ResponseEntity<DaemonResponse<ResourceManagerReport>> response = webClient.post("resourceElements/addResourceElementsWithoutValidation", responseType, elem, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IResourceManagerReport report = response.getBody().getContent();
			return report;
		}	
	}


	/**
	 * Check if resource element exist in resource
	 * 
	 * @param resourceId
	 * @param term
	 * @return
	 * @throws DaemonException
	 */
	public Boolean checkResourceElementExistsInResource(Long resourceId, String term) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("term",term);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.get("resourceElements/checkResourceElementExistsInResource", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}


	/**
	 * Remove resoruce class
	 * 
	 * @param resourceId
	 * @param classId
	 * @return
	 * @throws DaemonException
	 */
	public Boolean removeResourceClass(Long resourceId, Long classId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("resourceId", resourceId);
		uriVariables.put("classId", classId);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.get("resourceElements/removeResourceClass", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}	
	} 

	/**
	 * Add Synonyms
	 * 
	 * @param resourceId
	 * @param resourceElmentId
	 * @param newSynonyms
	 * @return
	 * @throws DaemonException
	 */
	public IResourceManagerReport addResourceElementSynonyms(Long resourceId, Long resourceElmentId, List<String> newSynonyms) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceManagerReport>> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceManagerReport>>() {};
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("resourceId", resourceId);
		uriVariables.put("resourceElmentId", resourceElmentId);

		ResponseEntity<DaemonResponse<ResourceManagerReport>> response = webClient.post("resourceElements/addResourceElementSynonyms", responseType, newSynonyms, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IResourceManagerReport report = response.getBody().getContent();
			return report;
		}	

	}

	/**
	 * Add external ids
	 * 
	 * @param resourceId
	 * @param resourceElmentId
	 * @param externalIDs
	 * @return
	 * @throws DaemonException
	 */
	public IResourceManagerReport addResourceElementExternalIDs(Long resourceId,Long resourceElmentId, List<IExternalID> externalIDs) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceManagerReport>> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceManagerReport>>() {};
		Map<String, Long> uriVariables = new LinkedHashMap<String, Long>();
		uriVariables.put("resourceId", resourceId);
		uriVariables.put("resourceElmentId", resourceElmentId);

		ResponseEntity<DaemonResponse<ResourceManagerReport>> response = webClient.post("resourceElements/addResourceElementExternalIDs", responseType, externalIDs, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IResourceManagerReport report = response.getBody().getContent();
			return report;
		}	

	}

	/**
	 * Update resource element
	 * 
	 * @param elem
	 * @return
	 * @throws DaemonException
	 */
	public IResourceManagerReport updateResourceElement(IResourceElement elem) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceManagerReport>> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceManagerReport>>() {};

		ResponseEntity<DaemonResponse<ResourceManagerReport>> response = webClient.put("resourceElements/updateResourceElement", responseType, elem);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IResourceManagerReport report = response.getBody().getContent();
			return report;
		}
	}

	/**
	 * Update resource element synonym
	 * 
	 * @param resourceId
	 * @param resourceElmentId
	 * @param oldSynonym
	 * @param newSynonym
	 * @return
	 * @throws DaemonException
	 */
	public IResourceManagerReport updateResourceElementSynonym(Long resourceId, Long resourceElmentId, String oldSynonym, String newSynonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceManagerReport>> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceManagerReport>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("resourceElmentId", String.valueOf(resourceElmentId));
		uriVariables.add("oldSynonym", oldSynonym);
		uriVariables.add("newSynonym", newSynonym);

		ResponseEntity<DaemonResponse<ResourceManagerReport>> response = webClient.post("resourceElements/updateResourceElementSynonym", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IResourceManagerReport report = response.getBody().getContent();
			return report;
		}	
	}

	/**
	 * Remove synonym from reosurce element
	 * 
	 * @param resourceElmentId
	 * @param synonym
	 * @return
	 * @throws DaemonException
	 */
	public Boolean removeResourceElementSynonym(Long resourceElmentId, String synonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceElmentId", String.valueOf(resourceElmentId));
		uriVariables.add("synonym", synonym);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("resourceElements/removeResourceElementSynonym", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}	
	}

	/**
	 * Remove external id from resource element
	 * 
	 * @param resourceElmentId
	 * @param extID
	 * @return
	 * @throws DaemonException
	 */
	public Boolean removeResourceElementExternalID(Long resourceElmentId, IExternalID extID) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("resourceElmentId", resourceElmentId);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("resourceElements/removeResourceElementExternalID", responseType, extID, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}

	}

	/**
	 * Remove all external ids from resource element
	 * 
	 * @param resourceElmentId
	 * @return
	 * @throws DaemonException
	 */
	public Boolean removeResourceElementAllExternalID(Long resourceElmentId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		MultiValueMap<String, Long> uriVariables = new LinkedMultiValueMap<String, Long>();
		uriVariables.add("resourceElmentId", resourceElmentId);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("resourceElements/removeResourceElementAllExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}

	}


	/**
	 * Get resource max priority
	 * 
	 * @param resourceElmentId
	 * @return
	 * @throws DaemonException
	 */
	public int getResourceMaxPriorety(Long resourceId)throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("resourceId", resourceId);

		ResponseEntity<DaemonResponse<Integer>> response = webClient.get("resourceElements/getResourceMaxPriorety", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Integer priority = response.getBody().getContent();
			return priority;
		}
	}


	/**
	 * Add resource element relations
	 * 
	 * @param resourceElmentIda
	 * @param resourceElmentIdb
	 * @param relationType
	 * @return
	 * @throws DaemonException
	 */
	public Boolean addResourceElementsRelation(Long resourceElmentIda, Long resourceElmentIdb,String relationType) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};	
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceElmentIda", String.valueOf(resourceElmentIda));
		uriVariables.add("resourceElmentIdb", String.valueOf(resourceElmentIdb));
		uriVariables.add("relationType", relationType);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("resourceElements/addResourceElementsRelation", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}


	}

	/**
	 * Get relations from a resource
	 * 
	 * @param resourceId
	 * @return
	 * @throws DaemonException
	 */
	public List<IResourceElementsRelation> getResourceElementsRelations(Long resourceId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<ResourceElementsRelationImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<ResourceElementsRelationImpl>>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("resourceId", resourceId);

		ResponseEntity<DaemonResponse<List<ResourceElementsRelationImpl>>> response = webClient.get("resourceElements/getResourceElementsRelations", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			List<IResourceElementsRelation> resourceElementRelation = new ArrayList<IResourceElementsRelation>();
			List<ResourceElementsRelationImpl> resourceElementRelationImpl = response.getBody().getContent();
			for (ResourceElementsRelationImpl resourceImpl : resourceElementRelationImpl) {
				resourceElementRelation.add(resourceImpl);
			}

			return resourceElementRelation;
		}	
	}

	/**
	 * Get resource from resource element
	 * 
	 * @param resourceElementId
	 * @return
	 * @throws DaemonException
	 */
	public IResource<IResourceElement> getResourceFromResourceElement(Long resourceElementId) throws DaemonException{

		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceImpl>>(){};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("resourceElementId", resourceElementId);

		ResponseEntity<DaemonResponse<ResourceImpl>> response = webClient.get("resourceElements/getResourceFromResourceElement", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			ResourceImpl resource = response.getBody().getContent();	

			IResource<IResourceElement> resourceElement = null;
			ResourcesTypeEnum typeEnum = ResourcesTypeEnum.valueOf(resource.getType());
			switch(typeEnum){
			case dictionary:
				resourceElement = new DictionaryImpl(resource.getId(), resource.getName(), resource.getInfo(), resource.isActive());
				break;
			case lookuptable:
				resourceElement = new LookupTableImpl(resource.getId(), resource.getName(), resource.getInfo(), resource.isActive());
				break;
			case rule:
				resourceElement = new RuleSetImpl(resource.getId(), resource.getName(), resource.getInfo(), resource.isActive());
				break;
			case ontology:
				resourceElement = new OntologyImpl(resource.getId(), resource.getName(), resource.getInfo(), resource.isActive());
				break;
			case lexicalwords:
				resourceElement = new LexicalWordsImpl(resource.getId(), resource.getName(), resource.getInfo(), resource.isActive());
				break;
			default:
				resourceElement = resource;
				break;
			}


			return resourceElement;
		}
	}

	/**
	 * Get resource element by id
	 * 
	 * @param resourceElementId
	 * @return
	 * @throws DaemonException
	 */
	public IResourceElement getResourceElemenByID(Long resourceElementId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementImpl>>(){};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("resourceElementId", resourceElementId);

		ResponseEntity<DaemonResponse<ResourceElementImpl>> response = webClient.get("resourceElements/getResourceElemenById", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			ResourceElementImpl resourceElement = response.getBody().getContent();	
			return resourceElement;
		}
	}

	/**
	 * Get resource elements by name
	 * 
	 * @param resourceId
	 * @param name
	 * @return
	 * @throws DaemonException
	 */
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByName(Long resourceId, String name) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("name", name);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsByName", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}

	/**
	 * Inactive resource element
	 * 
	 * @param resourceElmentId
	 * @return
	 * @throws DaemonException
	 */
	public Boolean inactivateResourceElement(Long resourceElmentId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceElmentId", String.valueOf(resourceElmentId));

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("resourceElements/inactivateResourceElement", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) { 
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Remove resource element Synonyms
	 * 
	 * @param resourceElmentId
	 * @return
	 * @throws DaemonException
	 */
	public Boolean removeResourceElementSynonyms(Long resourceElmentId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		MultiValueMap<String, Long> uriVariables = new LinkedMultiValueMap<String, Long>();
		uriVariables.add("resourceElmentId", resourceElmentId);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("resourceElements/removeResourceElementSynonyms", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	public IResourceElementSet<IResourceElement> getResourceElementsInBatchWithLimit(long resourceId, int index,
			int pagination) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("index", String.valueOf(index));
		uriVariables.add("pagination", String.valueOf(pagination));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsInBatchWithLimit", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}

}
