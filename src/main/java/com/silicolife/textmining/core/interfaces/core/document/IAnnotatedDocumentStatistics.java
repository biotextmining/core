package com.silicolife.textmining.core.interfaces.core.document;

import java.util.Map;

import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public interface IAnnotatedDocumentStatistics {
	
	public int getEntitiesNumber();
	public int getEventsNumber();
	public Map<IAnoteClass, Integer> getclassNumberOfEntities();
	public void addClassStatistics(IAnoteClass klass, int numberOFClassEntities);
	public void addEventNumber(int numberOfevents);
}
