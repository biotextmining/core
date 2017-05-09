package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.hyperlink;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenuSourceAssociation;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenuSourceAssociationId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.HyperLinkMenus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Sources;
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
	
	public static Set<HyperLinkMenuSourceAssociation> convertToDaemonStructure(List<ISource> sources, HyperLinkMenus menuItem){
		Set<HyperLinkMenuSourceAssociation> associations = new HashSet<>();
		for(ISource source : sources){
			Sources sources_ = SourcesWrapper.convertToDaemonStructure(source);
			HyperLinkMenuSourceAssociationId id = new HyperLinkMenuSourceAssociationId(menuItem.getHylId(), sources_.getSouId());
			associations.add(new HyperLinkMenuSourceAssociation(id, menuItem, sources_));
		}
		return associations;
	}

}
