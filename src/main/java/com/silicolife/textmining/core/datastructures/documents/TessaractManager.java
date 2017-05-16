package com.silicolife.textmining.core.datastructures.documents;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;

public class TessaractManager {
	
	private static TessaractManager _instance;
	private Tesseract tessaract;

	private TessaractManager(){
		tessaract = new Tesseract();
		File tessDataFolder = LoadLibs.extractTessResources("tessdata"); 
		tessaract.setDatapath(tessDataFolder.getParent());
		tessaract.setPageSegMode(1);
		tessaract.setTessVariable("textord_min_linesize", "2.5");
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
