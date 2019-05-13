package com.silicolife.textmining.core.interfaces.core.analysis;

import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public interface IAnnotatedDocumentStatistics {
	
	public Long getEntitiesCount();
	public Long getEventsCount();
	public Map<IAnoteClass, Long> getCountOfEntitiesByClass();
	
	public void addClassStatistics(IAnoteClass klass, Long numberOFClassEntities);
	public void setEntitiesCount(Long numberOfEntities);
	public void setEventsCount(Long numberOfevents);
}
