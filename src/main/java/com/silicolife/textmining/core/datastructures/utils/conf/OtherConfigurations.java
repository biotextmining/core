package com.silicolife.textmining.core.datastructures.utils.conf;

import java.util.ArrayList;
import java.util.List;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.utils.GenericPairC;

public class OtherConfigurations {
	



	public static String getVerbColor() {
		if(GlobalOptions.verbColor == null)
		{
			ArrayList<String> elements = new ArrayList<String>();
			elements.add("Verb-Color");
			List<String> data = InitConfiguration.getElementByXMLFile(GlobalOptions.otherConfigurationFile,elements);
			if(data.size()>0 && data.get(0)!=null)
			{
				GlobalOptions.verbColor = data.get(0);
			}
			else
			{
				setVerbColor("#FF0000");
			}
		}
		return GlobalOptions.verbColor;
	}


	public static void setVerbColor(String verbColor) {
		ArrayList<GenericPairC<String, String>> changes = new ArrayList<GenericPairC<String,String>>();
		changes.add(new GenericPairC<String, String>("Verb-Color",verbColor));
		InitConfiguration.setXmlProperties(GlobalOptions.otherConfigurationFile, changes);	
		GlobalOptions.verbColor = verbColor;
	}
	
	public static String getVerbColorBackGround() {
		if(GlobalOptions.verbColorBackground == null)
		{
			ArrayList<String> elements = new ArrayList<String>();
			elements.add("Verb-Color-Background");
			List<String> data = InitConfiguration.getElementByXMLFile(GlobalOptions.otherConfigurationFile,elements);
			if(data.size()>0 && data.get(0)!=null)
			{
				GlobalOptions.verbColorBackground = data.get(0);
			}
			else
			{
				setVerbColor("#FF0000");
			}
		}
		return GlobalOptions.verbColorBackground;
	}
	
	public static void setVerbColorBackGround(String verbColorBackGround) {
		ArrayList<GenericPairC<String, String>> changes = new ArrayList<GenericPairC<String,String>>();
		changes.add(new GenericPairC<String, String>("Verb-Color-Background",verbColorBackGround));
		InitConfiguration.setXmlProperties(GlobalOptions.otherConfigurationFile, changes);	
		GlobalOptions.verbColorBackground = verbColorBackGround;
	}
	
	public static boolean getUsingTitleInAbstract() {
		if(InitConfiguration.getPropertyValue("Using-Title-In-Abstract")==null)
		{
			ArrayList<String> elements = new ArrayList<String>();
			elements.add("Using-Title-In-Abstract");
			List<String> data = InitConfiguration.getElementByXMLFile(GlobalOptions.otherConfigurationFile,elements);
			if(data!=null && data.size()>0 && data.get(0)!=null)
			{
				GlobalOptions.usingTitleInAbstract = Boolean.valueOf(data.get(0));
				InitConfiguration.addProperty("Using-Title-In-Abstract", data.get(0));
			}
			else
			{
				setUsingTitleInAbstract(true);
			}
		}
		return Boolean.valueOf(InitConfiguration.getPropertyValue("Using-Title-In-Abstract"));
	}

	
	public static void setUsingTitleInAbstract(boolean usingTitleInAbstract) {
		ArrayList<GenericPairC<String, String>> changes = new ArrayList<GenericPairC<String,String>>();
		changes.add(new GenericPairC<String, String>("Using-Title-In-Abstract",String.valueOf(usingTitleInAbstract)));
		InitConfiguration.setXmlProperties(GlobalOptions.otherConfigurationFile, changes);
		InitConfiguration.addProperty("Using-Title-In-Abstract", String.valueOf(usingTitleInAbstract));
		GlobalOptions.usingTitleInAbstract = usingTitleInAbstract;
	}

	public static int getThreadsNumber() {
		if(GlobalOptions.threadsNumber == null)
		{
			ArrayList<String> elements = new ArrayList<String>();
			elements.add("Number-Threads");
			List<String> data = InitConfiguration.getElementByXMLFile(GlobalOptions.otherConfigurationFile,elements);
			if(data!=null && data.size()>0 && data.get(0)!=null)
			{
				GlobalOptions.threadsNumber = Integer.valueOf(data.get(0));
			}
			else
			{
				GlobalOptions.threadsNumber= 2;
			}
		}
		return GlobalOptions.threadsNumber;
	}
	
	public static boolean getFreeFullTextOnly() {
		if(GlobalOptions.freeFullTextOnly == null)
		{
			ArrayList<String> elements = new ArrayList<String>();
			elements.add("Free-Full-Text-Only");
			List<String> data = InitConfiguration.getElementByXMLFile(GlobalOptions.otherConfigurationFile,elements);
			if(data!=null && data.size()>0 && data.get(0)!=null)
			{
				GlobalOptions.freeFullTextOnly = Boolean.valueOf(data.get(0));
			}
			else
			{
				GlobalOptions.freeFullTextOnly = true;
			}
		}
		return GlobalOptions.freeFullTextOnly;
	}
	
	public static boolean getDictionaryViewSearchForSynonyms()
	{
		if(GlobalOptions.dictionaryViewSearchForSynonyms == null)
		{
			ArrayList<String> elements = new ArrayList<String>();
			elements.add("Dictionary-View-Search-Synonyms");
			List<String> data = InitConfiguration.getElementByXMLFile(GlobalOptions.otherConfigurationFile,elements);
			if(data!=null && data.size()>0 && data.get(0)!=null)
			{
				GlobalOptions.dictionaryViewSearchForSynonyms = Boolean.valueOf(data.get(0));
			}
			else
			{
				GlobalOptions.dictionaryViewSearchForSynonyms = true;
			}
		}
		return GlobalOptions.dictionaryViewSearchForSynonyms;
	}


	public static String getHighlightBackGroundColor() {
		if(GlobalOptions.highlightColor == null)
		{
			ArrayList<String> elements = new ArrayList<String>();
			elements.add("Highlight-Color");
			List<String> data = InitConfiguration.getElementByXMLFile(GlobalOptions.otherConfigurationFile,elements);
			if(data!=null && data.size()>0 && data.get(0)!=null)
			{
				GlobalOptions.highlightColor = data.get(0);
			}
			else
			{
				setHighlightColor("#FFFF99");
			}
		}
		return GlobalOptions.highlightColor;
	}


	private static void setHighlightColor(String highlightColor) {
		ArrayList<GenericPairC<String, String>> changes = new ArrayList<GenericPairC<String,String>>();
		changes.add(new GenericPairC<String, String>("Highlight-Color",highlightColor));
		InitConfiguration.setXmlProperties(GlobalOptions.otherConfigurationFile, changes);	
		GlobalOptions.highlightColor = highlightColor;	
	}

}
