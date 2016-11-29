package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.lucene.resources;

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
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactTerm(Long resourceId, String term) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("term", term);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromResourceByExactTerm", responseType, uriVariables);
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
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTerm(Long resourceId, String partialTerm) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("partialTerm", partialTerm);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromResourceByPartialTerm", responseType, uriVariables);
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
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialTermPaginated(Long resourceId, String partialTerm, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("partialTerm", partialTerm);
		uriVariables.add("index", String.valueOf(index));
		uriVariables.add("paginationSize", String.valueOf(paginationSize));
		

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromResourceByPartialTermPaginated", responseType, uriVariables);
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
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByExactSynonym(Long resourceId, String synonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("synonym", synonym);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromResourceByExactSynonym", responseType, uriVariables);
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
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonym(Long resourceId, String partialSynonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("partialSynonym", partialSynonym);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromResourceByPartialSynonym", responseType, uriVariables);
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
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceByPartialSynonymPaginated(String partialSynonym, Long resourceId, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialSynonym", partialSynonym);
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("index", String.valueOf(index));
		uriVariables.add("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromResourceByPartialSynonymPaginated", responseType, uriVariables);
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
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByExactExternalID(String externalId, Long sourceId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("externalId", externalId);
		uriVariables.add("sourceId", String.valueOf(sourceId));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromSourceByExactExternalID", responseType, uriVariables);
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
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("externalId", externalId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromResourceByExactExternalID", responseType, uriVariables);
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
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("sourceId", String.valueOf(sourceId));
		uriVariables.add("externalId", externalId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromResourceAndSourceByExactExternalID", responseType, uriVariables);
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
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByPartialExternalID(String partialExteralId, Long sourceId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialExteralId", partialExteralId);
		uriVariables.add("sourceId", String.valueOf(sourceId));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromSourceByPartialExternalID", responseType, uriVariables);
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
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("partialExternalId", partialExternalId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromResourceByPartialExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}
	
	@SuppressWarnings("unchecked")
	public IResourceElementSet<IResourceElement> getResourceElementsFromResourceAndSourceByPartialExternalID(Long resourceId, Long sourceId, String partialExternalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("sourceId", String.valueOf(sourceId));
		uriVariables.add("partialExternalId", partialExternalId);

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromResourceAndSourceByPartialExternalID", responseType, uriVariables);
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
	public IResourceElementSet<IResourceElement> getResourceElementsFromSourceByPartialExternalIDPaginated(Long sourceId, String partialExternalId, int index, int paginationSize) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >> responseType = new ParameterizedTypeReference<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl> >>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("sourceId", String.valueOf(sourceId));
		uriVariables.add("partialExternalId", partialExternalId);
		uriVariables.add("index", String.valueOf(index));
		uriVariables.add("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromSourceByPartialExternalIDPaginated", responseType, uriVariables);
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
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("partialExternalId", partialExternalId);
		uriVariables.add("index", String.valueOf(index));
		uriVariables.add("paginationSize", String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromResourceByPartialExternalIDPaginated", responseType, uriVariables);
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

		ResponseEntity<DaemonResponse<ResourceElementSetImpl<ResourceElementImpl>>> response = webClient.post("resourceElements/getResourceElementsFromResourceAndSourceByPartialExternalIDPaginated", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			IResourceElementSet<? extends IResourceElement> resourceElements = response.getBody().getContent();
			return (IResourceElementSet<IResourceElement>) resourceElements;
		}
	}

	public Integer getCountResourceElementsByPartialTerm(String partialTerm) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialTerm",  partialTerm);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getCountResourceElementsByPartialTerm", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
	
	public Integer getCountResourceElementsFromResourceByPartialTerm(Long resourceId, String partialTerm) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("partialTerm",  partialTerm);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getCountResourceElementsFromResourceByPartialTerm", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}

	
	public Integer getCountResourceElementsByPartialSynonym(String partialSynonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialSynonym",partialSynonym);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getCountResourceElementsByPartialSynonym", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
	
	public Integer getCountResourceElementsFromResourceByPartialSynonym(Long resourceId, String partialSynonym) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("partialSynonym",partialSynonym);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getCountResourceElementsFromResourceByPartialSynonym", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
	
	public Integer getCountResourceElementsByPartialExternalID(String partialExternalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("partialExternalId",partialExternalId);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getCountResourceElementsByPartialExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
	
	public Integer getCountResourceElementsFromSourceByPartialExternalID(Long sourceId, String partialExternalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("sourceId", String.valueOf(sourceId));
		uriVariables.add("partialExternalId",partialExternalId);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getCountResourceElementsFromSourceByPartialExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
	
	public Integer getCountResourceElementsFromResourceByPartialExternalID(Long resourceId, String partialExternalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("partialExternalId",partialExternalId);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getCountResourceElementsFromResourceByPartialExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
	
	public Integer getCountResourceElementsFromResourceAndSourceByPartialExternalID(Long resourceId, Long sourceId, String partialExternalId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("resourceId", String.valueOf(resourceId));
		uriVariables.add("sourceId", String.valueOf(sourceId));
		uriVariables.add("partialExternalId",partialExternalId);
		
		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("resourceElements/getCountResourceElementsFromResourceAndSourceByPartialExternalID", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			return response.getBody().getContent();
		}
	}
}
