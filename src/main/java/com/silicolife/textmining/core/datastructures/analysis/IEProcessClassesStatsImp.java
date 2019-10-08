package com.silicolife.textmining.core.datastructures.analysis;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.deserializes.MapClassDeserialize;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public class IEProcessClassesStatsImp {
	
	@JsonDeserialize(using = MapClassDeserialize.class)
	private Map<String,IAnoteClass> klassNameKlass;
	private Map<String,Long> klassNameNumber;

	public IEProcessClassesStatsImp() {
		klassNameKlass = new HashMap<String, IAnoteClass>();
		klassNameNumber = new HashMap<String, Long>();
	}

	public Map<String, IAnoteClass> getKlassNameKlass() {
		return klassNameKlass;
	}

	public Map<String, Long> getKlassNameNumber() {
		return klassNameNumber;
	}

	public void setKlassNameKlass(Map<String, IAnoteClass> klassNameKlass) {
		this.klassNameKlass = klassNameKlass;
	}

	public void setKlassNameNumber(Map<String, Long> klassNameNumber) {
		this.klassNameNumber = klassNameNumber;
	}
	
	

	public Map<IAnoteClass, Long> classesNumberOfocuurrences() {
		Map<IAnoteClass, Long> result = new HashMap<IAnoteClass, Long>();
		for(String klass:klassNameNumber.keySet())
		{
			IAnoteClass key = klassNameKlass.get(klass);
			Long value = klassNameNumber.get(klass);
			result.put(key, value);
		}
		return result;
	}

	@JsonIgnore
	public void setClassesNumberOfOcurrences(Map<IAnoteClass, Long> mapClassesNumberOFOccurrences) {
		klassNameKlass = new HashMap<String, IAnoteClass>();
		klassNameNumber = new HashMap<String, Long>();
		for(IAnoteClass klass :mapClassesNumberOFOccurrences.keySet())
		{
			klassNameKlass.put(klass.getName(), klass);
			klassNameNumber.put(klass.getName(), mapClassesNumberOFOccurrences.get(klass));
		}
	}
	

	public void addClassStatistics(IAnoteClass klass, Long numberOFClassEntities)
	{
		if(!klassNameKlass.containsKey(klass.getName()))
		{
			klassNameKlass.put(klass.getName(), klass);
			klassNameNumber.put(klass.getName(), numberOFClassEntities);
		}
	}
}
