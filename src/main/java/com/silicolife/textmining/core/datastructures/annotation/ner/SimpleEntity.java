package com.silicolife.textmining.core.datastructures.annotation.ner;

import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

/**
 * 
 * Class that represent a Simple Entity .
 * 
 * @author Hugo Costa
 *
 */
public class SimpleEntity implements Comparable<SimpleEntity>{
	
	private String name;
	private long classID;
	private IResourceElement resourceElement;
	
	/**
	 * 
	 * @param name
	 * @param classID
	 * @param resourceElementID
	 */
	public SimpleEntity(String name, long classID, IResourceElement resourceElement) {
		super();
		this.name = name;
		this.classID = classID;
		this.resourceElement = resourceElement;
	}

	public SimpleEntity clone(){
		return new SimpleEntity(getName(), getClassID(), getResourceElement());
	}

	public String toString() {
		return "SimpleEntity [name=" + name + ", classID=" + classID
				+ ", resourceElementID=" + resourceElement + "]";
	}


	public long getClassID() {
		return classID;
	}

	public String getName() {
		return name;
	}
	
	public int compareTo(SimpleEntity o) {
		if(this.getResourceElement()!=null && o.getResourceElement() != null && this.getResourceElement().getId() == o.getResourceElement().getId())
		{
			return 0;
		}
		else if(this.getName().equals(o.getName()))
		{
			return (int) (this.getClassID() - o.getClassID());
		}
		else
		{
			return this.getName().compareTo(o.getName());
		}
	}
	
	public boolean equals(SimpleEntity o)
	{
		return compareTo(o) == 0;
	}


	public IResourceElement getResourceElement() {
		return resourceElement;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
