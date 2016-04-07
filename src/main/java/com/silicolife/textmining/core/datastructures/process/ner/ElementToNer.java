package com.silicolife.textmining.core.datastructures.process.ner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.silicolife.textmining.core.datastructures.annotation.ner.EntityAnnotationImpl;
import com.silicolife.textmining.core.datastructures.general.ClassPropertiesManagement;
import com.silicolife.textmining.core.datastructures.textprocessing.NormalizationForm;
import com.silicolife.textmining.core.datastructures.textprocessing.TermSeparator;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalNames;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.dictionary.IDictionary;
import com.silicolife.textmining.core.interfaces.resource.lookuptables.ILookupTable;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IOntology;
import com.silicolife.textmining.core.interfaces.resource.rules.IRuleSet;

public class ElementToNer {
	
	private List<IEntityAnnotation> terms;
	private List<IResourceElement> rules;
	private Map<Long,String> resourceIdsOptions; 
	private Map<Long,Long> resourceMapClass;
	private SortedMap<String,Set<Long>> maplowerCaseToPossibleResourceIDs;
	private TreeMap<Long, String> mapPossibleResourceIDsToTermString;
	private ResourcesToNerAnote resourceToNER;
	private boolean termNormalization;
	private boolean usingOtherResourceInfoToImproveRuleAnnotstions;
	private Map<Long, IResourceElement> mapResourceIDsToResourceElements;
	

	public ElementToNer(ResourcesToNerAnote resourceToNER,boolean termNormalization) throws ANoteException
	{
		this.resourceToNER = resourceToNER;
		terms = new ArrayList<IEntityAnnotation>();
		rules = new ArrayList<IResourceElement>();
		resourceIdsOptions = new HashMap<>();
		resourceMapClass = new HashMap<>();
		maplowerCaseToPossibleResourceIDs = new TreeMap<>();
		mapPossibleResourceIDsToTermString = new TreeMap<>(); 
		mapResourceIDsToResourceElements = new TreeMap<>();
		this.termNormalization = termNormalization;
		this.usingOtherResourceInfoToImproveRuleAnnotstions = resourceToNER.isUseOtherResourceInformationInRules();
		processingINfo();
	}
	
	private void processingINfo() throws ANoteException {

		for(int i=0;i<resourceToNER.getList().size();i++)
		{
			Set<Long> selected = resourceToNER.getList().get(i).getZ();
			Set<Long> all = resourceToNER.getList().get(i).getY();
			if(selected.equals(all))
			{
				addResource(resourceToNER.getList().get(i).getX(),termNormalization);
			}
			else
			{
				addResource(resourceToNER.getList().get(i).getX(),new ArrayList<Long>(selected),termNormalization);
			}
		}
	}
	
	public List<IResource<IResourceElement>> getResourcesForRulesInfo()
	{
		List<IResource<IResourceElement>> resources =  new ArrayList<IResource<IResourceElement>>();
		for(int i=0;i<resourceToNER.getList().size();i++)
		{
			IResource<IResourceElement> resource = resourceToNER.getList().get(i).getX();
			if(resource instanceof IDictionary)
			{
				resources.add(resource);
			}
			else if(resource instanceof IOntology)
			{
				resources.add(resource);
			}
		}
		return resources;
	}

	public void addResource(IResource<IResourceElement> resource, List<Long> classes, boolean normalization) throws ANoteException
	{
		if(resource instanceof IDictionary || resource instanceof ILookupTable || resource instanceof IOntology) 
		{
			addResources(resource,classes,normalization);
		}
		else if(resource instanceof IRuleSet)
		{
			addResources((IRuleSet) resource,classes);
		}
		else
		{
			System.err.println(" Undifined Resource");
		}
	}
	
	public void addResource(IResource<IResourceElement> resource,boolean normalization) throws ANoteException
	{
		if(resource instanceof IDictionary || resource instanceof ILookupTable || resource instanceof IOntology) 
		{
			addResources(resource,normalization);
		}
		else if(resource instanceof IRuleSet)
		{
			addResources((IRuleSet) resource);
		}
		else
		{
			System.err.println(" Undifined Resource");
		}
	}
	
