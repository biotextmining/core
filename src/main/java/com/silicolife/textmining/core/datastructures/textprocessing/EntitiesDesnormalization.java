package com.silicolife.textmining.core.datastructures.textprocessing;

import java.util.ArrayList;
import java.util.List;

import com.silicolife.textmining.core.datastructures.annotation.ner.EntityAnnotationImpl;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;

public class EntitiesDesnormalization {

	private String rawText;
	private String normalizedText;
	private List<IEntityAnnotation> sortedAnnotations;

	public EntitiesDesnormalization(String rawText, String normalizedText, List<IEntityAnnotation> sortedAnnotations){
		this.rawText = rawText;
		this.normalizedText = normalizedText;
		this.sortedAnnotations = sortedAnnotations;
	}

	public String getRawText() {
		return rawText;
	}

	public String getNormalizedText() {
		return normalizedText;
	}

	public List<IEntityAnnotation> getSortedAnnotations() {
		return sortedAnnotations;
	}

	public List<IEntityAnnotation> getDesnormalizedAnnotations(){
		if(getRawText().equals(getNormalizedText())){
			return getSortedAnnotations();
		}
		List<IEntityAnnotation> correctedAnnotations = new ArrayList<>();
		for(IEntityAnnotation entity :getSortedAnnotations()){
			correctedAnnotations.add(getConvertedAnnotation(entity));
		}
		if(correctedAnnotations.size()!=getSortedAnnotations().size()){
			System.err.println("The desnormalized didn't performed a good desnormalization!"
					+ " Please verify the given annotations and the output annotations");
		}
		return correctedAnnotations;
	}


	private IEntityAnnotation getConvertedAnnotation(IEntityAnnotation entity){
		int startoffset = (int)entity.getStartOffset();
		int endoffset = (int)entity.getEndOffset();
		String entityText = getNormalizedText().substring(startoffset, endoffset);
		if(entity.getStartOffset()!=0){
			String caractersBefore = getNormalizedText().substring(0, startoffset);
			startoffset = getCorrectedStartOffset(caractersBefore);
		}
		endoffset = getCorrectedEndOffset(startoffset, entityText);
		entityText = getRawText().substring(startoffset, endoffset);
		return new EntityAnnotationImpl(startoffset, endoffset, entity.getClassAnnotation(), entity.getResourceElement(), entityText, entityText, entity.getProperties());
	}
	

	/**
	 * Get the text before the entity annotation in normalized text.
	 * This text is used to count the number of characters that are not equal to whitespace.
	 * After the loop will use the raw text to get the corrected offset until the length of all previously given characters.
	 * NOTE: If the raw text ends with a whitespace in the corrected offset, the counter is added with 1.
	 */
	private int getCorrectedStartOffset(String caractersBefore){
		caractersBefore = caractersBefore.replaceAll("\\s", "");
		int correctedStartOffset = 0;
		int caractersBeforeOffset = countWhiteSpacesUsedAsCharacters(caractersBefore);
		while(correctedStartOffset<getRawText().length()-1 && caractersBeforeOffset<caractersBefore.length()){
			char characterInRawText = getRawText().charAt(correctedStartOffset);
			if(!Character.isWhitespace(characterInRawText)){
				caractersBeforeOffset++;
			}
			correctedStartOffset++;
		}
		char lastChar = getRawText().charAt(correctedStartOffset);
		if(Character.isWhitespace(lastChar))
			return correctedStartOffset + 1;
		return correctedStartOffset;
	}
	
	private int getCorrectedEndOffset(int correctedStartOffset, String entityCaracters){
		entityCaracters = entityCaracters.replaceAll("\\s", "");
		int correctedEndOffset = correctedStartOffset;
		int entityCaractersOffset = countWhiteSpacesUsedAsCharacters(entityCaracters);
		while(correctedEndOffset<getRawText().length()-1 && entityCaractersOffset<entityCaracters.length()){
			char characterInRawText = getRawText().charAt(correctedEndOffset);
			if(!Character.isWhitespace(characterInRawText)){
				entityCaractersOffset++;
			}
			correctedEndOffset++;
		}
		return correctedEndOffset;
	}
	
	/**
	 * 
	 * This method will count the number of whitespaces not removed by regular expression and are whitespaces from other unicode type.
	 * The value will restrict the loop to stop in the right place, because !Character.isWhitespace will not be right for those spaces in this case.
	 * 
	 * @param caractersBefore
	 * @return
	 */
	private int countWhiteSpacesUsedAsCharacters(String caractersBefore) {
		int valueToAdd = 0;
		for(int i=0; i<caractersBefore.length(); i++){
			char charToValidate = caractersBefore.charAt(i);
			if(Character.isWhitespace(charToValidate)){
				valueToAdd++;
			}
		}
		return valueToAdd;
	}

