package com.silicolife.textmining.core.datastructures.resources;

import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum ResourcesLuceneFieldsEnum {
	
	name ("reso_resource_nameNCS","reso_resource_nameCS"),
	info("reso_notesNCS","reso_notesCS"),
	type("resourceTypes.rtyResourceType", "resourceTypes.rtyResourceType");
	
	private final String NCS;
	private final String CS;
	
	ResourcesLuceneFieldsEnum(String NCS,String CS) {
		this.NCS = NCS;
		this.CS = CS;
	}
	 
	 public static String getLuceneField(ISearchProperties searchProperties, String field){
		 ResourcesLuceneFieldsEnum resourcesLuceneFieldsEnum = ResourcesLuceneFieldsEnum.valueOf(field);

				if(searchProperties.isCaseSensitive())
					return resourcesLuceneFieldsEnum.CS;
				else
					return resourcesLuceneFieldsEnum.NCS;
		}

}
