package com.silicolife.textmining.core.datastructures.utils;

import java.io.Serializable;

/**
 * 
 * @author Rafael Carreira
 *
 * @param <A> first object pair
 * @param <B> second object pair
 * 
 * GenericPAir datatype
 * 
 */
public class GenericPairComparable<A extends Comparable<A>,B extends Comparable<B>> implements Serializable,Comparable<GenericPairComparable<A,B>>{
	
	private static final long serialVersionUID = -2513080470091718476L;
	/**
	 *  first object pair
	 */
	private A x;
	/**
	 * second object pair
	 */
	private B y;
	
	/**
	 * Constructor
	 * 
	 * @param a first object pair
	 * @param b second object pair
	 */
	public GenericPairComparable(A a, B b) {
		this.x = a;
		this.y = b;
	}

	public A getX() {
		return x;
	}
	
	public B getY() {
		return y;
	}

	public void setX(A a) {
		this.x = a;
	}

	public void setY(B b) {
		this.y = b;
	}
	
	public boolean equals( GenericPairComparable<A,B> pair)
	{
		return this.getX().equals(pair.getX()) && this.getY().equals(pair.getY());
	}

	public int compareTo(GenericPairComparable<A, B> o) {
		if(this.getX().compareTo(o.getX())==0)
		{
			return this.getY().compareTo(o.getY());
		}
		else
		{
			return this.getX().compareTo(o.getX());
		}
	}

}
