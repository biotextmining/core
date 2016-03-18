package com.silicolife.textmining.core.datastructures.process.export.re.configuration;

import com.silicolife.textmining.core.interfaces.process.IE.re.IRECSVColumns;

public class RECSVColumnImpl implements IRECSVColumns{

	
	private int annotationIDColumn;
	private int publicationIDColumn;
	private int clueColumn;
	private int startOffset;
	private int endOffset;
	private int entitiesAtLeft;
	private int entitiesAtRight;
	private int sentenceColumn;
	private int leftEntitiesExternalIDs;
	private int rightEntitiesExternalIDs;

	
	
	public RECSVColumnImpl(int annotationIDColumn, int publicationIDColumn,
			int clueColumn, int startOffset, int endOffset, int entitiesAtLeft,
			int entitiesAtRight,int setenceColumn,int leftEntitiesExternalIDs,int rightEntitiesExternalIDs) {
		super();
		this.annotationIDColumn = annotationIDColumn;
		this.publicationIDColumn = publicationIDColumn;
		this.clueColumn = clueColumn;
		this.startOffset = startOffset;
		this.endOffset = endOffset;
		this.entitiesAtLeft = entitiesAtLeft;
		this.entitiesAtRight = entitiesAtRight;
		this.sentenceColumn = setenceColumn;
		this.leftEntitiesExternalIDs = leftEntitiesExternalIDs;
		this.rightEntitiesExternalIDs = rightEntitiesExternalIDs;
	}

	@Override
	public int getAnnotationIDColumn() {
		return annotationIDColumn;
	}

	@Override
	public int getPublicationIDColumn() {
		return publicationIDColumn;
	}

	@Override
	public int getElementColumn() {
		return clueColumn;
	}

	@Override
	public int getStartOffset() {
		return startOffset;
	}

	@Override
	public int getEndOffset() {
		return endOffset;
	}

	@Override
	public int getLeftEntitiesColumn() {
		return entitiesAtLeft;
	}

	@Override
	public int getRightEntitiesColumn() {
		return entitiesAtRight;
	}

	@Override
	public int getSentenceColumn() {
		return sentenceColumn;
	}

	@Override
	public int getLeftEntitiesExternalIDs() {
		return leftEntitiesExternalIDs;
	}

	@Override
	public int getRightEntitiesExternalIDs() {
		return rightEntitiesExternalIDs;
	}

}
