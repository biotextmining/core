package com.silicolife.textmining.core.interfaces.core.cluster;

import java.util.List;

public interface IClusterLabel {

	public long getId();

	public String getName();

	public Double getScore();
	
	public List<Long> getClusterPublications();
}
