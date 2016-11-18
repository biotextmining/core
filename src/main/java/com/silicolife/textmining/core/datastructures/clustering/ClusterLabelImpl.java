package com.silicolife.textmining.core.datastructures.clustering;

import java.util.List;

import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterLabel;

public class ClusterLabelImpl implements IClusterLabel {

	private long id;
	private String name;
	private Double score;
	private List<Long> clusterPublications;

	public ClusterLabelImpl() {
		super();
	}

	public ClusterLabelImpl(long id, String name, Double score, List<Long> clusterPublications) {
		this.id = id;
		this.name = name;
		this.score = score;
		this.clusterPublications = clusterPublications;
	}
	
	public ClusterLabelImpl(String name, Double score, List<Long> clusterPublications) {
		this(GenerateRandomId.generateID(), name, score, clusterPublications);
	}

	
	
	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Double getScore() {
		return score;
	}
	

	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public List<Long> getClusterPublications() {
		return clusterPublications;
	}

	public void setClusterPublications(List<Long> clusterPublications) {
		this.clusterPublications = clusterPublications;

	}
	
	@Override
	public String toString() {
		String result = getName() + " (" + clusterPublications.size() + ")";
		if (score != null)
			result = result + " Score: " + GlobalOptions.decimalformat.format(getScore());
		return result;
	}
}
