package com.silicolife.textmining.core.interfaces.core.document;

import java.util.Set;

public interface IPublicationFilter {

	public Set<String> getCategories();

	public void setCategories(Set<String> categories);

	public Set<String> getTypes();

	public void setTypes(Set<String> types);

	public Set<String> getStatus();

	public void setStatus(Set<String> status);

	public Set<Integer> getYears();

	public void setYears(Set<Integer> years);

}
