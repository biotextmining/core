package com.silicolife.textmining.core.datastructures.resources;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silicolife.textmining.core.datastructures.general.AnoteClass;
import com.silicolife.textmining.core.datastructures.general.ExternalIDImpl;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class ResourceElementImpl implements IResourceElement{

	private long id;
	private String term;
	@JsonDeserialize(as = AnoteClass.class)
	private IAnoteClass klass;
	@JsonDeserialize(contentAs = ExternalIDImpl.class)
	private List<IExternalID> externalIDs;
	private List<String> synonyms;
	private int priority;
	private boolean isActive;

	public ResourceElementImpl(long id, String term, IAnoteClass klass,List<IExternalID> externalIDs,List<String> synonyms, int priority, boolean isActive) {
		super();
		this.id = id;
		this.term = term;
		this.klass=klass;
		this.externalIDs = externalIDs;
		this.synonyms = synonyms;
		this.priority = priority;
		this.isActive = isActive;
	}
	
	public ResourceElementImpl(String term, IAnoteClass termClass, List<IExternalID> externalIDs,List<String> synonyms, int priorety, boolean isActive) {
		this(GenerateRandomId.generateID(), term, termClass, externalIDs, synonyms, priorety, isActive);
	}


	public ResourceElementImpl() {
		super();
	}

	@Override
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String getTerm() {
		return this.term;
	}

	@Override
	public void setTerm(String newTermName) {
		this.term=newTermName;
	}

	@JsonGetter("klass")
	@Override
	public IAnoteClass getTermClass() {
		return this.klass;
	}

	@JsonSetter("klass")
	@Override
	public void setClass(IAnoteClass klass) {
		this.klass = klass;
	}
	
	@Override
	public int getPriority() {
		return priority;
	}

	@Override
	public void setPriority(int priorety) {
		this.priority = priorety;
	}

	@Override
	public List<String> getSynonyms() {
		return synonyms;
	}
	
	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}

	@JsonGetter("externalIDs")
	@Override
	public List<IExternalID> getExtenalIDsImMemory() {
		if(externalIDs==null)
			return new ArrayList<IExternalID>();
		return externalIDs;
	}
	
	public void setExternalIDs(List<IExternalID> externalIDs) {
		this.externalIDs = externalIDs;
	}
	
	@Override
	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	

	@JsonIgnore
	public String getElementType() {
		return "";
	}

	public boolean equals(IResourceElement elem){
		return this.compareTo(elem) == 0;
	}

	public int compareTo(IResourceElement elem) {
		if(this.id==elem.getId())
		{
			int term = this.term.compareTo(elem.getTerm());
			if(term==0)
			{
				if(elem.getTermClass()==null)
				{
					return -2;
				}
				return this.klass.getName().compareTo(elem.getTermClass().getName());
			}
			else
				return term;
		}
		else if(id<elem.getId())
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}

	
	public ResourceElementImpl clone()
	{
		try {
			return new ResourceElementImpl(this.getId(),this.getTerm(),this.getTermClass(),this.getExtenalIDs(),this.getSynonyms(),this.getPriority(),this.isActive());
		} catch (ANoteException e) {
			return null;
		}
	}

	/*
	 * externalIDs.size() == 0 -- not god. See later
	 * 
	 * (non-Javadoc)
	 * @see pt.uminho.anote2.interfaces.resource.IResourceElement#getExtenalIDs()
	 */
	@JsonIgnore
	public synchronized List<IExternalID> getExtenalIDs() throws ANoteException {
		if(externalIDs==null || externalIDs.size() == 0)
		{
			externalIDs = InitConfiguration.getDataAccess().getResourceElementExternalIds(this);
		}
		return externalIDs;
	}

	@Override
	public boolean hasExternalID(IExternalID ext) {
		try {
			if(this.getExtenalIDs().size()>0)
			{
				for(IExternalID externalID : this.getExtenalIDs())
				{
					if(externalID.getExternalID().equals(ext.getExternalID()) && externalID.getSource().getSource().equals(ext.getSource().getSource()))
					{
						return true;
					}
				}
			}
		} catch (ANoteException e) {
			return true;
		}
		return false;
	}

	
	@Override
	public void generateNewId() {
		this.id = GenerateRandomId.generateID();		
	}
}
