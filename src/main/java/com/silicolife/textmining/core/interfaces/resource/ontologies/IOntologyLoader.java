package com.silicolife.textmining.core.interfaces.resource.ontologies;

import java.io.IOException;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateReport;
import com.silicolife.textmining.core.interfaces.resource.ontologies.configuration.IOntologyLoaderConfiguration;

public interface IOntologyLoader {
	public IResourceUpdateReport processOntologyFile(IOntologyLoaderConfiguration configuration) throws ANoteException, IOException;
	public void stop();

}
