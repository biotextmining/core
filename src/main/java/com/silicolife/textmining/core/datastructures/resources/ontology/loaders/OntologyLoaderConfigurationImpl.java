package com.silicolife.textmining.core.datastructures.resources.ontology.loaders;

import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.ontologies.configuration.IOntologyLoaderConfiguration;

public class OntologyLoaderConfigurationImpl implements IOntologyLoaderConfiguration{


	private IResource<IResourceElement> ontology;
	private String filepath;
	
	public OntologyLoaderConfigurationImpl(IResource<IResourceElement> ontology,String filepath)
	{
		this.ontology=ontology;
		this.filepath = filepath;
	}

	@Override
	public IResource<IResourceElement> getOntology() {
		return ontology;
	}

	@Override
	public String getFilePath() {
		return filepath;
	}

}
