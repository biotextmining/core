package com.silicolife.textmining.core.datastructures.process.ner;


public enum NERCaseSensativeEnum {
	NONE,
	ONLYINSMALLWORDS,
	INALLWORDS;
	
	private int smallWordsSize = 8;
	
	public int getSmallWordSize(){
		return smallWordsSize;
	}
	
	public void setSmallWordSize(int size){
		if(size>0){
			smallWordsSize = size;
		}
	}
	
	public static NERCaseSensativeEnum convertStringToNERCaseSensativeEnum(String toconvert)
	{
		if(toconvert.equals("NONE"))
		{
			return NERCaseSensativeEnum.NONE;
		}
		else if(toconvert.equals("ONLYINSMALLWORDS"))
		{
			return NERCaseSensativeEnum.ONLYINSMALLWORDS;
		}
		else if(toconvert.equals("INALLWORDS"))
		{
			return NERCaseSensativeEnum.INALLWORDS;
		}
		return null;
	}

}
