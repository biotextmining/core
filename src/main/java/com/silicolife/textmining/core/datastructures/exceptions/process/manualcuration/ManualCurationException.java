package com.silicolife.textmining.core.datastructures.exceptions.process.manualcuration;

public class ManualCurationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ManualCurationException(String notes)
	{
		super(notes);
	}

}
