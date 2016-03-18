package com.silicolife.textmining.core.datastructures.process.re;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeMap;

import com.silicolife.textmining.core.interfaces.core.document.structure.IPOSToken;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentenceSintaxRepresentation;
import com.silicolife.textmining.core.interfaces.process.IE.re.clue.IVerbInfo;


public class SentenceSintaxRepresentationImpl implements ISentenceSintaxRepresentation{
	
	private TreeMap<Long,IPOSToken> sentenceSintax;
	private List<IVerbInfo> verbList;
	private List<Long> terminationPositions;
	private ISentence sentence;
	
	public TreeMap<Long, IPOSToken> getSentenceSintax() {
		return sentenceSintax;
	}
	
	public SentenceSintaxRepresentationImpl(ISentence sentence)
	{
		this.sentence = sentence;
		this.sentenceSintax=new TreeMap<Long, IPOSToken>();
		this.verbList = new ArrayList<IVerbInfo>();
		this.terminationPositions = new ArrayList<Long>();
	}

	public SentenceSintaxRepresentationImpl(ISentence sentence,List<IVerbInfo> verbList,List<Long> terminationPositions)
	{
		this(sentence);
		this.verbList = verbList;
		this.terminationPositions = terminationPositions;
	}
	
	public void addElement(IPOSToken postoken)
	{
		if(postoken.getText().equals(""))
			System.err.print("Element can not be empty " + postoken.getText() +" "+ postoken.getPOSCategory() +" "+ postoken.getMorph());
		else
		{
			this.sentenceSintax.put(postoken.getStartOffset(), postoken);
		}
	}
	
	public IPOSToken getNextElement(Long offset)
	{
		if(this.sentenceSintax.containsKey(offset))
		{
			NavigableSet<Long> tst = this.sentenceSintax.descendingKeySet();
			Iterator<Long> it = this.sentenceSintax.keySet().iterator();
			while(it.hasNext())
			{
				Long offsetSintaxPosition = it.next();
				if(offsetSintaxPosition>=offset)
				{
					IPOSToken triple = this.sentenceSintax.get(offsetSintaxPosition);
					return triple;
				}
			}
		}
		return null;
	}
	

	@Override
	public IPOSToken getPreviousElement(Long offset) {
		NavigableSet<Long> tst = this.sentenceSintax.descendingKeySet();
		Iterator<Long> it = this.sentenceSintax.keySet().iterator();
		return null;
	}


	@Override
	public List<IVerbInfo> getListVerbs() {
		return verbList;
	}

	@Override
	public List<Long> getListTeminationPositions() {
		return terminationPositions;
	}

	@Override
	public ISentence getSentence() {
		return sentence;
	}

}
