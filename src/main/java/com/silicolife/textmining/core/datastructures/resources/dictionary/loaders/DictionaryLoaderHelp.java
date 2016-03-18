package com.silicolife.textmining.core.datastructures.resources.dictionary.loaders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.silicolife.textmining.core.datastructures.dataaccess.database.schema.TableResourcesElements;
import com.silicolife.textmining.core.datastructures.general.AnoteClass;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.report.resources.PortResourceUpdateReport;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceMergeReport;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateConflits;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceUpdateReport;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.dictionary.IDictionary;

public class DictionaryLoaderHelp extends PortResourceUpdateReport{
	
	


	/**
	 * Logger
	 */
	static Logger logger = Logger.getLogger(DictionaryLoaderHelp.class.getName());
	
	private List<IResourceElement> batch;
	public final int recomendedMaxbatchSize = 100;
	private Set<String> alreadyAddedElemnt;
	
	public DictionaryLoaderHelp(String font)
	{
		super(font);
		this.batch = new ArrayList<>();
		this.alreadyAddedElemnt = new HashSet<>();
	}

	private String getSynonym(Set<String> termSynomns) {
		if(termSynomns==null || termSynomns.size()<1)
		{
			return new String();
		}
		else
		{
			for(String syn : termSynomns)
			{
				if(syn.trim().length() >= TableResourcesElements.mimimumElementSize)
				{
					termSynomns.remove(syn);
					return syn.trim();
				}
			}
		}
		return new String();
	}

//	public List<Long> getInsertedTermIDList()
//	{
//		return this.insertedTermIDList;
//	}
//	
//	public void resetInsertedList()
//	{
//		this.insertedTermIDList = new ArrayList<Long>();;
//	}
//	
//	public Set<Long> getNewClassesAdded()
//	{
//		return this.newClassesAdded;
//	}

	public void addElementToBatch(String term, String klassStr,Set<String> synonyms, List<IExternalID> externalIDs,Integer prioretyOrder) {
		term = term.trim();
		if(term.isEmpty()|| term.length()<TableResourcesElements.mimimumElementSize ||term.length()>=TableResourcesElements.elementSize)
		{
			term = getSynonym(synonyms);
		}
		if(!term.isEmpty() && term.length() >= TableResourcesElements.mimimumElementSize && term.length()< TableResourcesElements.elementSize)
		{
			IAnoteClass klass = null;
			if(klassStr!=null && !klassStr.isEmpty())
				klass = new AnoteClass(klassStr);
			int priorety = 0;
			if(prioretyOrder!=null && prioretyOrder > -1)
				priorety = prioretyOrder;
			Set<String> synonymsList = new HashSet<>();
			for(String synonym:synonyms)
			{
				if(synonym.trim().length() >= TableResourcesElements.mimimumElementSize && synonym.length()< TableResourcesElements.elementSize && !this.alreadyAddedElemnt.contains(synonym))
				{
					synonymsList.add(synonym.trim());
				}
			}
			Set<String> extendalIDsalreadyAddedfortems = new HashSet<>();
			List<IExternalID> externalIDLIst  = new ArrayList<>();
			for(IExternalID externalID:externalIDs)
			{
				String internalID = externalID.getExternalID();
				String source = externalID.getSource().getSource();
				String merge = internalID+source;
				merge = merge.toLowerCase();
				if(!extendalIDsalreadyAddedfortems.contains(merge))
				{
					extendalIDsalreadyAddedfortems.add(merge);
					externalIDLIst.add(externalID);
				}
			}
			
			synchronized (this.alreadyAddedElemnt) {
				
				if(!this.alreadyAddedElemnt.contains(term) || !synonymsList.isEmpty())
				{
					IResourceElement elem = new ResourceElementImpl(term, klass, externalIDLIst, new ArrayList<>(synonymsList), priorety, true);
					this.batch.add(elem);
				}
			
				this.alreadyAddedElemnt.add(term);
				this.alreadyAddedElemnt.addAll(synonymsList);
			}
		}
	}
	
	public void addElementToBatch(IResourceElement resourceElement) {
		this.batch.add(resourceElement);

	}
	
	public int getBatchSize()
	{
		return this.batch.size();
	}
	
	public boolean isBatchSizeLimitOvertaken()
	{
		return this.batch.size() > recomendedMaxbatchSize;
	}

	public IResourceManagerReport executeBatch() throws ANoteException {
		IResourceManagerReport report;
		if(super.usevalidation())
		{
			report = InitConfiguration.getDataAccess().addResourceElements(getResource(), this.batch);
		}
		else
		{
			report = InitConfiguration.getDataAccess().addResourceElementsWithouValidation(getResource(), this.batch);
		}
		this.batch.clear();
		return report;
	}
	
	public IResourceManagerReport executeBatchWithoutValidation() throws ANoteException {
		IResourceManagerReport report = InitConfiguration.getDataAccess().addResourceElementsWithouValidation(getResource(), this.batch);
		this.batch.clear();
		return report;
	}

	public void updateReport(IResourceManagerReport reportBatchInserted,IResourceUpdateReport report) {
		report.addExternalIDs(reportBatchInserted.getExternalIDAdded());
		report.addSynonymsAdding(reportBatchInserted.getSynonymsAdded());
		report.addTermAdding(reportBatchInserted.getTermsAdded());
		for(IResourceUpdateConflits conflit : reportBatchInserted.getConflits())
			report.addConflit(conflit);		
	}
	
	public IDictionary getDictionary() {
		return (IDictionary) super.getResource();
	}

	public void updateReport(IResourceManagerReport reportBatchInserted,IResourceMergeReport report) {
		report.addExternalIDs(reportBatchInserted.getExternalIDAdded());
		report.addSynonymsAdding(reportBatchInserted.getSynonymsAdded());
		report.addTermAdding(reportBatchInserted.getTermsAdded());
		for(IResourceUpdateConflits conflit : reportBatchInserted.getConflits())
			report.addConflit(conflit);			
	}
	
}
