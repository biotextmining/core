package com.silicolife.textmining.core.datastructures.process.export;

import com.silicolife.textmining.core.interfaces.process.IE.io.export.DefaultDelimiterValue;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.Delimiter;
import com.silicolife.textmining.core.interfaces.process.IE.io.export.TextDelimiter;

public class CSVConfiguration {
	
	private Delimiter mainDelimiter;
	private TextDelimiter textDelimiter;
	private DefaultDelimiterValue defaultDelimiter;


	public CSVConfiguration(Delimiter mainDelimiter,
			TextDelimiter textDelimiter, DefaultDelimiterValue defaultDelimiter) {
		super();
		this.mainDelimiter = mainDelimiter;
		this.textDelimiter = textDelimiter;
		this.defaultDelimiter = defaultDelimiter;
	}
	
	
	public Delimiter getMainDelimiter() {
		return mainDelimiter;
	}


	public TextDelimiter getTextDelimiter() {
		return textDelimiter;
	}


	public DefaultDelimiterValue getDefaultDelimiter() {
		return defaultDelimiter;
	}
	
}
