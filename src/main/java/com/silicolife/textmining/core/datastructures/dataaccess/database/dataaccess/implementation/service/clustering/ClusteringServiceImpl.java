package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.clustering;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ClusteringException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.ClustersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.QueriesManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserLogs;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterLabelPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterLabelPublicationsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProcessHasLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProcessHasLabelsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Queries;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryHasClusterProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryHasClusterProcessesId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryHasPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.clustering.ClustersLabelsWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.clustering.ClustersProcessWrapper;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.clustering.ClustersPropertiesWrapper;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterLabel;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;

/**
 * Service layer which implements all operations about clustering.
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Service
@Transactional(readOnly = true)
public class ClusteringServiceImpl implements IClusteringService {

	private ClustersManagerDao clustersManagerDao;
	private UsersManagerDao usersManagerDao;
	private QueriesManagerDao queriesManagerDao;
	private UsersLogged userLogged;

	@Autowired
	public ClusteringServiceImpl(ClustersManagerDao clustersManagerDao, UsersManagerDao usersManagerDao, QueriesManagerDao queriesManagerDao, UsersLogged userLogged) {
		this.clustersManagerDao = clustersManagerDao;
		this.usersManagerDao = usersManagerDao;
		this.queriesManagerDao = queriesManagerDao;
		this.userLogged = userLogged;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean createClustering(IClusterProcess clustering_) {
		/*
		 * save cluster process
		 */
		ClusterProcesses clusterProcess = ClustersProcessWrapper.convertToDaemonStructure(clustering_);
		clustersManagerDao.getClusterProcessesDao().save(clusterProcess);
		
		Properties properties = clustering_.getProperties();
		if (properties != null) {
			Set<ClusterProperties> clustersProperties = ClustersPropertiesWrapper.convertToDaemonStructure(properties, clusterProcess);
			for (ClusterProperties clusterProperty : clustersProperties) {
				createClustersPrperties(clusterProperty);
			}
		}
		/*
		 * Log
		 */
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "cluster_processes/cluster_labels/cluster_properties", null, "create cluster processes");
		usersManagerDao.getAuthUserLogsDao().save(log);

		return true;
	}

	@Override
	public List<IClusterProcess> getClustersFromQuery(Long queryId) {
		List<IClusterProcess> clustersProcess_ = new ArrayList<IClusterProcess>();
		List<ClusterProcesses> clustersProcesses = clustersManagerDao.getClusterProcessAuxDao().findClustersByQueryId(queryId);
		for (ClusterProcesses clusterProcess : clustersProcesses) {
			IClusterProcess clusterProcess_ = ClustersProcessWrapper.convertToAnoteStructure(clusterProcess);
			clustersProcess_.add(clusterProcess_);
		}
//		if (clustersProcess_.size() == 0)
//			return null;

		return clustersProcess_;
	}

	@Override
	public IClusterProcess getClusteringById(Long clusterId) {
		ClusterProcesses clusterProcess = clustersManagerDao.getClusterProcessesDao().findById(clusterId);
		if (clusterProcess == null)
			return null;

		IClusterProcess clusterProcess_ = ClustersProcessWrapper.convertToAnoteStructure(clusterProcess);
		return clusterProcess_;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean inactivateClustering(Long clusteringId) throws ClusteringException {

		ClusterProcesses clustersProcess = clustersManagerDao.getClusterProcessesDao().findById(clusteringId);
		if(clustersProcess == null)
		{
			throw new ClusteringException(ExceptionsCodes.codeNoClusteringProcess, ExceptionsCodes.msgNoClusteringProcess);
		}
		clustersProcess.setClpActive(false);

		clustersManagerDao.getClusterProcessesDao().update(clustersProcess);

		return true;
	}

	/*
	 * private auxiliar methods to save cluster processes
	 */
	private void createClustersPrperties(ClusterProperties properties) {
		clustersManagerDao.getClusterPropertiesDao().save(properties);
	}



	@Transactional(readOnly = false)
	@Override
	public boolean addClusteringLabels(Long cluesteringID,List<IClusterLabel> lables) throws ClusteringException {
		ClusterProcesses clusteringProcess_ = clustersManagerDao.getClusterProcessesDao().findById(cluesteringID);
		if(clusteringProcess_ == null)
		{
			throw new ClusteringException(ExceptionsCodes.codeNoClusteringProcess, ExceptionsCodes.msgNoClusteringProcess);
		}
		Set<QueryHasClusterProcesses> queriesHasClusterProcesses = clusteringProcess_.getQueryHasClusterProcesseses();
		QueryHasClusterProcesses queryhas = queriesHasClusterProcesses.iterator().next();
		Queries queries = queryhas.getQueries();
//		/*
//		 * save clusters associations
//		 */
		if (lables != null) {
			for (IClusterLabel clusterLabel_ : lables) {
				ClusterLabels clusterLabel = ClustersLabelsWrapper.convertToDaemonStructure(clusterLabel_);
				createClustersLabels(queries,clusteringProcess_, clusterLabel,clusterLabel_.getClusterPublications());
			}
		}

		
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "clusters_labels", null, "Update Clusters");
		usersManagerDao.getAuthUserLogsDao().save(log);
		return true;
	}
	
	private void createClustersLabels(Queries query,ClusterProcesses clusterProcess, ClusterLabels clusterLabel, List<Long> labelDocuments) {
		clustersManagerDao.getClusterLabelsDao().save(clusterLabel);

		ClusterProcessHasLabelsId id = new ClusterProcessHasLabelsId(clusterProcess.getClpId(), clusterLabel.getCllClusterLabelId());
		ClusterProcessHasLabels clusterProcessHasLabel = clustersManagerDao.getClusterProcessHasLabelsDao().findById(id);
		if (clusterProcessHasLabel == null) {
			ClusterProcessHasLabels clusterProcessHasLabelToDb = new ClusterProcessHasLabels(id, clusterLabel, clusterProcess);
			clustersManagerDao.getClusterProcessHasLabelsDao().save(clusterProcessHasLabelToDb);
		}
		for(Long docID:labelDocuments)
		{
			ClusterLabelPublicationsId id3 = new ClusterLabelPublicationsId(clusterLabel.getCllClusterLabelId(), query.getQuId(), docID);
			QueryHasPublications queryHasPublications3 = null;
			ClusterLabelPublications clusterLAbelPublication = new ClusterLabelPublications(id3, clusterLabel, queryHasPublications3);
			clustersManagerDao.getClusterLabelsPublications().save(clusterLAbelPublication);
		}
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean registerQueryClustering(Long queryID, Long cluesteringID) throws ClusteringException {
		Queries query = queriesManagerDao.getQueriesDao().findById(queryID);
		if(query==null)
		{
			throw new ClusteringException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);
		}
		ClusterProcesses clusterting = clustersManagerDao.getClusterProcessesDao().findById(cluesteringID);
		if(clusterting == null)
		{
			throw new ClusteringException(ExceptionsCodes.codeNoClusteringProcess, ExceptionsCodes.msgNoClusteringProcess);
		}
		QueryHasClusterProcessesId id = new QueryHasClusterProcessesId(queryID, cluesteringID);
		QueryHasClusterProcesses queriesHasClusters = new QueryHasClusterProcesses(id, clusterting, query);
		queriesManagerDao.getQueryHasClusterProcessesDao().saveOrUpdate(queriesHasClusters);
		
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserLogs log = new AuthUserLogs(user, new Date(), "create", "queries_has_clusters_processes", null, "Association between queries and clusters processes");
		usersManagerDao.getAuthUserLogsDao().save(log);
		
		return true;
		
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean updateQueryClusteringProcess(IClusterProcess clustering)throws ClusteringException {
		ClusterProcesses clusterting = clustersManagerDao.getClusterProcessesDao().findById(clustering.getId());
		if(clusterting == null)
		{
			throw new ClusteringException(ExceptionsCodes.codeNoClusteringProcess, ExceptionsCodes.msgNoClusteringProcess);
		}
		clusterting.setClpDescription(clustering.getDescription());
		clustersManagerDao.getClusterProcessesDao().update(clusterting);
		
		return true;
	}
	
	@Override
	public void setUserLogged(UsersLogged userLogged) {
		this.userLogged = userLogged;
	}
}