	public static void main(String[] args) {
		String rawText = "The methane(CH4) is a compound.\n The 1,2-di-(9Z)-octadecenoyl-sn-glycerol 3-phosphate is other compound.";
		String normalizedText = "The methane ( CH4 ) is a compound. The 1 , 2 - di - ( 9Z ) - octadecenoyl - sn - glycerol 3 - phosphate is other compound.";
//		String rawText = "MiRNAs are key regulators of tumorigenesis that are aberrantly expressed in the circulation and tissue of patients with cancer. The aim of this study was to determine whether miRNA dysregulation in the circulation reflected similar changes in tumour tissue. Athymic nude mice (n = 20) received either a mammary fat pad (n = 8, MFP), or subcutaneous (n = 7, SC) injection of MDA-MB-231 cells. Controls received no tumour cells (n  = 5). Tumour volume was monitored weekly and blood sampling performed at weeks 1, 3 and 6 following tumour induction (total n = 60). Animals were sacrificed at week 6 and tumour tissue (n = 15), lungs (n = 20) and enlarged lymph nodes (n = 3) harvested. MicroRNAs were extracted from all samples (n = 98) and relative expression quantified using RQ-PCR. MiR-221 expression was significantly increased in tumour compared to healthy tissue (p«0.001). MiR-10b expression was significantly higher in MFP compared to SC tumours (p«0.05), with the highest levels detected in diseased lymph nodes (p«0.05). MiR-10b was undetectable in the circulation, with no significant change in circulating miR-221 expression detected during disease progression. MiR-195 and miR-497 were significantly decreased in tumour tissue (p«0.05), and also in the circulation of animals 3 weeks following tumour induction (p«0.05). At both tissue and circulating level, a positive correlation was observed between miR-497 and miR-195 (r = 0.61, p«0.001; r = 0.41, p«0.01 respectively). This study highlights the distinct roles of miRNAs in circulation and tissue. It also implicates miRNAs in disease dissemination and progression, which may be important in systemic therapy and biomarker development. Relationship between circulating and tissue microRNAs in a murine model of breast cancer.";
//		String normalizedText = "MiRNAs are key regulators of tumorigenesis that are aberrantly expressed in the circulation and tissue of patients with cancer. The aim of this study was to determine whether miRNA dysregulation in the circulation reflected similar changes in tumour tissue. Athymic nude mice ( n = 20 ) received either a mammary fat pad ( n = 8 , MFP ) , or subcutaneous ( n  = 7 , SC ) injection of MDA - MB - 231 cells. Controls received no tumour cells ( n = 5 ) . Tumour volume was monitored weekly and blood sampling performed at weeks 1 , 3 and 6 following tumour induction ( total n = 60 ) . Animals were sacrificed at week 6 and tumour tissue ( n = 15 ) , lungs ( n = 20 ) and enlarged lymph nodes ( n = 3 ) harvested. MicroRNAs were extracted from all samples ( n = 98 ) and relative expression quantified using RQ - PCR. MiR - 221 expression was significantly increased in tumour compared to healthy tissue ( p«0.001 ) . MiR - 10b expression was significantly higher in MFP compared to SC tumours ( p«0.05 ) , with the highest levels detected in diseased lymph nodes ( p«0.05 ) . MiR - 10b was undetectable in the circulation , with no significant change in circulating miR - 221 expression detected during disease progression. MiR - 195 and miR - 497 were significantly decreased in tumour tissue ( p«0.05 ) , and also in the circulation of animals 3 weeks following tumour induction ( p«0.05 ) . At both tissue and circulating level , a positive correlation was observed between miR - 497 and miR - 195 ( r = 0.61 , p«0.001 ; r = 0.41 , p«0.01 respectively ) . This study highlights the distinct roles of miRNAs in circulation and tissue. It also implicates miRNAs in disease dissemination and progression , which may be important in systemic therapy and biomarker development. Relationship between circulating and tissue microRNAs in a murine model of breast cancer.";
		List<IEntityAnnotation> annotations = new ArrayList<>();
		annotations.add(new EntityAnnotationImpl(4, 11, null, null, "methane", "methane", null));
		annotations.add(new EntityAnnotationImpl(14, 17, null, null, "CH4", "CH4", null));
		annotations.add(new EntityAnnotationImpl(39, 103, null, null, "1 , 2 - di - ( 9Z ) - octadecenoyl - sn - glycerol 3 - phosphate", "1 , 2 - di - ( 9Z ) - octadecenoyl - sn - glycerol 3 - phosphate", null));
//		annotations.add(new EntityAnnotationImpl(1849,1862,null, null,"breast cancer", "breast cancer", null));
		EntitiesDesnormalization desnormalizer = new EntitiesDesnormalization(rawText,normalizedText,annotations);
//		System.out.println(normalizedText.substring(1849, 1862));
		System.out.println(desnormalizer.getDesnormalizedAnnotations());
	}




}
