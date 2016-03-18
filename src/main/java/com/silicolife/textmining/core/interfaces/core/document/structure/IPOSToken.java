package com.silicolife.textmining.core.interfaces.core.document.structure;


/**
 * Class that represent a POS Tag tokne
 * 
 * @author Hugo Costa
 *
 */
public interface IPOSToken extends IToken, Comparable<IPOSToken>{
	/**
	 * Return POSCategory Category ( Verb, Noun, ..)
	 * @return
	 */
	public String getPOSCategory();
	public String getMorph();
	public String toString2();
}
