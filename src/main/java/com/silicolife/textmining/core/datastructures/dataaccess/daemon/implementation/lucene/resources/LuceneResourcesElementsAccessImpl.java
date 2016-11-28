package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.lucene.resources;

import java.util.HashMap;
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
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;

public class LuceneResourcesElementsAccessImpl extends RestClientAccess{
	
	public LuceneResourcesElementsAccessImpl(){
		
	}

	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTerm(String term) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("term", term);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsByExactTerm", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactTerm(Long resourceId, String term) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("term", term);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromResourceByExactTerm", responseType, uriVariables);
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
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialTerm", partialTerm);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsByPartialTerm", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTerm(Long resourceId, String partialTerm) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("partialTerm", partialTerm);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromResourceByPartialTerm", responseType, uriVariables);
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

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsByPartialTermPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTermPaginated(Long resourceId, String partialTerm, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("resourceId", String.valueOf(resourceId));
		uriVariables.put("partialTerm", partialTerm);
		uriVariables.put("index", String.valueOf(index));
		uriVariables.put("paginationSize", String.valueOf(paginationSize));
		

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromResourceByPartialTermPaginated", responseType, uriVariables);
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
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("synonym", synonym);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsByExactSynonym", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactSynonym(Long resourceId, String synonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("resourceId", String.valueOf(resourceId));
		uriVariables.put("synonym", synonym);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromResourceByExactSynonym", responseType, uriVariables);
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
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialSynonym", partialSynonym);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsByPartialSynonym", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonym(Long resourceId, String partialSynonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("resourceId", String.valueOf(resourceId));
		uriVariables.put("partialSynonym", partialSynonym);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromResourceByPartialSynonym", responseType, uriVariables);
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
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialSynonym", partialSynonym);
		uriVariables.put("index", String.valueOf(index));
		uriVariables.put("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsByPartialSynonymPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonymPaginated(String partialString, Long resourceId, int index, int paginationSize) throws DaemonException{
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
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalID(String externalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("externalId", externalId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsByExactExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByExactExternalID(String externalId, Long sourceId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("externalId", externalId);
		uriVariables.put("sourceId", String.valueOf(sourceId));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromSourceByExactExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactExternalID(String externalId, Long resourceId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("resourceId", String.valueOf(resourceId));
		uriVariables.put("externalId", externalId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromResourceByExactExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceAndSourceByExactExternalID(Long resourceId, Long sourceId, String externalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("resourceId", String.valueOf(resourceId));
		uriVariables.put("sourceId", String.valueOf(sourceId));
		uriVariables.put("externalId", externalId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromResourceAndSourceByExactExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalID(String partialExternalID) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialExternalID", partialExternalID);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsByPartialExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByPartialExternalID(String partialExteralId, Long sourceId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialExteralId", partialExteralId);
		uriVariables.put("sourceId", String.valueOf(sourceId));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromSourceByPartialExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalID(Long resourceId, String partialExternalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("resourceId", String.valueOf(resourceId));
		uriVariables.put("partialExternalId", partialExternalId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromResourceByPartialExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceAndSourceByPartialExternalID(Long resourceId, Long sourceId, String partialExternalID) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("resourceId", String.valueOf(resourceId));
		uriVariables.put("sourceId", String.valueOf(sourceId));
		uriVariables.put("partialExternalID", partialExternalID);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromResourceAndSourceByPartialExternalID", responseType, uriVariables);
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
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("partialExternalId", partialExternalId);
		uriVariables.put("index", String.valueOf(index));
		uriVariables.put("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsByPartialExternalIDPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByPartialExternalIDPaginated(Long sourceId, String partialExternalId, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("sourceId", String.valueOf(sourceId));
		uriVariables.put("partialExternalId", partialExternalId);
		uriVariables.put("index", String.valueOf(index));
		uriVariables.put("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromSourceByPartialExternalIDPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialExternalIDPaginated(Long resourceId, String partialExternalId, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("resourceId", String.valueOf(resourceId));
		uriVariables.put("partialExternalId", partialExternalId);
		uriVariables.put("index", String.valueOf(index));
		uriVariables.put("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromResourceByPartialExternalIDPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceAndSourceByPartialExternalIDPaginated(Long resourceId,Long sourceId, String partialExternalId, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId",  String.valueOf(resourceId));
		uriVariables.add("sourceId",  String.valueOf(sourceId));
		uriVariables.add("partialExternalId", partialExternalId);
		uriVariables.add("index",  String.valueOf(index));
		uriVariables.add("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.get("resourceElements/getResourceElementsFromResourceAndSourceByPartialExternalIDPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}


}