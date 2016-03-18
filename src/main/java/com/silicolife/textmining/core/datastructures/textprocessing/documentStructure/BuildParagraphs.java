package com.silicolife.textmining.core.datastructures.textprocessing.documentStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildParagraphs {
	
	public BuildParagraphs(){}
	
	public List<String> getParapraphs(String text){
		List<String> paragraphs = new ArrayList<String>();
		Pattern p = Pattern.compile("\\.[ ]*\n[ ]*");
		Matcher m = p.matcher(text);
		int offset = 0;
		String paragraph;
		while(m.find())
		{
			paragraph = text.substring(offset, m.start()) + ".";
			paragraphs.add(paragraph);
			offset = m.end();
		}
		paragraph = text.substring(offset);
		paragraphs.add(paragraph);
		return paragraphs;
	}
	
	public String removeBrokedLines(String text){
		Pattern p = Pattern.compile("[ ]*\n[ ]*");
		Matcher m = p.matcher(text);
		return m.replaceAll(" ");
	}
	
	public String annotateParagraphs(List<String> paragraphs){
		String paragraph;
		for(String p : paragraphs)
		{
			paragraph = removeBrokedLines(p);
		}
		return null;
	}
	
	public static void main(String... args){
		String text = "Ola tudo bem.\n Ola tudo bem\neu Sou o rafael";
		BuildParagraphs buildPar = new BuildParagraphs();
		List<String> paragraphs = buildPar.getParapraphs(text);
		buildPar.annotateParagraphs(paragraphs);
		
	}
}
