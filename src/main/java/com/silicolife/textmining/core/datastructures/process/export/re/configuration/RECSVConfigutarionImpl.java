package com.silicolife.textmining.core.datastructures.process.export.re.configuration;

import java.io.File;

import com.silicolife.textmining.core.datastructures.process.export.CSVConfiguration;
import com.silicolife.textmining.core.interfaces.process.IE.IRESchema;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.DefaultDelimiterValue;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.Delimiter;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.TextDelimiter;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRECSVColumns;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRECSVConfiguration;

public class RECSVConfigutarionImpl extends CSVConfiguration implements IRECSVConfiguration{

	private IRECSVColumns columns;
	private Delimiter entitiesDelimiter;
	private Delimiter entityExternalIDMainDelimiter;
	private Delimiter entityExternalIDIntraDelimiter;
	private boolean exportPublicationOtherID;
	private boolean exportResourceExternalID;
	private File file;
	private IRESchema reSchema;
	
	public RECSVConfigutarionImpl(File file, IRESchema reSchema, Delimiter mainDelimiter,TextDelimiter textDelimiter, DefaultDelimiterValue defaultDelimiter,Delimiter entitiesDilimiter,Delimiter entityExternalIDMainDelimiter,
			Delimiter entityExternalIDIntraDelimiter,boolean exportPublicationOtherID,boolean exportResourceExternalID,IRECSVColumns columns) {
		super(mainDelimiter, textDelimiter, defaultDelimiter);
		this.entitiesDelimiter = entitiesDilimiter;
		this.columns = columns;
		this.entityExternalIDMainDelimiter = entityExternalIDMainDelimiter;
		this.entityExternalIDIntraDelimiter = entityExternalIDIntraDelimiter;
		this.exportPublicationOtherID = exportPublicationOtherID;
		this.exportResourceExternalID = exportResourceExternalID;
		this.file = file;
		this.reSchema = reSchema;
	}

	@Override
	public IRECSVColumns getColumnConfiguration() {
		return columns;
	}

	@Override
	public Delimiter getEntityDelimiter() {
		return entitiesDelimiter;
	}

	public static IRECSVConfiguration getDefaultValues(File file, IRESchema reSchema) {
		IRECSVColumns columnConf = new RECSVColumnImpl(0, 1, 3, 5, 6, 2, 4, 7, 8, 9);
		return new RECSVConfigutarionImpl(file, reSchema, Delimiter.TAB, TextDelimiter.QUOTATION_MARK, DefaultDelimiterValue.HYPHEN,Delimiter.SEMICOLON, Delimiter.WHITE_SPACE,Delimiter.COLON,true,true,columnConf);
	}

	@Override
	public Delimiter getEntityExternalIDMainDelimiter() {
		return entityExternalIDMainDelimiter;
	}

	@Override
	public Delimiter getEntityExternalIDIntraDelimiter() {
		return entityExternalIDIntraDelimiter;
	}

	@Override
	public boolean exportPublicationOtherID() {
		return exportPublicationOtherID;
	}

	@Override
	public boolean exportResourceExternalID() {
		return exportResourceExternalID;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public IRESchema getRESchema() {
		return reSchema;
	}

}
