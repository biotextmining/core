package com.silicolife.textmining.core.interfaces.core.document.structure;

import java.util.List;

public interface IParsingToken extends IChunkerToken,Comparable<IParsingToken>{
	public List<IParsingToken> getConsists();
	public void setText(String originalName);
	public boolean isLeaf();
	public boolean isTop();
	public boolean isSentenceToken();
}
