package com.silicolife.textmining.core.datastructures.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.silicolife.textmining.core.interfaces.core.analysis.IStatistics;

public class StatisticsImpl<T> implements IStatistics<T> {
	
	private Long totalCount;
	private Map<T, Long> countby;
	private List<IStatistics<?>> substatistics;

	public StatisticsImpl() {
		this.totalCount = 0L;
		this.countby = new HashMap<>();
		this.substatistics = new ArrayList<>();
	}
	
	@Override
	public Long getTotalCount() {
		return this.totalCount;
	}

	@Override
	public Map<T, Long> getCountBy() {
		return this.countby;
	}

	@Override
	public List<IStatistics<?>> getSubStatistics() {
		return this.substatistics;
	}

	@Override
	public void addSubStatistics(IStatistics<?> substatistic) {
		this.substatistics.add(substatistic);
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.totalCount=totalCount;
	}

	@Override
	public void addOrSumCountBy(T objectBy, Long objectCount) {
		
		if(!this.countby.containsKey(objectBy))
			this.countby.put(objectBy, 0L);
		
		this.countby.put(objectBy, this.countby.get(objectBy) + objectCount);
	}

	@Override
	public void addOrSumAllCountBy(Map<T, Long> countbyToAdd) {
		for(Entry<T, Long> entry : countbyToAdd.entrySet())
			this.addOrSumCountBy(entry.getKey(), entry.getValue());
	}

}