	private void addResources(IResource<IResourceElement> resource,boolean normalization) throws ANoteException
	{
		if(normalization)
		{
			IResourceElementSet<IResourceElement> elems = resource.getResourceElements();
			for(IResourceElement elem : elems.getResourceElements())
			{
				IEntityAnnotation annot = new EntityAnnotationImpl(0,0, elem.getTermClass(),elem, TermSeparator.termSeparator(elem.getTerm()),NormalizationForm.getNormalizationForm(elem.getTerm()),null);		
				terms.add(annot);
				for(String synonym:elem.getSynonyms())
				{
					IEntityAnnotation annotSynonym = new EntityAnnotationImpl(0,0, elem.getTermClass(),elem, TermSeparator.termSeparator(synonym),NormalizationForm.getNormalizationForm(synonym),null);		
					terms.add(annotSynonym);
				}
			}
		}
		else
		{
			IResourceElementSet<IResourceElement> elems = resource.getResourceElements();
			for(IResourceElement elem : elems.getResourceElements())
			{
				IEntityAnnotation annot = new EntityAnnotationImpl(0,0, elem.getTermClass(),elem, elem.getTerm(),NormalizationForm.getNormalizationForm(elem.getTerm()),null);		
				terms.add(annot);
				for(String synonym:elem.getSynonyms())
				{
					IEntityAnnotation annotSynonym = new EntityAnnotationImpl(0,0, elem.getTermClass(),elem, synonym,NormalizationForm.getNormalizationForm(synonym),null);		
					terms.add(annotSynonym);
				}
			}
		}
		resourceIdsOptions.put(resource.getId(),GlobalNames.allclasses);
		
	}
	

	/**
	 * 
	 * @param rules
	 * @throws SQLException 
	 * @throws DatabaseLoadDriverException 
	 * @throws DaemonException 
	 */
	private void addResources(IRuleSet rules) throws ANoteException
	{
		IResourceElementSet<IResourceElement> elems = rules.getResourceElements();
		for(IResourceElement elem : elems.getResourceElementsOrder())
		{
			this.rules.add(elem);
		}
		resourceIdsOptions.put(rules.getId(),GlobalNames.allclasses);
	}
	
	private void addResources(IResource<IResourceElement> dictionary,List<Long> arrayList,boolean normalization) throws ANoteException
	{
		String classes = new String();
		if(normalization)
		{
			for(long classID:arrayList)
			{
				IResourceElementSet<IResourceElement> elems = dictionary.getResourceElementsByClass(ClassPropertiesManagement.getClassGivenClassID(classID));
				for(IResourceElement elem : elems.getResourceElements())
				{
					IEntityAnnotation annot = new EntityAnnotationImpl(0,0, elem.getTermClass(),elem, TermSeparator.termSeparator(elem.getTerm()),NormalizationForm.getNormalizationForm(elem.getTerm()),null);		
					terms.add(annot);
					for(String synonym:elem.getSynonyms())
					{
						IEntityAnnotation annotSynonym = new EntityAnnotationImpl(0,0, elem.getTermClass(),elem, TermSeparator.termSeparator(synonym),NormalizationForm.getNormalizationForm(synonym),null);		
						terms.add(annotSynonym);
					}
					
				}
				classes = classes.concat(classID+",");
			}
		}
		else
		{
			for(long classID:arrayList)
			{
				IResourceElementSet<IResourceElement> elems = dictionary.getResourceElements();
				for(IResourceElement elem : elems.getResourceElements())
				{
					IEntityAnnotation annot = new EntityAnnotationImpl(0,0, elem.getTermClass(),elem, elem.getTerm(),NormalizationForm.getNormalizationForm(elem.getTerm()),null);		
					terms.add(annot);
					for(String synonym:elem.getSynonyms())
					{
						IEntityAnnotation annotSynonym = new EntityAnnotationImpl(0,0, elem.getTermClass(),elem, synonym,NormalizationForm.getNormalizationForm(synonym),null);		
						terms.add(annotSynonym);
					}
				}
				classes = classes.concat(classID+",");
			}
		}
		resourceIdsOptions.put(dictionary.getId(),classes);
	}
	
	
	private void addResources(IRuleSet rules,List<Long> termClassesID) throws ANoteException
	{
		String classes = new String();
		for(long classID:termClassesID)
		{
			IResourceElementSet<IResourceElement> elems = rules.getResourceElementsByClass(ClassPropertiesManagement.getClassGivenClassID(classID));
			for(IResourceElement elem : elems.getResourceElementsOrder())
			{
				this.rules.add(elem);
			}
			classes = classes.concat(classID+",");
		}
		resourceIdsOptions.put(rules.getId(),classes);
	}
	
	public List<IEntityAnnotation> getTerms() {
		return terms;
	}
	
	public List<IEntityAnnotation> getTermsByAlphabeticOrder(NERCaseSensativeEnum caseSensativeEnum){
		if(caseSensativeEnum.equals(NERCaseSensativeEnum.INALLWORDS)){
			return  getTermsByAlphabeticOrderCaseSentitive();
		}else if(caseSensativeEnum.equals(NERCaseSensativeEnum.ONLYINSMALLWORDS)){
			return getTermsByAlphabeticOrderCaseSentitiveForSmallWords(caseSensativeEnum.getSmallWordSize());
		}
		return getTermsByAlphabeticOrderCaseInsentitive();
	}
	
