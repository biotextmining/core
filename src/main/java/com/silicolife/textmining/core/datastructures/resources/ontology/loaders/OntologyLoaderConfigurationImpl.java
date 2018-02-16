package com.silicolife.textmining.core.datastructures.resources.ontology.loaders;

import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.ontologies.configuration.IOntologyLoaderConfiguration;

public class OntologyLoaderConfigurationImpl implements IOntologyLoaderConfiguration{


	private IResource<IResourceElement> ontology;
	private String filepath;
	private boolean importExternalIds;
	private boolean importOnlyMaintExternalIds;

	
	public OntologyLoaderConfigurationImpl(IResource<IResourceElement> ontology,String filepath,boolean importExternalIds,boolean importOnlyMaintExternalIds)
	{
		this.ontology=ontology;
		this.filepath = filepath;
		this.importExternalIds=importExternalIds;
		this.importOnlyMaintExternalIds=importOnlyMaintExternalIds;
	}

	@Override
	public IResource<IResourceElement> getOntology() {
		return ontology;
	}

	@Override
	public String getFilePath() {
		return filepath;
	}

	@Override
	public boolean importExternalIds() {
		return importExternalIds;
	}

	@Override
	public boolean importOnlyMaintExternalIds() {
		return importOnlyMaintExternalIds;
	}

}
