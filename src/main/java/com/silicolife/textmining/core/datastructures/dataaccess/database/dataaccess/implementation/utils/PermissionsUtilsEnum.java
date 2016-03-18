package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils;

public enum PermissionsUtilsEnum {
	no_permission("no access"),owner("owner"), read("read"), read_write("read_write");

	private final String name;

	private PermissionsUtilsEnum(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		if (equalsName("no access")) {
			return "No Access";
		} else if (equalsName("owner")) {
			return "Owner";
		}else if (equalsName("read")) {
			return "Read";
		}else if (equalsName("read_write")) {
			return "Read and Write";
		}

		return null;
	}
}
