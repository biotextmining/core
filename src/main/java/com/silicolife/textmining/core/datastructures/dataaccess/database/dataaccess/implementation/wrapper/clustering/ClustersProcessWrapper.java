package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.clustering.ClusterProcessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProcessHasLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterProperties;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterLabel;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;

/**
 * Class to transform anote2 Cluster Process structures to daemon
 * Cluster Process structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ClustersProcessWrapper {

	public static IClusterProcess convertToAnoteStructure(ClusterProcesses clusterProcesses) {

		Long id = clusterProcesses.getClpId();
		String name = clusterProcesses.getClpDescription();
		Set<ClusterProperties> clusterProperties = clusterProcesses.getClusterPropertieses();
		Set<ClusterProcessHasLabels> clusterLabels = clusterProcesses.getClusterProcessHasLabelses();
		Properties properties = new Properties();
		List<IClusterLabel> clusterLabels_ = new ArrayList<IClusterLabel>();
		/**
		 * create properties
		 */
		for (ClusterProperties clusterProp : clusterProperties) {
			String key = clusterProp.getId().getClprPropKey();
			String value = clusterProp.getClprPropValue();
			properties.put(key, value);
		}
		/**
		 * create cluster labels
		 */
		for (ClusterProcessHasLabels clusterLabel : clusterLabels) {
			IClusterLabel clusterLabel_ = ClustersLabelsWrapper.convertToAnoteStructure(clusterLabel.getClusterLabels());
			clusterLabels_.add(clusterLabel_);
		}
		
//		if (properties.size() == 0)
//			properties = null;
//
//		if (clusterLabels_.size() == 0)
//			clusterLabels_ = null;

		IClusterProcess clusterProcess_ = new ClusterProcessImpl(id, name, clusterLabels_,properties);
		clusterProcess_.setProperties(properties);
		return clusterProcess_;
	}


	public static ClusterProcesses convertToDaemonStructure(IClusterProcess clusterProcess_) {
		
		Long id = clusterProcess_.getId();
		String description = clusterProcess_.getDescription();
		ClusterProcesses clusterProcesses = new ClusterProcesses(id,true);
		clusterProcesses.setClpDescription(description);
		return clusterProcesses;
	}
}
