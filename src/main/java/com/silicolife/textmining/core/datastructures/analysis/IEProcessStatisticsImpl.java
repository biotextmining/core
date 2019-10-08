package com.silicolife.textmining.core.datastructures.analysis;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.interfaces.core.analysis.IIEProcessStatistics;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public class IEProcessStatisticsImpl implements IIEProcessStatistics{

	private Long entitiesSize;
	private Long relationsSize;
	private IEProcessClassesStatsImp klassStatistics;
	
	
	public IEProcessStatisticsImpl(Long entitiesSize, Long relationsSize) {
		super();
		this.entitiesSize = entitiesSize;
		this.relationsSize = relationsSize;
		this.klassStatistics = new IEProcessClassesStatsImp();
	}
	
	public IEProcessStatisticsImpl() {
		super();
	}

	@Override
	public Long getEntitiesSize() {
		return entitiesSize;
	}

	@Override
	public Long getRelationsSize() {
		return relationsSize;
	}	

	public void setEntitiesSize(Long entitiesSize) {
		this.entitiesSize = entitiesSize;
	}

	public void setRelationsSize(Long relationsSize) {
		this.relationsSize = relationsSize;
	}

	public IEProcessClassesStatsImp getKlassStatistics() {
		return klassStatistics;
	}

	public void setKlassStatistics(IEProcessClassesStatsImp klassStatistics) {
		this.klassStatistics = klassStatistics;
	}

	@JsonIgnore
	@Override
	public Map<IAnoteClass, Long> getClassesNumberOfOcurrences() {
		return klassStatistics.classesNumberOfocuurrences();
	}
	
	@JsonIgnore
	@Override
	public void setClassesNumberOfOcurrences(Map<IAnoteClass, Long> mapClassesNumberOFOccurrences) {
		this.klassStatistics.setClassesNumberOfOcurrences(mapClassesNumberOFOccurrences);
	}
}
