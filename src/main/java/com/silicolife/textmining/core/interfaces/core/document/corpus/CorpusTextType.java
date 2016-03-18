package com.silicolife.textmining.core.interfaces.core.document.corpus;

import com.silicolife.textmining.core.datastructures.utils.conf.GlobalNames;

public enum CorpusTextType {
	Abstract,
	FullText,
	Hybrid;

	public static CorpusTextType convertStringToCorpusType(String propValue) {
		if(propValue.equals(CorpusTextType.Abstract.toString()))
		{
			return CorpusTextType.Abstract;
		}
		else if(propValue.equals(CorpusTextType.FullText.toString()))
		{
			return CorpusTextType.FullText;
		}
		else if(propValue.equals(CorpusTextType.Hybrid.toString()))
		{
			return CorpusTextType.Hybrid;
		}
		return null;
	}

	public static String convertCorpusTetTypeToString(CorpusTextType corpusTextType) {
		String value = null;
		switch (corpusTextType) {
			case Abstract :
				value = GlobalNames.abstracts;
				break;
			case FullText :
				value = GlobalNames.fullText;
				break;
			case Hybrid :
				value = GlobalNames.abstractOrFullText;
				break;
			default :
				break;
		}
		return value;
	}
}
