package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.clustering.ClusterLabelImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterLabelPublications;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ClusterLabels;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Publications;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterLabel;

/**
 * Class to transform anote2 Cluster Labels structures to daemon Cluster Labels
 * structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ClustersLabelsWrapper {

	public static IClusterLabel convertToAnoteStructure(ClusterLabels clusterLabel) {
		Long id = clusterLabel.getCllClusterLabelId();
		String name = clusterLabel.getCllClusterLabelName();
		Double score = clusterLabel.getCllScore();
		List<Long> publicationsId = new ArrayList<Long>();
		/**
		 * get publications
		 */
		Set<ClusterLabelPublications> clustersPub = clusterLabel.getClusterLabelPublicationses();
		for (ClusterLabelPublications clusterPub : clustersPub) {
			Publications pub = clusterPub.getQueryHasPublications().getPublications();
			publicationsId.add(pub.getPubId());
		}
//		if (publicationsId.size() == 0)
//			publicationsId = null;
		
		IClusterLabel clusterLabel_ = new ClusterLabelImpl(id, name, score, publicationsId);
		return clusterLabel_;
	}

	public static ClusterLabels convertToDaemonStructure(IClusterLabel clusterLabel_) {
		Long id = clusterLabel_.getId();
		String name = clusterLabel_.getName();
		Double score = clusterLabel_.getScore();
		ClusterLabels clusterLabels = new ClusterLabels(id, name);
		clusterLabels.setCllScore(score);
		return clusterLabels;
	}
}
