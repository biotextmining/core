package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.lucene.publications;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

public class LucenePublicationsAcessImpl extends RestClientAccess  {
	
	public LucenePublicationsAcessImpl(){
		
	}
	
	@SuppressWarnings("unchecked")
	public List<IPublication> getPublicationsByTitle(String title) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<IPublication>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<IPublication>>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("title", title);

		ResponseEntity<DaemonResponse<List<IPublication>>> response = webClient.get("publications/getPublicationsByTitle", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			List<IPublication> publications = response.getBody().getContent();
			return (List<IPublication>) publications;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<IPublication> getPublicationsFromSearch(ISearchProperties searchProperties)  throws ANoteException{
		checkAndForceLoginIfNecessary();
		
		ParameterizedTypeReference<DaemonResponse<List<IPublication>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<IPublication>>>() {};
		//MultiValueMap<String, ISearchProperties> uriVariables = new LinkedMultiValueMap<String, ISearchProperties>();
		//uriVariables.add("searchProperties", searchProperties);

		ResponseEntity<DaemonResponse<List<IPublication>>> response = webClient.post("publications/getPublicationsFromSearch", responseType, searchProperties);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			List<IPublication> publications = response.getBody().getContent();
			return (List<IPublication>) publications;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<IPublication> getPublicationsFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize ) throws ANoteException{
		checkAndForceLoginIfNecessary();
		
		ParameterizedTypeReference<DaemonResponse<List<IPublication>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<IPublication>>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("index", String.valueOf(index));
		uriVariables.add("paginationSize",  String.valueOf(paginationSize));

		ResponseEntity<DaemonResponse<List<IPublication>>> response = webClient.post("publications/getPublicationsFromSearchPaginated", responseType, searchProperties, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			List<IPublication> publications = response.getBody().getContent();
			return (List<IPublication>) publications;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public Integer countGetPublicationsFromSearch(ISearchProperties searchProperties)  throws ANoteException{
		checkAndForceLoginIfNecessary();
		
		ParameterizedTypeReference<DaemonResponse<Integer>> responseType = new ParameterizedTypeReference<DaemonResponse<Integer>>() {};
		//MultiValueMap<String, ISearchProperties> uriVariables = new LinkedMultiValueMap<String, ISearchProperties>();
		//uriVariables.add("searchProperties", searchProperties);

		ResponseEntity<DaemonResponse<Integer>> response = webClient.post("publications/countGetPublicationsFromSearch", responseType, searchProperties);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			return response.getBody().getContent();
		}
	}
	
}


