package com.silicolife.textmining.core.interfaces.core.document.relevance;

public enum RelevanceTypeEnum {
	none,
	irrelevant,
	related,
	relevant;

	public static RelevanceTypeEnum convertString(String string) {
		if(string== null)
		{
			return RelevanceTypeEnum.none;
		}
		string = string.toLowerCase();
		if(string.equals("irrelevant"))
		{
			return RelevanceTypeEnum.irrelevant;
		}
		else if(string.equals("related"))
		{
			return RelevanceTypeEnum.related;
		}
		else if(string.equals("relevant"))
		{
			return RelevanceTypeEnum.relevant;
		}
		return RelevanceTypeEnum.none;
	}
}
