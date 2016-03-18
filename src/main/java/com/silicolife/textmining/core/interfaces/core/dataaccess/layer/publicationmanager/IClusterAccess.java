package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.publicationmanager;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

public interface IClusterAccess {

	/**
	 * Create a cluster process in database with 
	 * 
	 * @param process
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void createClustering(IClusterProcess clustering) throws ANoteException;



	/**
	 * Associate cluster to query
	 * 
	 * @param query
	 * @param clustering
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void addClusteringLabels(IClusterProcess cluster) throws ANoteException;

	/**
	 * Get clusters from a query
	 * 
	 * @param query
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public List<IClusterProcess> getQueryClusters(IQuery query) throws ANoteException;

	/**
	 * Get cluster by id
	 * 
	 * @param clusteringID
	 * @return
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public IClusterProcess getClusterByID(long clusterID) throws ANoteException;

	/**
	 * Remove a cluster
	 * 
	 * @param clustering
	 * @throws DaemonException
	 * @throws DatabaseLoadDriverException
	 */
	public void inactivateCluster(IClusterProcess clustering) throws ANoteException;
	
	/**
	 * Register Query Cluster
	 * 
	 * @param query
	 * @param clustering
	 * @throws ANoteException 
	 */
	public void registerQueryClustering(IQuery query, IClusterProcess clustering) throws ANoteException;

	/**
	 * Update Clustering
	 * 
	 * @param clustering
	 * @throws ANoteException 
	 */
	public void updateQueryClusteringProcess(IClusterProcess clustering) throws ANoteException;


}
