package com.silicolife.textmining.core.datastructures.textprocessing.documentStructure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** This class removes the wrong tags in the text */
public class VerifyTags {
	
	public VerifyTags(){}

	public String verify(String text){
		
		String closeTag = "</([A-Z]+)?>";
		Pattern p = Pattern.compile(closeTag);
		Matcher m = p.matcher(text);
		int offset = 0;
		
		String result = text;
		
		while(m.find())
		{
			if(!m.group(1).equals("PARAGRAPH") && !m.group(1).equals("ARTICLE"))
			{
				int pos = m.start();
				
				String openTag = "<" + m.group(1) + ">";
				
				int b = text.indexOf(openTag,offset);
				
				if(b==-1 || b>pos)
				{
					String cTag = "</" + m.group(1) + ">";
					result = result.replace(openTag,"");
					result = result.replace(cTag,"");
				}
				
				offset = m.end();
			}
		}
		
		return result;
	}
	
	public String verify2(String text){
		
		String[] tags = new String[]{"ABSTRACT","TITLE","AUTHORS","JOURNAL"};
		String result = text;
		
		for(String tag: tags)
		{
			String closeTag = "</" + tag + ">";
			String openTag = "<" + tag + ">";
			
			int a = text.indexOf(openTag);
			int b = text.indexOf(closeTag);
			
			if(a<0 || b<0 || b<a)
			{
				result = result.replace(openTag, "");
				result = result.replace(closeTag, "");
			}
		}
		return result;
	}
}
