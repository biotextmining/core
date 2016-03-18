package com.silicolife.textmining.core.datastructures.dataaccess;

public enum DataAccessSourcesEnum {
	database("database"), daemon("daemon");

	private final String name;

	private DataAccessSourcesEnum(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String toString() {
		return name;
	}
}
