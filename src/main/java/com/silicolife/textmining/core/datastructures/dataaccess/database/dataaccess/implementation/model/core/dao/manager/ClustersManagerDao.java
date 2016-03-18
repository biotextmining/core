package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.GenericDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao.ClustersAuxDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterLabelPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProcessHasLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProperties;

/**
 * Class to handler with clusters object DAO
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */

@Repository
public class ClustersManagerDao {

	private GenericDao<ClusterProcesses> clusterProcessesDao;
	private GenericDao<ClusterLabels> clusterLabelsDao;
	private GenericDao<ClusterProperties> clusterPropertiesDao;
	private GenericDao<ClusterProcessHasLabels> clusterProcessHasLabelsDao;
	private GenericDao<ClusterLabelPublications> clusterLabelsPublications;

	private ClustersAuxDao clustersAuxDao;

	@Autowired
	public ClustersManagerDao(GenericDao<ClusterProcesses> clusterProcessesDao, GenericDao<ClusterLabels> clusterLabelsDao, GenericDao<ClusterProperties> clusterPropertiesDao,
			GenericDao<ClusterProcessHasLabels> clusterProcessHasLabelsDao,GenericDao<ClusterLabelPublications> clusterLabelsPublications,
			ClustersAuxDao clustersAuxDao) {
		this.clusterProcessesDao = clusterProcessesDao;
		this.clusterLabelsDao = clusterLabelsDao;
		this.clusterPropertiesDao = clusterPropertiesDao;
		this.clusterProcessHasLabelsDao = clusterProcessHasLabelsDao;
		this.clusterLabelsPublications=clusterLabelsPublications;
		this.clustersAuxDao = clustersAuxDao;
	}

	public GenericDao<ClusterProcesses> getClusterProcessesDao() {
		return clusterProcessesDao;
	}

	public GenericDao<ClusterLabels> getClusterLabelsDao() {
		return clusterLabelsDao;
	}

	public GenericDao<ClusterProperties> getClusterPropertiesDao() {
		return clusterPropertiesDao;
	}

	public GenericDao<ClusterProcessHasLabels> getClusterProcessHasLabelsDao() {
		return clusterProcessHasLabelsDao;
	}

	public ClustersAuxDao getClusterProcessAuxDao() {
		return clustersAuxDao;
	}

	public GenericDao<ClusterLabelPublications> getClusterLabelsPublications() {
		return clusterLabelsPublications;
	}

}
