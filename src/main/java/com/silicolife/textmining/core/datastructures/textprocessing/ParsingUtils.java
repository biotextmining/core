package com.silicolife.textmining.core.datastructures.textprocessing;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingUtils {
	
	/*  ************* Regular Expressions ************** */
	
	/* 0 or more ( 0 or more separators followed by "<br/>" (new line) followed by 0 or more separators ) */
	public static final String ENTER_OPTIONAL = "(\\s*(<br/>)\\s*)*";
	
	/* 1 or more ( 0 or more separators followed by "<br/>" (new line) followed by 0 or more separators ) */
	public static final String ENTER_MANDATORY = "(\\s*(<br/>)\\s*)+";
	
	/* ENTER in the begining of the text */
	public static final String BEGINING_ENTER = "^"+ENTER_MANDATORY;
	
	/* ENTER in the end of the text */
	public static final String END_ENTER = ENTER_MANDATORY+"$";
	
	/* <FONT COLOR= followed by one or more ( any character except '>' )followed by ><b> */  
	public static final String FONT_START = "<FONT COLOR=[^>]+><b>";
	
	
	public static final String FONT_END = "</b></FONT>";
	
	/* 0 or more separator followed by <br/> followed by 0 or more separator followed by <br/> followed by 0 or more separator */
	public static final String DOUBLE_ENTER = "(\\s)*<br/>(\\s)*<br/>(\\s)*";
	
	/* 0 or more separator followed by <PARAGRAPH> followed by 0 or more separator */
	public static final String BEGINING_PARAGRAPH = "((\\s)*<PARAGRAPH>(\\s)*)+";
	
	/* 0 or more separator followed by </PARAGRAPH> followed by 0 or more separator */
	public static final String END_PARAGRAPH = "((\\s)*</PARAGRAPH>(\\s)*)+";
	
	//public static final String JOURNAL = "&#.*;";
	
	
	/*  ************************************************ */
	
	
	
	
	/* Converts a given text to its regular expression representation
	 * Converts special characters to their regular expression code 
	 */
	public static String textToRegExp(String text){
		
		String regExp = text.replace("\\", "\\\\");
		regExp = regExp.replace("(", "\\(");
		regExp = regExp.replace(")", "\\)");
		regExp = regExp.replace("+", "\\+");
		regExp = regExp.replace("-", "\\-");
		regExp = regExp.replace("*", "\\*");
		regExp = regExp.replace("?", "\\?");
		regExp = regExp.replace("[", "\\[");
		regExp = regExp.replace("]", "\\]");
		regExp = regExp.replace("{", "\\{");
		regExp = regExp.replace("}", "\\}");
		regExp = regExp.replace("|", "\\|");
		regExp = regExp.replace("$", "\\$");
		regExp = regExp.replace("^", "\\^");
		regExp = regExp.replace("=", "\\=");
		regExp = regExp.replace(".", "\\.");
		return regExp;
	}
	
	/* Remove the enters ( <br/> ) in the given text */
	public static String removeHtmlEnters(String text){
		return text.replaceAll(ENTER_OPTIONAL,"");
	}
	
	/* Remove enters from the begining and end of the given text */
	public static String removeExtremeEnters(String text){
	
		String result = text.replaceAll(BEGINING_ENTER,"");
		result = result.replaceAll(END_ENTER, "");
		
		return result;
	}
	
	/* Remove Html tokens and <br/> tags form a given text */
	public static String clearText(String text){
		String resultext;		
		resultext = text.replaceAll(FONT_START, "");
		resultext = resultext.replaceAll(FONT_END, "");		
		//resultext = resultext.replaceAll("</?b>", "");
		
		resultext = resultext.replaceAll(DOUBLE_ENTER, "  ");
				
		
		resultext = resultext.replaceAll(BEGINING_ENTER, "");
		resultext = resultext.replaceAll(END_ENTER, "");
		resultext = resultext.replaceAll(ENTER_MANDATORY, " ");
		
		resultext = resultext.replace("<br/>", ""); // RETIRAR??????????????????????????????????????????????????????????????????
				
		return resultext;
	}
	
	
	public static String replace(String text, String term, String leftTag, String rithtTag){

		Pattern pattern = Pattern.compile("([ -.\n]+)" + ParsingUtils.textToRegExp(term) + "([ -.]+)");
		Matcher m = pattern.matcher(text);

		int start=0, end=0;
		StringBuffer str = new StringBuffer();
			
		while(m.find())
		{
			String s1 = m.group(1) + term + m.group(2);
			String s2 = m.group(1) + leftTag + term + rithtTag + m.group(2);

			start = end;
			end = m.end();
			str.append(text.substring(start,end).replace(s1,s2));		
		}
		
		if(end<text.length())
			str.append(text.substring(end, text.length()));
				
		return str.toString();
	}
	
	public static String removeBoundarieSpaces(String text){
		Pattern p = Pattern.compile("^\\s*(.+?)\\s*$");
		Matcher m = p.matcher(text);
		String res = text;
		if(m.find())
			res = m.group(1);
		return res;
	}

}
