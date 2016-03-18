package com.silicolife.textmining.core.datastructures.utils;


import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class OrderedMap<K,V> implements Serializable {
	private static final long serialVersionUID = -4543746923283257093L;

	private Comparator<K> comparator;
	private Map<K,V> map;
	
	public OrderedMap(Comparator<K> comparator){
		this.comparator = comparator;
		this.map = new LinkedHashMap<K, V>();
	}
	
	public V put(K key, V value){
		
		Map<K,V> newMap = new LinkedHashMap<K, V>();
		boolean notInserted = true;
		
		for(K k: map.keySet())
		{
			if(notInserted && comparator.compare(k, key)>0)
			{
				newMap.put(key, value);
				newMap.put(k, map.get(k));
				notInserted = false;
			}
			else
				newMap.put(k, map.get(k));
		}
		
		if(notInserted)
			newMap.put(key, value);
			
		map = newMap;
		return value;	
	}
	
	public V remove(K key){
		return map.remove(key);
	}
	
	public boolean containsValue(V value){
		return map.containsValue(value);
	}
	
	public boolean containsKey(K key){
		return map.containsKey(key);
	}
	
	public V get(K key){
		return map.get(key);
	}
	
	public Set<K> keySet(){
		return map.keySet();
	}
	
	public Collection<V> values(){
		return map.values();
	}	
	
	public int size(){
		return map.size();
	}
	
	public String toString(){
		StringBuffer str = new StringBuffer();
		for(K k : map.keySet())
			str.append(k.toString() + " - " + map.get(k).toString() + "\n");
		return str.toString();
	}
	
	public Comparator<K> getComparator(){
		return this.comparator;
	}
}

