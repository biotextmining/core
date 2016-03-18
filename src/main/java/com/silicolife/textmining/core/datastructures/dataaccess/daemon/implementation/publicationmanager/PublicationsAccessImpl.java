package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.publicationmanager;

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
import com.silicolife.textmining.core.datastructures.documents.PublicationImpl;
import com.silicolife.textmining.core.datastructures.documents.lables.PublicationLabelImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;

/**
 * Class which implements all Publications daemon access methods
 * 
 * 
 * @author Joel Azevedo Costa
 *
 */
public class PublicationsAccessImpl extends RestClientAccess {


	/**
	 * Get all publications labels
	 * 
	 * @return
	 * @throws DaemonException
	 */
	public List<IPublicationLabel> getAllPublicationsLabels() throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<PublicationLabelImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<PublicationLabelImpl>>>() {};
		ResponseEntity<DaemonResponse<List<PublicationLabelImpl>>> response = webClient.get("publications/getAllPublicationsLabels", responseType);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {

			List<IPublicationLabel> returned = new ArrayList<IPublicationLabel>();
			List<PublicationLabelImpl> pubLabels = response.getBody().getContent();
			for (PublicationLabelImpl label : pubLabels) {
				returned.add(label);
			}
			return returned;
		}
	}

	/**
	 * Connect to daemon and get publication by id
	 * 
	 * @param publicationId
	 * @return
	 * @throws DaemonException
	 */

	public IPublication getPublicationById(long publicationId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<PublicationImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<PublicationImpl>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("publicationId", publicationId);
		
		ResponseEntity<DaemonResponse<PublicationImpl>> response = webClient.get("publications/getPublicationById", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IPublication publication = IPublication.class.cast(response.getBody().getContent());
			return publication;
		}
	}

	/**
	 * Connect to daemon and get publications Ids from source
	 * 
	 * @param source
	 * @return
	 * @throws DaemonException
	 */
	public Map<String, Long> getAllPublicationsExternalIDFromSource(String source) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Map<String, Long>>> responseType = new ParameterizedTypeReference<DaemonResponse<Map<String, Long>>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("source", source);

		ResponseEntity<DaemonResponse<Map<String, Long>>> response = webClient.get("publications/getAllPublicationsExternalIdFromSource", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Map<String, Long> publicationExternalIds = response.getBody().getContent();

			return publicationExternalIds;
		}
	}

	/**
	 * Connect to daemon and get publication full text
	 * 
	 * @param publicationId
	 * @return
	 * @throws DaemonException
	 */
	public String getPublicationFullText(Long publicationId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<String>> responseType = new ParameterizedTypeReference<DaemonResponse<String>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("publicationId", publicationId);
		
		ResponseEntity<DaemonResponse<String>> response = webClient.get("publications/getFullText", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			String fullText = response.getBody().getContent();
			return fullText;
		}
	}

	/**
	 * Connect to daemon and create publications
	 * 
	 * @param documents
	 * @return
	 * @throws DaemonException
	 */
	public Boolean addPublications(List<IPublication> documents) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("publications/createPublications", responseType, documents);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
	
	
	/**
	 * Update publication full text content
	 * 
	 * @param documents
	 * @return
	 * @throws DaemonException
	 */
	public Boolean updatePublicationFullTextContent(Long publicationId, String fullText) throws DaemonException {

		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("publicationId", String.valueOf(publicationId));
		uriVariables.add("fullText", fullText);
		
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("publications/updatePublicationFullTextContent", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Connect to daemon and update publication
	 * 
	 * @param publication
	 * @return
	 * @throws DaemonException
	 */
	public Boolean updatePublication(IPublication publication) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.put("publications/updatePublication", responseType, publication);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Update publication with available free full text
	 * 
	 * @param pubId
	 * @param isAvailable
	 * @return
	 */
	public Boolean updatePublicationAvailableFreeFullText(Long publicationId, Boolean isAvailable) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("publicationId", String.valueOf(publicationId));
		uriVariables.add("isAvailable", String.valueOf(isAvailable));

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("publications/updatePublicationAvailableFreeFullText", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
}
