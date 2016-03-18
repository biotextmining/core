package com.silicolife.textmining.core.datastructures.annotation.properties;

import java.io.Serializable;

public class AnnotationColorStyleProperty implements Serializable{

	private static final long serialVersionUID = 2L;
	private final static int MAX_EXPRESSION_LENGTH = 2;
	private String name = null;
	private String color = null;
	private String style = null;
	
	public AnnotationColorStyleProperty(String name, String color, String style){
		this.name = name;
		this.color = color;
		this.style = style;
	}
	
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the MAX_EXPRESSION_LENGTH
	 */
	public static int getMAX_EXPRESSION_LENGTH() {
		return MAX_EXPRESSION_LENGTH;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}