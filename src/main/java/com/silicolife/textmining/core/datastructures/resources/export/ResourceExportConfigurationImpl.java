package com.silicolife.textmining.core.datastructures.resources.export;

import java.util.HashMap;
import java.util.Map;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.utils.generic.CSVFileConfigurations;
import com.silicolife.textmining.core.datastructures.utils.generic.ColumnDelemiterDefaultValue;
import com.silicolife.textmining.core.datastructures.utils.generic.ColumnParameters;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.DefaultDelimiterValue;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.Delimiter;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.TextDelimiter;
import com.silicolife.textmining.core.interfaces.resource.export.tsv.IResourceExportConfiguration;

public class ResourceExportConfigurationImpl implements IResourceExportConfiguration{

	private String filePath;
	private CSVFileConfigurations csvFileConfigurations;
	
	
	public ResourceExportConfigurationImpl(String filePath, CSVFileConfigurations csvFileConfigurations)
	{
		this.filePath = filePath;
		this.csvFileConfigurations = csvFileConfigurations;
	}
	
	public ResourceExportConfigurationImpl(String filePath)
	{
		this(filePath, getCSVDefaultConfigurations());
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
	
	private static CSVFileConfigurations getCSVDefaultConfigurations() {
		String termColumn= "Term"; 
		String externalIDColumn= "External Ids"; 
		String synonymsColumn= "Synonyms";
		String classColumn= "Class";
		Map<String, ColumnParameters> columnNameColumnParameters = new HashMap<String, ColumnParameters>();
		ColumnParameters termColum = new ColumnParameters(0, null, null);
		columnNameColumnParameters.put(termColumn, termColum );
		ColumnParameters klass = new ColumnParameters(1, null, null);
		columnNameColumnParameters.put(classColumn, klass );
		Delimiter vbar = Delimiter.USER;
		vbar.setUserDelimiter("|");
		ColumnParameters synonyms = new ColumnParameters(2,vbar, null);
		columnNameColumnParameters.put(synonymsColumn, synonyms );
		ColumnParameters externalids = new ColumnParameters(3, vbar, null, Delimiter.COLON );
		columnNameColumnParameters.put(externalIDColumn, externalids );
		Delimiter generalDelimiter = Delimiter.TAB;
		TextDelimiter textDelimiters = TextDelimiter.QUOTATION_MARK;
		DefaultDelimiterValue defaultValue = DefaultDelimiterValue.HYPHEN;
		ColumnDelemiterDefaultValue columsDelemiterDefaultValue = new ColumnDelemiterDefaultValue(columnNameColumnParameters);
		boolean hasHeaders = true;
		CSVFileConfigurations csvFileConfig = new CSVFileConfigurations(generalDelimiter,textDelimiters,defaultValue,columsDelemiterDefaultValue,hasHeaders);
		return csvFileConfig;
	}

}
