package com.silicolife.textmining.core.interfaces.process.IE.re;

import java.util.Set;


public interface IRelationMultiType extends Comparable<Object>{
	public Set<Long> getRightClassID();
	public Set<Long> getLeftClassID();
}
