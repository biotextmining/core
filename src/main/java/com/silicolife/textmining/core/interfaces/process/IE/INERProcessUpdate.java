package com.silicolife.textmining.core.interfaces.process.IE;

import com.silicolife.textmining.core.datastructures.exceptions.process.InvalidConfigurationException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.processes.INERProcessReport;
import com.silicolife.textmining.core.interfaces.process.IE.ner.INERConfiguration;

public interface INERProcessUpdate {
	public INERProcessReport updateNER(INERConfiguration configuration) throws ANoteException, InvalidConfigurationException;
	public void stop();
	public void validateUpdateConfiguration(INERConfiguration configuration)  throws InvalidConfigurationException;

}
