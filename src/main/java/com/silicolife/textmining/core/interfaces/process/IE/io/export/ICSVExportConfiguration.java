package com.silicolife.textmining.core.interfaces.process.IE.io.export;


public interface ICSVExportConfiguration {
	public Delimiter getMainDelimiter();
	public TextDelimiter getTextDelimiter();
	public DefaultDelimiterValue getDefaultDelimiter();
}
