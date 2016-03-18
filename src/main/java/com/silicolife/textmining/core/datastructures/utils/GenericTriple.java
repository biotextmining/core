package com.silicolife.textmining.core.datastructures.utils;

import java.io.Serializable;
/**
 * 
 * @author Hugo Costa
 * 
 * GenericTriple datatype
 * @param <X> first object pair
 * @param <Y> second object pair
 * @param <Z> third object pair
 */
public class GenericTriple<X,Y,Z> extends GenericPairImpl<X, Y> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2028090486636639228L;
	/**
	 * third object triple
	 */
	private Z z;
	
	public GenericTriple(X x, Y y,Z z) {
		super(x, y);
		this.z=z;
		
	}

	public Z getZ() {
		return z;
	}

	public void setZ(Z z) {
		this.z = z;
	}
		



}
