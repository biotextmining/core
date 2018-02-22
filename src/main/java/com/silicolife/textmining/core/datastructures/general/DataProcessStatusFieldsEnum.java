package com.silicolife.textmining.core.datastructures.general;

public enum DataProcessStatusFieldsEnum {
	id ("dpsId"),
	resourceObjectId ("dpsDataObjectId"),
	resourceType("dpsTypeResource"),
	status ("dpsStatus"),
	report ("dpsReport"),
	progress ("dpsProgress"),
	creationDate ("dpsCreateDate"),
	updateDate ("dpsUpdateDate"),
	finishedDate ("dpsFinishDate"),
	user ("authUsers"),
	none ("none");
	
	private final String uniqueIdentifier;
	
	DataProcessStatusFieldsEnum(String id){
		this.uniqueIdentifier = id;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}
}
