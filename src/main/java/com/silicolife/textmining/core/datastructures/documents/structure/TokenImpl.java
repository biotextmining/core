package com.silicolife.textmining.core.datastructures.documents.structure;

import java.util.Properties;

import com.silicolife.textmining.core.interfaces.core.document.structure.IToken;

public class TokenImpl implements IToken{
	private long startOffset;
	private long endOffset;
	private String text;
	private String uid;
	private Properties properties;
	
	public TokenImpl(String uid,long startOffset, long endOffset, String text) {
		this.startOffset = startOffset;
		this.endOffset = endOffset;
		this.text = text;
		this.uid = uid;
		this.properties = new Properties();
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
	
	public String toString()
	{
		return getStartOffset() + " <> " + getEndOffset() + " " + getText();
	}

	@Override
	public String getUID() {
		return uid;
	}

	protected void setText(String originalName) {
		this.text = originalName;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public void addProperty(String key,Object value)
	{
		this.properties.put(key, value);
	}
}
