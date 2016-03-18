package com.silicolife.textmining.core.interfaces.resource.merge;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceMergeReport;

public interface IResourceMerge {
	
	public IResourceMergeReport merge(IResourceMergeConfiguration configuration) throws ANoteException;

}
