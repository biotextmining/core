package com.silicolife.textmining.core.datastructures.documents.structure;

import java.util.ArrayList;
import java.util.List;

public enum PublicationTypeEnum {
	
	PATENT ("Patent"),
	PUBLICATION ("Publication"),
	BOOK ("Book");
	
	private final String typeIdentifier;
	
	PublicationTypeEnum(String type){
		this.typeIdentifier = type;
	}

	public String getTypeIdentifier() {
		return typeIdentifier;
	}
	
	public static List<String> getAllTypeIdentifiers() {
		ArrayList<String> list =  new ArrayList<String>();
		list.add(PublicationTypeEnum.PATENT.getTypeIdentifier());
		list.add(PublicationTypeEnum.PUBLICATION.getTypeIdentifier());
		list.add(PublicationTypeEnum.BOOK.getTypeIdentifier());
		return list;
	}

}
