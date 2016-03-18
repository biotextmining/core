package com.silicolife.textmining.core.datastructures.process.re.export.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentInformation {
	private Map<Long, List<String>> documentIDRelationSetences;
	
	public DocumentInformation()
	{
		documentIDRelationSetences = new HashMap<Long, List<String>>();
	}
	
	public void update(long docID,String sentence)
	{
		if(!documentIDRelationSetences.containsKey(docID))
		{
			documentIDRelationSetences.put(docID, new ArrayList<String>());
		}
		documentIDRelationSetences.get(docID).add(sentence);
	}

	public Map<Long, List<String>> getDocumentIDRelationSetences() {
		return documentIDRelationSetences;
	}
}
