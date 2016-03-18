package com.silicolife.textmining.core.datastructures.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;

public class ResourceElementSetImpl<T extends IResourceElement> implements IResourceElementSet<T>{

	private Set<T> resourceElements;
	private List<T> resourceElementsOrder;
	private Map<Long,Long> resourceIDClassID;
	
	public ResourceElementSetImpl()
	{
		this.resourceElements=new TreeSet<T>();
		this.resourceElementsOrder = new ArrayList<T>();
		this.resourceIDClassID = new HashMap<Long, Long>();
	}
	

	public void setResourceElements(Set<T> resourceElements) {
		this.resourceElements = resourceElements;
	}
	
	
	@Override
	public Set<T> getResourceElements() {
		return  resourceElements;
	}
	

	@Override
	public List<T> getResourceElementsOrder() {
		Set<T> elements = getResourceElements();
		SortedMap<Integer, T> sortceSet = new TreeMap<Integer, T>();
		for(T elem : elements)
		{
			sortceSet.put(elem.getPriority(), elem);
		}
		return new ArrayList<>(sortceSet.values());
	}
	
	public void setResourceElementsOrder(List<T> resourceElementsOrder) {
		this.resourceElementsOrder = resourceElementsOrder;
	}
	

	@Override
	public Map<Long, Long> getResourceIDClassID() {
		return resourceIDClassID;
	}


	public void setResourceIDClassID(Map<Long, Long> resourceIDClassID) {
		this.resourceIDClassID = resourceIDClassID;
	}

	@JsonIgnore
	@Override
	public long getResourceClassByID(long id) {
		return resourceIDClassID.get(id);
	}


	public boolean addElementResource(T element) {
		if(resourceElements.contains(element))
		{
			return false;
		}
		else
		{
			resourceElements.add(element);
			resourceElementsOrder.add(element);
			if(element.getTermClass()!=null)
				resourceIDClassID.put(element.getId(), element.getTermClass().getId());
			return true;
		}
	}

	@Override
	public boolean removeElementResource(T element) {
		if(this.resourceElements.contains(element))
		{
			this.resourceElements.remove(element);
			this.resourceElementsOrder.remove(element);
			this.resourceIDClassID.remove(element.getId());
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean updateElementResource(T arg0) {
		/**
		 * To Implemet
		 */
		return false;
	}
	
	public int size() {
		return resourceElements.size();
	}

	public boolean addAllElementResource(Set<T> elemenst) {
		for(T elem:elemenst)
		{
			if(resourceElements.contains(elem))
			{
			}
			else
			{
				resourceElements.add(elem);
				resourceElementsOrder.add(elem);
				if(elem.getTermClass()!=null)
					resourceIDClassID.put(elem.getId(), elem.getTermClass().getId());
			}
		}
		return true;
	}

	@JsonIgnore
	@Override
	public List<T> getAlphabeticOrder() {
		Set<T> elements = getResourceElements();
		SortedMap<String, T> sortceSet = new TreeMap<String, T>();
		for(T elem : elements)
		{
			sortceSet.put(elem.getTerm(), elem);
		}
		return new ArrayList<>(sortceSet.values());
	}

}
