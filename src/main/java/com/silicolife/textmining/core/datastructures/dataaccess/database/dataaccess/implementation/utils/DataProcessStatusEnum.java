package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils;

public enum DataProcessStatusEnum {
	start("start"), running("running"), finished("finished"), stop("stop"), error("error");

	private final String name;

	private DataProcessStatusEnum(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String toString() {
		return name;
	}
}
