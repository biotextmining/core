package com.silicolife.textmining.core.datastructures.textprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;

/**
 * Class for generate the normalization form of a word
 * 
 * 
 * @author Hugo Costa
 *
 */
public class NormalizationForm {
	
//private static String numbers = "-{0,1}\\d+?\\.\\d+|-{0,1}\\d+";

	private static Properties textRulesChangesProperties = null;

	public static String getNormalizationForm(String term) {
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
		text = text.replaceAll("([a-zA-Z]{2,})[-|—|−]\\n{1}([a-zA-Z]{3,})", "$1$2"); // word break removal in pdf
		text = text.replaceAll("\\n\\n", ". ");
		text = text.replaceAll("\\n", " "); 
		text = text.replaceAll("\\s", " ");
		text = text.replaceAll("\\t", " ");
		text = text.replaceAll("\\f", "");			
		text = text.replaceAll("\\e", "");
		text = text.replaceAll("\\c[", "");
		text = text.replaceAll("«", "<");
		text = text.replaceAll("»", ">");
		text = text.replaceAll("·", ".");
		text = text.replaceAll("\\Q", "");	
		text = text.replaceAll("\\.{2,}\\s", ". ");
		text = text.replaceAll("-\\r{0,1}\\n", "");
		text = text.replaceAll("\\.([a-zA-Z]{1,})", ". $1"); // CASES LIKE "i.e." will be converted to "i. e.", it need to be reviewed.
		text = text.replaceAll("’", "");
		text = text.replaceAll("\u2019", "'");
		text = text.replaceAll("\\s+", " ");
//		text = text.replaceAll("("+numbers+")\\s{0,1}[×x]\\s{0,1}10\\s{0,1}(\\d+)", "$1 x 10exp$2"); // convert numbers to cientific numbers
		text = text.replaceAll( "([\\ud800-\\udbff])", ""); // remove invalid caracters
		text = text.replaceAll( "([\\udc00-\\udfff])", ""); //  remove invalid caracters
//		text = text.replaceAll( "[−|—]", "-"); // Added to Ana project
		text = applyTextRulesChanges(text);
		return text;
	}
	
	private static String applyTextRulesChanges(String text) {
		File file = new File(GlobalOptions.tranformationTextFile);
		if(textRulesChangesProperties==null && file.exists()) {
			//HUGO// FileInputStream inputstream;
			InputStreamReader inputstream;
			try {
				textRulesChangesProperties = new Properties();
				//HUGO// inputstream = new FileInputStream(file);
				inputstream = new InputStreamReader(new FileInputStream(file), "UTF-8");
				textRulesChangesProperties.load(inputstream);
			}
			catch (IOException e) {
			}
		}else{
			textRulesChangesProperties = new Properties();
		}
		for(String prop:textRulesChangesProperties.stringPropertyNames()) {
			text = text.replaceAll(prop, textRulesChangesProperties.getProperty(prop));
			System.out.println(prop+"\t"+textRulesChangesProperties.getProperty(prop));
		}
		return text;
	}
	
	//ANA
	private static String applyTextRulesChangesTEST(String text) throws IOException {
		String fileInput = "C:\\Users\\anaal\\Anote2GIT\\core\\src\\main\\resources\\tranformationTextFile.prop";	
		BufferedReader br = new BufferedReader(new FileReader(fileInput));
	    String currentLine;
		Map<String, String> invCharNewChar = new HashMap<String, String>();
	    while ((currentLine = br.readLine()) != null) {
	    	String[] dataline = currentLine.split("\\t");
	    	invCharNewChar.put(dataline[0], dataline[1]);
	    }
	    for(String symbol : invCharNewChar.keySet()) {
	    	text = text.replaceAll(symbol, invCharNewChar.get(symbol));
	    	System.out.println("TEST: " + symbol + " -> " + invCharNewChar.get(symbol));
	    }
		return text;
	}

	public static String removeOffsetProblemSituationSubAbstractTags(String text) {
		text = text.replaceAll("\\r", "");//.trim();
		text = text.replaceAll("([a-zA-Z]{2,})[-|—|−]\\n{1}([a-zA-Z]{3,})", "$1$2"); // word break removal in pdf
		text = text.replaceAll("\\n\\n", ". ");
		text = text.replaceAll("\\n", " "); 
		text = text.replaceAll("\\s", " ");
		text = text.replaceAll("\\t", " ");
		text = text.replaceAll("\\f", "");			
		text = text.replaceAll("\\e", "");
		text = text.replaceAll("\\c[", "");
		text = text.replaceAll("«", "<");
		text = text.replaceAll("»", ">");
		text = text.replaceAll("·", ".");
		text = text.replaceAll("\\Q", "");	
		text = text.replaceAll("\\.{2,}\\s", ". ");
		text = text.replaceAll("-\\r{0,1}\\n", "");
		text = text.replaceAll("\\.([a-zA-Z]{1,})", ". $1");
		text = text.replaceAll("’", "");
		text = text.replaceAll("\u2019", "'");
		text = text.replaceAll("\\s+", " ");
//		text = text.replaceAll("("+numbers+")\\s{0,1}[×x]\\s{0,1}10\\s{0,1}(\\d+)", "$1 x 10exp$2"); // convert numbers to cientific numbers
		text = text.replaceAll( "([\\ud800-\\udbff])", ""); // remove invalid caracters
		text = text.replaceAll( "([\\udc00-\\udfff])", ""); //  remove invalid caracters
//		text = text.replaceAll( "[−|—]", "-"); // Added to Ana project
		text = applyTextRulesChanges(text);
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
	
	public static void main(String[] args) {
		String text = "Some culture filtrates or enterotoxin — preparations from enterobacteria — that activate 1,4x1035 the adenylate cyclase system (vibrio cholerae, LT fraction from escherichia coli and klebsiella pneumoniae, shigella dysenteriae type 1) exibit an inhibiting effect on ADP-induced platelet aggregation, while other enterotoxin preparations not effective on adenylate cyclase system, don't interfere with this model. The A. propose the platelet aggregation as cellular assay to detect enterotoxin fractions effective upon adenylate cyclase system.";
		System.out.println(removeOffsetProblemSituation(text));
	}
	
}
