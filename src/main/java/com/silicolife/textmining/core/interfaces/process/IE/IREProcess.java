package com.silicolife.textmining.core.interfaces.process.IE;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.processes.IREProcessReport;

public interface IREProcess extends IIEProcess{
	public IREProcessReport executeRE() throws ANoteException;
	public void stop();
}
