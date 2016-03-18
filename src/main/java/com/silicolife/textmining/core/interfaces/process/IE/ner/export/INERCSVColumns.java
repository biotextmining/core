package com.silicolife.textmining.core.interfaces.process.IE.ner.export;

import com.silicolife.textmining.core.interfaces.process.IE.IIECSVColumns;

public interface INERCSVColumns extends IIECSVColumns{
	public int getClassColumn();
	public int getResourceIDColumn();
	public int getResourceInformation();
	public int getResourceExternalIDs();
	public int getSentenceColumn();
}
