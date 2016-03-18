package com.silicolife.textmining.core.datastructures.documents.structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.silicolife.textmining.core.interfaces.core.document.structure.IParsingToken;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentenceDeepParsingStructure;

public class SentenceDeepParsingStructureImpl extends SentenceImpl implements ISentenceDeepParsingStructure {

	private static final String sentenceCategory = "S";

	private SortedSet<IParsingToken> leaves;
	private LinkedList<IParsingToken> linkeListerminal;
	private List<IParsingToken> nonTerminals;
	private List<IParsingToken> sNodes;

	
	public SentenceDeepParsingStructureImpl(ISentence sentence) {
		super(sentence.getStartOffset(), sentence.getEndOffset(), sentence.getText(),sentence.getOrderPOSTokens(),sentence.getChunkTokens(),sentence.getParsingTokens());
		leaves = new TreeSet<IParsingToken>();
		nonTerminals = new ArrayList<IParsingToken>();
		linkeListerminal = new LinkedList<IParsingToken>();
		sNodes = null;
		buildStructure(sentence);
	}

	private void buildStructure(ISentence sentence) {
		List<IParsingToken> parsingTokens = sentence.getParsingTokens();
		for(IParsingToken parsingToken:parsingTokens)
		{
			if(parsingToken.isLeaf())
			{
				leaves.add(parsingToken);
				linkeListerminal.add(parsingToken);
			}
			else
			{
				nonTerminals.add(parsingToken);
			}
		}
	}


	public List<IParsingToken> getSNodes() {
		if(sNodes == null)
		{
			sNodes = new ArrayList<IParsingToken>();
			for(IParsingToken node:nonTerminals)
			{
				if(node.getCategory().equals(sentenceCategory))
				{
					sNodes.add(node);
				}
			}
		}
		return sNodes;
	}

	@Override
	public IParsingToken getPreviousNode(IParsingToken node) {
		int index = linkeListerminal.indexOf(node);
		if(index>0)
			return linkeListerminal.get(index-1);
		return null;
	}
	
	@Override
	public IParsingToken getNextNode(IParsingToken node) {
		int index = linkeListerminal.indexOf(node);
		if(index<linkeListerminal.size()-1)
			return linkeListerminal.get(index+1);
		return null;
	}

	public SortedSet<IParsingToken> getLeaves() {
		return leaves;
	}

	public List<IParsingToken> getNonTerminals() {
		return nonTerminals;
	}

	@Override
	public IParsingToken getParsingNode(long startOffset, long endOffset) {
		for(IParsingToken parsing : linkeListerminal)
		{
			if(parsing.getStartOffset()<=startOffset && parsing.getEndOffset()>=endOffset)
			{
				return parsing;
			}
			else if(parsing.getStartOffset()<=startOffset && parsing.getEndOffset()>startOffset && (startOffset-parsing.getStartOffset() == endOffset-parsing.getEndOffset()))
			{
				return parsing;
			}
		}
		return null;
	}

}
