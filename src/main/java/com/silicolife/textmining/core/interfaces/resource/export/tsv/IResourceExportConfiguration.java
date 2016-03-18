package com.silicolife.textmining.core.interfaces.resource.export.tsv;

import com.silicolife.textmining.core.datastructures.utils.generic.CSVFileConfigurations;

public interface IResourceExportConfiguration {
	public String getFormat();
	public String getFile();
	public CSVFileConfigurations getCSVFileConfigurations();
}
