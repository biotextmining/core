package com.silicolife.textmining.core.datastructures.process.export.ner.configuration;

import java.io.File;

import com.silicolife.textmining.core.datastructures.process.export.CSVConfiguration;
import com.silicolife.textmining.core.interfaces.process.IE.INERSchema;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.DefaultDelimiterValue;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.Delimiter;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.TextDelimiter;
import com.silicolife.textmining.core.interfaces.process.IE.ner.export.INERCSVColumns;
import com.silicolife.textmining.core.interfaces.process.IE.ner.export.INERCSVExporterConfiguration;

public class NERCSVExporterConfigurationImpl extends CSVConfiguration implements INERCSVExporterConfiguration{


	private INERCSVColumns columns;
	private boolean exportPublicationID;
	private boolean exportResourceInformation;
	private boolean exportResourceExternalIDs;
	private Delimiter externalIDDelimiter;
	private Delimiter intraExtenalIDdelimiter;
	private File csvFile;
	private INERSchema nerSchema;

	public NERCSVExporterConfigurationImpl(File csvFile, INERSchema nerSchema, Delimiter mainDelemeter,TextDelimiter textDelimiter,DefaultDelimiterValue defaultDelimiter, INERCSVColumns columns) {
		super(mainDelemeter,textDelimiter,defaultDelimiter);
		this.csvFile = csvFile;
		this.nerSchema = nerSchema;
		this.columns = columns;
		this.exportPublicationID = false;
		this.exportResourceInformation = false;
		this.exportResourceExternalIDs = false;
		this.externalIDDelimiter = Delimiter.VERTICAL_BAR;
		this.intraExtenalIDdelimiter = Delimiter.COLON;
		
	}
	
	public NERCSVExporterConfigurationImpl(File csvFile, INERSchema nerSchema, Delimiter mainDelemeter,TextDelimiter textDelimiter,DefaultDelimiterValue defaultDelimiter, INERCSVColumns columns,boolean exportPublicationID,boolean exportResourceInformation,boolean exportResourceExternalIDs,
			Delimiter externalIDDelimiter, Delimiter intraExtenalIDdelimiter) {
		super(mainDelemeter,textDelimiter,defaultDelimiter);
		this.csvFile = csvFile;
		this.nerSchema = nerSchema;
		this.columns = columns;
		this.exportPublicationID = exportPublicationID;
		this.exportResourceInformation =  exportResourceInformation;
		this.exportResourceExternalIDs = exportResourceExternalIDs;
		this.externalIDDelimiter = externalIDDelimiter;
		this.intraExtenalIDdelimiter = intraExtenalIDdelimiter;
	}

	@Override
	public INERCSVColumns getColumnConfiguration() {
		return columns;
	}

	public static NERCSVExporterConfigurationImpl getDefaultSettings(File csvFile, INERSchema nerSchema)
	{
		INERCSVColumns columnsDefenition = new NERCSVColumnImpl(0, 1, 2, 3, 6,4,5,7,8,9);
		return new NERCSVExporterConfigurationImpl(csvFile,  nerSchema, Delimiter.TAB,TextDelimiter.QUOTATION_MARK,DefaultDelimiterValue.HYPHEN,columnsDefenition,true,true,true,Delimiter.SEMICOLON,Delimiter.COLON);
	}

	public boolean exportPublicationOtherID() {
		return exportPublicationID;
	}

	@Override
	public boolean exportResourceInformation() {
		return exportResourceInformation;
	}

	@Override
	public boolean exportResourceExternalID() {
		return exportResourceExternalIDs;
	}
	
	public Delimiter getExternalIDDelimiter() {
		return externalIDDelimiter;
	}
	
	public Delimiter getIntraExtenalIDdelimiter() {
		return intraExtenalIDdelimiter;
	}

	@Override
	public File getFile() {
		return csvFile;
	}

	@Override
	public INERSchema getNERSchema() {
		return nerSchema;
	}


}
