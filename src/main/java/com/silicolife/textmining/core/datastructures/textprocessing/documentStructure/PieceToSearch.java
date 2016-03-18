package com.silicolife.textmining.core.datastructures.textprocessing.documentStructure;

/** This class contains the information about a piece of text to find in the Document Structuring process */
public class PieceToSearch {

	/** The maximum size of a word */
	public static final int MAXWORDSIZE = 50;
	
	/** The text of the piece */
	private String text;
	/** The first words of the piece */
	private int firstWords;
	/** The last words of the piece */
	private int lastWords;
	/** The number of paragraphs to search for the piece. If it is to search in all the text,
	 * the value must be minor than 0 */
	private int nParagraphs;
	

	public PieceToSearch(String text){
		this.text = text;
		this.firstWords = this.lastWords = 2;
		this.nParagraphs = -1;
	}
	
	public PieceToSearch(String text, int firstWords, int lastWords,int nParagraphs) {
		this.text = text;
		this.firstWords = firstWords;
		this.lastWords = lastWords;
		this.nParagraphs = nParagraphs;
	}


	public boolean allParagraphs(){
		if(nParagraphs<0)
			return true;
		return false;
	}
	
	public String firstTextWords(){
		String result = "";
		
		String[] words = text.substring(0,firstWords*MAXWORDSIZE).split(" ");
		
		for(int i=0; i<firstWords; i++)
			result += words[i] + " ";
		
		return result.substring(0,result.length()-1);
	}
	
	public String lastTextWords(){
		String result = "";
		
		if(lastWords==0)
			return null;
		
		String[] words = text.substring(text.length()-lastWords*MAXWORDSIZE).split(" ");
		
		for(int i=lastWords-1; i>=0; i--)
			result = words[i] + " " + result;
		
		return result.substring(1);
	}

	public String getText() {return text;}
	public void setText(String text) {this.text = text;}
	public int getFirstWords() {return firstWords;}
	public void setFirstWords(int firstWords) {this.firstWords = firstWords;}
	public int getLastWords() {return lastWords;}
	public void setLastWords(int lastWords) {this.lastWords = lastWords;}
	public int getNParagraphs() {return nParagraphs;}
	public void setNParagraphs(int paragraphs) {nParagraphs = paragraphs;}
}
