package com.silicolife.textmining.core.datastructures.nlptools;

public enum DeepParsingLabels {
	S{
		public String getDescription()
		{
			return "Sentence or Sub-sentence ";
		}
		
		public String value()
		{
			return "S";
		}
	},
	TOP{
		public String getDescription()
		{
			return "TOP";
		}
		
		public String value()
		{
			return "TOP";
		}
	},
	VP{
		public String getDescription()
		{
			return "Verb Phase";
		}
		
		public String value()
		{
			return "VP";
		}
	},
	NP{
		public String getDescription()
		{
			return "Noun Phase";
		}
		
		public String value()
		{
			return "NP";
		}
	},
	PP{
		public String getDescription()
		{
			return "Preposition Phase";
		}
		
		public String value()
		{
			return "PP";
		}
	},
	ADJP
	{
		public String getDescription()
		{
			return "Adjective Phase";
		}
		
		public String value()
		{
			return "ADJP";
		}
	},
	SBAR	{
		public String getDescription()
		{
			return "SBAR";
		}
		
		public String value()
		{
			return "SBAR";
		}
	},
	DOT	{
		public String getDescription()
		{
			return "DOT";
		}
		
		public String value()
		{
			return ".";
		}
	};
	
	public String getDescription()
	{
		return this.getDescription();
	}
	
	public String value()
	{
		return this.value();
	}
	
	
}
