package com.silicolife.textmining.core.datastructures.resources.content;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceClassesContent;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;

public class ResourceContentImpl implements IResourceContent{
	
	private int termNumber;
	private int synonymsNumbers;
	private int externalIds;
	@JsonDeserialize(as = ResourceClassesContentImpl.class)
	private IResourceClassesContent resourceClassContent;
	
	public ResourceContentImpl(int termNumber,int synonymsNumber,int externalIds,IResourceClassesContent resourceClassContent)
	{
		this.termNumber=termNumber;
		this.synonymsNumbers = synonymsNumber;
		this.externalIds = externalIds;
		this.resourceClassContent = resourceClassContent;
	}
	
	public ResourceContentImpl() {
		super();
	}

	@Override
	public IResourceClassesContent getResourceClassContent() {
		return resourceClassContent;
	}

	@Override
	public int getTermNumber() {
		return termNumber;
	}

	@Override
	public int getSynonymsNumbers() {
		return synonymsNumbers;
	}

	@Override
	public int getExternalIds() {
		return externalIds;
	}

	public void setSynonymsNumbers(int synonymsNumber) {
		this.synonymsNumbers = synonymsNumber;
	}

	public void setExternalIds(int externalIds) {
		this.externalIds = externalIds;
	}

	public void setTermNumber(int termNumber) {
		this.termNumber = termNumber;
	}

	public void setResourceClassContent(IResourceClassesContent resourceClassContent) {
		this.resourceClassContent = resourceClassContent;
	}
}
