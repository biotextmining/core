package com.silicolife.textmining.core.datastructures.textprocessing;

import java.util.List;

import com.silicolife.textmining.core.datastructures.annotation.AnnotationPosition;
import com.silicolife.textmining.core.datastructures.annotation.AnnotationPositions;
import com.silicolife.textmining.core.datastructures.annotation.ner.EntityAnnotationImpl;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;

public class EntitiesDesnormalizationOld {

	private String rawText;
	private String normalizedText;
	private List<IEntityAnnotation> sortedAnnotations;

	private long tempStartOffset = -1;
	private int tokenIndexadd= 0;
	private int tokenIndex = 0;
	private int annotationIndex = 0;

	public EntitiesDesnormalizationOld(String rawText, String normalizedText, List<IEntityAnnotation> sortedAnnotations){
		this.rawText = rawText;
		this.normalizedText = normalizedText;
		this.sortedAnnotations = sortedAnnotations;
	}

	private String getRawText(){
		return rawText;
	}

	private String getNormalizedText(){
		return normalizedText;
	}

	private List<IEntityAnnotation> getSortedAnnotations(){
		return sortedAnnotations;
	}

	public List<IEntityAnnotation> getDesnormalizedAnnotations() throws ANoteException {
		AnnotationPositions correctedAnnotations = new AnnotationPositions();

		String[] rawTextTokens = getRawText().split("\\s");
		String[] normalizedTokens = getNormalizedText().split("\\s");

		if(rawTextTokens.length>0 && getSortedAnnotations().size()>0){

			long startOffsetRawText = 0;
			long endOffsetRawText = 0;
			long startOffsetNormalized = 0;
			long endOffsetNormalized = 0;


			while( tokenIndex<rawTextTokens.length && tokenIndex+tokenIndexadd<normalizedTokens.length && annotationIndex < getSortedAnnotations().size()){
				IEntityAnnotation annotation = getSortedAnnotations().get(annotationIndex);

				//get start and end offsets in both lists
				if(startOffsetRawText >= endOffsetRawText){
					endOffsetRawText = startOffsetRawText + rawTextTokens[tokenIndex].length();
				}
				if(startOffsetNormalized >= endOffsetNormalized){
					endOffsetNormalized = startOffsetNormalized + normalizedTokens[tokenIndex+tokenIndexadd].length();
				}

				//process possible annotation if both match or add normalized tokens until match
				if(rawTextTokens[tokenIndex].equals(normalizedTokens[tokenIndex+tokenIndexadd])){
					searchForAnnotations(correctedAnnotations, rawTextTokens,startOffsetRawText, 
							endOffsetRawText, startOffsetNormalized, endOffsetNormalized,annotation);

					startOffsetRawText = endOffsetRawText;
					startOffsetNormalized = endOffsetNormalized;

				}else{
					endOffsetNormalized = searchForNormalizedToken(correctedAnnotations, rawTextTokens, normalizedTokens, startOffsetRawText,
							endOffsetRawText, startOffsetNormalized, endOffsetNormalized, annotation);

					startOffsetRawText = endOffsetRawText;
					startOffsetNormalized = endOffsetNormalized;
				}

				//add white space
				if(tokenIndex != rawTextTokens.length -1){
					startOffsetRawText++;
					startOffsetNormalized++;
				}

				tokenIndex++;
			}
		}
		
		if(correctedAnnotations.getEntitiesFromAnnoattionPositions().size() != sortedAnnotations.size()){
		System.err.println("The desnormalized didn't performed a good desnormalization!"
				+ " Please verify the given annotations and the output annotations");
//			throw new ANoteException("The desnormalized didn't performed a good desnormalization!"
//								+ " Please verify the given annotations and the output annotations");
		}
		
		return correctedAnnotations.getEntitiesFromAnnoattionPositions(); 

	}

	private long searchForNormalizedToken(AnnotationPositions correctedAnnotations,String[] rawTextTokens, String[] normalizedTokens, long startOffsetRawText, 
			long endOffsetRawText, long startOffsetNormalized, long endOffsetNormalized, IEntityAnnotation annotation) {

		boolean match = false;
		String tempString = normalizedTokens[tokenIndex+tokenIndexadd];
		tokenIndexadd++;
		while(!match && normalizedTokens.length>tokenIndex+tokenIndexadd){
			tempString = tempString + normalizedTokens[tokenIndex+tokenIndexadd];
			endOffsetNormalized = endOffsetNormalized + 1 + normalizedTokens[tokenIndex+tokenIndexadd].length();
			if(rawTextTokens[tokenIndex].equals(tempString)){
				searchForAnnotations(correctedAnnotations, rawTextTokens, startOffsetRawText,
						endOffsetRawText, startOffsetNormalized, endOffsetNormalized, annotation);
				match = true;
				tempString = new String();
			}
			if(!match){
				tokenIndexadd++;
			}
		}
		return endOffsetNormalized;
	}

