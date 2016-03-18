package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.resources.ResourceImpl;
import com.silicolife.textmining.core.datastructures.resources.dictionary.loaders.DictionaryImpl;
import com.silicolife.textmining.core.datastructures.resources.lexiacalwords.LexicalWordsImpl;
import com.silicolife.textmining.core.datastructures.resources.lookuptable.LookupTableImpl;
import com.silicolife.textmining.core.datastructures.resources.ontology.OntologyImpl;
import com.silicolife.textmining.core.datastructures.resources.rule.RuleSetImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.ResourcesTypeEnum;

/**
 * Class which implements all resources daemon access methods
 * 
 * @author Joel Azevedo Costa
 *
 */
public class ResourcesAccessImpl extends RestClientAccess {

	public ResourcesAccessImpl() {
		super();
	}

	/**
	 * Create a new resource
	 * 
	 * @param resource
	 * @return
	 * @throws DaemonException
	 */
	public Boolean createResource(IResource<IResourceElement> resource) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResourcesTypeEnum typeEnum = ResourcesTypeEnum.valueOf(resource.getType());
		ResponseEntity<DaemonResponse<Boolean>> response = null;
		
		switch(typeEnum){
		case dictionary:
			response = webClient.post("resources/createResourceDictionary", responseType, resource);
			break;
		case lookuptable:
			response = webClient.post("resources/createResourceLookupTable", responseType, resource);
			break;
		case rule:
			response = webClient.post("resources/createResourceRuleSet", responseType, resource);
			break;
		case ontology:
			response = webClient.post("resources/createResourceOntology", responseType, resource);
			break;
		case lexicalwords:
			response = webClient.post("resources/createResourceLexicalWords", responseType, resource);
			break;
		default:
			break;
		}
		
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
	
	
	/**
	 * Get Resources by type
	 * 
	 * @param resourceType
	 * @return
	 * @throws DaemonException
	 */
	public List<IResource<IResourceElement>> getResourcesByType(String resourceType) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<ResourceImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<ResourceImpl>>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("type", resourceType);
		
		ResponseEntity<DaemonResponse<List<ResourceImpl>>> response = webClient.get("resources/getResourcesByType", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			
			List<IResource<IResourceElement>> iResources = new ArrayList<IResource<IResourceElement>>();
			List<ResourceImpl> resources = response.getBody().getContent();
			for (ResourceImpl resourceImpl : resources) {
				IResource<IResourceElement> resourceElement = null;
				ResourcesTypeEnum typeEnum = ResourcesTypeEnum.valueOf(resourceImpl.getType());
				switch(typeEnum){
				case dictionary:
					resourceElement = new DictionaryImpl(resourceImpl.getId(), resourceImpl.getName(), resourceImpl.getInfo(), resourceImpl.isActive());
					break;
				case lookuptable:
					resourceElement = new LookupTableImpl(resourceImpl.getId(), resourceImpl.getName(), resourceImpl.getInfo(), resourceImpl.isActive());
					break;
				case rule:
					resourceElement = new RuleSetImpl(resourceImpl.getId(), resourceImpl.getName(), resourceImpl.getInfo(), resourceImpl.isActive());
					break;
				case ontology:
					resourceElement = new OntologyImpl(resourceImpl.getId(), resourceImpl.getName(), resourceImpl.getInfo(), resourceImpl.isActive());
					break;
				case lexicalwords:
					resourceElement = new LexicalWordsImpl(resourceImpl.getId(), resourceImpl.getName(), resourceImpl.getInfo(), resourceImpl.isActive());
					break;
				default:
					resourceElement = resourceImpl;
					break;
				}
				iResources.add(resourceElement);
			}

			return iResources;
		}
	}
	
	/**
	 * Get resource by id
	 * 
	 * @param resourceId
	 * @return
	 * @throws DaemonException
	 */
	public IResource<IResourceElement> getResourceById(Long resourceId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceImpl>>(){};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("resourceId", resourceId);
		
		ResponseEntity<DaemonResponse<ResourceImpl>> response = webClient.get("resources/getResourceById", responseType, uriVariables);

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
	 * Update a resource
	 * 
	 * @param resource (They are datatypes)
	 * @return
	 * @throws DaemonException
	 */
	public Boolean updateResource(IResource<IResourceElement> resource) throws DaemonException {
		checkAndForceLoginIfNecessary();
		
		ResourceImpl resourceElement = new ResourceImpl(resource.getId(), resource.getName(), 
																	   resource.getInfo(), resource.getType(), resource.isActive());
		
		
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.put("resources/updateResource", responseType, resourceElement);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}	
	
	/**
	 * Get All owner or admin resources
	 * 
	 * @return
	 * @throws DaemonException
	 */
	public List<IResource<IResourceElement>> getAllPrivilegesResourcesAdminAccess() throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<ResourceImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<ResourceImpl>>>() {};

		ResponseEntity<DaemonResponse<List<ResourceImpl>>> response = webClient.get("resources/getAllPrivilegesResourcesAdminAccess", responseType);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			List<IResource<IResourceElement>> iResources = new ArrayList<IResource<IResourceElement>>();
			List<ResourceImpl> resources = response.getBody().getContent();
			for (ResourceImpl resourceImpl : resources) {
				IResource<IResourceElement> resourceElement = null;
				ResourcesTypeEnum typeEnum = ResourcesTypeEnum.valueOf(resourceImpl.getType());
				switch(typeEnum){
				case dictionary:
					resourceElement = new DictionaryImpl(resourceImpl.getId(), resourceImpl.getName(), resourceImpl.getInfo(), resourceImpl.isActive());
					break;
				case lookuptable:
					resourceElement = new LookupTableImpl(resourceImpl.getId(), resourceImpl.getName(), resourceImpl.getInfo(), resourceImpl.isActive());
					break;
				case rule:
					resourceElement = new RuleSetImpl(resourceImpl.getId(), resourceImpl.getName(), resourceImpl.getInfo(), resourceImpl.isActive());
					break;
				case ontology:
					resourceElement = new OntologyImpl(resourceImpl.getId(), resourceImpl.getName(), resourceImpl.getInfo(), resourceImpl.isActive());
					break;
				case lexicalwords:
					resourceElement = new LexicalWordsImpl(resourceImpl.getId(), resourceImpl.getName(), resourceImpl.getInfo(), resourceImpl.isActive());
					break;
				default:
					resourceElement = resourceImpl;
					break;
				}
				iResources.add(resourceElement);
			}

			return iResources;
		}	
	}

}