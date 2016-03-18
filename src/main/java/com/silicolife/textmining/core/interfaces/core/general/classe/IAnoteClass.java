package com.silicolife.textmining.core.interfaces.core.general.classe;


public interface IAnoteClass extends Comparable<IAnoteClass> {

	public long getId();

	public String getName();

	public String getColor();

	public void setColor(String color);

}
