package com.silicolife.textmining.core.datastructures.process.ner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.utils.GenericTriple;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class ResourcesToNerAnote {
	
	private List<GenericTriple<IResource<IResourceElement>,Set<Long>,Set<Long>>> list; // Resource, selected class(es), all class(es)
	private NERCaseSensativeEnum caseSensitive;
	private boolean useOtherResourceInformationInRules;
	
	public ResourcesToNerAnote()
	{
		this.caseSensitive = NERCaseSensativeEnum.NONE;
		this.list = new ArrayList<GenericTriple<IResource<IResourceElement>,Set<Long>,Set<Long>>>();
		this.useOtherResourceInformationInRules = false;
	}
	
	public ResourcesToNerAnote(NERCaseSensativeEnum caseSensitive,boolean useOtherResourceInformationInRules)
	{
		this.caseSensitive = caseSensitive;
		list = new ArrayList<GenericTriple<IResource<IResourceElement>,Set<Long>,Set<Long>>>();
		this.useOtherResourceInformationInRules = useOtherResourceInformationInRules;
	}
	
	public void add(IResource<IResourceElement> resElem,Set<Long> classContent,Set<Long> selectedClass)
	{
		GenericTriple<IResource<IResourceElement>,Set<Long>,Set<Long>> triple =
			new GenericTriple<IResource<IResourceElement>, Set<Long>, Set<Long>>(resElem,classContent,selectedClass);
		list.add(triple);
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

	public List<GenericTriple<IResource<IResourceElement>, Set<Long>, Set<Long>>> getList() {
		return list;
	}
	
	@JsonIgnore
	public void setList(List<GenericTriple<IResource<IResourceElement>, Set<Long>, Set<Long>>> list) {
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

	public boolean containsResource(IResource<IResourceElement> x) {
		for(GenericTriple<IResource<IResourceElement>,Set<Long>,Set<Long>>triple:list)
		{
			if(triple.getX().getId() == x.getId())
			{
				return true;
			}
		}
		return false;
	}

	

}
