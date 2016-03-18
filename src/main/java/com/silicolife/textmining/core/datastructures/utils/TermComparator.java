package com.silicolife.textmining.core.datastructures.utils;

import java.io.Serializable;
import java.util.Comparator;

public class TermComparator<T> implements Comparator<T>, Serializable{

	private static final long serialVersionUID = 2013034921877344609L;

	public TermComparator(){
		super();
	}
	
	public int  compare( Object o1, Object o2 )
	{
		String str1 = (String) o1;
		String str2 = (String) o2;
		return str2.length() - str1.length();
	}
}
