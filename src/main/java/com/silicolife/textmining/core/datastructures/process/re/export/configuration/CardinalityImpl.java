package com.silicolife.textmining.core.datastructures.process.re.export.configuration;

import com.silicolife.textmining.core.interfaces.process.IE.re.export.configuration.ICardinality;

/**
 * 
 * Class for represent a relation cardinality 
 * Representation 
 * 0 - Zero
 * 1 - One
 * -1 - To Many
 * 
 * @author Hugo Costa
 *
 */
public class CardinalityImpl implements ICardinality{

	private Long leftEntities;
	private Long righEntities;
	
	public CardinalityImpl(long leftEntities, long righEntities) {
		super();
		this.leftEntities = leftEntities;
		this.righEntities = righEntities;
	}

	public Long getLeftEntities() {
		return leftEntities;
	}

	public Long getRightEntities() {
		return righEntities;
	}

	public int compareTo(ICardinality o) {
		if(this.leftEntities==o.getLeftEntities())
		{
			return (int) (this.righEntities - o.getRightEntities());
		}
		return (int) (this.leftEntities - o.getLeftEntities());
	}
	
	public boolean equals(ICardinality o)
	{
		return compareTo(o) == 0;
	}

	public String toString() {
		return getString(leftEntities) + " x " + getString(righEntities);
	}

	private String getString(long number) {
		if(number == 0)
			return "Zero";
		else if(number == 1)
			return "One";
		return "Many";
	}
	

}
