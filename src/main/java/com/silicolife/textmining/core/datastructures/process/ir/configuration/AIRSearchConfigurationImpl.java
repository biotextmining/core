package com.silicolife.textmining.core.datastructures.process.ir.configuration;

import java.util.Properties;

import com.silicolife.textmining.core.datastructures.process.ConfigurationImpl;
import com.silicolife.textmining.core.interfaces.process.IR.IIRSearchConfiguration;


public abstract class AIRSearchConfigurationImpl extends ConfigurationImpl implements IIRSearchConfiguration{


	public String queryName;
	public Properties properties;
	
	public AIRSearchConfigurationImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public AIRSearchConfigurationImpl(String queryName,Properties properties) {
		super();
		this.queryName = queryName;
		this.properties = properties;
	}


	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public String getQueryName() {
		return queryName;
	}

	
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}


	public void setProperties(Properties properties) {
		this.properties = properties;
	}


}
