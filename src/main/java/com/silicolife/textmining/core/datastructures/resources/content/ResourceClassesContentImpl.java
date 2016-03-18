package com.silicolife.textmining.core.datastructures.resources.content;

import java.util.SortedMap;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.deserializes.MapResourceClassesContentDeserialize;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceClassContent;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceClassesContent;

public class ResourceClassesContentImpl implements IResourceClassesContent{
	
	@JsonDeserialize(using = MapResourceClassesContentDeserialize.class)
	private SortedMap<Long, IResourceClassContent> classContent;
	
	public ResourceClassesContentImpl()
	{
		this.classContent = new TreeMap<>();
	}

	@Override
	public SortedMap<Long, IResourceClassContent> getClassContent() {
		return classContent;
	}
		
	public void setClassContent(SortedMap<Long, IResourceClassContent> classContent) {
		this.classContent = classContent;
	}

	
	public void addClassContent(long classID,IResourceClassContent classContent)
	{
		this.classContent.put(classID, classContent);
	}
	
}
