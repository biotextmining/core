package com.silicolife.textmining.core.interfaces.resource.rules;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;


public interface IRuleSet extends IResource<IResourceElement>{
	public int getMaxPriority() throws ANoteException;
}
