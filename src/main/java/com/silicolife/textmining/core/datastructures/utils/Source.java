package com.silicolife.textmining.core.datastructures.utils;

public class Source {
	private int sourceID;
	private String source;
	
	public Source(int sourceID, String source) {
		super();
		this.sourceID = sourceID;
		this.source = source;
	}
	
	public int getSourceID() {
		return sourceID;
	}


	public String getSource() {
		return source;
	}
	
	public String toString()
	{
		return getSource() + " ( " + getSourceID() + " )";
	}
	
}
