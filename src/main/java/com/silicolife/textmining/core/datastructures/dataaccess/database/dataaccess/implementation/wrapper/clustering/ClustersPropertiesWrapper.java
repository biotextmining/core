package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.clustering;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterPropertiesId;

/**
 * Class to transform anote2 Cluster Properties structures to daemon
 * Cluster Properties structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ClustersPropertiesWrapper {

	public static Properties convertToAnoteStructure(Set<ClusterProperties> clusterProperties) {

		Properties properties = new Properties();
		for (ClusterProperties clusterProperty : clusterProperties) {
			String key = clusterProperty.getId().getClprPropKey();
			String value = clusterProperty.getClprPropValue();
			properties.put(key, value);
		}

		//		if (properties.size() == 0)
		//			return null;

		return properties;
	}

	public static Set<ClusterProperties> convertToDaemonStructure(Properties properties, ClusterProcesses clusterProcess) {

		Set<ClusterProperties> clusterProperties = new HashSet<ClusterProperties>(0);
		for (Object key : properties.keySet()) {
			Object value = properties.get(key);
			ClusterPropertiesId clusterPropertiesId = new ClusterPropertiesId(clusterProcess.getClpId(), key.toString());
			ClusterProperties clusterPropertiesDaemon = new ClusterProperties(clusterPropertiesId, clusterProcess, value.toString());
			clusterProperties.add(clusterPropertiesDaemon);
		}

		return clusterProperties;
	}
}
