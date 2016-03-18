package com.silicolife.textmining.core.datastructures.process;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.deserializes.MapClassDeserialize;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public class IEProcessClassesStatsImp {
	
	@JsonDeserialize(using = MapClassDeserialize.class)
	private Map<String,IAnoteClass> klassNameKlass;
	private Map<String,Integer> klassNameNumber;

	public IEProcessClassesStatsImp() {
		klassNameKlass = new HashMap<String, IAnoteClass>();
		klassNameNumber = new HashMap<String, Integer>();
	}

	public Map<String, IAnoteClass> getKlassNameKlass() {
		return klassNameKlass;
	}

	public Map<String, Integer> getKlassNameNumber() {
		return klassNameNumber;
	}

	public void setKlassNameKlass(Map<String, IAnoteClass> klassNameKlass) {
		this.klassNameKlass = klassNameKlass;
	}

	public void setKlassNameNumber(Map<String, Integer> klassNameNumber) {
		this.klassNameNumber = klassNameNumber;
	}
	
	

	public Map<IAnoteClass, Integer> classesNumberOfocuurrences() {
		Map<IAnoteClass, Integer> result = new HashMap<IAnoteClass, Integer>();
		for(String klass:klassNameNumber.keySet())
		{
			IAnoteClass key = klassNameKlass.get(klass);
			Integer value = klassNameNumber.get(klass);
			result.put(key, value);
		}
		return result;
	}

	@JsonIgnore
	public void setClassesNumberOfOcurrences(Map<IAnoteClass, Integer> mapClassesNumberOFOccurrences) {
		klassNameKlass = new HashMap<String, IAnoteClass>();
		klassNameNumber = new HashMap<String, Integer>();
		for(IAnoteClass klass :mapClassesNumberOFOccurrences.keySet())
		{
			klassNameKlass.put(klass.getName(), klass);
			klassNameNumber.put(klass.getName(), mapClassesNumberOFOccurrences.get(klass));
		}
	}
	

	public void addClassStatistics(IAnoteClass klass, int numberOFClassEntities)
	{
		if(!klassNameKlass.containsKey(klass.getName()))
		{
			klassNameKlass.put(klass.getName(), klass);
			klassNameNumber.put(klass.getName(), numberOFClassEntities);
		}
	}
}
