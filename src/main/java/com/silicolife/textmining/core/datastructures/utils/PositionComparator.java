package com.silicolife.textmining.core.datastructures.utils;

import java.io.Serializable;
import java.util.Comparator;

import com.silicolife.textmining.core.datastructures.annotation.AnnotationPosition;


public class PositionComparator<T> implements Comparator<T>, Serializable{

	private static final long serialVersionUID = 2013034921877344609L;

	public PositionComparator(){
		super();
	}
	
	public int  compare( Object o1, Object o2 )
	{
		AnnotationPosition pos1 = (AnnotationPosition) o1;
		AnnotationPosition pos2 = (AnnotationPosition) o2;
		
		if(pos1.getStart() == pos2.getStart())
		{
			return pos1.getEnd() - pos2.getEnd();
		}
		else
		{
			return pos1.getStart() - pos2.getStart();
		}
	}
}
