package com.silicolife.textmining.core.datastructures.process.network;

import com.silicolife.textmining.core.interfaces.process.IE.network.ICoordenates;

public class CoordinatesImpl implements ICoordenates{

	private double x;
	private double y;
	private double height;
	private double width;
	
	public CoordinatesImpl(double x, double y, double height, double width) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getHeight() {
		return height;
	}
	public double getWidth() {
		return width;
	}
	
	
}