	private void searchForAnnotations(AnnotationPositions correctedAnnotations, String[] rawTextTokens, long startOffsetRawText,
			long endOffsetRawText, long startOffsetNormalized, long endOffsetNormalized, IEntityAnnotation annotation) {

		boolean finishAdded = false;
		while(!finishAdded && annotationIndex<getSortedAnnotations().size()){
			boolean added = addAnnotation(correctedAnnotations, rawTextTokens, startOffsetRawText, endOffsetRawText,
						startOffsetNormalized, endOffsetNormalized,
						tokenIndex, annotation);
			if(added){
				if(annotationIndex<getSortedAnnotations().size()){
					annotation = getSortedAnnotations().get(annotationIndex);
				}
			}else{
				finishAdded = true;
			}
		}
	}

	private boolean addAnnotation(AnnotationPositions correctedAnnotations, String[] nonNormalizedTokens, long startOffsetNonNormalized, long endOffsetNonNormalized, 
			long startOffsetNormalized,long endOffsetNormalized, int tokenIndex, IEntityAnnotation annotation) {

		if(annotation.getStartOffset() >= startOffsetNormalized && annotation.getEndOffset() <= endOffsetNormalized){

			if(annotation.getAnnotationValue().equals(nonNormalizedTokens[tokenIndex])){
				correctedAnnotations.addAnnotationWhitConflitsAndReplaceIfRangeIsMore(new AnnotationPosition((int)startOffsetNonNormalized, (int)endOffsetNonNormalized),new EntityAnnotationImpl(startOffsetNonNormalized, endOffsetNonNormalized, annotation.getClassAnnotation(), 
						annotation.getResourceElement(), annotation.getAnnotationValue(), annotation.isAbreviation(), annotation.getProperties()));
				annotationIndex++;
				return true;
				
			}else{
				//the annotations are sorted by offsets.
				//tries to find several annotations in the non normalized token the annotation. e.g : (NADH/NAD)
				//first annot: NADH
				//second annot: NAD
				//if the first annot is added and contains the same first text caracters of the second
				//The loop will remove the first caracter and try to find in the token text of the second annotation
				String annotationVal = annotation.getAnnotationValue();
				annotationVal = annotationVal.replaceAll("\\s", "");
				String originalToken = nonNormalizedTokens[tokenIndex];
				String temporaryToken =  originalToken;
				int start = originalToken.indexOf(annotationVal);
				boolean added = false;
				while(!added && start+1<originalToken.length()){
					long correctedStart = startOffsetNonNormalized + (long)start ;
					long correctedEnd = correctedStart + annotationVal.length();
					AnnotationPosition position = new AnnotationPosition((int)correctedStart, (int)correctedEnd);
					if(correctedAnnotations.containsConflits(position)){
						//lets try to find the second annotation
						temporaryToken= temporaryToken.substring(start+1);
						start = originalToken.length() - temporaryToken.length() + temporaryToken.indexOf(annotationVal);
					}else{
						correctedAnnotations.addAnnotationWhitConflitsAndReplaceIfRangeIsMore(position,new EntityAnnotationImpl(correctedStart, correctedEnd, 
								annotation.getClassAnnotation(), annotation.getResourceElement(), 
								annotationVal, annotation.isAbreviation(), annotation.getProperties()));
						annotationIndex++;
						added = true;
					}
					
				}
				return added;
				
			}

		}else if(annotation.getStartOffset() >= startOffsetNormalized && annotation.getStartOffset() < endOffsetNormalized){
			//starts a multi-token annotation search
			//TODO: Problem if a token is added in the same token, this don't get the write start offset... change split by replace... and find the indexOf... or verify something eg.: 3-(2-aminopheny1thio)-3-hydroxypropanoic
			//doo a search until start be -1. Save the last start given and the first token of the multi must be equal to the last chars of the nonNormalized token
			String[] annotationTokens = annotation.getAnnotationValue().split("\\s");
			int annotIndex = 0;
			int start = 0;
			while(start != -1 && annotIndex < annotationTokens.length){
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i<=annotIndex; i++){
					sb.append(annotationTokens[i]);
				}
				start = nonNormalizedTokens[tokenIndex].indexOf(sb.toString());
				if(start != -1){
					tempStartOffset = startOffsetNonNormalized + start;
				}
				annotIndex++;
			}
			 
			return false;

		}else if(annotation.getEndOffset() > startOffsetNormalized && annotation.getEndOffset() <= endOffsetNormalized && tempStartOffset != -1){
			//finish a multi-token annotation search
			String[] annotationTokens = annotation.getAnnotationValue().split("\\s");
			int start = nonNormalizedTokens[tokenIndex].indexOf(annotationTokens[annotationTokens.length-1]);
			int end = start + annotationTokens[annotationTokens.length-1].length();
			long correctedEnd = startOffsetNonNormalized + end;
			correctedAnnotations.addAnnotationWhitConflitsAndReplaceIfRangeIsMore(new AnnotationPosition((int)tempStartOffset, (int)correctedEnd),new EntityAnnotationImpl(tempStartOffset, correctedEnd, 
					annotation.getClassAnnotation(), annotation.getResourceElement(), 
					annotation.getAnnotationValue(), annotation.isAbreviation(), annotation.getProperties()));
			tempStartOffset = -1;
			annotationIndex++;
			return true;
		}
		return false;
	}

}
