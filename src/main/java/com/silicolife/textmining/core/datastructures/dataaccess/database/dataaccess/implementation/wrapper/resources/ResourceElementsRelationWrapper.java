package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.resources;

import java.util.ArrayList;
import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ResourceElementRelations;
import com.silicolife.textmining.core.datastructures.resources.ontology.ResourceElementsRelationImpl;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IResourceElementsRelation;

public class ResourceElementsRelationWrapper {
	
	public static List<IResourceElementsRelation> convertToAnoteStructure(List<ResourceElementRelations> resourceElementsRelationList) 
	{
		List<IResourceElementsRelation> result = new ArrayList<>();
		for(ResourceElementRelations relationElement : resourceElementsRelationList)
		{
			IResourceElementsRelation anoteRElation = convertToAnoteStructure(relationElement);
			result.add(anoteRElation);
		}
		return result;
	}

	public static IResourceElementsRelation convertToAnoteStructure(ResourceElementRelations relationElement) {
		Long resourceElementIdB = relationElement.getId().getRelResourceElementIdB();
		Long resourceElementIdA = relationElement.getId().getRelResourceElementIdA();;
		String relationType = relationElement.getResourceElementRelationTypes().getRerRelationType();
		IResourceElementsRelation relation = new ResourceElementsRelationImpl(resourceElementIdA, resourceElementIdB, relationType);
		return relation;
	}

}
