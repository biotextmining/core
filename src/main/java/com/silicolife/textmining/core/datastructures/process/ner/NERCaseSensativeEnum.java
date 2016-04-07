package com.silicolife.textmining.core.datastructures.process.ner;

public enum NERCaseSensativeEnum {
	NONE,
	ONLYINSMALLWORDS,
	INALLWORDS;
	
	private int smallWordsSize = 4;
	
	public int getSmallWordSize(){
		return smallWordsSize;
	}
	
	public void setSmallWordSize(int size){
		if(size>0){
			smallWordsSize = size;
		}
	}

}
