package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Sources;
import com.silicolife.textmining.core.datastructures.general.SourceImpl;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;

public class SourcesWrapper {

	public static ISource convertToAnoteStructure(Sources sources) {

		long sourceId = sources.getSouId();
		String sourceDescription = sources.getSouDescription();
		if (sourceDescription == null)
			sourceDescription = new String();

		SourceImpl sourceImpl = new SourceImpl(sourceId, sourceDescription);

		return sourceImpl;
	}
	
	public static Sources convertToDaemonStructure(ISource source){
		String sourceDescrition = source.getSource();
		if(source.getSource().isEmpty()){
			sourceDescrition = null;
		}
		return new Sources(source.getSourceID(), sourceDescrition);
	}
}
