package com.silicolife.textmining.core.datastructures.resources;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private IAnoteClass termClass;
	@JsonDeserialize(contentAs = ExternalIDImpl.class)
	private List<IExternalID> externalIDsInMemory;
	private List<String> synonyms;
	private int priority;
	private boolean isActive;

	public ResourceElementImpl(long id, String term, IAnoteClass klass,List<IExternalID> externalIDsInMemory,List<String> synonyms, int priority, boolean isActive) {
		super();
		this.id = id;
		this.term = term;
		this.termClass=klass;
		this.externalIDsInMemory = externalIDsInMemory;
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

	@Override
	public IAnoteClass getTermClass() {
		return this.termClass;
	}

	@Override
	public void setTermClass(IAnoteClass klass) {
		this.termClass = klass;
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

	public List<IExternalID> getExternalIDsInMemory() {
		if(externalIDsInMemory==null)
			return new ArrayList<IExternalID>();
		return externalIDsInMemory;
	}
	
	public void setExternalIDsInMemory(List<IExternalID> externalIDsInMemory) {
		this.externalIDsInMemory = externalIDsInMemory;
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
				return this.termClass.getName().compareTo(elem.getTermClass().getName());
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
		if(externalIDsInMemory==null || externalIDsInMemory.isEmpty())
		{
			externalIDsInMemory = InitConfiguration.getDataAccess().getResourceElementExternalIds(this);
		}
		return externalIDsInMemory;
	}

	@Override
	public String toString() {
		return "ResourceElementImpl [id=" + id + ", term=" + term + ", termClass=" + termClass + ", externalIDs="
				+ externalIDsInMemory + ", synonyms=" + synonyms + ", priority=" + priority + ", isActive=" + isActive + "]";
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceElementImpl other = (ResourceElementImpl) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
