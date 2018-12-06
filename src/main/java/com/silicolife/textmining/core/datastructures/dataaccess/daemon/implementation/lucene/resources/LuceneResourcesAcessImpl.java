package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.lucene.resources;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.resources.ResourceImpl;
import com.silicolife.textmining.core.datastructures.resources.dictionary.loaders.DictionaryImpl;
import com.silicolife.textmining.core.datastructures.resources.lexiacalwords.LexicalWordsImpl;
import com.silicolife.textmining.core.datastructures.resources.lookuptable.LookupTableImpl;
import com.silicolife.textmining.core.datastructures.resources.ontology.OntologyImpl;
import com.silicolife.textmining.core.datastructures.resources.rule.RuleSetImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.ResourcesTypeEnum;

public class LuceneResourcesAcessImpl extends RestClientAccess {
	
	public LuceneResourcesAcessImpl() {
		
	}

	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuth(ISearchProperties searchProperties,
			int index, int paginationSize) throws DaemonException{
		
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<ResourceImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<ResourceImpl>>>() {};
		
		Map<String, Integer> uriVariables = new LinkedHashMap<String, Integer>();
		uriVariables.put("index", index);
		uriVariables.put("paginationSize", paginationSize);

		ResponseEntity<DaemonResponse<List<ResourceImpl>>> response = webClient.post("resources/getResourcesFromSearchPaginatedWAuth", responseType, searchProperties, uriVariables);
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

	public Integer countActiveResourcesFromSearch(ISearchProperties searchProperties) throws DaemonException{
		//TODO on controller side
		return null;
	}

	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties, String permission) throws DaemonException {
		
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resources/countResourcesFromSearchWAuth", responseType, searchProperties);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}

	public List<IResource<IResourceElement>> getActiveResourcesFromSearchPaginatedWSort(
			ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy) throws DaemonException {
		// TODO on controller side
		return null;
	}

	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuthAndSort(
			ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy) throws DaemonException {

		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<ResourceImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<ResourceImpl>>>() {};
		
		Map<String, Object> uriVariables = new LinkedHashMap<String, Object>();
		uriVariables.put("index", index);
		uriVariables.put("paginationSize", paginationSize);
		uriVariables.put("asc", asc);
		uriVariables.put("sortBy", sortBy);

		ResponseEntity<DaemonResponse<List<ResourceImpl>>> response = webClient.post("resources/getResourcesFromSearchPaginatedWAuthAndSort", responseType, searchProperties, uriVariables);
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

	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuthAndSort(
			ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy,
			String permission) {
		// TODO on controller side
		return null;
	}

	public List<IResource<IResourceElement>> getPrivilegesResourcesAdminAccessFromSearchPaginated(
			ISearchProperties searchProperties, Integer index, Integer paginationSize, boolean asc,
			String sortBy) throws DaemonException {

		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<ResourceImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<ResourceImpl>>>() {};
		
		Map<String, Object> uriVariables = new LinkedHashMap<String, Object>();
		uriVariables.put("index", index);
		uriVariables.put("paginationSize", paginationSize);
		uriVariables.put("asc", asc);
		uriVariables.put("sortBy", sortBy);

		ResponseEntity<DaemonResponse<List<ResourceImpl>>> response = webClient.post("resources/getPrivilegesResourcesAdminAccessFromSearchPaginated", responseType, searchProperties, uriVariables);
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

	public Integer countPrivilegesResourcesAdminAccessFromSearch(ISearchProperties searchProperties) throws DaemonException {
		
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resources/countPrivilegesResourcesAdminAccessFromSearch", responseType, searchProperties);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
	
	
	
}
