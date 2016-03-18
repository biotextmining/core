package com.silicolife.textmining.core.datastructures.process.export.ner.configuration;

import com.silicolife.textmining.core.interfaces.process.IE.ner.export.INERCSVColumns;

public class NERCSVColumnImpl implements INERCSVColumns{

	private int annotationIDColumn;
	private int publicationIDColumn;
	private int elementColumn;
	private int classColumn;
	private int resorcesIDColumn;
	private int startOffset;
	private int endOffset;
	private int resourceInformation;
	private int resourceExternalIds;
	private int sentenceColumn;
	
	
	public NERCSVColumnImpl(int annotationIDColumn, int publicationIDColumn,int elementColumn, int classColumn, int resorcesIDColumn,
			int startOffset, int endOffset,int resourceInformation,int resourceExternalIds,int sentenceColumn) {
		super();
		this.annotationIDColumn = annotationIDColumn;
		this.publicationIDColumn = publicationIDColumn;
		this.elementColumn = elementColumn;
		this.classColumn = classColumn;
		this.resorcesIDColumn = resorcesIDColumn;
		this.startOffset = startOffset;
		this.endOffset = endOffset;
		this.resourceInformation = resourceInformation;
		this.resourceExternalIds = resourceExternalIds;
		this.sentenceColumn = sentenceColumn;
	}

	@Override
	public int getAnnotationIDColumn() {
		return annotationIDColumn;
	}

	public int getResourceInformation() {
		return resourceInformation;
	}

	public int getResourceExternalIDs() {
		return resourceExternalIds;
	}

	@Override
	public int getPublicationIDColumn() {
		return publicationIDColumn;
	}

	@Override
	public int getElementColumn() {
		return elementColumn;
	}

	@Override
	public int getClassColumn() {
		return classColumn;
	}

	@Override
	public int getResourceIDColumn() {
		return resorcesIDColumn;
	}

	@Override
	public int getStartOffset() {
		return startOffset;
	}

	@Override
	public int getEndOffset() {
		return endOffset;
	}

	public int getSentenceColumn() {
		return sentenceColumn;
	}

	public void setSentenceColumn(int sentenceColumn) {
		this.sentenceColumn = sentenceColumn;
	}

}
