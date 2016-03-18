package com.silicolife.textmining.core.interfaces.core.annotation.re;

import com.silicolife.textmining.core.interfaces.process.IE.re.clue.IVerbInfo;



public interface IDirectionality {
	
	public DirectionallyEnum getDirectionality(IVerbInfo verbInfo,com.silicolife.textmining.core.interfaces.core.document.structure.ISentenceSintaxRepresentation sentenceSintax);

}
