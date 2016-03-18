package com.silicolife.textmining.core.datastructures.resources.ontology;

import com.silicolife.textmining.core.interfaces.resource.ontologies.IResourceElementsRelation;

public class ResourceElementsRelationImpl implements IResourceElementsRelation{
	
	private Long resourceElementIdA;
	private Long resourceElementIdB;
	private String relationType;
	
	public ResourceElementsRelationImpl(Long resourceElementIdA,Long resourceElementIdB,String relationType)
	{
		this.resourceElementIdA=resourceElementIdA;
		this.resourceElementIdB=resourceElementIdB;
		this.relationType=relationType;
	}

	public ResourceElementsRelationImpl() {
		super();
	}

	@Override
	public Long getResourceElementIdA() {
		return resourceElementIdA;
	}


	@Override
	public Long getResourceElementIdB() {
		return resourceElementIdB;
	}

	@Override
	public String getRelationType() {
		return relationType;
	}

	public void setResourceElementIdA(Long resourceElementIdA) {
		this.resourceElementIdA = resourceElementIdA;
	}


	public void setResourceElementIdB(Long resourceElementIdB) {
		this.resourceElementIdB = resourceElementIdB;
	}


	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

}
