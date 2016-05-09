package com.silicolife.textmining.core.interfaces.process.IE;

import com.silicolife.textmining.core.datastructures.exceptions.process.InvalidConfigurationException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.processes.IREProcessReport;
import com.silicolife.textmining.core.interfaces.process.IE.re.IREConfiguration;

public interface IREProcess{
	public IREProcessReport executeRE(IREConfiguration configuration) throws ANoteException, InvalidConfigurationException;
	public void stop();
	public void validateConfiguration(IREConfiguration configuration)  throws InvalidConfigurationException;

}
