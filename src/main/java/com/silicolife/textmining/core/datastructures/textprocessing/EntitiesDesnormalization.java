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
		String entityCaracters = entityText.replaceAll("\\s", "");
		if(entity.getStartOffset()!=0){
			String caractersBefore = getNormalizedText().substring(0, startoffset);
			startoffset = getCorrectedStartOffset(caractersBefore);
		}
		endoffset = getCorrectedEndOffset(startoffset, entityCaracters);
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
		int caractersBeforeOffset = 0;
		while(correctedStartOffset<getRawText().length() && caractersBeforeOffset<caractersBefore.length()){
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
		int correctedEndOffset = correctedStartOffset;
		int entityCaractersOffset = 0;
		while(correctedEndOffset<getRawText().length() && entityCaractersOffset<entityCaracters.length()){
			char characterInRawText = getRawText().charAt(correctedEndOffset);
			if(!Character.isWhitespace(characterInRawText)){
				entityCaractersOffset++;
			}
			correctedEndOffset++;
		}
		return correctedEndOffset;
	}

	public static void main(String[] args) {
		String rawText = "The methane(CH4) is a compound.\n The 1,2-di-(9Z)-octadecenoyl-sn-glycerol 3-phosphate is other compound.";
		String normalizedText = "The methane ( CH4 ) is a compound. The 1 , 2 - di - ( 9Z ) - octadecenoyl - sn - glycerol 3 - phosphate is other compound.";
		List<IEntityAnnotation> annotations = new ArrayList<>();
		annotations.add(new EntityAnnotationImpl(4, 11, null, null, "methane", "methane", null));
		annotations.add(new EntityAnnotationImpl(14, 17, null, null, "CH4", "CH4", null));
		annotations.add(new EntityAnnotationImpl(39, 103, null, null, "1 , 2 - di - ( 9Z ) - octadecenoyl - sn - glycerol 3 - phosphate", "1 , 2 - di - ( 9Z ) - octadecenoyl - sn - glycerol 3 - phosphate", null));
		EntitiesDesnormalization desnormalizer = new EntitiesDesnormalization(rawText,normalizedText,annotations);
		desnormalizer.getDesnormalizedAnnotations();
	}




}
