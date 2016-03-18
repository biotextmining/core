package com.silicolife.textmining.core.datastructures.exceptions.resources.ontologies;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;

public class OntologyMissingRootExeption extends Exception {
	
	private static final long serialVersionUID = -4574335219818517049L;

	public OntologyMissingRootExeption(){
		super(LanguageProperties.getLanguageStream("pt.uminho.anote2.general.ontology.err.ontologyrootFault"));
	}

}
