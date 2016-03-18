package com.silicolife.textmining.core.datastructures.exceptions;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;

public class ColorRestrictionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ColorRestrictionException(String color)
	{
		super(LanguageProperties.getLanguageStream("pt.uminho.anote2.general.color.err.notcolor")+" "+color);
	}

}
