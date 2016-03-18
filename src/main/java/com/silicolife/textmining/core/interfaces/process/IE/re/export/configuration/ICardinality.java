package com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration;


public interface ICardinality extends Comparable<ICardinality>{
	public Long getLeftEntities();
	public Long getRightEntities();
}
