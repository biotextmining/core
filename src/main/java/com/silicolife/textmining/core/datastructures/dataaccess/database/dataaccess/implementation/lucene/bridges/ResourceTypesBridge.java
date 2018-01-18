package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.bridges;

import org.hibernate.search.bridge.TwoWayStringBridge;
import org.hibernate.search.bridge.spi.IgnoreAnalyzerBridge;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceTypes;

public class ResourceTypesBridge implements TwoWayStringBridge, IgnoreAnalyzerBridge {
	
	private String separator = "__";
	
	@Override
	public String objectToString(Object obj) {
		StringBuilder sb = new StringBuilder();
		ResourceTypes castedObj = (ResourceTypes) obj;
		sb.append(castedObj.getRtyId());
		sb.append(separator);
		sb.append(castedObj.getRtyResourceType());
		return null;
	}

	@Override
	public Object stringToObject(String str) {
		String[] parts = str.split(separator);
		ResourceTypes resourceTypes = new ResourceTypes();
		resourceTypes.setRtyId(Long.valueOf(parts[0]));
		resourceTypes.setRtyResourceType(parts[1]);
		return resourceTypes;
	}
}
