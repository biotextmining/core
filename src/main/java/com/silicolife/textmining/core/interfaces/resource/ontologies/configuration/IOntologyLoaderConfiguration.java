package com.silicolife.textmining.core.interfaces.resource.ontologies.configuration;

import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public interface IOntologyLoaderConfiguration {
	
	public IResource<IResourceElement> getOntology();
	public String getFilePath();
	public boolean importExternalIds();
	public boolean importOnlyMaintExternalIds();

}
