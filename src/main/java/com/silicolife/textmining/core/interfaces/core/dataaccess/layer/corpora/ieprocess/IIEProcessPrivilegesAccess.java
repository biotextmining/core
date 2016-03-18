package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.corpora.ieprocess;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public interface IIEProcessPrivilegesAccess {
	
	public List<IIEProcess> getPrivilegesAllProcessesAdminAccess() throws ANoteException;


}
