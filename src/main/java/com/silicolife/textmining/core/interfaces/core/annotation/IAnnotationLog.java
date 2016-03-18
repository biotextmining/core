package com.silicolife.textmining.core.interfaces.core.annotation;

import java.util.Date;

public interface IAnnotationLog extends Comparable<IAnnotationLog>{
	

	public long getOriginalAnnotationID();
	public long getAnnotationLogID();
	public long getCorpusID();
	public long getProcessID();
	public long getDocumentID();
	public AnnotationLogTypeEnum getType();
	public String getOldString();
	public String getNewString();
	public String getNotes();
	public Date getDate();
	public String getUser();

}
