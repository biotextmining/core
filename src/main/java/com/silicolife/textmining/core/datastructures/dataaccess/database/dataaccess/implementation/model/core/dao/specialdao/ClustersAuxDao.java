package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProcesses;

public interface ClustersAuxDao {

	public List<ClusterProcesses> findClustersByQueryId(Long queryId);
}
