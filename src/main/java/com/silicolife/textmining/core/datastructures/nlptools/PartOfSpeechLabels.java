package com.silicolife.textmining.core.datastructures.nlptools;

import java.util.HashSet;
import java.util.Set;

/**
 * Enum class that contans all POS Labels
 * 
 * @author Hugo Costa
 *
 */
public enum PartOfSpeechLabels {
	CC{
		public String getDescription()
		{
			return "Coordinating conjunction";
		}
		
		public String value()
		{
			return "CC";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	CD{
		public String getDescription()
		{
			return "Cardinal number";
		}
		
		public String value()
		{
			return "CD";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	DT{
		public String getDescription()
		{
			return "Determiner";
		}
		
		public String value()
		{
			return "DT";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	EX{
		public String getDescription()
		{
			return "Existential there";
		}
		
		public String value()
		{
			return "EX";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	FW{
		public String getDescription()
		{
			return "Foreign word";
		}
		
		public String value()
		{
			return "FW";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	IN{
		public String getDescription()
		{
			return "Preposition or subordinating conjunction";
		}
		
		public String value()
		{
			return "IN";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	JJ{
		public String getDescription()
		{
			return "Adjective";
		}
		
		public String value()
		{
			return "JJ";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	JJR{
		public String getDescription()
		{
			return "Adjective, comparative";
		}
		
		public String value()
		{
			return "JJR";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	JJS{
		public String getDescription()
		{
			return "Adjective, superlative";
		}
		
		public String value()
		{
			return "JJS";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	LS{
		public String getDescription()
		{
			return "List item marker";
		}
		
		public String value()
		{
			return "LS";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	MD{
		public String getDescription()
		{
			return "Modal";
		}
		
		public String value()
		{
			return "MD";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	NN{
		public String getDescription()
		{
			return "Noun, singular or mass";
		}
		
		public String value()
		{
			return "NN";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	NNS
	{
		public String getDescription()
		{
			return "Noun, plural";
		}
		
		public String value()
		{
			return "NNS";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	NNP{
		public String getDescription()
		{
			return "Proper noun, singular";
		}
		
		public String value()
		{
			return "NNP";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	NNPS
	{
		public String getDescription()
		{
			return "Proper noun, plural";
		}
		
		public String value()
		{
			return "NNPS";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	PDT{
		public String getDescription()
		{
			return "Predeterminer";
		}
		
		public String value()
		{
			return "PDT";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	POS{
		public String getDescription()
		{
			return "Possessive ending";
		}
		
		public String value()
		{
			return "POS";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	PRP{
		public String getDescription()
		{
			return "Personal pronoun";
		}
		
		public String value()
		{
			return "PRP";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	PRP${
		public String getDescription()
		{
			return "Possessive pronoun";
		}
		
		public String value()
		{
			return "PRP$";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	RBR{
		public String getDescription()
		{
			return "Adverb, comparative";
		}
		
		public String value()
		{
			return "RBR";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	RBS{
		public String getDescription()
		{
			return "Adverb, superlative";
		}
		
		public String value()
		{
			return "RBS";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	RP{
		public String getDescription()
		{
			return "Particle";
		}
		
		public String value()
		{
			return "RP";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	SYM{
		public String getDescription()
		{
			return "Symbol";
		}
		
		public String value()
		{
			return "SYM";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	TO{
		public String getDescription()
		{
			return "to";
		}
		
		public String value()
		{
			return "TO";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	UH{
		public String getDescription()
		{
			return "Interjection";
		}
		
		public String value()
		{
			return "UH";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	VB{
		public String getDescription()
		{
			return "Verb, base form";
		}
		
		public String value()
		{
			return "VB";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	VBD
	{
		public String getDescription()
		{
			return "Verb, past tense";
		}
		
		public String value()
		{
			return "VBD";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	VBG{
		public String getDescription()
		{
			return "Verb, gerund or present participle";
		}
		
		public String value()
		{
			return "VBG";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	VBN{
		public String getDescription()
		{
			return "Verb, past participle";
		}
		
		public String value()
		{
			return "VBN";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	VBP{
		public String getDescription()
		{
			return "Verb, non​ 3rd person singular present";
		}
		
		public String value()
		{
			return "VBP";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	VBZ{
		public String getDescription()
		{
			return "Verb, 3rd person singular present";
		}
		
		public String value()
		{
			return "VBZ";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	WDT{
		public String getDescription()
		{
			return "Wh​ determiner";
		}
		
		public String value()
		{
			return "WDT";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	WP{
		public String getDescription()
		{
			return "Wh​ pronoun";
		}
		
		public String value()
		{
			return "WP";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	WP${
		public String getDescription()
		{
			return "Possessive wh​ pronoun";
		}
		
		public String value()
		{
			return "WP$";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	WRB{
		public String getDescription()
		{
			return "Wh​ adverb";
		}
		
		public String value()
		{
			return "WRB";
		}
		
		public boolean getEnableDefaultValue()
		{
			return false;
		}
	},
	LRB{
		public String getDescription()
		{
			return "Left bracket";
		}
		
		public String value()
		{
			return "-LRB-";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	RRB{
		public String getDescription()
		{
			return "Right bracket";
		}
		
		public String value()
		{
			return "-RRB-";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	COMMA{
		public String getDescription()
		{
			return "Comma (,) ";
		}
		
		public String value()
		{
			return ",";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	DOT{
		public String getDescription()
		{
			return "DOT (.) ";
		}
		
		public String value()
		{
			return ".";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	HYPHEN{
		public String getDescription()
		{
			return "Hyphen (-) ";
		}
		
		public String value()
		{
			return ":";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
		}
	},
	APOSTROPHE{
		public String getDescription()
		{
			return "Apostrophe (') ";
		}
		
		public String value()
		{
			return "''";
		}
		
		public boolean getEnableDefaultValue()
		{
			return true;
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
	
	public boolean getEnableDefaultValue()
	{
		return this.getEnableDefaultValue();
	}
	
	public String toString()
	{
		return this.value() +" ( " +this.getDescription() +" )";
	}
	
	public static Set<String> getDefaultPOStags()
	{
		Set<String> posTags = new HashSet<String>();
		for(PartOfSpeechLabels label:PartOfSpeechLabels.values())
		{
			if(label.getEnableDefaultValue())
			{
				posTags.add(label.value());
			}
		}
		return posTags;
	}

}
