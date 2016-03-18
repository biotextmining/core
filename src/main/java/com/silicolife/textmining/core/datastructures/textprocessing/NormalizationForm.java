package com.silicolife.textmining.core.datastructures.textprocessing;

/**
 * Class for generate the normalization form of a word
 * 
 * 
 * @author Hugo Costa
 *
 */
public class NormalizationForm {
	
	private static String numbers = "-{0,1}\\d+?\\.\\d+|-{0,1}\\d+";

	
	public static String getNormalizationForm(String term)
	{
		return term;
	}
	
//	public static String removeOffsetProblemSituation(String text) {
//		text = text.replaceAll("\\r", "").trim();
//		text = text.replaceAll("\\n\\n", "\\n");
//		text = text.replaceAll("\\n", ". ");
//		text = text.replaceAll("\\s", " ");
//		text = text.replaceAll("\\t", " ");
//		text = text.replaceAll("\\f", "");			
//		text = text.replaceAll("\\e", "");
//		text = text.replaceAll("\\c[", "");
//		text = text.replaceAll("<", "«");
//		text = text.replaceAll(">", "»");
//		text = text.replaceAll("\\Q", "");	
//		text = text.replaceAll("\\s+", " ");
//		text = text.replaceAll("\\.\\.\\s", ". ");
//		text = text.replaceAll("[^\\x20-\\x7e]", "");
//		text = text.replaceAll( "([\\ud800-\\udbff\\udc00-\\udfff])", "");
//		return text;
//	}
	
	public static String removeOffsetProblemSituation(String text) {
		text = text.replaceAll("\\r", "").trim();
		text = text.replaceAll("\\n\\n", ". ");
		text = text.replaceAll("\\n", " "); 
		text = text.replaceAll("\\s", " ");
		text = text.replaceAll("\\t", " ");
		text = text.replaceAll("\\f", "");			
		text = text.replaceAll("\\e", "");
		text = text.replaceAll("\\c[", "");
		text = text.replaceAll("<", "«");
		text = text.replaceAll(">", "»");
		text = text.replaceAll("·", ".");
		text = text.replaceAll("\\Q", "");	
		text = text.replaceAll("\\.{2,}\\s", ". ");
		text = text.replaceAll("-\\r{0,1}\\n", "");
		text = text.replaceAll("\\.([a-zA-Z]{1,})", ". $1");
		text = text.replaceAll("’", "");
		text = text.replaceAll("\u2019", "'");
		text = text.replaceAll("\\s+", " ");
		text = text.replaceAll("([a-zA-Z]{3,})-\\s{1}([a-zA-Z]{3,})", "$1$2"); 
		text = text.replaceAll("("+numbers+")\\s{0,1}[×x]\\s{0,1}10\\s{0,1}(\\d+)", "$1 x 10exp$2"); 
		text = text.replaceAll( "([\\ud800-\\udbff])", "");
		text = text.replaceAll( "([\\udc00-\\udfff])", "");
		text = text.replaceAll( "−", "-"); // Added to Ana project
		return text;
	}
	
	
	
	public static String removeOffsetProblemSituationWithoutXMLMarks(String text) {
		text = text.replaceAll("\\r", "").trim();
		text = text.replaceAll("\\n\\n", "");
		text = text.replaceAll("\\n", "");
		text = text.replaceAll("\\t", " ");
		text = text.replaceAll("\\f", "");	
		text = text.replaceAll("\\s", " ");
		text = text.replaceAll("\\e", "");
		text = text.replaceAll("\\c[", "");
		text = text.replaceAll("\\Q", "");	
		text = text.replaceAll("\\s+", " ");
		text = text.replaceAll("> ", ">");
		return text;
	}
	

}
