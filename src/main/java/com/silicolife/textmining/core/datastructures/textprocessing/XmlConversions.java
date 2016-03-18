package com.silicolife.textmining.core.datastructures.textprocessing;


public class XmlConversions {

	public static String replaceXmlInit(String text){
		String res = text;
		
		/*
		<?xml version="1 . 0" encoding="ISO - 8859 - 1"?>
		<?xml - stylesheet type="text/css" href=" . . \ . . \ default . css"?>
		*/
		res = res.replaceAll("<\\?xml version.+?\\?>", "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
		res = res.replaceAll("<\\?xml - stylesheet.+?\\?>", "<?xml-stylesheet type=\"text/css\" href=\"..\\\\..\\\\default.css\"?>");
		
		return res;
	}

	public static String removeSpecialCharacteres(String str) {
		String result = str;
		result = result.replaceAll("&", "");
		result = result.replaceAll("\"", "&quot;");
		result = result.replaceAll("<", "«");
		result = result.replaceAll("/", "&#47;");
		result = result.replaceAll("\\p{C}", " ");
		return result;
	}
}
