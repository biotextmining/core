package com.silicolife.textmining.core.interfaces.core.analysis;

import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public interface IIEProcessStatistics {
	
	public Long getEntitiesSize();
	public Long getRelationsSize();
	
	public Map<IAnoteClass,Long> getClassesNumberOfOcurrences();
	public void setClassesNumberOfOcurrences(Map<IAnoteClass,Long> mapClassesNumberOFOccurrences);

}
