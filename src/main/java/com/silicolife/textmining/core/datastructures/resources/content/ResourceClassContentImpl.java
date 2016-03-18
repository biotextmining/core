package com.silicolife.textmining.core.datastructures.resources.content;

import com.silicolife.textmining.core.interfaces.resource.content.IResourceClassContent;

public class ResourceClassContentImpl implements IResourceClassContent{
	
	private int termNumber;
	private int synonymNumber;

	public ResourceClassContentImpl(int termNumber,int synonymNumber)
	{
		this.termNumber = termNumber;
		this.synonymNumber = synonymNumber;
	}

	public ResourceClassContentImpl() {
		super();
	}

	@Override
	public int getTermNumber() {
		return termNumber;
	}

	@Override
	public int getSynonymNumber() {
		return synonymNumber;
	}

	public void setTermNumber(int termNumber) {
		this.termNumber = termNumber;
	}

	public void setSynonymNumber(int synonymNumber) {
		this.synonymNumber = synonymNumber;
	}
}
