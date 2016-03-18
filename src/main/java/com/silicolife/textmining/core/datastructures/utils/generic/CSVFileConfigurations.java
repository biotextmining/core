package com.silicolife.textmining.core.datastructures.utils.generic;

import com.silicolife.textmining.core.interfaces.process.IE.io.export.DefaultDelimiterValue;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.Delimiter;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.TextDelimiter;



public class CSVFileConfigurations {
	
	private Delimiter generalDelimiter;
	private TextDelimiter textDelimiter;
	private ColumnDelemiterDefaultValue columsDelemiterDefaultValue;
	private DefaultDelimiterValue defaultValue;
	private boolean hasHeaders;

	
	public CSVFileConfigurations(Delimiter generalDelimiter,
			TextDelimiter textDelimiters,
			DefaultDelimiterValue defaultValue,
			ColumnDelemiterDefaultValue columsDelemiterDefaultValue,
			boolean hasHeaders)
	{
		this.generalDelimiter=generalDelimiter;
		this.textDelimiter=textDelimiters;
		this.setDefaultValue(defaultValue);
		this.columsDelemiterDefaultValue = columsDelemiterDefaultValue;
		this.setHasHeaders(hasHeaders);
	}
	
	public Delimiter getGeneralDelimiter() {
		return generalDelimiter;
	}

	public TextDelimiter getTextDelimiter() {
		return textDelimiter;
	}

	public void setTextDelimiter(TextDelimiter textDelimiter) {
		this.textDelimiter = textDelimiter;
	}

	public ColumnDelemiterDefaultValue getColumsDelemiterDefaultValue() {
		return columsDelemiterDefaultValue;
	}

	public void setColumsDelemiterDefaultValue(
			ColumnDelemiterDefaultValue columsDelemiterDefaultValue) {
		this.columsDelemiterDefaultValue = columsDelemiterDefaultValue;
	}

	public void setGeneralDelimiter(Delimiter generalDelimiter) {
		this.generalDelimiter = generalDelimiter;
	}

	public DefaultDelimiterValue getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(DefaultDelimiterValue defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isHasHeaders() {
		return hasHeaders;
	}

	public void setHasHeaders(boolean hasHeaders) {
		this.hasHeaders = hasHeaders;
	}


}
