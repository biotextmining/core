package com.silicolife.textmining.core.datastructures.resources;

import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum ResourceElementsLuceneFieldsEnum {
	
	name ("res_elementNCS","res_elementCS");
	
	
	private final String NCS;
	private final String CS;
	
	ResourceElementsLuceneFieldsEnum(String NCS,String CS) {
		this.NCS = NCS;
		this.CS = CS;
	}
	 
	 public static String getLuceneField(ISearchProperties searchProperties, String field){
		 ResourceElementsLuceneFieldsEnum resourceElementsLuceneFieldsEnum = ResourceElementsLuceneFieldsEnum.valueOf(field);

				if(searchProperties.isCaseSensitive())
					return resourceElementsLuceneFieldsEnum.CS;
				else
					return resourceElementsLuceneFieldsEnum.NCS;
		}

}
