package com.silicolife.textmining.core.interfaces.core.document;

import java.io.Serializable;

public interface IPublicationExternalSourceLink extends Serializable{
	public String getSource();

	public long getSourceId();

	public String getSourceInternalId();
}
