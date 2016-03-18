package com.silicolife.textmining.core.datastructures.utils;

import java.io.Serializable;

import com.silicolife.textmining.core.interfaces.core.utils.IGenericPair;

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
public class GenericPairImpl<A,B> implements Serializable,IGenericPair<A, B>{
	
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
	public GenericPairImpl(A a, B b) {
		this.x = a;
		this.y = b;
	}
	
	public GenericPairImpl() {
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
	
}
