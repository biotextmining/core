package com.silicolife.textmining.core.interfaces.resource.export.tsv;

import java.io.IOException;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IResourceExportToCSV {
	public void exportCSVFile(IResource<IResourceElement> resource,IResourceExportConfiguration configuration) throws ANoteException, IOException;

}
