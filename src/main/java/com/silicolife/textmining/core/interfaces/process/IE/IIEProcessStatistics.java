package com.silicolife.textmining.core.interfaces.process.IE;

import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public interface IIEProcessStatistics {
	
	public int getEntitiesSize();
	public int getRelationsSize();
	
	public Map<IAnoteClass,Integer> getClassesNumberOfOcurrences();
	public void setClassesNumberOfOcurrences(Map<IAnoteClass,Integer> mapClassesNumberOFOccurrences);

}
