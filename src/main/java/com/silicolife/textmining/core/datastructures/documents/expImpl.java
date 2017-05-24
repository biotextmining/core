package com.silicolife.textmining.core.datastructures.documents;

public class expImpl {
	
	private boolean value;
	
	public expImpl(){
		this.value = false;
	}

	public expImpl(boolean value) {
		super();
		this.value = value;
	}
	
	public expImpl (expImpl value){
		this(value.getValue());
	}
	
	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "expImpl [value=" + value + "]";
	}
	
	
	
	
	/*
	 * public class expImpl {
		
		private String value;
		private List<String> fields;
		private boolean wholeWords;
		private boolean keywords;
		private boolean caseSensitive;
		private boolean sugestions;
		
		public expImpl(){
			this.value = "";
			this.fields = new ArrayList<String>();
			this.wholeWords = false;
			this.keywords = true;
			this.caseSensitive = false;
			this.sugestions = false;
		}

		
		public expImpl(String value, List<String> fields, boolean wholeWords, boolean keywords, boolean caseSensitive,
				boolean sugestions) {
			super();
			this.value = value;
			this.fields = fields;
			this.wholeWords = wholeWords;
			this.keywords = keywords;
			this.caseSensitive = caseSensitive;
			this.sugestions = sugestions;
		}

		public expImpl (expImpl e){
			this(e.getValue(), e.getFields(), e.isWholeWords(), e.isKeywords(), e.isCaseSensitive(), e.isSugestions());
		}
		
		public String getValue() {
			return value;
		}

		public void setValue(String e) {
			this.value = e;
		}

		public List<String> getFields() {
			return fields;
		}

		public void setFields(List<String> fields) {
			this.fields = fields;
		}

		public boolean isWholeWords() {
			return wholeWords;
		}

		public void setWholeWords(boolean wholeWords) {
			this.wholeWords = wholeWords;
		}

		public boolean isKeywords() {
			return keywords;
		}

		public void setKeywords(boolean keywords) {
			this.keywords = keywords;
		}

		public boolean isCaseSensitive() {
			return caseSensitive;
		}

		public void setCaseSensitive(boolean caseSensitive) {
			this.caseSensitive = caseSensitive;
		}

		public boolean isSugestions() {
			return sugestions;
		}

		public void setSugestions(boolean sugestions) {
			this.sugestions = sugestions;
		}
		
		
		

	}
	 * 
	 */


	/*
	 * public class expImpl {
		
		private String value;
		private List<String> fields;
		private String wholeWords;
		private String keywords;
		private String caseSensitive;
		private String sugestions;
		
		public expImpl(){
			this.value = "";
			this.fields = new ArrayList<String>();
			this.wholeWords = "";
			this.keywords = "";
			this.caseSensitive = "";
			this.sugestions = "";
		}

		
		public expImpl(String value, List<String> fields, String wholeWords, String keywords, String caseSensitive,
				String sugestions) {
			super();
			this.value = value;
			this.fields = fields;
			this.wholeWords = wholeWords;
			this.keywords = keywords;
			this.caseSensitive = caseSensitive;
			this.sugestions = sugestions;
		}

		public expImpl (expImpl e){
			this(e.getValue(), e.getFields(), e.isWholeWords(), e.isKeywords(), e.isCaseSensitive(), e.isSugestions());
		}
		
		public String getValue() {
			return value;
		}

		public void setValue(String e) {
			this.value = e;
		}

		public List<String> getFields() {
			return fields;
		}

		public void setFields(List<String> fields) {
			this.fields = fields;
		}

		public String isWholeWords() {
			return wholeWords;
		}

		public void setWholeWords(String wholeWords) {
			this.wholeWords = wholeWords;
		}

		public String isKeywords() {
			return keywords;
		}

		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}

		public String isCaseSensitive() {
			return caseSensitive;
		}

		public void setCaseSensitive(String caseSensitive) {
			this.caseSensitive = caseSensitive;
		}

		public String isSugestions() {
			return sugestions;
		}

		public void setSugestions(String sugestions) {
			this.sugestions = sugestions;
		}
		
		
		

	}
	 * 
	 */
	
	
	/*
	 * 
	 * public class expImpl {
	
	private String value;
	
	public expImpl(){
		this.value = "";
	}

	public expImpl(String value) {
		super();
		this.value = value;
	}
	
	public expImpl (expImpl value){
		this(value.getValue());
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "expImpl [value=" + value + "]";
	}
	 */
	
	
}
