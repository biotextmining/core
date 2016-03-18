package com.silicolife.textmining.core.datastructures.utils.generic;

import com.silicolife.textmining.core.interfaces.process.IE.io.export.DefaultDelimiterValue;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.Delimiter;


public class ColumnParameters {
	
	private int columnNumber;
	private Delimiter delimiter;
	private DefaultDelimiterValue defaultValue;
	private Delimiter subDelimiter;
	
	public ColumnParameters(int columnNumber,Delimiter delimiter,DefaultDelimiterValue defaultValue)
	{
		this.columnNumber=columnNumber;
		this.delimiter=delimiter;
		this.setDefaultValue(defaultValue);
		this.setSubDelimiter(null);
	}
	
	public ColumnParameters(int columnNumber,Delimiter delimiter,DefaultDelimiterValue defaultValue,Delimiter subDelimiter)
	{
		this.columnNumber=columnNumber;
		this.delimiter=delimiter;
		this.setDefaultValue(defaultValue);
		this.setSubDelimiter(subDelimiter);
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public Delimiter getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(Delimiter delimiter) {
		this.delimiter = delimiter;
	}


	public Delimiter getSubDelimiter() {
		return subDelimiter;
	}

	public void setSubDelimiter(Delimiter subDelimiter) {
		this.subDelimiter = subDelimiter;
	}

	public DefaultDelimiterValue getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(DefaultDelimiterValue defaultValue) {
		this.defaultValue = defaultValue;
	}

}
