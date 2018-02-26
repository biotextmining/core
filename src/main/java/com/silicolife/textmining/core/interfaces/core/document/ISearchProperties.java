package com.silicolife.textmining.core.interfaces.core.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ISearchProperties {

	public String getValue();

	public void setValue(String value);

	public List<String> getFields();

	public void setFields(ArrayList<String> fields);

	public void addField(String field);

	public boolean isWholeWords();

	public void setWholeWords(boolean wholeWords);

	public boolean isKeywords();

	public void setKeywords(boolean keywords);

	public boolean isCaseSensitive();

	public void setCaseSensitive(boolean caseSensitive);

	public boolean isSugestions();

	public void setSugestions(boolean sugestions);

	

	public Map<String, String> getRestrictions();

	public void setRestrictions(Map<String, String> restrictions);

	public void addRestriction(String field, String value);

	public Map<String, List<String>> getFilters();

	public void setFilters(Map<String, List<String>> filters);

	public List<String> addFilter(String field, List<String> values);

	public boolean addValueToFilter(String field, String value);

	public void setExpression(boolean expression);

	public boolean isExpression();

	public String toHtml();

}
