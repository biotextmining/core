package com.silicolife.textmining.core.interfaces.core.cluster;

import java.util.List;
import java.util.Properties;


public interface IClusterProcess {

	public long getId();

	public String getDescription();
	
	public List<IClusterLabel> getClusterLabels();

	public Properties getProperties();
	
	public void setProperties(Properties properties);

}
