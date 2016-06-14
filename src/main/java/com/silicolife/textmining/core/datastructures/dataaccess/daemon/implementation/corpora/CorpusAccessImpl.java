package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.corpora;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.silicolife.textmining.core.datastructures.corpora.CorpusImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.documents.DocumentSetImpl;
import com.silicolife.textmining.core.datastructures.documents.corpus.CorpusStatisticsImpl;
import com.silicolife.textmining.core.datastructures.process.IEProcessImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpusStatistics;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

/**
 * Class which implements all Corpus daemon access methods
 * 
 * @author Joel Azevedo Costa
 *
 */
public class CorpusAccessImpl extends RestClientAccess {

	/**
	 * Connect to daemon and create corpus
	 * 
	 * @param corpus
	 * @return
	 * @throws DaemonException 
	 */
	public Boolean createCorpus(ICorpus corpus) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("corpus/createCorpus", responseType, corpus);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Connect to daemon and update corpus
	 * 
	 * @param corpus
	 * @return
	 * @throws DaemonException 
	 */
	public Boolean updateCorpus(ICorpus corpus) throws DaemonException {
		checkAndForceLoginIfNecessary();
		// Not the best way
		CorpusImpl copusInstance =  new CorpusImpl(corpus.getId(), corpus.getDescription(), corpus.getNotes(), corpus.getProperties());
		
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.put("corpus/updateCorpus", responseType, copusInstance);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Connect to daemon and get all corpus
	 * 
	 * @return
	 * @throws DaemonException 
	 */
	public List<ICorpus> getAllCorpus() throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<CorpusImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<CorpusImpl>>>() {};
		ResponseEntity<DaemonResponse<List<CorpusImpl>>> response = webClient.get("corpus/getAllCorpus", responseType);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {

			List<ICorpus> returned = new ArrayList<ICorpus>();
			List<CorpusImpl> corpus = response.getBody().getContent();
			for (CorpusImpl obj : corpus) {
				returned.add(obj);
			}
			return returned;
		}
	}

	/**
	 * Connect to daemon and get corpus by id
	 * 
	 * @param corpusId
	 * @return
	 * @throws DaemonException 
	 */
	public ICorpus getCorpusById(long corpusId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<CorpusImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<CorpusImpl>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("corpusId", corpusId);
		
		ResponseEntity<DaemonResponse<CorpusImpl>> response = webClient.get("corpus/getCorpusById", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			ICorpus corpus = response.getBody().getContent();
			return corpus;
		}
	}

	/**
	 * Connect to daemon and get publications from corpus
	 * 
	 * @param corpusId
	 * @return
	 * @throws DaemonException 
	 */
	public IDocumentSet getCorpusPublications(Long corpusId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<DocumentSetImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<DocumentSetImpl>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("corpusId", corpusId);
		
		ResponseEntity<DaemonResponse<DocumentSetImpl>> response = webClient.get("corpus/getCorpusPublications", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			IDocumentSet documentSet = response.getBody().getContent();
			return documentSet;
		}
	}
	
	public Long getCorpusPublicationsCount(Long corpusId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Long>> responseType = new ParameterizedTypeReference<DaemonResponse<Long>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("corpusId", corpusId);
		
		ResponseEntity<DaemonResponse<Long>> response = webClient.get("corpus/getCorpusPublicationsCount", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			Long count = response.getBody().getContent();
			return count;
		}
	}
	
	public IDocumentSet getCorpusPublicationsPaginated(Long corpusId, Integer paginationIndex, Integer paginationSize) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<DocumentSetImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<DocumentSetImpl>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("corpusId", String.valueOf(corpusId));
		uriVariables.add("paginationIndex", String.valueOf(paginationIndex));
		uriVariables.add("paginationSize", String.valueOf(paginationSize));
		
		ResponseEntity<DaemonResponse<DocumentSetImpl>> response = webClient.get("corpus/getCorpusPublicationsPaginated", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			IDocumentSet documentSet = response.getBody().getContent();
			return documentSet;
		}
	}

	/**
	 * Register corpus to a process
	 * 
	 * @param corpusId
	 * @param processId
	 * @return
	 * @throws DaemonException 
	 */
	public Boolean registerCorpusProcess(Long corpusId, Long processId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("corpusId", String.valueOf(corpusId));
		uriVariables.add("processId", String.valueOf(processId));

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("corpus/registerCorpusProcess", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
	/**
	 * Get process by corpus id
	 * 
	 * @param corpusId
	 * @return
	 * @throws DaemonException 
	 */
	public List<IIEProcess> getCorpusProcesses(Long corpusId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<IEProcessImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<IEProcessImpl>>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("corpusId", corpusId);

		ResponseEntity<DaemonResponse<List<IEProcessImpl>>> response = webClient.get("corpus/getCorpusProcesses", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			List<IEProcessImpl> processes = response.getBody().getContent();
			List<IIEProcess> returned = new ArrayList<IIEProcess>();
			for (IIEProcess process : processes) {
				returned.add(process);
			}
			return returned;
		}
	}
	
	/**
	 * Add corpus to publication
	 * 
	 * @param corpusId
	 * @param publicationId
	 * @return
	 * @throws DaemonException
	 */
	public Boolean addCorpusPublication(Long corpusId, Long publicationId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("corpusId", String.valueOf(corpusId));
		uriVariables.add("publicationId", String.valueOf(publicationId));
		
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("corpus/addCorpusPublication", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
	
	/**
	 * get Corpus statistics
	 * 
	 * @param corpusId
	 * @return
	 * @throws DaemonException
	 */
	public ICorpusStatistics getCorpusStatistics(Long corpusId) throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<CorpusStatisticsImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<CorpusStatisticsImpl>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("corpusId", corpusId);
		
		ResponseEntity<DaemonResponse<CorpusStatisticsImpl>> response = webClient.get("corpus/getCorpusStatistics", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			ICorpusStatistics corpusStat = response.getBody().getContent();
			return corpusStat;
		}
	}
	

	/**
	 * Get all corpus with owner privileges or role_admin
	 * 
	 * @param corpusId
	 * @return
	 * @throws DaemonException
	 */
	public List<ICorpus> getAllPrivilegesCorpusAdminAccess() throws DaemonException{
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<CorpusImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<CorpusImpl>>>() {};
		ResponseEntity<DaemonResponse<List<CorpusImpl>>> response = webClient.get("corpus/getAllPrivilegesCorpusAdminAccess", responseType);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			List<ICorpus> returned = new ArrayList<ICorpus>();
			List<CorpusImpl> corpus = response.getBody().getContent();
			for (CorpusImpl obj : corpus) {
				returned.add(obj);
			}
			return returned;
		}
	}
}
