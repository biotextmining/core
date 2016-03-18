package com.silicolife.textmining.core.interfaces.process.IE.ner.export;

import java.io.File;

import com.silicolife.textmining.core.interfaces.process.IE.INERSchema;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.Delimiter;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.ICSVExportConfiguration;

public interface INERCSVExporterConfiguration extends ICSVExportConfiguration{
	
	public File getFile();
	public INERSchema getNERSchema();
	
	public INERCSVColumns getColumnConfiguration();
	public Delimiter getExternalIDDelimiter();
	public Delimiter getIntraExtenalIDdelimiter();
	public boolean exportPublicationOtherID();
	public boolean exportResourceInformation();
	public boolean exportResourceExternalID();

}
