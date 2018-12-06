package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.lucene.resources;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementImpl;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementSetImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementsFilter;

public class LuceneResourcesElementsAccessImpl extends RestClientAccess{
	
	public LuceneResourcesElementsAccessImpl(){
		
	}

	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTerm(String term) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("term", term);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsByExactTerm", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactTerm(IResourceElementsFilter filter, String term) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("term", term);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFilteredByExactTerm", responseType, filter, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTerm(String partialTerm) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialTerm", partialTerm);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsByPartialTerm", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTerm(IResourceElementsFilter filter, String partialTerm) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialTerm", partialTerm);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFilteredByPartialTerm", responseType, filter, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}

	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermPaginated(String partialTerm, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialTerm", partialTerm);
		uriVariables.add("index", String.valueOf(index));
		uriVariables.add("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsByPartialTermPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTermPaginated(IResourceElementsFilter filter, String partialTerm, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialTerm", partialTerm);
		uriVariables.put("index", String.valueOf(index));
		uriVariables.put("paginationSize", String.valueOf(paginationSize));
		

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFilteredByPartialTermPaginated", responseType, filter, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByExactSynonym(String synonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("synonym", synonym);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsByExactSynonym", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactSynonym(IResourceElementsFilter filter, String synonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("synonym", synonym);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFilteredByExactSynonym", responseType, filter, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}

	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonym(String partialSynonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialSynonym", partialSynonym);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsByPartialSynonym", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonym(IResourceElementsFilter filter, String partialSynonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialSynonym", partialSynonym);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFilteredByPartialSynonym", responseType, filter, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonymPaginated(String partialSynonym, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialSynonym", partialSynonym);
		uriVariables.add("index", String.valueOf(index));
		uriVariables.add("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsByPartialSynonymPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonymPaginated(IResourceElementsFilter filter, String partialSynonym, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialSynonym", partialSynonym);
		uriVariables.put("index", String.valueOf(index));
		uriVariables.put("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFilteredByPartialSynonymPaginated", responseType, filter, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalID(String externalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("externalId", externalId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsByExactExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactExternalID(IResourceElementsFilter filter, String externalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("externalId", externalId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFilteredByExactExternalID", responseType, filter, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalID(String partialExternalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialExternalId", partialExternalId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsByPartialExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalID(IResourceElementsFilter filter, String partialExternalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialExternalId", partialExternalId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFilteredByPartialExternalID", responseType, filter, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIDPaginated(String partialExternalId, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialExternalId", partialExternalId);
		uriVariables.add("index", String.valueOf(index));
		uriVariables.add("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsByPartialExternalIDPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	

	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalIDPaginated(IResourceElementsFilter filter, String partialExternalId, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialExternalId", partialExternalId);
		uriVariables.put("index",  String.valueOf(index));
		uriVariables.put("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFilteredByPartialExternalIDPaginated", responseType, filter, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}

	public Integer getResourceElementsCountByPartialTerm(String partialTerm) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialTerm",  partialTerm);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getResourceElementsCountByPartialTerm", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
	
	public Integer getResourceElementsFilteredCountByPartialTerm(IResourceElementsFilter filter, String partialTerm) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialTerm",  partialTerm);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getResourceElementsFilteredCountByPartialTerm", responseType, filter, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}

	
	public Integer getResourceElementsCountByPartialSynonym(String partialSynonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialSynonym",partialSynonym);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getResourceElementsCountByPartialSynonym", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
	
	public Integer getResourceElementsFilteredCountByPartialSynonym(IResourceElementsFilter filter, String partialSynonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialSynonym",partialSynonym);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getResourceElementsFilteredCountByPartialSynonym", responseType, filter, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
	
	public Integer getResourceElementsCountByPartialExternalID(String partialExternalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialExternalId",partialExternalId);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getResourceElementsCountByPartialExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
	
	public Integer getResourceElementsFilteredCountByPartialExternalID(IResourceElementsFilter filter, String partialExternalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialExternalId",partialExternalId);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getResourceElementsFilteredCountByPartialExternalID", responseType, filter, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}

	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermOrPartialSynonymPaginated(
			String partialString, int index, int paginationSize) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialString",  partialString);
		uriVariables.add("index",  String.valueOf(index));
		uriVariables.add("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsByPartialTermOrPartialSynonymPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}

	public Integer getResourceElementsCountByPartialTermOrPartialSynonym(String partialString) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialString", partialString);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getResourceElementsCountByPartialTermOrPartialSynonym", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}

	public IResourceElementSet<IResourceElement> getResourceElementsByExactTermOrExactSynonymPaginated(
			String exactString, int index, int paginationSize) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("exactString",  exactString);
		uriVariables.add("index",  String.valueOf(index));
		uriVariables.add("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsByExactTermOrExactSynonymPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}

	public Integer getCountResourceElementsByExactTermOrExactSynonymPaginated(String exactString) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("exactString",exactString);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getCountResourceElementsByExactTermOrExactSynonymPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}

	public IResourceElementSet<IResourceElement> getResourceElementsPaginated(ISearchProperties searchProperties,
			int index, int paginationSize, boolean asc, String sortBy) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, Object> uriVariables = new LinkedHashMap<String, Object>();
		uriVariables.put("index",  index);
		uriVariables.put("paginationSize", paginationSize);
		uriVariables.put("asc", asc);
		uriVariables.put("sortBy", sortBy);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsPaginated", responseType, searchProperties, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	public Integer countResourceElements(ISearchProperties searchProperties) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/countResourceElementsWAuthAndSort", responseType, searchProperties);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
}