	private List<IEntityAnnotation> getTermsByAlphabeticOrderCaseSentitive() {
		List<IEntityAnnotation> elements = getTerms();
		SortedMap<String, IEntityAnnotation> sortceSet = new TreeMap<String, IEntityAnnotation>();
		for(IEntityAnnotation elem : elements)
		{
			sortceSet.put(elem.getAnnotationValue(), elem);
			resourceMapClass.put(elem.getResourceElement().getId(), elem.getClassAnnotation().getId());
			mapResourceIDsToResourceElements.put(elem.getResourceElement().getId(), elem.getResourceElement());
		}
		return new ArrayList<IEntityAnnotation>(sortceSet.values());
	}
	
	private List<IEntityAnnotation> getTermsByAlphabeticOrderCaseSentitiveForSmallWords(int wordSize) {
		List<IEntityAnnotation> elements = getTerms();
		SortedMap<String, IEntityAnnotation> sortceSet = new TreeMap<String, IEntityAnnotation>();
		for(IEntityAnnotation elem : elements)
		{
			String term = elem.getAnnotationValue();
			if(wordSize<elem.getAnnotationValue().length() ){
				term = term.toLowerCase();
			}
			sortceSet.put(term, elem);
			if(!maplowerCaseToPossibleResourceIDs.containsKey(term)){
				maplowerCaseToPossibleResourceIDs.put(term, new HashSet<Long>());
			}
			Set<Long> listResourceIds = maplowerCaseToPossibleResourceIDs.get(term);
			listResourceIds.add(elem.getResourceElement().getId());
			maplowerCaseToPossibleResourceIDs.put(term, listResourceIds);
			mapPossibleResourceIDsToTermString.put(elem.getResourceElement().getId(), elem.getAnnotationValue());
			resourceMapClass.put(elem.getResourceElement().getId(), elem.getClassAnnotation().getId());
			mapResourceIDsToResourceElements.put(elem.getResourceElement().getId(), elem.getResourceElement());
		}
		return new ArrayList<IEntityAnnotation>(sortceSet.values());
	}
	
	private List<IEntityAnnotation> getTermsByAlphabeticOrderCaseInsentitive() {
		List<IEntityAnnotation> elements = getTerms();
		SortedMap<String, IEntityAnnotation> sortceSet = new TreeMap<String, IEntityAnnotation>();
		for(IEntityAnnotation elem : elements)
		{
			String term = elem.getAnnotationValue().toLowerCase();
			sortceSet.put(term, elem);
			if(!maplowerCaseToPossibleResourceIDs.containsKey(term)){
				maplowerCaseToPossibleResourceIDs.put(term, new HashSet<Long>());
			}
			Set<Long> listResourceIds = maplowerCaseToPossibleResourceIDs.get(term);
			listResourceIds.add(elem.getResourceElement().getId());
			maplowerCaseToPossibleResourceIDs.put(term, listResourceIds);
			mapPossibleResourceIDsToTermString.put(elem.getResourceElement().getId(), elem.getAnnotationValue());
			resourceMapClass.put(elem.getResourceElement().getId(), elem.getClassAnnotation().getId());
			mapResourceIDsToResourceElements.put(elem.getResourceElement().getId(), elem.getResourceElement());
		}
		return new ArrayList<IEntityAnnotation>(sortceSet.values());
	}
	
	public Map<Long, IResourceElement> getMapResourceIDsToResourceElements(){
		return mapResourceIDsToResourceElements;
	}
	
	public Map<String, Set<Long>> getMaplowerCaseToPossibleResourceIDs(){
		return maplowerCaseToPossibleResourceIDs;
	}
	
	public Map<Long, String> getMapPossibleResourceIDsToTermString(){
		return mapPossibleResourceIDsToTermString;
	}
	
	public Map<Long,Long> getResourceMapClass(){
		return resourceMapClass;
	}

	public List<IResourceElement> getRules() {
		return rules;
	}

	public void setRules(List<IResourceElement> rules) {
		this.rules = rules;
	}

	public void setResourceIdsOptions(Map<Long,String> resourceIdsOptions) {
		this.resourceIdsOptions = resourceIdsOptions;
	}

	public Map<Long,String> getResourceIdsOptions() {
		return resourceIdsOptions;
	}	
	
	public ResourcesToNerAnote getResourceToNER() {
		return resourceToNER;
	}
	
	public boolean isUsingOtherResourceInfoToImproveRuleAnnotstions() {
		return usingOtherResourceInfoToImproveRuleAnnotstions;
	}

}
