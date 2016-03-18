package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils;

public enum RolesEnum {
	role_admin("role_admin"), role_general("role_general");

	private final String name;

	private RolesEnum(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String toString() {
		return name;
	}
}
