package com.silicolife.textmining.core.interfaces.resource;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IResourceElementSet<T extends IResourceElement>{
	public Set<T> getResourceElements();
	public List<T> getAlphabeticOrder();
	public List<T> getResourceElementsOrder();
	public long getResourceClassByID(long id);
	public boolean addElementResource(T element);
	public boolean addAllElementResource(Set<T> elemenst);
	public boolean updateElementResource(T element);
	public boolean removeElementResource(T element);
	public int size();
	public Map<Long, Long> getResourceIDClassID();

}
