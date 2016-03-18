package com.silicolife.textmining.core.datastructures.process;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcessStatistics;

public class IEProcessStatisticsImpl implements IIEProcessStatistics{

	private int entitiesSize;
	private int relationsSize;
	private IEProcessClassesStatsImp klassStatistics;
	
	
	public IEProcessStatisticsImpl(int entitiesSize, int relationsSize) {
		super();
		this.entitiesSize = entitiesSize;
		this.relationsSize = relationsSize;
		this.klassStatistics = new IEProcessClassesStatsImp();
	}
	
	public IEProcessStatisticsImpl() {
		super();
	}

	@Override
	public int getEntitiesSize() {
		return entitiesSize;
	}

	@Override
	public int getRelationsSize() {
		return relationsSize;
	}	

	public void setEntitiesSize(int entitiesSize) {
		this.entitiesSize = entitiesSize;
	}

	public void setRelationsSize(int relationsSize) {
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
	public Map<IAnoteClass, Integer> getClassesNumberOfOcurrences() {
		return klassStatistics.classesNumberOfocuurrences();
	}
	
	@JsonIgnore
	@Override
	public void setClassesNumberOfOcurrences(Map<IAnoteClass, Integer> mapClassesNumberOFOccurrences) {
		this.klassStatistics.setClassesNumberOfOcurrences(mapClassesNumberOFOccurrences);
	}
}
