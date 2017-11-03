package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.bridges;

import org.hibernate.search.bridge.TwoWayStringBridge;
import org.hibernate.search.bridge.spi.IgnoreAnalyzerBridge;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjectsId;

public class AuthUserDataObjectsIdBridge implements TwoWayStringBridge, IgnoreAnalyzerBridge {
	
	private String separator = "__";

	@Override
	public String objectToString(Object obj) {
		StringBuilder sb = new StringBuilder();
		AuthUserDataObjectsId castedObj = (AuthUserDataObjectsId) obj;
		sb.append(castedObj.getAudoUserId());
		sb.append(separator);
		sb.append(castedObj.getAudoUidResource());
		sb.append(separator);
		sb.append(castedObj.getAudoTypeResource());
		return sb.toString();
	}

	@Override
	public Object stringToObject(String str) {
		String[] parts = str.split(separator);
		AuthUserDataObjectsId authDataObjectId = new AuthUserDataObjectsId();
		authDataObjectId.setAudoUserId(Long.valueOf(parts[0]));
		authDataObjectId.setAudoUidResource(Long.valueOf(parts[1]));
		authDataObjectId.setAudoTypeResource(parts[2]);
		return authDataObjectId;
	}
}
