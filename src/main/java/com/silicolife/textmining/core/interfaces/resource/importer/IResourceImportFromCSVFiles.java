package com.silicolife.textmining.core.interfaces.resource.importer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.utils.generic.CSVFileConfigurations;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateReport;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IResourceImportFromCSVFiles {
	public IResourceUpdateReport loadTermFromGenericCVSFile(IResource<IResourceElement> resource,File file, CSVFileConfigurations csvfileconfigurations) throws ANoteException, IOException;

	public void stop();

	public List<Long> getInsertedTermIDList();

	public Set<Long> getNewClassesAdded();
}
