package com.silicolife.textmining.core.datastructures.documents;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.annotation.AnnotationPosition;
import com.silicolife.textmining.core.datastructures.annotation.AnnotationPositions;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.nlptools.OpenNLPSentenceSpliter;
import com.silicolife.textmining.core.datastructures.utils.HTMLCodes;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalNames;
import com.silicolife.textmining.core.datastructures.utils.conf.OtherConfigurations;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;


public class AnnotatedDocumentImpl extends PublicationImpl implements IAnnotatedDocument{

	private List<IEntityAnnotation> entities;
	private List<IEventAnnotation> events;
	private IIEProcess process;
	private ICorpus corpus;
	private List<ISentence> sentences;
	
	public AnnotatedDocumentImpl(IPublication document,IIEProcess reProcess, ICorpus corpus) {	
		super(document);
		this.corpus=corpus;
		this.process=reProcess;
		this.entities=null;
		this.events=null;
	}
	
	public AnnotatedDocumentImpl(IPublication pub,IIEProcess process,ICorpus corpus,List<IEntityAnnotation> entities) 
	{
		this(pub, process, corpus);
		this.entities=entities;
	}
	
	public AnnotatedDocumentImpl(IPublication pub,IIEProcess process,ICorpus corpus,List<IEntityAnnotation> entities,List<IEventAnnotation> events) 
	{
		this(pub, process, corpus, entities);
		this.events=events;
	}

	public synchronized List<IEntityAnnotation> getEntitiesAnnotations() throws ANoteException {
		if(entities==null)
		{
			entities = InitConfiguration.getDataAccess().getAnnotatedDocumentEntities(this);
		}
		return entities;
	}


	public synchronized List<IEventAnnotation> getEventAnnotations() throws ANoteException {
		if(entities==null)
		{
			entities = InitConfiguration.getDataAccess().getAnnotatedDocumentEntities(this);
		}
		if(events==null)
		{
			events = InitConfiguration.getDataAccess().getAnnotatedDocumentEvents(this);
		}
		return this.events;
	}
	
	public AnnotationPositions getEntitiesAnnotationsOrderByOffset() throws ANoteException
	{
		AnnotationPositions annotations = new AnnotationPositions();
		List<IEntityAnnotation> ent = getEntitiesAnnotations();
		for(int i=0;i<ent.size();i++)
		{
			IEntityAnnotation entity = ent.get(i);
			AnnotationPosition pos = new AnnotationPosition((int)entity.getStartOffset(),(int)entity.getEndOffset());
			annotations.addAnnotationWhitConflicts(pos, entity);
		}
		return annotations;
	}


	public IIEProcess getProcess() {
		return process;
	}
	
	public ICorpus getCorpus() {
		return corpus;
	}

	public List<ISentence> getSentencesText() throws ANoteException {
		if(sentences==null)
		{
			try {
				sentences =  OpenNLPSentenceSpliter.getInstance().getSentencesText(getDocumentAnnotationText());
			} catch (IOException e) {
				throw new ANoteException(e);
			}
		}
		return sentences;
	}
	
	public String getDocumentAnnotationText() throws ANoteException {	
		String text = getTExt();
		return text;
	}
	
	public String getDocumentAnnotationTextHTML() throws ANoteException
	{
		String text = getDocumentAnnotationText();
		text = HTMLCodes.textToHTML(text);
		return text;
	}

	private String getTExt() throws ANoteException {
		if(corpus.getProperties().get(GlobalNames.textType).equals(GlobalNames.abstracts))
		{
			if(OtherConfigurations.getUsingTitleInAbstract() && getTitle()!=null && getTitle().length() > 0)
			{
				return getAbstractSection() + " " + getTitle();
			}
			else
			{
				return getAbstractSection();
			}
		}
		else if(corpus.getProperties().get(GlobalNames.textType).equals(GlobalNames.fullText))
		{
			return getFullTextContent();
		}
		else if(corpus.getProperties().get(GlobalNames.textType).equals(GlobalNames.abstractOrFullText))
		{
			String fullTExt = getFullTextContent();
			if(getFullTextContent() != null && !getFullTextContent().isEmpty())
			{
				return fullTExt;
			}
			else
			{
				if(OtherConfigurations.getUsingTitleInAbstract() && getTitle()!=null && getTitle().length() > 0)
				{
					return getAbstractSection() + " " + getTitle();
				}
				else
				{
					return getAbstractSection();
				}
			}
		}
		return new String();
	}
	
	public void setEntities(List<IEntityAnnotation> entities) {
		this.entities = entities;
	}

	public void setEvents(List<IEventAnnotation> events) {
		this.events = events;
	}

	public void setProcess(IIEProcess process) {
		this.process = process;
	}

	public void setCorpus(ICorpus corpus) {
		this.corpus = corpus;
	}

	public void setSentences(List<ISentence> sentences) {
		this.sentences = sentences;
	}

	public void freememory() {
		this.entities=null;
		this.events=null;		
	}

	@Override
	public IAnnotatedDocumentStatistics getStatistics() throws ANoteException {
		return InitConfiguration.getDataAccess().getProcessDocumentStatistics(this);
	}

	@Override
	public Set<IAnoteClass> getEntityAnnotatedClasses() throws ANoteException {
		return getStatistics().getclassNumberOfEntities().keySet();
	}
	

}
