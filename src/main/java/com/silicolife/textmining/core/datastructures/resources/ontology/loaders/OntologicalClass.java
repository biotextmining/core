package com.silicolife.textmining.core.datastructures.resources.ontology.loaders;

import java.util.List;

import com.silicolife.textmining.core.interfaces.core.general.IExternalID;

public class OntologicalClass {
	
	private String name;
	private String defenition;
	private List<String> is_a;
	private List<String> partof;
	private List<String> synonyms;
	private List<IExternalID> externalIDs;
	
	
	public OntologicalClass(String name, String defenition, List<String> is_a,List<String> partof,List<String> synonyms,List<IExternalID> externalIDs) {
		this.name = name;
		this.defenition = defenition;
		this.is_a = is_a;
		this.partof=partof;
		this.synonyms = synonyms;
		this.externalIDs = externalIDs;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDefenition() {
		return defenition;
	}


	public void setDefenition(String defenition) {
		this.defenition = defenition;
	}


	public List<String> getIs_a() {
		return is_a;
	}


	public void setIs_a(List<String> is_a) {
		this.is_a = is_a;
	}


	public List<String> getSynonyms() {
		return synonyms;
	}


	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}


	public List<IExternalID> getExternalIDs() {
		return externalIDs;
	}


	public List<String> getPartof() {
		return partof;
	}


	public void setPartof(List<String> partof) {
		this.partof = partof;
	}
	
	
	
	

}
