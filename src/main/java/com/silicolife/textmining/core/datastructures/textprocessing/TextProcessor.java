package com.silicolife.textmining.core.datastructures.textprocessing;

public class TextProcessor {
	
	public static int getNumberOFwords(String text)
	{
		String textToCompare = text;
		textToCompare = textToCompare.trim();
		textToCompare = textToCompare.replace("(", "");
		textToCompare = textToCompare.replace(")", "");
		textToCompare = textToCompare.replace(":", "");
		textToCompare = textToCompare.replace(".", "");
		textToCompare = textToCompare.replace(",", "");
		textToCompare = textToCompare.replace("?", "");
		textToCompare = textToCompare.replace("-", "");
		// If empty - no words
		if(textToCompare.isEmpty())
			return 0;
		int words = textToCompare.split("\\s+").length;
		// if just one word no split
		if(words==1)
			return 1;
		// otherwise plus one
		return words + 1 ;
	}

}
