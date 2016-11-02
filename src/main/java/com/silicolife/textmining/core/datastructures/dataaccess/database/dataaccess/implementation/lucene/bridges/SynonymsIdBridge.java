package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.bridges;

import org.hibernate.search.bridge.TwoWayStringBridge;
import org.hibernate.search.bridge.spi.IgnoreAnalyzerBridge;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.SynonymsId;

public class SynonymsIdBridge implements TwoWayStringBridge, IgnoreAnalyzerBridge{

	private String separator = "__";
	
	@Override
	public String objectToString(Object obj) {
		StringBuilder sb = new StringBuilder();
		SynonymsId castedObj = (SynonymsId) obj;
		sb.append(castedObj.getSynResourceElementId());
		sb.append(separator);
		sb.append(castedObj.getSynSynonym());
		sb.append(separator);
		sb.append(castedObj.isSynActive());
		return sb.toString();
	}

	@Override
	public Object stringToObject(String str) {
		String[] parts = str.split(separator);
		SynonymsId syn = new SynonymsId();
		syn.setSynResourceElementId(Long.valueOf(parts[0]));
		syn.setSynSynonym(parts[1]);
		syn.setSynActive(Boolean.valueOf(parts[2]));
		return syn;
	}

}
