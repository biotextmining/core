package com.silicolife.textmining.core.interfaces.core.report.resources;

public enum ResourceUpdateConflitsType {
	AlreadyHaveTerm{
		
		public String getDescription() {
			return "Term is already Present in Destiny Resource";
		};
	},
	TermInDiferentClasses
	{
		public String getDescription() {
			return "Term is already Present in Destiny Resource whit a diferent entity class";
		};
	},
	AlreadyHaveSynonyms
	{
		public String getDescription() {
			return "Synonym is already Present in Destiny Resource";
		};
	},
	AlteradyHaveExternalID{
		
		public String getDescription() {
			return "Term already has External ID";
		};
		
	};
	
	public String getDescription() {
		return this.getDescription();
	}
}


