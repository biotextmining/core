package com.silicolife.textmining.core.interfaces.core.document;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IPublicationEditable extends IPublication{
	public boolean completeMetaData();
	public void setTitle(String title);
	public void setAbstract(String abstractTExt);
	public void setAuthors(String authors);
	public void setJournal(String journal);
	public void setYearDate(String year);
	public void setFullTextFromURL(String sourceURL) throws FileNotFoundException, IOException;

}
