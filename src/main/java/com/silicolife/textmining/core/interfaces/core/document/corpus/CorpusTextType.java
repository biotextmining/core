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
				value = CorpusTextType.Abstract.toString();
				break;
			case FullText :
				value = CorpusTextType.FullText.toString();
				break;
			case Hybrid :
				value = CorpusTextType.Hybrid.toString();
				break;
			default :
				break;
		}
		return value;
	}
}
