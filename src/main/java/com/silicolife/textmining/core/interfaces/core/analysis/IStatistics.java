package com.silicolife.textmining.core.interfaces.core.analysis;

import java.util.List;
import java.util.Map;

public interface IStatistics<T> {

	public Long getTotalCount();
	
	public Map<T,Long> getCountBy();
	
	public List<IStatistics<?>> getSubStatistics();
	
	public void addSubStatistics(IStatistics<?> substatistic);
	
	public void setTotalCount(Long totalCount);
	
	public void addOrSumCountBy(T objectBy, Long objectCount);
	
	public void addOrSumAllCountBy(Map<T, Long> countbyToAdd);
	
}
