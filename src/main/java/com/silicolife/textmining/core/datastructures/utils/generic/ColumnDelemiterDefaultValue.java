package com.silicolife.textmining.core.datastructures.utils.generic;

import java.util.Map;


public class ColumnDelemiterDefaultValue {
	
	private Map<String,ColumnParameters> columnNameColumnParameters;
	
	public ColumnDelemiterDefaultValue(Map<String,ColumnParameters> columnNameColumnParameters)
	{
		this.columnNameColumnParameters=columnNameColumnParameters;
	}

	public Map<String, ColumnParameters> getColumnNameColumnParameters() {
		return columnNameColumnParameters;
	}

	public void setColumnNameColumnParameters(
			Map<String, ColumnParameters> columnNameColumnParameters) {
		this.columnNameColumnParameters = columnNameColumnParameters;
	}



}
