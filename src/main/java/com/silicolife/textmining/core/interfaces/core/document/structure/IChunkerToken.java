package com.silicolife.textmining.core.interfaces.core.document.structure;



/**
 * Interface that represent a Chunker Token ( From Shallow PArsing results)
 * 
 * @author Hugo Costa
 *
 */
public interface IChunkerToken extends IToken{
	
	/**
	 * return Chunker category ( NN,NP,VP)
	 * 
	 * @return
	 */
	public String getCategory();

	public String toString2();
}
