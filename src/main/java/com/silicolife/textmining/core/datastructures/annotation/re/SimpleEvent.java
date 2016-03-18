package com.silicolife.textmining.core.datastructures.annotation.re;

import com.silicolife.textmining.core.datastructures.annotation.ner.SimpleEntity;

/**
 * Class that represents a Simple Event 
 * 
 * @author Hugo Costa
 *
 */
public class SimpleEvent implements Comparable<SimpleEvent>{
	private String clue;
	private SimpleEntity sourceEntity;
	private SimpleEntity targetEntity;
	private boolean directed;

	public SimpleEvent(String clue, SimpleEntity sourceEntity, SimpleEntity targetEntity,boolean directed) {
		super();
		this.clue = clue;
		this.sourceEntity = sourceEntity;
		this.targetEntity = targetEntity;
		this.directed = directed;
	}
	
	@Override
	public String toString() {
		return "SimpleEvent [clue=" + clue + ", sourceEntity=" + sourceEntity+ ", targetEntity=" + targetEntity + "]";
	}

	public String getClue() {
		return clue;
	}

	public SimpleEntity getSourceEntity() {
		return sourceEntity;
	}

	public SimpleEntity getTargetEntity() {
		return targetEntity;
	}

	public int compareTo(SimpleEvent o) {
		if(directed)
		{
			if(this.sourceEntity.compareTo(o.getSourceEntity()) == 0)
			{
				if(this.targetEntity.compareTo(o.getTargetEntity()) == 0)
				{
					return this.clue.compareTo(o.getClue());
				}
				else
				{
					return this.targetEntity.compareTo(o.getTargetEntity());
				}
			}
			else
			{
				return this.sourceEntity.compareTo(o.getSourceEntity());
			}
		}
		else
		{
			if(this.sourceEntity.compareTo(o.getSourceEntity()) == 0)
			{
				if(this.targetEntity.compareTo(o.getTargetEntity()) == 0)
				{
					return this.clue.compareTo(o.getClue());
				}
				else
				{
					return this.targetEntity.compareTo(o.getTargetEntity());
				}
			}
			else if(this.sourceEntity.compareTo(o.getTargetEntity()) == 0)
			{
				if(this.targetEntity.compareTo(o.getSourceEntity()) == 0)
				{
					return this.clue.compareTo(o.getClue());
				}
				else
				{
					return this.sourceEntity.compareTo(o.getSourceEntity());
				}
			}
			else
			{
				return this.sourceEntity.compareTo(o.getSourceEntity());
			}
		}
	}

}
