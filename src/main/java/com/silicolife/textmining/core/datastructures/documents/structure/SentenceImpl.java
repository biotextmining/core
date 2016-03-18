package com.silicolife.textmining.core.datastructures.documents.structure;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.document.structure.IChunkerToken;
import com.silicolife.textmining.core.interfaces.core.document.structure.IPOSToken;
import com.silicolife.textmining.core.interfaces.core.document.structure.IParsingToken;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;

public class SentenceImpl implements ISentence{
	
	

	public long startOffset;
	public long endOffset;
	public String text;
	private List<IPOSToken> orderPosTokens;
	private List<IChunkerToken> orderChunkTokens;
	private List<IParsingToken> parsingTokens;
	
	public SentenceImpl(long startOffset,long endOffset,String text)
	{
		this.startOffset=startOffset;
		this.endOffset=endOffset;
		this.text=text;
	}
	
	public SentenceImpl(long startOffset,long endOffset,String text,List<IPOSToken> orderPosTokens)
	{
		this.startOffset=startOffset;
		this.endOffset=endOffset;
		this.text=text;
		this.orderPosTokens = orderPosTokens;
	}
	
	public SentenceImpl(long startOffset,long endOffset,String text,List<IPOSToken> orderPosTokens,List<IChunkerToken> orderChunkTokens)
	{
		this.startOffset=startOffset;
		this.endOffset=endOffset;
		this.text=text;
		this.orderPosTokens = orderPosTokens;
		this.orderChunkTokens = orderChunkTokens;
	}
	
	public SentenceImpl(long startOffset,long endOffset,String text,List<IPOSToken> orderPosTokens,List<IChunkerToken> orderChunkTokens,List<IParsingToken> parsingtoKens)
	{
		this.startOffset=startOffset;
		this.endOffset=endOffset;
		this.text=text;
		this.orderPosTokens = orderPosTokens;
		this.orderChunkTokens = orderChunkTokens;
		this.parsingTokens = parsingtoKens;
		
	}

	public long getStartOffset() {
		return startOffset;
	}

	public long getEndOffset() {
		return endOffset;
	}

	public String getText() {
		return text;	
	}

	public List<IPOSToken> getOrderPOSTokens() {
		return orderPosTokens;
	}
	
	public String toString()
	{
		String total = new String();
		total = "Limit : "+ getStartOffset() + " <> "+ getEndOffset() + "\n";
		total = total + "Sentence : " + getText() + "\n";
		if(orderPosTokens!=null)
		{
			total = total + "Pos Tags : \n";
			for(IPOSToken token:orderPosTokens)
				total = total + "\t"+token.toString() +"\n";
		}
		if(orderChunkTokens!=null)
		{
			total = total + "Chunker Tags : \n";
			for(IChunkerToken chunkerToken:orderChunkTokens)
				total = total + "\t"+chunkerToken.toString() +"\n";
		}
		return total;
	}
	
	public String toString2()
	{
		String total = new String();
		if(orderPosTokens!=null)
		{
			total = total + "Pos Tags : \n";
			for(IPOSToken token:orderPosTokens)
				total = total + " "+token.toString2();
		}
		if(orderChunkTokens!=null)
		{
			total = total + "Chunker Tags : \n";
			for(IChunkerToken chunkerToken:orderChunkTokens)
				total = total +chunkerToken.toString2();
		}
		return total;
	}

	public List<IChunkerToken> getChunkTokens() {
		return orderChunkTokens;
	}

	public List<IParsingToken> getParsingTokens() {
		return parsingTokens;
	}

	public void setParsingTokens(List<IParsingToken> parsingTokens) {
		this.parsingTokens = parsingTokens;
	}

	public int compareTo(ISentence o) {
		if( this.getStartOffset() ==  o.getStartOffset()){
			return 0;
		}else if( this.getStartOffset() > o.getStartOffset()){
			return 1;
		} else {
			return -1;
		}
	}

}
