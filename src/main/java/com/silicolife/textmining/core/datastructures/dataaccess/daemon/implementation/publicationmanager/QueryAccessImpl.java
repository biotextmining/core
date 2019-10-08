package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.publicationmanager;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.silicolife.textmining.core.datastructures.documents.PublicationImpl;
import com.silicolife.textmining.core.datastructures.documents.query.QueryImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.relevance.RelevanceTypeEnum;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

/**
 * Implementation of Query Access Operations
 * 
 * @author Joel Azevedo Costa
 *
 */
public class QueryAccessImpl extends RestClientAccess {

	/**
	 * Connect to daemon and get all queries
	 * 
	 * 
	 * @return
	 * @throws DaemonException
	 */
	public List<IQuery> getAllQueries() throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<QueryImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<QueryImpl>>>() {};
		ResponseEntity<DaemonResponse<List<QueryImpl>>> response = webClient.get("queries/getAllQueries", responseType);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {

			List<IQuery> returned = new ArrayList<IQuery>();
			List<QueryImpl> queries = response.getBody().getContent();
			for (QueryImpl query : queries) {
				returned.add(query);
			}
			return returned;
		}
	}

	/**
	 * get all queries from system
	 * 
	 * @return
	 * @throws DaemonException
	 */
	public List<IQuery> getAllPrivilegesQueriesAccessAdmin() throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<QueryImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<QueryImpl>>>() {};
		ResponseEntity<DaemonResponse<List<QueryImpl>>> response = webClient.get("queries/getAllPrivilegesQueriesAccessAdmin", responseType);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {

			List<IQuery> returned = new ArrayList<IQuery>();
			List<QueryImpl> queries = response.getBody().getContent();
			for (QueryImpl query : queries) {
				returned.add(query);
			}
			return returned;
		}
	}

	/**
	 * Connect to daemon and get query by id
	 * 
	 * @param queryId
	 * @return
	 * @throws DaemonException
	 */
	public IQuery getQueryByID(long queryId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<QueryImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<QueryImpl>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("queryId", queryId);
		ResponseEntity<DaemonResponse<QueryImpl>> response = webClient.get("queries/getQueryById", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IQuery query = response.getBody().getContent();
			return query;
		}
	}

	/**
	 * Connect to daemon and get publications relevance by query id
	 * 
	 * 
	 * @param queryId
	 * @return
	 * @throws DaemonException
	 */
	public Map<Long, RelevanceTypeEnum> getQueryPublicationsRelevance(long queryId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Map<Long, RelevanceTypeEnum>>> responseType = new ParameterizedTypeReference<DaemonResponse<Map<Long, RelevanceTypeEnum>>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("queryId", String.valueOf(queryId));
		
		ResponseEntity<DaemonResponse<Map<Long, RelevanceTypeEnum>>> response = webClient.get("queries/getQueryPublicationsRelevance", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Map<Long, RelevanceTypeEnum> publicationsRelevance = response.getBody().getContent();
			return publicationsRelevance;
		}
	}

	/**
	 * Connect to daemon and create query
	 * 
	 * @param query
	 * @return
	 * @throws DaemonException
	 */
	public Boolean createQuery(IQuery query) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("queries/createQuery", responseType, query);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Connect to daemon and get publications by query
	 * 
	 * @param queryId
	 * @return
	 * @throws DaemonException
	 */
	public List<IPublication> getQueryPublications(Long queryId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<PublicationImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<PublicationImpl>>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("queryId", queryId);
		ResponseEntity<DaemonResponse<List<PublicationImpl>>> response = webClient.get("queries/getAllPublications", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {

			List<IPublication> returned = new ArrayList<IPublication>();
			List<PublicationImpl> publications = response.getBody().getContent();

			for (PublicationImpl pub : publications) {
				returned.add(pub);
			}
			return returned;
		}
	}

	/**
	 * Connect to daemon and update query
	 * 
	 * 
	 * @param query
	 * @return
	 * @throws DaemonException
	 */
	public Boolean updateQuery(IQuery query) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.put("queries/updateQuery", responseType, query);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Connect to daemon and update publication/query relevance
	 * 
	 * 
	 * @param publicationId
	 * @param queryId
	 * @param relevance
	 * @return
	 * @throws DaemonException
	 */
	public Boolean updateRelevance(Long publicationId, Long queryId, String relevance) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("queryId", String.valueOf(queryId));
		uriVariables.add("publicationId", String.valueOf(publicationId));
		uriVariables.add("relevance", relevance);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("queries/updateRelevance", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}

	}

	/**
	 * Connect to daemon and add publications to query
	 * 
	 * @param queryId
	 * @param publicationsIds
	 * @return
	 * @throws DaemonException
	 */
	public Boolean addPublicationsToQuery(Long queryId, Set<Long> publicationsIds) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("queryId", queryId);

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("queries/addPublicationsToQuery", responseType, publicationsIds, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Connect to daemon and get all spurceExtenalID for a Query
	 * 
	 * @param id
	 * @param source
	 * @return
	 * @throws DaemonException
	 */
	public Set<String> getQueryPublicationsExternalIDFromSource(Long queryId, String source) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Set<String>>> responseType = new ParameterizedTypeReference<DaemonResponse<Set<String>>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("queryId", String.valueOf(queryId));
		uriVariables.add("source", source);

		ResponseEntity<DaemonResponse<Set<String>>> response = webClient.get("queries/getAllQueryPublicationsExternalIdFromSource", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {

			Set<String> externalSourcesIds = response.getBody().getContent();
			return externalSourcesIds;
		}
	}

	public List<IPublication> getQueryPublicationsPaginated(Long id, Integer paginationIndex, Integer paginationSize,
			Boolean asc, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long countQueryPublications(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}