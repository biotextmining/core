package com.silicolife.textmining.core.datastructures.clustering;

import java.util.List;
import java.util.Observable;
import java.util.Properties;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterLabel;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;

public class ClusterProcessImpl extends Observable implements IClusterProcess {

	private long id;
	private Properties properties;
	private String description;
	@JsonDeserialize(contentAs=ClusterLabelImpl.class)
	private List<IClusterLabel> clusterLabels;

	public ClusterProcessImpl() {
		super();
	}

	public ClusterProcessImpl(long id,String description, List<IClusterLabel> clusterLabels,Properties properties) {
		super();
		this.id = id;
		this.properties = properties;
		this.description = description;
		this.clusterLabels = clusterLabels;
	}
	
	public ClusterProcessImpl( String desscription, List<IClusterLabel> clusterLabels,Properties properties) {
		this(GenerateRandomId.generateID(), desscription, clusterLabels, properties);
	}

	
	@Override
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}
	
	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public List<IClusterLabel> getClusterLabels() {
		return clusterLabels;
	}

	public void setClusterLabels(List<IClusterLabel> clusterLabels) {
		this.clusterLabels = clusterLabels;

	}
	
	public String toString() {
		return "ID :" + getId() + " " + getDescription();
	}
}
