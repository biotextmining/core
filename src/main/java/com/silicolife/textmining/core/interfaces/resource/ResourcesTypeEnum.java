package com.silicolife.textmining.core.interfaces.resource;

public enum ResourcesTypeEnum {
	dictionary("dictionary"),
	lookuptable("lookuptable"),
	rule("rule"),
	ontology("ontology"),
	lexicalwords("lexicalwords")
	;

	private final String name;

	private ResourcesTypeEnum(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String toString() {
		return name;
	}
}
