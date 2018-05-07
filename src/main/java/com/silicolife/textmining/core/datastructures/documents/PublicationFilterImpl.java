package com.silicolife.textmining.core.datastructures.documents;

import java.util.HashSet;
import java.util.Set;

import com.silicolife.textmining.core.interfaces.core.document.IPublicationFilter;

public class PublicationFilterImpl implements IPublicationFilter {
	
	private Set<String> categories;
	private Set<String> types;
	private Set<String> status;
	private Set<Integer> years;
	
	public PublicationFilterImpl() {
		this.categories = new HashSet<String>();
		this.types = new HashSet<String>();
		this.status = new HashSet<String>();
		this.years = new HashSet<Integer>();
	}

	@Override
	public Set<String> getCategories() {
		return categories;
	}
	
	@Override
	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}
	
	@Override
	public Set<String> getTypes() {
		return types;
	}
	
	@Override
	public void setTypes(Set<String> types) {
		this.types = types;
	}
	
	@Override
	public Set<String> getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(Set<String> status) {
		this.status = status;
	}

	@Override
	public Set<Integer> getYears() {
		return years;
	}

	@Override
	public void setYears(Set<Integer> years) {
		this.years = years;
	}
	
	
	
}
