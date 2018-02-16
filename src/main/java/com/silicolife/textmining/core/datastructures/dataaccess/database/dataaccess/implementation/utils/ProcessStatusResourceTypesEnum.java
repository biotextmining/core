package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils;

public enum ProcessStatusResourceTypesEnum {
	queries("queries"), resources("resources"), corpus("corpus"), ner("ner"), re("re");

	private final String name;

	private ProcessStatusResourceTypesEnum(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String toString() {
		return name;
	}
}
