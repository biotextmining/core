package com.silicolife.textmining.core.datastructures.utils;

import java.util.Iterator;
import java.util.Set;


/**
 * Class that saves some math functions 
 *
 */
public class MathUtils {
	
	public static int getAverage(int[] values){
		int average=0, total=0;

		for(int i=0;i<values.length;i++)
		{
			if(values[i]!=0)
			{
				average+=values[i];
				total++;
			}
		}
				
		double factor = (double)total / 2;
		int mod = average % total; 
		average = average / total;
		
		if( mod >= factor)
			return average+1;

		return average;
	}

	public static Object round(Double ret, Integer doublePrecision) {
		return null;
	}
	
	public static boolean interceptedSet(Set<? extends Object> setone,Set<? extends Object> settwo )
	{
		Iterator<? extends Object> it = setone.iterator();
		while(it.hasNext())
		{
			Object obj = it.next();
			if(settwo.contains(obj))
			{
				return true;
			}
		}
		return false;
	}

}
