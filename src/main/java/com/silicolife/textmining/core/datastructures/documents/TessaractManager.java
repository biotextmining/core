package com.silicolife.textmining.core.datastructures.documents;

import net.sourceforge.tess4j.Tesseract;

public class TessaractManager {
	
	private static TessaractManager _instance;
	private Tesseract tessaract;

	private TessaractManager(){
		tessaract = new Tesseract();
		tessaract.setDatapath("src/main/resources");
	}
	
	public Tesseract getTessaract(){
		return tessaract;
	}
	
	/**
	 * 
	 * Gives access to the features manager instance.
	 * 
	 * @return Instance of features manager singleton.
	 */
	public static synchronized TessaractManager getInstance(){
		if (_instance == null) {
			TessaractManager.createInstance();
		}
		return _instance;
	}

	/**
	 * Creates the singleton instance.
	 * 
	 * @throws BioTMLException 
	 */
	private static void createInstance(){

		if (_instance == null) {
			_instance = new TessaractManager();
		}
	}
}
