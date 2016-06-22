package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.clustering;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ClusteringException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterLabel;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;

/**
 * 
 * Interface to define all methods of Service layer about clustering
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public interface IClusteringService {
	/**
	 * Create a cluster process
	 * 
	 * @param clustering
	 * @return
	 */
	public Boolean createClustering(IClusterProcess clustering_);
	/**
	 * Associate cluster to query
	 * 
	 * @param queryId
	 * @param labels
	 * @return
	 * @throws ClusteringException 
	 */
	public boolean addClusteringLabels(Long clusteringID,List<IClusterLabel> lables) throws ClusteringException;	/**
	 * Get cluster from query
	 * 
	 * @param queryId
	 * @return
	 */
	public List<IClusterProcess> getClustersFromQuery(Long queryId);
	/**
	 * Get cluster by id
	 * 
	 * @param clusterId
	 * @return
	 */
	public IClusterProcess getClusteringById(Long clusterId);
	/**
	 * 
	 * Inactive a cluster
	 * 
	 * @param clusteringId
	 * @return
	 * @throws ClusteringException 
	 */
	public Boolean inactivateClustering(Long clusteringId) throws ClusteringException;
	
	/**
	 * Register Cluster in Query
	 * 
	 * @param queryID
	 * @param cluesteringID
	 * @throws ClusteringException 
	 */
	public Boolean registerQueryClustering(Long queryID, Long cluesteringID) throws ClusteringException;
	
	/**
	 * Update Clustering process
	 * 
	 * @param clustering
	 * @throws ClusteringException
	 */
	public Boolean updateQueryClusteringProcess(IClusterProcess clustering) throws ClusteringException;;
	
	public void setUserLogged(UsersLogged userLogged);
}
