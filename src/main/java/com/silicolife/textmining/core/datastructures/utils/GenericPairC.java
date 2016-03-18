package com.silicolife.textmining.core.datastructures.utils;


public class GenericPairC<A extends Comparable<A>,B extends Comparable<B>> implements Comparable<GenericPairC<A,B>>{
	
	private A x;
	private B y;
	
	public GenericPairC(A a, B b) {
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

	public int compareTo(GenericPairC<A, B> pair){
		if(x.compareTo(pair.getX()) == 0)
		{
			return y.compareTo(pair.getY());
		}
		else
		{
			return x.compareTo(pair.getX());
		}
	}


	
	

	
	
}
