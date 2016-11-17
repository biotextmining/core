package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.bridges;

import org.hibernate.search.bridge.TwoWayStringBridge;
import org.hibernate.search.bridge.spi.IgnoreAnalyzerBridge;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementExtenalIdsId;

public class ResourceElementExtenalIdsIdBridge implements TwoWayStringBridge, IgnoreAnalyzerBridge{

	private String separator = "__";
	
	@Override
	public String objectToString(Object obj) {
		StringBuilder sb = new StringBuilder();
		ResourceElementExtenalIdsId castedObj = (ResourceElementExtenalIdsId) obj;
		sb.append(castedObj.getReleResourceElementId());
		sb.append(separator);
		sb.append(castedObj.getReleSourceId());
		sb.append(separator);
		sb.append(castedObj.getReleExternalId());
		return null;
	}

	@Override
	public Object stringToObject(String str) {
		String[] parts = str.split(separator);
		ResourceElementExtenalIdsId resExternalId = new ResourceElementExtenalIdsId();
		resExternalId.setReleResourceElementId(Long.valueOf(parts[0]));
		resExternalId.setReleSourceId(Long.valueOf(parts[1]));
		resExternalId.setReleExternalId(parts[2]);
		return resExternalId;
	}

}
