package com.silicolife.textmining.core.datastructures.documents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public class SearchPropertiesImpl implements ISearchProperties {
	
	private String value;
	private List<String> fields;
	private boolean wholeWords;
	private boolean keywords;
	private boolean caseSensitive;
	private boolean sugestions;
	private Map<String, String> restrictions;
	private Map<String,List<String>> filters;
	private boolean expression;
	
	public SearchPropertiesImpl(){
		this.value = "";
		this.fields = new ArrayList<String>();
		this.wholeWords = false;
		this.keywords = true;
		this.caseSensitive = false;
		this.sugestions = false;
		this.restrictions = new HashMap<String, String>();
		this.filters = new HashMap<String,List<String>>();
		this.expression = false;
	}
	
	public SearchPropertiesImpl(String value, List<String> fields, boolean wholeWords, boolean keywords ,
	boolean caseSensitive, boolean sugestions, boolean expression){
		this.value = value;
		this.fields = fields;
		this.wholeWords = wholeWords;
		this.keywords = keywords;
		this.caseSensitive = caseSensitive;
		this.sugestions = sugestions;
		this.expression = expression;
	}
	
	public SearchPropertiesImpl(ISearchProperties s){
		this(s.getValue(), s.getFields(), s.isWholeWords(), s.isKeywords(), s.isCaseSensitive(), s.isSugestions(), s.isExpression());
	}
	
	@Override
	public boolean isExpression() {
		return expression;
	}
	
	@Override
	public void setExpression(boolean expression) {
		this.expression = expression;
	}

	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public List<String> getFields() {
		return fields;
	}
	
	@Override
	public void setFields(ArrayList<String> fields) {
		this.fields = fields;
	}
	
	@Override
	public void addField(String field){
		this.fields.add(field);
	}
	
	@Override
	public boolean isWholeWords() {
		return wholeWords;
	}
	
	@Override
	public void setWholeWords(boolean wholeWords) {
		this.wholeWords = wholeWords;
	}
	
	@Override
	public boolean isKeywords() {
		return keywords;
	}
	
	@Override
	public void setKeywords(boolean keywords) {
		this.keywords = keywords;
	}
	
	@Override
	public boolean isCaseSensitive() {
		return caseSensitive;
	}
	
	@Override
	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}
	
	@Override
	public boolean isSugestions() {
		return sugestions;
	}
	
	@Override
	public void setSugestions(boolean sugestions) {
		this.sugestions = sugestions;
	}
	
	
	@Override
	public Map<String, String> getRestrictions() {
		return restrictions;
	}
	
	@Override
	public void setRestrictions(Map<String, String> restrictions) {
		this.restrictions = restrictions;
	}
	
	@Override
	public Map<String, List<String>> getFilters() {
		return filters;
	}
	
	@Override
	public void setFilters(Map<String, List<String>> filters) {
		this.filters = filters;
	}

	@Override
	@JsonIgnore
	public void addRestriction(String field, String value){
		 this.restrictions.put(field, value);
	}
	
	@Override
	@JsonIgnore
	public List<String> addFilter(String field, List<String> values) {
		return this.filters.put(field, values);
	}
	
	@Override
	@JsonIgnore
	public boolean addValueToFilter(String field, String value) {
		return this.filters.get(field).add(value);
	}
	
	@Override
	@JsonIgnore
	public String toHtml() {
		String report = "";
		StringBuilder sb = new StringBuilder();
		//sb.append("<h3>"+searchOn+"</h3>");
		sb.append("<p><b>Search expression:</b> "+this.value+"</p>");
		sb.append("<p><b>Search fields:</b> </p>");
		int count =0;
		int fieldsSize = this.fields.size();
		sb.append("<p>");
		while(count < fieldsSize) {
			if(count == fieldsSize-1) {
				sb.append(this.fields.get(count));
			}else {
				sb.append(this.fields.get(count)+", ");
			}
			count++;
		}
		sb.append("</p>");
		if(!this.filters.isEmpty()) {
			sb.append("<p><b>Filters:</b></p>");
			for(String key : this.filters.keySet()) {
				if(key==PublicationFieldsEnum.yeardate.toString()) {
					sb.append("<p>");
					sb.append(key+": ");
					List<String> fields = this.filters.get(key);
					int size = fields.size();
					sb.append(fields.get(0));
					sb.append(" - ");
					sb.append(fields.get(size-1));
					sb.append("</p>");
				}else {
					sb.append("<p>");
					sb.append(key+": ");
					sb.append(this.filters.get(key).toString());
					sb.append("</p>");
				}
			}
		}
		if(!this.restrictions.isEmpty()) {
			sb.append("<p><b>Restrictions:</b></p>");
			for(String key : this.restrictions.keySet()) {
				sb.append("<p>");
				sb.append(key+": ");
				sb.append(this.restrictions.get(key));
				sb.append("</p>");
			}
		}
		sb.append("<p><b>Search options:</b></p>");
		sb.append("<p>"); sb.append("WholeSentence: "); sb.append(this.isWholeWords()); sb.append("</p>");
		sb.append("<p>"); sb.append("Keywords: "); sb.append(this.isKeywords()); sb.append("</p>");
		sb.append("<p>"); sb.append("Expression: "); sb.append(this.isExpression()); sb.append("</p>");
		sb.append("<p>"); sb.append("Case sensitive: "); sb.append(this.isCaseSensitive()); sb.append("</p>");
		report = sb.toString();
		return report;
	}
	
	
}
