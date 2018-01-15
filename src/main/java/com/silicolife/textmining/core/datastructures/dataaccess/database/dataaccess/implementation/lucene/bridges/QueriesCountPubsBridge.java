package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.bridges;

import java.util.Collection;

import org.hibernate.search.bridge.builtin.IntegerBridge;

public class QueriesCountPubsBridge extends IntegerBridge{
	
	@Override
	   public String objectToString(Object object) {
	     if (object == null || (!(object instanceof Collection))) {
	         return null;
	     }
	     Collection<?> coll = (Collection<?>) object;
	     int size = coll.size();
	     //System.out.println("col.size(): " + size);
	     return super.objectToString(size);
	  }

}
