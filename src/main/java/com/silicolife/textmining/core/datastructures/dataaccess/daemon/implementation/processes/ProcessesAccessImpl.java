package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.processes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.silicolife.textmining.core.datastructures.corpora.CorpusImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.datastructures.process.IEProcessImpl;
import com.silicolife.textmining.core.datastructures.process.IEProcessStatisticsImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcessStatistics;

/**
 * Class which implements all processes daemon access methods
 * 
 * @author Joel Azevedo Costa
 *
 */
public class ProcessesAccessImpl extends RestClientAccess {
	
	/**
	 * Get process statistics
	 * 
	 * @param corpusId
	 * @return
	 * @throws DaemonException
	 */
	public IIEProcessStatistics getIEProcessStatistics(Long processId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<IEProcessStatisticsImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<IEProcessStatisticsImpl>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("processId", processId);
		
		ResponseEntity<DaemonResponse<IEProcessStatisticsImpl>> response = webClient.get("processes/getProcessStatistics", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			IEProcessStatisticsImpl processStats = response.getBody().getContent();
			return processStats;
		}
	}
	
	/**
	 * Create a new process
	 * 
	 * @param process
	 * @return
	 * @throws DaemonException
	 */
	public Boolean createIEProcess(IIEProcess process) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ICorpus corpusInstance = new CorpusImpl(process.getCorpus().getId(), 
												process.getCorpus().getDescription(), process.getCorpus().getNotes(), process.getCorpus().getProperties());
		IEProcessImpl processInstance = new IEProcessImpl(process.getID(), corpusInstance, 
												process.getName(), process.getNotes(), process.getType(),
												process.getProcessOrigin(), process.getProperties());
		
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("processes/createIEProcess", responseType, processInstance);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
	
	/**
	 * Update process
	 * 
	 * @param process
	 * @return
	 * @throws DaemonException
	 */
	public Boolean updateIEProcess(IIEProcess process) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ICorpus corpusInstance = new CorpusImpl(process.getCorpus().getId(), 
												process.getCorpus().getDescription(), process.getCorpus().getNotes(), process.getCorpus().getProperties());
		IEProcessImpl processInstance = new IEProcessImpl(process.getID(), corpusInstance, 
															process.getName(), process.getNotes(), process.getType(),
															process.getProcessOrigin(), process.getProperties());
		
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.put("processes/updateIEProcess", responseType, processInstance);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
	
	/**
	 * Get process by id
	 * 
	 * @param processId
	 * @return
	 * @throws DaemonException
	 */
	public IIEProcess getProcessByID(Long processId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<IEProcessImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<IEProcessImpl>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("processId", processId);
		ResponseEntity<DaemonResponse<IEProcessImpl>> response = webClient.get("processes/getProcessByID", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IIEProcess process = response.getBody().getContent();
			return process;
		}
	}
	
	
	/**
	 * Get owner or admin processes
	 * 
	 * @return
	 * @throws DaemonException
	 */
	public List<IIEProcess> getPrivilegesAllProcessesAdminAccess() throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<IEProcessImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<IEProcessImpl>>>() {};
		ResponseEntity<DaemonResponse<List<IEProcessImpl>>> response = webClient.get("processes/getPrivilegesAllProcessesAdminAccess", responseType);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getMessage());
		} else {
			List<IIEProcess> returned = new ArrayList<IIEProcess>();
			List<IEProcessImpl> processes = response.getBody().getContent();
			for (IEProcessImpl obj : processes) {
				returned.add(obj);
			}
			return returned;
		}
	}
}
