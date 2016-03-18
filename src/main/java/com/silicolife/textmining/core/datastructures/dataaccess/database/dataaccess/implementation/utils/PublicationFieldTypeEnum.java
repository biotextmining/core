package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils;

public enum PublicationFieldTypeEnum {
	abstracttext("abstracttext"), fulltext("fulltext");

	private final String name;

	private PublicationFieldTypeEnum(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

}
