package com.silicolife.textmining.core.datastructures.process.ir.configuration;

import java.util.Properties;

import com.silicolife.textmining.core.interfaces.process.IR.IIRSearchConfiguration;


public class IRSearchConfigurationImpl implements IIRSearchConfiguration{

	
	public String keywords;
	public String organism;
	public String queryName;
	public Properties propeties;
	
	
	public IRSearchConfigurationImpl(String keywords, String organism,String queryName,Properties propeties) {
		super();
		this.keywords = keywords;
		this.organism = organism;
		this.queryName = queryName;
		this.propeties = propeties;
	}

	@Override
	public String getKeywords() {
		return keywords;
	}

	@Override
	public String getOrganism() {
		return organism;
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
