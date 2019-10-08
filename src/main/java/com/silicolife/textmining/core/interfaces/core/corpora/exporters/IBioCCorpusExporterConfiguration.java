package com.silicolife.textmining.core.interfaces.core.corpora.exporters;

import java.io.File;

public interface IBioCCorpusExporterConfiguration {
	
	public File getFileToExport();
	
	public String getProcessTypeToExport();
	
	public Integer getPublicationBatchSize();
	

}
