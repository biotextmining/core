package com.silicolife.textmining.core.datastructures.utils.conf;

public class GlobalNames {
	
	/**
	 * NER Options
	 */
	public final static String stopWords= "Stop Words";
	public final static String normalization = "Normalization";
	public final static String stopWordsResourceID = "Stop Words Resource ID";
	public final static String allclasses = "All Classes";
	public final static String casesensitive = "Case Sensitive";
	public final static String nerAbnerModel = "model";
	public final static String nerChemistryTaggerChemistrylon = "ChemicalIon";
	public final static String nerChemistryTaggerChemistryElements = "ChemicalElement";
	public final static String nerChemistryTaggerChemistryCompounds = "ChemicalCompound";
	public final static String recooccurrenceModel = "Rel@tion Cooccurrence Model";
	public final static String nerpreProcessing = "NER Preprocessing :";
	public final static String nerpreProcessingTags = "NER Preprocessing (POS Tags) :";
	public final static String nerpreProcessingPosTagging = "POS Tagging"; 
	public final static String nerpreProcessingNo = "No";
	public final static String nerpreProcessingPosTaggingAndStopWords = nerpreProcessingPosTagging+" + "+stopWords;
	public final static String useOtherResourceInformationInRules = "Use Other Resource Information In Rules";
	public final static String sizeOfNonAnnotatedSmallWords = "Size of Small Words to be not annotated :";



	
	/**
	 * Corpus Properties
	 */
	public final static String fullText = "Full Text";
	public final static String abstracts = "Abstract";
	public final static String abstractOrFullText = "Abstract or Full Text";
	public final static String textType = "Text Type";
	public final static String sourceQueries = "Source Queries";
	
	/**
	 * Native Process Names
	 */
	public final static String mergeNER = "Merged Schemas";

	public final static String mergeNERSchema = "mergeNERSchema";
	public final static String NERSchema = "merged Schema";

	
	/**
	 * Processes Type
	 */
	public final static String ner = "NER";
	public final static String re = "RE";

	
	/**
	 * Relation
	 */
	
	public final static String entityBasedProcess = "Entity Based Process";
	public final static String taggerName = "Tagger";
	public final static String relationModel = "Relation model";
	public final static String verbFilter = "Verb Filter Resource (ID) ";
	public final static String verbAddition = "Verb Addition Resource (ID)";
	public final static String verbAdditionOnly = "Verb List Resource (ID)";
	
	/**
	 * Relation Properties
	 */
	public final static String relationPropertyLemma = "lemma";
	public final static String relationPropertyDirectionally = "directionally";
	public final static String relationPropertyPolarity = "polarity";

	/**
	 * Entities
	 */
	
	public final static String entity = "Entity";

	public final static String reactions = "Reactions";
	public final static String metabolicGenes = "Metabolic Genes";
	
	
	public final static String createdby ="Created by";
	
	public final static String color = "Color";
	public final static String backgoundcolor = "Backgound Color";

	public final static String duplicatedFrom ="Duplicated from";
	
	public final static String serverXMLsDirectory = "Server XML directory";


}
