package com.silicolife.textmining.core.interfaces.process.IE.io.export;

public enum DefaultDelimiterValue {
	HYPHEN{
		public String getValue() {
			return "-";
		}
		
		public String toString() {
			return "Hyphen ( - )";
		}
	},
	UNDERSCORE{
		public String getValue() {
			return "_";
		}
		
		public String toString() {
			return "Underscore ( _ )";
		}
	},
	NONE{
		public String getValue() {
			return "";
		}
		
		public String toString() {
			return "NONE ()";
		}
	},
	USER{					
		public String getValue(){
			return userDelimiter;
		}
		
		public String toString() {
			return "Other :";
		}	
	};
	
	public void setUserDelimiter(String delimiter) {
		this.userDelimiter = delimiter;
	}
	
	public String getValue(){
		return this.getValue();
	}
	
	protected String userDelimiter = new String();
	
	public static DefaultDelimiterValue getDelimiterByString(String settingsDefaultValue) {
		if(settingsDefaultValue.equals(DefaultDelimiterValue.HYPHEN.name()))
		{
			return DefaultDelimiterValue.HYPHEN;
		}
		else if(settingsDefaultValue.equals(DefaultDelimiterValue.UNDERSCORE.name()))
		{
			return DefaultDelimiterValue.UNDERSCORE;

		}
		else if(settingsDefaultValue.equals(DefaultDelimiterValue.NONE.name()))
		{
			return DefaultDelimiterValue.NONE;
		}
		return null;
	}

}
