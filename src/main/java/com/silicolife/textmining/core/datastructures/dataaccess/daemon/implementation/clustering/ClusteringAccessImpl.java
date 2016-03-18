package com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.silicolife.textmining.core.datastructures.clustering.ClusterProcessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.implementation.RestClientAccess;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.DaemonResponse;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterLabel;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;

/**
 * Class which implements all clustering daemon access methods
 * 
 * @author Joel Azevedo Costa
 *
 */
public class ClusteringAccessImpl extends RestClientAccess {

	/**
	 * Connect to daemon and get cluster by id
	 * 
	 * @param clusterId
	 * @return
	 * @throws DaemonException 
	 */
	public IClusterProcess getClusteringByID(long clusterId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<ClusterProcessImpl>> responseType = new ParameterizedTypeReference<DaemonResponse<ClusterProcessImpl>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("clusterId", clusterId);
	
		ResponseEntity<DaemonResponse<ClusterProcessImpl>> response = webClient.get("clustering/getClusteringById", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			IClusterProcess clusterProcess = response.getBody().getContent();
			return clusterProcess;
		}
	}

	/**
	 * Connect to daemon and get clusters by query
	 * 
	 * @param queryId
	 * @return
	 * @throws DaemonException 
	 */
	public List<IClusterProcess> getClustersFromQuery(long queryId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<List<ClusterProcessImpl>>> responseType = new ParameterizedTypeReference<DaemonResponse<List<ClusterProcessImpl>>>() {};
		Map<String, Long> uriVariables = new HashMap<String, Long>();
		uriVariables.put("queryId", queryId);
	
		ResponseEntity<DaemonResponse<List<ClusterProcessImpl>>> response = webClient.get("clustering/getClustersFromQuery", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {

			List<IClusterProcess> returned = new ArrayList<IClusterProcess>();
			List<ClusterProcessImpl> clusterProcesses = response.getBody().getContent();
			for (ClusterProcessImpl cluster : clusterProcesses) {
				returned.add(cluster);
			}
			return returned;
		}
	}

	/**
	 * Connect to daemon and create cluster
	 * 
	 * @param clustering
	 * @return
	 * @throws DaemonException 
	 */
	public Boolean createClustering(IClusterProcess clustering) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("clustering/createClusterProcess", responseType, clustering);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * Connect to daemon and associate cluster to query
	 * 
	 * @param queryId
	 * @param clusteringId
	 * @return
	 * @throws DaemonException 
	 */
	public Boolean clusterProcessQueryRegistry(long queryId, long clusteringId) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("queryId", String.valueOf(queryId));
		uriVariables.add("clusteringId", String.valueOf(clusteringId));

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("clustering/clusterProcessQueryRegistry", responseType, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}

	/**
	 * 
	 * 
	 * @param clusteringId
	 * @param labels
	 * @return
	 * @throws DaemonException
	 */
	public Boolean addClusteringLabels(long clusteringId, List<IClusterLabel> labels) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		Map<String, String> uriVariables = new LinkedHashMap<String, String>();
		uriVariables.put("clusteringId", String.valueOf(clusteringId));

		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("clustering/addClusteringLabels", responseType, labels, uriVariables);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
	
	/**
	 * 
	 * 
	 * @param clustering
	 * @return
	 * @throws DaemonException
	 */
	public Boolean updateQueryClusteringProcess(IClusterProcess clustering) throws DaemonException {
		checkAndForceLoginIfNecessary();
		ParameterizedTypeReference<DaemonResponse<Boolean>> responseType = new ParameterizedTypeReference<DaemonResponse<Boolean>>() {};
		ResponseEntity<DaemonResponse<Boolean>> response = webClient.post("clustering/updateQueryClusteringProcess", responseType, clustering);

		if (response.getStatusCode() != HttpStatus.OK) {
			throw new DaemonException(response.getBody().getException().getCode(), response.getBody().getException().getCompletedMessage());
		} else {
			Boolean boo = response.getBody().getContent();
			return boo;
		}
	}
}
