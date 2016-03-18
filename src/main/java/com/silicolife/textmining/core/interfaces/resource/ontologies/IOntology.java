package com.silicolife.textmining.core.interfaces.resource.ontologies;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IOntology extends IResource<IResourceElement>{
	
	public List<IResourceElementsRelation> getResourceElementsRelation() throws ANoteException;
}
