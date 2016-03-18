package com.silicolife.textmining.core.datastructures.documents.structure;

import com.silicolife.textmining.core.interfaces.core.document.structure.IPOSToken;

public class POSTokenImpl extends TokenImpl implements IPOSToken{

	private String posCategory;
	private String morph;
	
	public POSTokenImpl(String uid,long startOffset, long endOffset, String text,String posCategory,String morph) 
	{
		super(uid,startOffset,endOffset,text);
		this.posCategory = posCategory;
		this.morph = morph;
	}


	public String getPOSCategory() {
		return posCategory;
	}
	
	public String toString()
	{
		return super.toString() + "_" + getPOSCategory();
	}


	@Override
	public String toString2() {
		return getText() + "_" + getPOSCategory();
	}


	@Override
	public String getMorph() {
		return morph;
	}


	@Override
	public int compareTo(IPOSToken o) {
		return (int)this.getStartOffset() - (int)o.getStartOffset();
	}

	
}
