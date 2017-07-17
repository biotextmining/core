package com.silicolife.textmining.core.datastructures.documents;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationEditable;

public class PublicationEditableImpl extends PublicationImpl  implements IPublicationEditable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PublicationEditableImpl(IPublication pub) {
		super(pub);
	}

	@Override
	public boolean completeMetaData() {
		if(super.getTitle().isEmpty() || super.getAbstractSection().isEmpty() || super.getAuthors().isEmpty())
			return false;
		return true;
	}

	@Override
	public void setAbstract(String abstractTExt) {
		if(abstractTExt!=null)
			this.abstractSection = abstractTExt;
	}

	@Override
	public void setYearDate(String yeardate) {
		if(yeardate!=null)
			this.yeardate = yeardate;
	}

	@Override
	public void setTitle(String title) {
		this.title=title;
	}

	@Override
	public void setAuthors(String authors) {
		this.authors=authors;
	}

	@Override
	public void setJournal(String journal) {
		this.journal=journal;
	}

	@Override
	public void setFullTextFromURL(String sourceURL) throws FileNotFoundException, IOException {
		setFullTextContent(PDFtoText.convertPDFDocument(sourceURL));
		
	}

}
