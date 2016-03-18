package com.silicolife.textmining.core.datastructures.annotation.re;

import com.silicolife.textmining.core.interfaces.core.annotation.re.DirectionallyEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.re.PolarityEnum;
import com.silicolife.textmining.core.interfaces.process.IE.re.clue.IVerbInfo;
import com.silicolife.textmining.core.interfaces.process.IE.re.clue.VerbTenseEnum;
import com.silicolife.textmining.core.interfaces.process.IE.re.clue.VerbVoiceEnum;



/**
 * 
 * @author Hugo Costa
 * 
 *
 */

public class VerbInfoImpl implements IVerbInfo{
	
	private String verb;
	private String lemma;
	/**
	 * 0 Negativo
	 * 1 Conditional
	 * 2 Positivo
	 * 3 Unknown
	 */
	private PolarityEnum polarity;
	/**
	 *  Verb Voice
	 */
	private VerbVoiceEnum voice;
	/**
	 * Verb Tense
	 */
	private VerbTenseEnum tense;
	private long startOffset;
	private long endOffset;
	/**
	 * Directionaly of verb
	 * true for L->R
	 * false for R->L
	 */
	private DirectionallyEnum directional;
	
	public VerbInfoImpl(long startOffset,long endOffset,String verb,String lemma, PolarityEnum polarity,DirectionallyEnum directionally)
	{
		this.startOffset=startOffset;
		this.endOffset=endOffset;
		this.verb=verb;
		this.lemma=lemma;
		this.polarity=polarity;
		this.directional = directionally;
	}
	
	public VerbInfoImpl(long startOffset,long endOffset,String verb,String lemma,PolarityEnum polarity,DirectionallyEnum directional,VerbVoiceEnum voice,VerbTenseEnum tense)
	{
		this(startOffset, endOffset, verb, lemma, polarity,directional);
		this.voice=voice;
		this.tense=tense;
	}
	
	public String toString()
	{
		String all = new String();
		all = all.concat("verb : "+this.verb+" lemma : "+this.lemma+" pos "+this.polarity+" tense :"+this.tense.toString()+"voice : "+
				this.voice.toString()+" offset : "+startOffset);
		return all;
	}

	public DirectionallyEnum getDirectionality() {
		return this.directional;
	}

	public long getEndOffset() {
		return this.endOffset;
	}

	public PolarityEnum getPolarity() {
		return this.polarity;
	}

	public long getStartOffset() {
		return this.startOffset;
	}

	public void setDirectionality(DirectionallyEnum dir) {
		this.directional=dir;
		
	}

	public void setPolarity(PolarityEnum polarity) {
		this.polarity=polarity;
	}

	public String getLemma() {
		return this.lemma;
	}

	public VerbTenseEnum getTense() {
		return this.tense;
	}

	public String getVerb() {
		return this.verb;
	}

	public VerbVoiceEnum getVoice() {
		return this.voice;
	}

	public void setLemma(String lemma) {
		this.lemma=lemma;
	}
	
	
	
	

}
