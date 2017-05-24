package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.bridges;

import org.hibernate.search.bridge.TwoWayStringBridge;
import org.hibernate.search.bridge.spi.IgnoreAnalyzerBridge;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.QueryHasPublicationsId;

public class QueryHasPublicationsBridge implements TwoWayStringBridge, IgnoreAnalyzerBridge {
	
private String separator = "__";
	
	@Override
	public String objectToString(Object obj) {
		StringBuilder sb = new StringBuilder();
		QueryHasPublicationsId castedObj = (QueryHasPublicationsId) obj;
		sb.append(castedObj.getQhbPublicationId());
		sb.append(separator);
		sb.append(castedObj.getQhbPublicationId());
		return null;
	}

	@Override
	public Object stringToObject(String str) {
		String[] parts = str.split(separator);
		QueryHasPublicationsId queryHasPubId = new QueryHasPublicationsId();
		queryHasPubId.setQhbPublicationId(Long.valueOf(parts[0]));
		queryHasPubId.setQhbQueryId(Long.valueOf(parts[1]));
		return queryHasPubId;
	}


}
