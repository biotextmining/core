package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils;

/**
 * Enum which save the resource type of anote2daemon
 * 
 * @author Joel Azevedo costa
 * @year 2015
 *
 */
public enum ResourcesTypeUtils {

	queries("queries"), corpus("corpus"), resources("resources"),processes("processes");

	private final String name;

	private ResourcesTypeUtils(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String getName() {
		return name;
	}

	public String toString() {
		if (equalsName("queries")) {
			return "Queries";
		} else if (equalsName("corpus")) {
			return "Corpus";
		} else if (equalsName("resources")) {
			return "Resources";
		} else if (equalsName("processes")) {
			return "Processes";
		}
		return null;
	}
}
