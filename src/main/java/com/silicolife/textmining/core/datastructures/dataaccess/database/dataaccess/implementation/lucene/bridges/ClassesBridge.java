package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.bridges;

import org.hibernate.search.bridge.TwoWayStringBridge;
import org.hibernate.search.bridge.spi.IgnoreAnalyzerBridge;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Classes;

public class ClassesBridge implements TwoWayStringBridge, IgnoreAnalyzerBridge{
	
	@Override
	public String objectToString(Object obj) {
		Classes castedObj = (Classes) obj;
		String res = null;
		if(castedObj!=null)
			res= String.valueOf(castedObj.getClaId());
		
			return res;
	}

	@Override
	public Object stringToObject(String str) {
		Classes cla = new Classes();
		cla.setClaId(Long.valueOf(str));
		return cla;
	}

}

