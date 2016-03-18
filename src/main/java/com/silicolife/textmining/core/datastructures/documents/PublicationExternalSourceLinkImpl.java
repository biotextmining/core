package com.silicolife.textmining.core.datastructures.documents;

import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationExternalSourceLink;

public class PublicationExternalSourceLinkImpl implements IPublicationExternalSourceLink {

	private String sourceInternalId;
	private String source;
	private long sourceId;

	public PublicationExternalSourceLinkImpl() {

	}

	public PublicationExternalSourceLinkImpl(String sourceInternalId, String source) {
		this.sourceId = GenerateRandomId.generateID();
		this.sourceInternalId = sourceInternalId;
		this.source = source;
	}

	public PublicationExternalSourceLinkImpl(String sourceInternalId, long sourceId, String source) {
		this(sourceInternalId, source);
		this.sourceId = sourceId;
	}

	@Override
	public String getSourceInternalId() {
		return sourceInternalId;
	}

	public void setSourceInternalId(String sourceInternalId) {
		this.sourceInternalId = sourceInternalId;
	}

	@Override
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public long getSourceId() {
		return sourceId;
	}

	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}
	
	public String toString()
	{
		return getSource()+": "+getSourceInternalId();
	}
}
