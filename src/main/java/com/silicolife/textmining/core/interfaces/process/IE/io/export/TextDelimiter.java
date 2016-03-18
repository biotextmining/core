package com.silicolife.textmining.core.interfaces.process.IE.io.export;

public enum TextDelimiter {
	
	NONE{
		public String getValue() {
			return "";
		}
		
		public String toString(){
			return "NONE";
		}
	},
	QUOTATION_MARK{
		public String getValue() {
			return "\"";
		}
		
		public String toString() {
			return "Quotation Marks ( \" )";
		}
	},
	USER{					
		public String getValue(){
			return userDelimiter;
		}
		
		public String toString(){
			return "Other :";
		}
	};

	public void setUserDelimiter(String delimiter) {
		this.userDelimiter = delimiter;
	}
	
	public String getValue(){
		return this.getValue();
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	protected String userDelimiter = null;
	protected String name;
	
	
	public static TextDelimiter getDelimiterByString(String settingsDefaultTextDelimiterValue) {
		if(settingsDefaultTextDelimiterValue.equals(TextDelimiter.NONE.name()))
		{
			return TextDelimiter.NONE;
		}
		else if(settingsDefaultTextDelimiterValue.equals(TextDelimiter.QUOTATION_MARK.name()))
		{
			return TextDelimiter.QUOTATION_MARK;
		}
		else
			return null;
	}
	

}
