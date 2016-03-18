package com.silicolife.textmining.core.interfaces.process.IR;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.processes.ir.IIRSearchUpdateReport;
import com.silicolife.textmining.core.interfaces.process.IR.exception.InternetConnectionProblemException;
import com.silicolife.textmining.core.interfaces.process.utils.ISimpleTimeLeft;

public interface IIRUniqueProcess {
	
	public IQueryOriginType getQueryOriginType();
	
	public IIRSearchUpdateReport updateQuery(IQuery queryInfo,ISimpleTimeLeft progress) throws ANoteException, InternetConnectionProblemException ;

	public void stop(); 
}
