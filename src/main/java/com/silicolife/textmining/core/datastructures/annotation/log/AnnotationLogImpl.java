package com.silicolife.textmining.core.datastructures.annotation.log;

import java.util.Date;

import com.silicolife.textmining.core.datastructures.utils.GenerateRandomId;
import com.silicolife.textmining.core.datastructures.utils.Utils;
import com.silicolife.textmining.core.interfaces.core.annotation.AnnotationLogTypeEnum;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;

/**
 * Class for saving a annotation log when user add , remove or edit some NER/RE annotation
 * 
 * @author Hugo Costa
 *
 */
public class AnnotationLogImpl implements IAnnotationLog{

	private long annotationLogID;
	private long originalAnnotationID;
	private long corpusID;
	private long processID;
	private long documentID;
	private AnnotationLogTypeEnum type;
	private String oldString;
	private String newString;
	private String notes;
	private Date date;
	private String user;
	
	
	public AnnotationLogImpl(long annotationLogID,long originalAnnotationID, long corpusID, long processID,
			long documentID, AnnotationLogTypeEnum type, String oldString,
			String newString, String notes,Date date,String user) {
		super();
		this.annotationLogID = annotationLogID;
		this.originalAnnotationID = originalAnnotationID;
		this.corpusID = corpusID;
		this.processID = processID;
		this.documentID = documentID;
		this.type = type;
		this.oldString = oldString;
		this.newString = newString;
		this.notes = notes;
		this.date = new Date();
		this.user = user;
		if(date!=null)
			this.date = date;
	}
	
	public AnnotationLogImpl(long originalAnnotationID, long corpusID, long processID,
			long documentID, AnnotationLogTypeEnum type, String oldString,
			String newString, String notes,Date date,String user)
	{
		this(GenerateRandomId.generateID(), originalAnnotationID, corpusID, processID, documentID, type, oldString, newString, notes, date, user);
	}


	public AnnotationLogImpl() {
		super();
	}

	@Override
	public long getAnnotationLogID() {
		return annotationLogID;
	}
	@Override
	public long getOriginalAnnotationID() {
		return originalAnnotationID;
	}
	@Override
	public long getCorpusID() {
		return corpusID;
	}
	@Override
	public long getProcessID() {
		return processID;
	}
	@Override
	public long getDocumentID() {
		return documentID;
	}
	@Override
	public AnnotationLogTypeEnum getType() {
		return type;
	}
	@Override
	public String getOldString() {
		return oldString;
	}
	@Override
	public String getNewString() {
		return newString;
	}
	@Override
	public String getNotes() {
		return notes;
	}
	@Override
	public Date getDate() {
		return date;
	}
	@Override
	public String getUser() {
		return user;
	}
	
	public void setAnnotationLogID(long annotationLogID) {
		this.annotationLogID = annotationLogID;
	}
	
	public void setOriginalAnnotationID(long originalAnnotationID) {
		this.originalAnnotationID = originalAnnotationID;
	}
	
	public void setCorpusID(long corpusID) {
		this.corpusID = corpusID;
	}
	
	public void setProcessID(long processID) {
		this.processID = processID;
	}
	
	public void setDocumentID(long documentID) {
		this.documentID = documentID;
	}
	
	public void setType(AnnotationLogTypeEnum type) {
		this.type = type;
	}
	
	public void setOldString(String oldString) {
		this.oldString = oldString;
	}

	public void setNewString(String newString) {
		this.newString = newString;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int compareTo(IAnnotationLog log)
	{
		int result = this.date.compareTo(log.getDate());
		if(result==0)
			return (int) (annotationLogID - log.getAnnotationLogID());
		else
			return result;
	}
	
	public String toString()
	{
		String base = Utils.SimpleDataFormat.format(date) + " " + type.toString() ;
		String changes = new String();
		if(!getNewString().isEmpty())
			changes = getNewString();
		if(!getOldString().isEmpty() && !getNewString().isEmpty())
			changes = changes + " <- " + getOldString();
		else if(!getOldString().isEmpty())
			changes = changes + getOldString();
		return base + " " + changes;
	}
}
