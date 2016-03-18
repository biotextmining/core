package com.silicolife.textmining.core.datastructures.documents.structure;

import com.silicolife.textmining.core.interfaces.core.document.structure.IChunkerToken;

public class ChunkerTokenImpl extends TokenImpl implements IChunkerToken{

	private String chunkCAtagory;
	
	public ChunkerTokenImpl(String uid,long startOffset, long endOffset,String chunkCatagory, String text) {
		super(uid,startOffset, endOffset, text);
		this.chunkCAtagory = chunkCatagory;
	}

	@Override
	public String getCategory() {
		return chunkCAtagory;
	}
	
	public String toString()
	{
		return super.toString() + "_" + getCategory();
	}

	@Override
	public String toString2() {

		return "*" + getText() + "*_" + getCategory() + " ";
	}

	protected void setText(String originalName) {
		super.setText(originalName);
	}

}
