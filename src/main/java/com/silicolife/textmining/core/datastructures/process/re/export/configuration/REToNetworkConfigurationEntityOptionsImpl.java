package com.silicolife.textmining.core.datastructures.process.re.export.configuration;

import java.util.Set;

import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.IREToNetworkConfigurationEntityOptions;

public class REToNetworkConfigurationEntityOptionsImpl implements IREToNetworkConfigurationEntityOptions{

	
	public Set<Long> classIdsAllowed;
	
	public REToNetworkConfigurationEntityOptionsImpl(Set<Long> classIdsAllowed) {
		super();
		this.classIdsAllowed = classIdsAllowed;
	}

	public Set<Long> getClassIdsAllowed() {
		return classIdsAllowed;
	}

}
