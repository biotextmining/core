package com.silicolife.textmining.core.interfaces.core.document.structure;

import java.util.List;
import java.util.Map;

import com.silicolife.textmining.core.interfaces.process.IE.re.clue.IVerbInfo;

public interface ISentenceSintaxRepresentation{

	public void addElement(IPOSToken token);
	public IPOSToken getNextElement(Long offset);
	public IPOSToken getPreviousElement(Long offset);	
	public Map<Long, IPOSToken> getSentenceSintax();
	public List<IVerbInfo> getListVerbs();
	public List<Long> getListTeminationPositions();
	public ISentence getSentence();

}
