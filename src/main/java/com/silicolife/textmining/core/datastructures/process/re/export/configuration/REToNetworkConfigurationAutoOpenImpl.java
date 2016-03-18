package com.silicolife.textmining.core.datastructures.process.re.export.configuration;

import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.IREToNetworkConfigurationAutoOpen;

public class REToNetworkConfigurationAutoOpenImpl implements IREToNetworkConfigurationAutoOpen{

	private boolean autoOpen;

	public REToNetworkConfigurationAutoOpenImpl(boolean autoOpen)
	{
		this.autoOpen=autoOpen;
	}
	
	@Override
	public boolean autoOpen() {
		return autoOpen;
	}

}
