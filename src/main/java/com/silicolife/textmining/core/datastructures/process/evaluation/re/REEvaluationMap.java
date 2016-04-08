package com.silicolife.textmining.core.datastructures.process.evaluation.re;

import java.util.HashMap;
import java.util.List;

import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationMultiType;


public class REEvaluationMap extends HashMap<IRelationMultiType, List<IEventAnnotation>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean containsKey(Object key)
	{
		if(key instanceof IRelationMultiType)
		{
			IRelationMultiType rType = (IRelationMultiType) key;
			for(IRelationMultiType rTypelist : keySet())
			{
				if(rType.equals(rTypelist))
				{
					return true;
				}
			}
		}
		return false;
		
	}
	
	@Override
	public List<IEventAnnotation> get(Object key)
	{
		if(key instanceof IRelationMultiType)
		{
			IRelationMultiType rType = (IRelationMultiType) key;
			for(IRelationMultiType rTypelist : keySet())
			{
				if(rType.equals(rTypelist))
				{
					return super.get(rTypelist);
				}
			}
		}
		return null;
	}

}
