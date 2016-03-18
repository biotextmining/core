package com.silicolife.textmining.core.interfaces.core.document.structure;

import java.util.List;

/**
 * Class that represent a Sentence
 * 
 * @author Hugo Costa
 *
 */
public interface ISentence extends Comparable<ISentence> {
	
	/**
	 * Return start offset sentence
	 * 
	 * @return
	 */
	public long getStartOffset();
	
	/**
	 * Return end offset sentence
	 * 
	 * @return
	 */
	public long getEndOffset();
	
	/**
	 * Return sentence Text
	 * 
	 * @return
	 */
	public String getText();
	
	/**
	 * Return {@link List} {@link IPOSToken} from sentence
	 * 
	 * @return
	 */
	public List<IPOSToken> getOrderPOSTokens();
	
	/**
	 * Return {@link List} {@link IChunkerToken} from Sentence
	 * 
	 * @return
	 */
	public List<IChunkerToken> getChunkTokens();
	
	/**
	 * Return {@link List} {@link IParsingToken} from Sentence
	 * 
	 * @return
	 */
	public List<IParsingToken> getParsingTokens();
	
	public void setParsingTokens(List<IParsingToken> parsingtokens);
	
	public String toString2();

}
