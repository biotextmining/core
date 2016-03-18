package com.silicolife.textmining.core.interfaces.core.document.structure;

import java.util.List;
import java.util.SortedSet;

public interface ISentenceDeepParsingStructure extends ISentence{
	public SortedSet<IParsingToken> getLeaves();
	public List<IParsingToken> getNonTerminals();
	public List<IParsingToken> getSNodes();
	public IParsingToken getPreviousNode(IParsingToken node);
	public IParsingToken getParsingNode(long startOffset, long endOffset);
	public IParsingToken getNextNode(IParsingToken token);
}
