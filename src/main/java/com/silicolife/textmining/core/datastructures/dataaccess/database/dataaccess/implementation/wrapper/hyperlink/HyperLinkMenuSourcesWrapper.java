package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.hyperlink;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenuSourceAssociation;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources.SourcesWrapper;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;

public class HyperLinkMenuSourcesWrapper {
	
	public static List<ISource> convertToAnoteStructure(Set<HyperLinkMenuSourceAssociation> sourceAssociations){
		List<ISource> sources = new ArrayList<>();
		Iterator<HyperLinkMenuSourceAssociation> itsourceAssociations = sourceAssociations.iterator();
		while(itsourceAssociations.hasNext()){
			HyperLinkMenuSourceAssociation sourceAssociation = itsourceAssociations.next();
			sources.add(SourcesWrapper.convertToAnoteStructure(sourceAssociation.getSources()));
		}
		return sources;
	}

}
