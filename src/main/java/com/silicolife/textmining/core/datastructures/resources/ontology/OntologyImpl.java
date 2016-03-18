package com.silicolife.textmining.core.datastructures.resources.ontology;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.resources.ResourceImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.ResourcesTypeEnum;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IOntology;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IResourceElementsRelation;

public class OntologyImpl extends ResourceImpl implements IOntology{
	
	public OntologyImpl(long id, String name, String info,boolean active) {
		super(id, name, info,ResourcesTypeEnum.ontology.toString(),active);
	}
	
	public OntologyImpl(String name, String info,boolean active)
	{
		super(name, info, ResourcesTypeEnum.ontology.toString(), active);
	}
	
	
	public OntologyImpl() {
		super();
	}

	public boolean equals(Object ruleset)
	{
		if(!(ruleset instanceof IOntology))
			return false;
		if(this.getId() == ((IOntology) ruleset).getId())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toString()
	{
		if(getId() < 1)
		{
			return "None";
		}
		else
		{
			String info = new String();
			info =LanguageProperties.getLanguageStream("pt.uminho.anote2.general.ontology")+" : " + getName() + " (ID :"+ getId() + " ) ";
			if(!getInfo().equals(""))
			{
				info = info + LanguageProperties.getLanguageStream("pt.uminho.anote2.general.notes")+": "+getInfo();
			}
			if(!isActive())
			{
				info = info + " ("+LanguageProperties.getLanguageStream("pt.uminho.anote2.general.inactive")+") ";
			}
			return info;
		}
	}
	
	@JsonIgnore
	@Override
	public List<IResourceElementsRelation> getResourceElementsRelation() throws ANoteException{
		return InitConfiguration.getDataAccess().getResourceElementsRelations(this);
	}
	
}