package com.silicolife.textmining.core.interfaces.process.utils;

public interface IEvaluation {
	
	/**
	 * Get precision according to https://en.wikipedia.org/wiki/Precision_and_recall TP/(TP+FP)
	 * 
	 * @return
	 */
	public float getPrecision();
	
	/**
	 * Get recall according to https://en.wikipedia.org/wiki/Precision_and_recall TP/(TP+FN)
	 * 
	 * @return
	 */
	public float getRecall();
	
	/**
	 * Get FScore according to https://en.wikipedia.org/wiki/Precision_and_recall 2*precision*recall/(precision+recall)
	 * 
	 * @return
	 */
	public float getFscore();
}
