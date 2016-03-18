package com.silicolife.textmining.core.datastructures.documents;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.process.IEProcessClassesStatsImp;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public class AnnotatedDocumentStatisticsImpl implements IAnnotatedDocumentStatistics{
	
	private int entitiesNumber;
	private int eventsNumber;
	private IEProcessClassesStatsImp klassStatistics;
		
	public AnnotatedDocumentStatisticsImpl()
	{
		this.entitiesNumber = 0;
		this.klassStatistics = new IEProcessClassesStatsImp();
	}
	

	@Override
	public int getEntitiesNumber() {
		return entitiesNumber;
	}

	public void setEntitiesNumber(int entitiesNumber) {
		this.entitiesNumber = entitiesNumber;
	}
	
	@Override
	public int getEventsNumber() {
		return eventsNumber;
	}
	

	public void setEventsNumber(int eventsNumber) {
		this.eventsNumber = eventsNumber;
	}
	
	public IEProcessClassesStatsImp getKlassStatistics() {
		return klassStatistics;
	}


	public void setKlassStatistics(IEProcessClassesStatsImp klassStatistics) {
		this.klassStatistics = klassStatistics;
	}

	@JsonIgnore
	@Override
	public Map<IAnoteClass, Integer> getclassNumberOfEntities() {
		return klassStatistics.classesNumberOfocuurrences();
	}


	@Override
	public void addEventNumber(int numberOfevents) {
		this.eventsNumber=numberOfevents;
	}

	
	public void addClassStatistics(IAnoteClass klass, int numberOFClassEntities)
	{
		klassStatistics.addClassStatistics(klass, numberOFClassEntities);
		this.entitiesNumber = entitiesNumber + numberOFClassEntities;

	}
}
