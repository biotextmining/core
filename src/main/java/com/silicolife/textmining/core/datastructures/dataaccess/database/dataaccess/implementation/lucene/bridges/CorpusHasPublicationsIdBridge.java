package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.bridges;

import org.hibernate.search.bridge.TwoWayStringBridge;
import org.hibernate.search.bridge.spi.IgnoreAnalyzerBridge;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasPublicationsId;

public class CorpusHasPublicationsIdBridge implements TwoWayStringBridge, IgnoreAnalyzerBridge {

	private String separator = "__";

	@Override
	public String objectToString(Object obj) {
		StringBuilder sb = new StringBuilder();
		CorpusHasPublicationsId castedObj = (CorpusHasPublicationsId) obj;
		sb.append(castedObj.getChpPublicationId());
		sb.append(separator);
		sb.append(castedObj.getChpCorpusId());
		return sb.toString();
	}

	@Override
	public Object stringToObject(String str) {
		String[] parts = str.split(separator);
		CorpusHasPublicationsId corpusHasPub = new CorpusHasPublicationsId();
		corpusHasPub.setChpPublicationId(Long.valueOf(parts[0]));
		corpusHasPub.setChpCorpusId(Long.valueOf(parts[1]));
		return corpusHasPub;
	}
	
	
}
