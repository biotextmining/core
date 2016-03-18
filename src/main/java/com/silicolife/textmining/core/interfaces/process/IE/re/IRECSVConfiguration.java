package com.silicolife.textmining.core.interfaces.process.IE.re;

import java.io.File;

import com.silicolife.textmining.core.interfaces.process.IE.IRESchema;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.Delimiter;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.ICSVExportConfiguration;

public interface IRECSVConfiguration extends ICSVExportConfiguration{
	
	public File getFile();
	public IRESchema getRESchema();
	
	public IRECSVColumns getColumnConfiguration();
	public Delimiter getEntityDelimiter();
	public Delimiter getEntityExternalIDMainDelimiter();
	public Delimiter getEntityExternalIDIntraDelimiter();
	public boolean exportPublicationOtherID();
	public boolean exportResourceExternalID();
}
