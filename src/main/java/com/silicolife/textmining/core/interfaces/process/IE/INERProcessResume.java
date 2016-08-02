package com.silicolife.textmining.core.interfaces.process.IE;

import com.silicolife.textmining.core.datastructures.exceptions.process.InvalidConfigurationException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.processes.INERProcessReport;
import com.silicolife.textmining.core.interfaces.process.IE.ner.INERResumeConfiguration;

public interface INERProcessResume {
	public INERProcessReport resumeNER(INERResumeConfiguration configuration) throws ANoteException, InvalidConfigurationException;
	public void stop();
	public void validateConfiguration(INERResumeConfiguration configuration)  throws InvalidConfigurationException;
}
