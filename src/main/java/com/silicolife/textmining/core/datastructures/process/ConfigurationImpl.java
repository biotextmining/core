package com.silicolife.textmining.core.datastructures.process;

import com.silicolife.textmining.core.interfaces.process.IConfiguration;

public class ConfigurationImpl implements IConfiguration{
	
	private String configurationUID;
	
	public ConfigurationImpl() {

	}

	@Override
	public String getConfigurationUID() {
		return configurationUID;
	}

	@Override
	public void setConfigurationUID(String configurationUID) {
		this.configurationUID=configurationUID;
	}

	
	
}
