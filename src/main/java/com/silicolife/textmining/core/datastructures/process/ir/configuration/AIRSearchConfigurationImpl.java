package com.silicolife.textmining.core.datastructures.process.ir.configuration;

import java.util.Properties;

import com.silicolife.textmining.core.datastructures.process.ConfigurationImpl;
import com.silicolife.textmining.core.interfaces.process.IR.IIRSearchConfiguration;


public abstract class AIRSearchConfigurationImpl extends ConfigurationImpl implements IIRSearchConfiguration{

	
	public String queryName;
	public Properties propeties;
	
	
	public AIRSearchConfigurationImpl(String queryName,Properties propeties) {
		super();
		this.queryName = queryName;
		this.propeties = propeties;
	}


	@Override
	public Properties getProperties() {
		return propeties;
	}

	@Override
	public String getQueryName() {
		return queryName;
	}


}
