package com.silicolife.textmining.core.datastructures.textprocessing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TermSeparator {

	private static final String SEPARATOR = "[ ]{0,1}([',;:\\+\\*\\-\\/\\(\\)\\]\\[{}])[ ]{0,1}";
	
	public static String termSeparator(String text){

		Pattern pattern = Pattern.compile(SEPARATOR);
		Matcher m = pattern.matcher(text);
		int start=0, end=0;
		StringBuffer str = new StringBuffer();
			
		while(m.find())
		{
			String s1 = text.substring(m.start(), m.end());
			String s2 = " " + m.group(1) + " ";
			start = end;
			end = m.end();
			str.append(text.substring(start,end).replace(s1,s2));
		}
		if(end<text.length())
			str.append(text.substring(end, text.length()));
				
		String result = str.toString().replaceAll("[ ]+", " ");
		return result.trim();
	}
	
//	
//	public static String termSeparator2(String text){
//		
//		Pattern pattern = Pattern.compile("(</*[A-Z]+?>)");
//		Matcher m = pattern.matcher(text);
//
//		int start=0, end=0;
//		StringBuffer str = new StringBuffer();
//			
//		while(m.find())
//		{
//			String s1 = text.substring(m.start(), m.end());
//			String s2 = " " + m.group(1) + " ";
//			start = end;
//			end = m.end();
//			str.append(text.substring(start,end).replace(s1,s2));
//		}
//		if(end<text.length())
//			str.append(text.substring(end, text.length()));
//				
//		String result = str.toString().replaceAll("[ ]+", " "); 
//		return result;
//	}
}
