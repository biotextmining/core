package com.silicolife.textmining.core.interfaces.process.IE.ner;

import java.util.SortedMap;
import java.util.SortedSet;

public interface INERClassEvaluation {
	
	public SortedSet<String> getAllClassTypes();

	public SortedMap<String, Float> getRecallOfEachClassType();

	public SortedMap<String, Float> getPrecisionOfEachClassType();

	public SortedMap<String, Float> getFscoreOfEachClassnType();

}
