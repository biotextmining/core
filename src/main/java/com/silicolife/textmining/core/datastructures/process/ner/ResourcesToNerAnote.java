package com.silicolife.textmining.core.datastructures.process.ner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class ResourcesToNerAnote {
	
	private List<ResourceSelectedClassesMap> list; // Resource, selected class(es), all class(es)
	private NERCaseSensativeEnum caseSensitive;
	private boolean useOtherResourceInformationInRules;
	private int sizeOfSmallWordsToBeNotAnnotated;
	
	public ResourcesToNerAnote()
	{
		this.caseSensitive = NERCaseSensativeEnum.NONE;
		this.list = new ArrayList<ResourceSelectedClassesMap>();
		this.useOtherResourceInformationInRules = false;
		this.sizeOfSmallWordsToBeNotAnnotated = 0;
	}
	
	public ResourcesToNerAnote(NERCaseSensativeEnum caseSensitive,boolean useOtherResourceInformationInRules, int sizeOfSmallWordsToBeNotAnnotated)
	{
		this.caseSensitive = caseSensitive;
		list = new ArrayList<ResourceSelectedClassesMap>();
		this.useOtherResourceInformationInRules = useOtherResourceInformationInRules;
		this.sizeOfSmallWordsToBeNotAnnotated = sizeOfSmallWordsToBeNotAnnotated;
	}
	
	public void add(IResource<IResourceElement> resElem,Set<Long> classContent,Set<Long> selectedClass)
	{
		ResourceSelectedClassesMap selectedClasses = new ResourceSelectedClassesMap(resElem,selectedClass,classContent);
		list.add(selectedClasses);
	}
	
	@JsonIgnore
	public void addUsingAnoteClasses(IResource<IResourceElement> resElem,Set<IAnoteClass> resourceClassContent,Set<IAnoteClass> selectedClasssContent) {
		Set<Long> classContent = new HashSet<>();
		Set<Long> selectedClass = new HashSet<>();
		for(IAnoteClass klass:resourceClassContent)
			classContent.add(klass.getId());
		for(IAnoteClass selectedklass:selectedClasssContent)
			selectedClass.add(selectedklass.getId());
		this.add(resElem,classContent,selectedClass);
	}

	public List<ResourceSelectedClassesMap> getList() {
		return list;
	}
	
	public void setList(List<ResourceSelectedClassesMap> list) {
		this.list = list;
	}

	public NERCaseSensativeEnum getCaseSensitive() {
		return caseSensitive;
	}
	
	public void setCaseSensitive(NERCaseSensativeEnum caseSensitive) {
		this.caseSensitive = caseSensitive;
	}
	
	public boolean isUseOtherResourceInformationInRules() {
		return useOtherResourceInformationInRules;
	}

	public void setUseOtherResourceInformationInRules(
			boolean useOtherResourceInformationInRules) {
		this.useOtherResourceInformationInRules = useOtherResourceInformationInRules;
	}

	public boolean containsResource(IResource<IResourceElement> resource) {
		for(ResourceSelectedClassesMap selectedClasses:list)
		{
			if(selectedClasses.getResource().getId() == resource.getId())
			{
				return true;
			}
		}
		return false;
	}

	public int getSizeOfSmallWordsToBeNotAnnotated() {
		return sizeOfSmallWordsToBeNotAnnotated;
	}

	public void setSizeOfSmallWordsToBeNotAnnotated(int sizeOfSmallWordsToBeNotAnnotated) {
		this.sizeOfSmallWordsToBeNotAnnotated = sizeOfSmallWordsToBeNotAnnotated;
	}

}
