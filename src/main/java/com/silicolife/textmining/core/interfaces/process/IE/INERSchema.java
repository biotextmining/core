package com.silicolife.textmining.core.interfaces.process.IE;

import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public interface INERSchema extends IIEProcess{
	public Set<Long> getEntityAnnotatedClasses() throws ANoteException;
}
