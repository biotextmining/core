package com.silicolife.textmining.core.interfaces.core.dataaccess.layer.corpora.ieprocess;

import java.util.Set;
import java.util.SortedSet;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationsType;

public interface IIEStatisticsAccess {
	
	public Set<String> getIEStatiticsAllLemmas() throws ANoteException;
	
	public SortedSet<IRelationsType> getIEStatiticsAllDefaultRelationsType() throws ANoteException;

}
