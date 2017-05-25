package com.silicolife.textmining.core.interfaces.core.document.relevance;

public enum RelevanceTypeEnum {
	none{
		@Override
		public String toString() {
			return null;
		}
	},
	irrelevant{
		@Override
		public String toString() {
			return "Irrelevant";
		}
	},
	related{
		@Override
		public String toString() {
			return "Related";
		}
	},
	relevant{
		@Override
		public String toString() {
			return "Relevant";
		}
	};

	public static RelevanceTypeEnum convertString(String string) {
		if(string == null){
			return RelevanceTypeEnum.none;
			
		}else if(string.equals(RelevanceTypeEnum.irrelevant.toString())){
			return RelevanceTypeEnum.irrelevant;
			
		}else if(string.equals(RelevanceTypeEnum.related.toString())){
			return RelevanceTypeEnum.related;
			
		}else if(string.equals(RelevanceTypeEnum.relevant.toString())){
			return RelevanceTypeEnum.relevant;
			
		}
		return RelevanceTypeEnum.none;
	}
}
