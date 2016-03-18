package com.silicolife.textmining.core.datastructures.general;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.datastructures.utils.Utils;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public class AnoteClass implements IAnoteClass {
	
	@JsonIgnore
	public static String defaultColor = "#0000FF";


	private long id;
	private String name;
	private String color;

	public AnoteClass(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public AnoteClass(String name)
	{
		this(GenerateRandomId.generateID(),name);
		this.color = AnoteClass.getRandomColor();
	}

	public AnoteClass() {
		super();
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getColor() {
		return color;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public static Set<Long> convert(Set<IAnoteClass> classes)
	{
		Set<Long> result = new HashSet<>();
		for(IAnoteClass klass:classes)	
			result.add(klass.getId());
		return result;
	}
	
	@JsonIgnore
	public static String getRandomColor()
	{
		return Utils.randomAlphaNumColor(6);
	}
	

	@Override
	public int compareTo(IAnoteClass o) {
		if(getId() == o.getId()){
			return 0;
		}else if(getName().toLowerCase().equals(o.getName().toLowerCase())){
			return 0;
		}else if(getId() > o.getId()){
			return 1;
		}
		return -1;
	}
	
	
	public boolean equals(IAnoteClass o)
	{
		return compareTo(o) == 0;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public boolean equals(Object o)
	{
		if(o instanceof IAnoteClass)
		{
			return compareTo((IAnoteClass)o) == 0;
		}
		else
		{
			return false;
		}
	}
}
