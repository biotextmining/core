package com.silicolife.textmining.core.interfaces.process.utils;

public interface ISimpleTimeLeft {
	public void setProgress(float total);
	public void setTime(long differTime, int pos, int max);
}
