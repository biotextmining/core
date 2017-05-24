package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.lucene.queries;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.documents.query.QueryImpl;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementImpl;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementSetImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;

public class LuceneQueriesAccessImpl extends RestClientAccess {
	
	public  LuceneQueriesAccessImpl(){
		
	}
	
	@SuppressWarnings("unchecked")
	public List<IQuery> getQueriesByName(String name) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<IQuery>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<IQuery>>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("name", name);

		ResponseEntity<DaemonResponse<List<IQuery>>> response = webClient.get("queries/getQueriesByName", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			List<IQuery> queries = response.getBody().getContent();
			return (List<IQuery>) queries;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	 public List<IQuery> getQueriesByOrganism(String organism) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<IQuery>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<IQuery>>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("organism", organism);

		ResponseEntity<DaemonResponse<List<IQuery>>> response = webClient.get("queries/getQueriesByOrganism", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			List<IQuery> queries = response.getBody().getContent();
			return (List<IQuery>) queries;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<IQuery> getQueriesBykeywords(String keywords) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<IQuery>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<IQuery>>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("keywords", keywords);

		ResponseEntity<DaemonResponse<List<IQuery>>> response = webClient.get("queries/getQueriesBykeywords", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			List<IQuery> queries = response.getBody().getContent();
			return (List<IQuery>) queries;
		}
	}
	
	@SuppressWarnings("unchecked")
	public  List<IQuery> getQueriesKeywordsByWildCard(String subKeyword) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<IQuery>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<IQuery>>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("subKeyword", subKeyword);

		ResponseEntity<DaemonResponse<List<IQuery>>> response = webClient.post("queries/getQueriesKeywordsByWildCard", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			List<IQuery> queries = response.getBody().getContent();
			return (List<IQuery>) queries;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getKeywordsOfQueriesByWildCard(String subKeyword) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<String>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<String>>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("subKeyword", subKeyword);

		ResponseEntity<DaemonResponse<List<String>>> response = webClient.post("queries/getKeywordsOfQueriesByWildCard", responseType, uriVariables);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			List<String> queries = response.getBody().getContent();
			return (List<String>) queries;
		}
	}
	
	
}
