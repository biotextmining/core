package com.silicolife.textmining.core.datastructures.resources.export;

import com.silicolife.textmining.core.datastructures.utils.generic.CSVFileConfigurations;
import com.silicolife.textmining.core.interfaces.resource.export.tsv.IResourceExportConfiguration;

public class ResourceExportConfigurationImpl implements IResourceExportConfiguration{

	private String filePath;
	private CSVFileConfigurations csvFileConfigurations;
	
	
	public ResourceExportConfigurationImpl(String filePath, CSVFileConfigurations csvFileConfigurations)
	{
		this.filePath = filePath;
		this.csvFileConfigurations = csvFileConfigurations;
	}
	
	
	@Override
	public String getFormat() {
		return null;
	}

	@Override
	public String getFile() {
		return filePath;
	}


	@Override
	public CSVFileConfigurations getCSVFileConfigurations() {
		return csvFileConfigurations;
	}

}
