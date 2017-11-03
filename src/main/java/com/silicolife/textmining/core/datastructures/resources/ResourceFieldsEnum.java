package com.silicolife.textmining.core.datastructures.resources;

public enum ResourceFieldsEnum {
	
	name ("reso_resource_name"),
	info ("reso_notes");

	
	
	private final String uniqueIdentifier;
	
	ResourceFieldsEnum(String id){
		this.uniqueIdentifier = id;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}
}
