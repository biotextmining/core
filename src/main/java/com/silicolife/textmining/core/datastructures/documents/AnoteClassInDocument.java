package com.silicolife.textmining.core.datastructures.documents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.silicolife.textmining.core.datastructures.annotation.AnnotationPosition;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class AnoteClassInDocument {
	
	private Set<IAnoteClass> classes;
	private IAnnotatedDocument annotaedDocument;
	
	private Set<String> diferentEntityClassNames;
	private Map<String, IResourceElement> mapClassNameResourceElement;
	private Map<String, List<IEntityAnnotation>> mapResourceElementEntitiesAnnoataed;
	private SortedMap<Integer,List<IEntityAnnotation>> sentenceIndexEntitiesAnnotatedFromClass;
	private Map<AnnotationPosition,Integer> sentenceBoundariesIndex;
	private Map<Integer,AnnotationPosition> indexsentenceBoundaries;

	
	public AnoteClassInDocument(Set<IAnoteClass> classes,IAnnotatedDocument annotaedDocument) throws ANoteException
	{
		this.classes=classes;
		this.annotaedDocument=annotaedDocument;
		this.diferentEntityClassNames = new HashSet<>();
		this.mapClassNameResourceElement = new HashMap<>();
		this.sentenceIndexEntitiesAnnotatedFromClass=new TreeMap<>();
		this.sentenceBoundariesIndex = new HashMap<>();
		this.indexsentenceBoundaries = new HashMap<>();
		this.mapResourceElementEntitiesAnnoataed = new HashMap<>();
		try {
			processSentences();
		} catch (IOException e) {
		}
		processAnnotations();
	}
	
	public AnoteClassInDocument(IAnoteClass klass,IAnnotatedDocument annotaedDocument) throws ANoteException
	{
		this(transform(klass), annotaedDocument);
	}
	
	private static Set<IAnoteClass> transform(IAnoteClass klass)
	{
		Set<IAnoteClass> organismSet = new HashSet<>();
		organismSet.add(klass);
		return organismSet;
	}
	
	public boolean onlyOneEntityClassName()
	{
		int diferentEntityClassNamesSize = diferentEntityClassNames.size();
		switch (diferentEntityClassNamesSize) {
			case 1 :
				return true;
			default :
				return false;
		}
	}
	
	public String getOnlyEntityClassNameString()
	{
		int diferentEntityClassNamesSize = diferentEntityClassNames.size();
		if(diferentEntityClassNamesSize==1)
		{
			return diferentEntityClassNames.iterator().next();
		}
		else
		{
			return null;
		}
	}
	
	public IResourceElement getOnlyEntityClassNameResourceName()
	{
		int diferentEntityClassNamesSize = diferentEntityClassNames.size();
		if(diferentEntityClassNamesSize==1)
		{
			String key = diferentEntityClassNames.iterator().next();
			return mapClassNameResourceElement.get(key);
		}
		else
		{
			return null;
		}
	}
	
	public List<IEntityAnnotation> getEntityFilterByClassInSentence(ISentence sentence)
	{
		AnnotationPosition position = new AnnotationPosition((int)sentence.getStartOffset(), (int)sentence.getEndOffset());
		if(sentenceBoundariesIndex.containsKey(position))
		{
			int index = sentenceBoundariesIndex.get(position);
			return sentenceIndexEntitiesAnnotatedFromClass.get(index);
		}
		else
			return new ArrayList<>();
	}
	
	public List<IEntityAnnotation> getEntityFilterByClassInNextSentence(ISentence sentence)
	{
		AnnotationPosition position = new AnnotationPosition((int)sentence.getStartOffset(), (int)sentence.getEndOffset());
		if(sentenceBoundariesIndex.containsKey(position))
		{
			int index = sentenceBoundariesIndex.get(position);
			return sentenceIndexEntitiesAnnotatedFromClass.get(++index);
		}
		else
			return new ArrayList<>();
	}
	
	public List<IEntityAnnotation> getEntityFilterByClassInPreviousSentence(ISentence sentence)
	{
		AnnotationPosition position = new AnnotationPosition((int)sentence.getStartOffset(), (int)sentence.getEndOffset());
		if(sentenceBoundariesIndex.containsKey(position))
		{
			int index = sentenceBoundariesIndex.get(position);
			if(index > 1)
				return sentenceIndexEntitiesAnnotatedFromClass.get(--index);
				
		}
		return new ArrayList<>();
	}

	private void processSentences() throws ANoteException, IOException {
		List<ISentence> sentences = annotaedDocument.getSentencesText();
		int i=1;
		for(ISentence sentence:sentences)
		{
			AnnotationPosition key = new AnnotationPosition((int)sentence.getStartOffset(), (int)sentence.getEndOffset());
			sentenceIndexEntitiesAnnotatedFromClass.put(i , new ArrayList<IEntityAnnotation>());
			sentenceBoundariesIndex.put(key, i);
			indexsentenceBoundaries.put(i,key);
			i++;
		}
	}

	private void processAnnotations() throws ANoteException {
		List<IEntityAnnotation> entitiesAnnotated = annotaedDocument.getEntitiesAnnotations();
		for(IEntityAnnotation entAnnotation:entitiesAnnotated)
		{
			// Filter entities by class  and ignore annotation without class
			if(entAnnotation.getClassAnnotation()!=null && classes.contains(entAnnotation.getClassAnnotation()))
			{
				IResourceElement resorceElement = entAnnotation.getResourceElement();
				// Test if exist a resource element associated
				if(resorceElement!=null)
				{
					String primaryTerm = resorceElement.getTerm();
					if(!diferentEntityClassNames.contains(primaryTerm))
						diferentEntityClassNames.add(primaryTerm);
					if(!mapClassNameResourceElement.containsKey(primaryTerm))
					{
						mapClassNameResourceElement.put(primaryTerm, resorceElement);
					}
					else if(mapClassNameResourceElement.get(primaryTerm)==null)
						mapClassNameResourceElement.put(primaryTerm, resorceElement);
					if(!mapResourceElementEntitiesAnnoataed.containsKey(primaryTerm))
					{
						mapResourceElementEntitiesAnnoataed.put(primaryTerm, new ArrayList<IEntityAnnotation>());
					}
					mapResourceElementEntitiesAnnoataed.get(primaryTerm).add(entAnnotation);

				}
				else
				{
					String entityClassName = entAnnotation.getAnnotationValue();
					if(!diferentEntityClassNames.contains(entityClassName))
						diferentEntityClassNames.add(entityClassName);
					if(!mapClassNameResourceElement.containsKey(entityClassName))
						mapClassNameResourceElement.put(entityClassName, null);
				}
				addEntityToSentence(entAnnotation);
			}
		}
	}

	private void addEntityToSentence(IEntityAnnotation entAnnotation) {
		Long startEntity = entAnnotation.getStartOffset();
		for(AnnotationPosition postion:sentenceBoundariesIndex.keySet())
		{
			if(postion.getStart() <= startEntity && startEntity <= postion.getEnd())
			{
				int index = sentenceBoundariesIndex.get(postion);
				sentenceIndexEntitiesAnnotatedFromClass.get(index).add(entAnnotation);
			}
		}
	}
	
	public String toString()
	{
		String result = "Document ("+annotaedDocument.getId()+") by class "+printClasses() + "\n";
		result = result +"Organisms "+ diferentEntityClassNames.toString() +"\n";
		for(String org:diferentEntityClassNames)
		{
			IResourceElement resource = mapClassNameResourceElement.get(org);
			result = result +"\t"+ org ;
			if(resource!=null)
			{
				try {
					result = result + "[";
					for(IExternalID extID : resource.getExtenalIDs())
					{
						result = result + extID.getSource() + ":" + extID.getExternalID()+" ";
					}
				} catch (ANoteException e) {
					e.printStackTrace();
				}
				result = result + "]\n";
			}
			else
			{
				result = result + "\n";
			}

		}
		String entitiesBySentence = printentitiesBySentence();
		return result + entitiesBySentence;
	}
	
	private String printClasses()
	{
		String result = new String();
		for(IAnoteClass klass:classes)
			result = result + klass.getName() + ",";
		return result; 
	}

	private String printentitiesBySentence() {
		String result = "Sentences\n";
		for(Integer index :sentenceIndexEntitiesAnnotatedFromClass.keySet())
		{
			result = result + "\t"+String.valueOf(index) + " "+indexsentenceBoundaries.get(index).toString()+"\n";
			List<IEntityAnnotation> entities = sentenceIndexEntitiesAnnotatedFromClass.get(index);
			for(IEntityAnnotation entity:entities)
			{
				result = result + "\t\t"+entity.toString() + " - "+printClasses()  +"\n";
			}
		}
		return result;
	}
	
	public Set<String> getDiferentEntityClassNames() {
		return diferentEntityClassNames;
	}

	public List<IEntityAnnotation> doubleLayerFilterByResourceElement(IResourceElement basedReesourceElement,AnoteClassInDocument classFilter) {
		Set<Integer> sentenceIndexWithResourceElement = getSentencesIndexForResourceElement(basedReesourceElement);
		List<IEntityAnnotation> annotationsFiltered = new ArrayList<>();
		for(Integer index:sentenceIndexWithResourceElement)
		{
			annotationsFiltered.addAll(classFilter.getEntityFilterByClassInSentenceIndex(index));
		}
		return annotationsFiltered;
	}
	
	public Map<IResourceElement,IEntityAnnotation> getMapResourceElementAnnotationClosedToOffset()
	{
		Map<IResourceElement,IEntityAnnotation> mapResourceElementAnnotationClosedToOffset = new HashMap<>();
		for(String entityPrimaryName:diferentEntityClassNames)
		{
			IResourceElement resourceElement = mapClassNameResourceElement.get(entityPrimaryName);
			List<IEntityAnnotation> entities = mapResourceElementEntitiesAnnoataed.get(entityPrimaryName);
			IEntityAnnotation candidate = entities.get(0);
//			long referencedistance = 0;
//			if(candidate.getStartOffset()-referencedistance>0)
//				referencedistance = candidate.getStartOffset()-referencedistance;
//			else
//				referencedistance = referencedistance - candidate.getStartOffset();
//			for(IEntityAnnotation entity:entities)
//			{
//				
//			}
			mapResourceElementAnnotationClosedToOffset.put(resourceElement, candidate);
		}	
		return mapResourceElementAnnotationClosedToOffset;
	}

	private List<IEntityAnnotation> getEntityFilterByClassInSentenceIndex(Integer index) {
		return sentenceIndexEntitiesAnnotatedFromClass.get(index);
	}

	private Set<Integer> getSentencesIndexForResourceElement(IResourceElement basedReesourceElement)
	{
		Set<Integer> indexs = new HashSet<>();
		for(int index:sentenceIndexEntitiesAnnotatedFromClass.keySet())
		{
			if(entityInSentence(basedReesourceElement, index))
			{
				indexs.add(index);
			}
		}
		return indexs;
	}

	private boolean entityInSentence(IResourceElement basedReesourceElement,int index) {
		List<IEntityAnnotation> annotationsInSentence = sentenceIndexEntitiesAnnotatedFromClass.get(index);
		for(IEntityAnnotation ent:annotationsInSentence)
		{
			if(ent.getResourceElement()!=null && ent.getResourceElement().getId() == basedReesourceElement.getId())
			{
				return true;
			}
		}
		return false;
	}
}
