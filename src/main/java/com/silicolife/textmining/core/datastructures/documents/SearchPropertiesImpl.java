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
	
	public SearchPropertiesImpl(){
		this.value = "";
		this.fields = new ArrayList<String>();
		this.wholeWords = false;
		this.keywords = true;
		this.caseSensitive = false;
		this.sugestions = false;
		this.restrictions = new HashMap<String, String>();
	}
	
	public SearchPropertiesImpl(String value, List<String> fields, boolean wholeWords, boolean keywords ,
	boolean caseSensitive, boolean sugestions){
		this.value = value;
		this.fields = fields;
		this.wholeWords = wholeWords;
		this.keywords = keywords;
		this.caseSensitive = caseSensitive;
		this.sugestions = sugestions;
	}
	
	public SearchPropertiesImpl(ISearchProperties s){
		this(s.getValue(), s.getFields(), s.isWholeWords(), s.isKeywords(), s.isCaseSensitive(), s.isSugestions());
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
	@JsonIgnore
	public void addRestriction(String field, String value){
		 this.restrictions.put(field, value);
	}
	
	
}
