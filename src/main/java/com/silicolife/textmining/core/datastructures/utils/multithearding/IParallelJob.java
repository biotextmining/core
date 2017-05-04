package com.silicolife.textmining.core.datastructures.utils.multithearding;

public interface IParallelJob<T> extends Runnable{
	
	public T getResultJob();
	public boolean isFinished();
	public void kill();

}
