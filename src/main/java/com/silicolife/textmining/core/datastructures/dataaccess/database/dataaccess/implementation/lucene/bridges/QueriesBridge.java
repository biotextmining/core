package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.bridges;

import org.hibernate.search.bridge.TwoWayStringBridge;
import org.hibernate.search.bridge.spi.IgnoreAnalyzerBridge;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Resources;

public class QueriesBridge implements TwoWayStringBridge, IgnoreAnalyzerBridge {
	
	@Override
	public String objectToString(Object obj) {
		Resources castedObj = (Resources) obj;
		return String.valueOf(castedObj.getResoId());
	}

	@Override
	public Object stringToObject(String str) {
		Resources res = new Resources();
		res.setResoId(Long.valueOf(str));
		return res;
	}

}
