package com.silicolife.textmining.core.datastructures.documents.structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.document.structure.IParsingToken;

public class ParsingTokenImpl extends ChunkerTokenImpl implements IParsingToken{

	private static final String sentenceCategory = "S";
	private static final String topCategory = "TOP";
	
	private List<IParsingToken> children;
	private Set<String> childrenUIDS;
	
	public ParsingTokenImpl(String uid, long startOffset, long endOffset,String chunkCatagory, String text) {
		super(uid, startOffset, endOffset, chunkCatagory, text);
		this.children = new ArrayList<IParsingToken>();
		this.childrenUIDS = new HashSet<>();
	}

	public void addConsist(IParsingToken child)
	{
		if(!childrenUIDS.contains(child.getUID()))
		{
			children.add(child);
			childrenUIDS.add(child.getUID());
		}
	}
	
	public List<IParsingToken> getConsists() {
		return children;
	}

	@Override
	public void setText(String originalName) {
		super.setText(originalName);
	}

	@Override
	public int compareTo(IParsingToken o) {
		if(this.getStartOffset() == o.getStartOffset())
		{
			if(this.getEndOffset() == o.getEndOffset())
			{
				return this.getCategory().compareTo(o.getCategory());
			}
			else
			{
				return (int)this.getEndOffset() - (int)o.getEndOffset();
			}
		}
		return (int)this.getStartOffset() - (int)o.getStartOffset();
	}

	@Override
	public boolean isLeaf() {
		return children.isEmpty();
	}

	public boolean isTop() {
		return getCategory().equals(topCategory);
	}

	public boolean isSentenceToken() {
		return getCategory().equals(sentenceCategory);
	}
	

}
