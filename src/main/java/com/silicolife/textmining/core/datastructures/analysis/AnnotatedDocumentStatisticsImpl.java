package com.silicolife.textmining.core.datastructures.analysis;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.interfaces.core.analysis.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.analysis.IStatistics;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public class AnnotatedDocumentStatisticsImpl implements IAnnotatedDocumentStatistics{
	
	private IStatistics<IEntityAnnotation> entityStatistics;
	private IStatistics<IEventAnnotation> eventsStatistics;
	private IStatistics<IAnoteClass> klassStatistics;
		
	public AnnotatedDocumentStatisticsImpl(){
		this.entityStatistics = new StatisticsImpl<IEntityAnnotation>();
		this.eventsStatistics = new StatisticsImpl<IEventAnnotation>();
		this.klassStatistics = new StatisticsImpl<IAnoteClass>();
	}
	

	@Override
	public Long getEntitiesCount() {
		return entityStatistics.getTotalCount();
	}

	@Override
	public void setEntitiesCount(Long entitiesNumber) {
		this.entityStatistics.setTotalCount(entitiesNumber);;
	}
	
	@Override
	public Long getEventsCount() {
		return this.eventsStatistics.getTotalCount();
	}
	
	@Override
	public void setEventsCount(Long eventsNumber) {
		this.eventsStatistics.setTotalCount(eventsNumber);
	}
	
	public IStatistics<IAnoteClass> getKlassStatistics() {
		return klassStatistics;
	}


	public void setKlassStatistics(IStatistics<IAnoteClass> klassStatistics) {
		this.klassStatistics = klassStatistics;
	}

	@JsonIgnore
	@Override
	public Map<IAnoteClass, Long> getCountOfEntitiesByClass() {
		return klassStatistics.getCountBy();
	}
	
	public void addClassStatistics(IAnoteClass klass, Long numberOFClassEntities)
	{
		this.klassStatistics.addOrSumCountBy(klass, numberOFClassEntities);
		this.klassStatistics.setTotalCount(this.klassStatistics.getTotalCount() + numberOFClassEntities);
		this.entityStatistics.setTotalCount(this.entityStatistics.getTotalCount() + numberOFClassEntities);

	}
}
