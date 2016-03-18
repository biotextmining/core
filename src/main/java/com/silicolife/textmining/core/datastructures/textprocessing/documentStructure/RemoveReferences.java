package com.silicolife.textmining.core.datastructures.textprocessing.documentStructure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.silicolife.textmining.core.datastructures.utils.GenericPairImpl;

public class RemoveReferences {
	
	public static GenericPairImpl<String,String> remove(String text){
		
		Pattern p = Pattern.compile("<section>acknowledgments</section>",Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(text);
		
		if(m.find())
		{
			int init = m.start();
			text = text.substring(0,init);
			text += "</PARAGRAPH>\n</ARTICLE>";
		}
		
		p = Pattern.compile("<references>.+?</references>",Pattern.CASE_INSENSITIVE);
		m = p.matcher(text);
		
		if(m.find())
		{
			int pos = m.start();
			text = text.substring(0,pos);
			text += "</PARAGRAPH>\n</ARTICLE>";
		}
		
		return new GenericPairImpl<String, String>(text,null);
	}

}
