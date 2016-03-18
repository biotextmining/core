package com.silicolife.textmining.core.interfaces.process.IE.re.clue;

import com.silicolife.textmining.core.interfaces.core.annotation.re.DirectionallyEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.re.PolarityEnum;


public interface IVerbInfo {
	
	public String getVerb();
	public String getLemma();
	public PolarityEnum getPolarity();
	public VerbVoiceEnum getVoice();
	public VerbTenseEnum getTense();
	public long  getStartOffset();
	public long getEndOffset();
	public DirectionallyEnum getDirectionality();
	
	public void setLemma(String lemma);
	/**
	 *  0 Negativa
	 *  1 Condicional
	 *  2 Positiva
	 *  3 Unknown
	 */
	public void setPolarity(PolarityEnum polarity);
	/**
	 * 0 - LeftToRight
	 * 1 - RightToLeft
	 * 2 - Unknown
	 * 3 - Both
	 */
	public void setDirectionality(DirectionallyEnum dir);

}
