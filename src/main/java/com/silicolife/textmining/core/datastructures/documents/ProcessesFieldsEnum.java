package com.silicolife.textmining.core.datastructures.documents;

public enum ProcessesFieldsEnum {
	name ("pro_name"),
	processOrigin ("po_description"),
	processType ("pt_process_type");
	
	
	private final String uniqueIdentifier;
	
	ProcessesFieldsEnum(String id){
		this.uniqueIdentifier = id;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}
}
