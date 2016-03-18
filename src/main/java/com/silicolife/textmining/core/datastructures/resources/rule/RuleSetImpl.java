package com.silicolife.textmining.core.datastructures.resources.rule;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.resources.ResourceImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.report.resources.IResourceMergeReport;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.ResourcesTypeEnum;
import com.silicolife.textmining.core.interfaces.resource.rules.IRuleSet;

public class RuleSetImpl extends ResourceImpl implements IRuleSet{
	
	/**
	 * Logger
	 */
//	static Logger logger = Logger.getLogger(RuleSetImpl.class.getName());
	
	private IResourceElementSet<IResourceElement> rulesOrder;
	
	public RuleSetImpl( long id, String name, String info,boolean active) {
		super(id, name, info,ResourcesTypeEnum.rule.toString(), active);
	}
	
	public RuleSetImpl(String name, String info,boolean active) {
		super(name, info,ResourcesTypeEnum.rule.toString(), active);
	}
	
	public RuleSetImpl() {
		super();
	}

	
	
	public int compareTo(IRuleSet dic)
	{
		if(this.getId()==dic.getId())
		{
			return 0;
		}
		else if(this.getId()<=dic.getId())
		{
			return -1;
		}
		return 1;
	}
	
	public boolean equals(Object ruleset)
	{
		if(!(ruleset instanceof IRuleSet))
			return false;
		if(this.getId() == ((IRuleSet) ruleset).getId())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toString()
	{
		if(getId() < 1)
		{
			return "None";
		}
		String info = new String();
		info = LanguageProperties.getLanguageStream("pt.uminho.anote2.general.ruleset")+" : " + getName() + " (ID :"+ getId() + " ) ";
		if(!getInfo().equals(""))
		{
			info = info + LanguageProperties.getLanguageStream("pt.uminho.anote2.general.notes")+": "+getInfo();
		}
		if(!isActive())
		{
			info = info + " ("+LanguageProperties.getLanguageStream("pt.uminho.anote2.general.inactive")+") ";
		}
		return info;
	}

	@JsonIgnore
	public int getMaxPriority() throws ANoteException
	{
		return InitConfiguration.getDataAccess().getResourceMaxPriority(this);
	}
	
	@JsonIgnore
	public List<IResourceElement> getResourceElementsOrderByPriority() throws ANoteException
	{
		return InitConfiguration.getDataAccess().getResourceElements(this).getResourceElementsOrder();
	}

	public IResourceMergeReport merge(IRuleSet ruleSnd) throws ANoteException
	{
//		IResourceMergeReport report = new ResourceMergeReportImpl(LanguageProperties.getLanguageStream("pt.uminho.anote2.general.resources.rules.merge.report.title"), ruleSnd, this);
//		long startTime = GregorianCalendar.getInstance().getTimeInMillis();
//		Set<IAnoteClass> classesSnd = ruleSnd.getResourceClassContent();
//		Set<IAnoteClass> classes1st= getResourceClassContent();
//		Set<Long> classesSndIDs = new HashSet<>();
//		Set<Long> classes1stIDs = new HashSet<>();
//		for(IAnoteClass klass:classesSnd)
//		{
//			classesSndIDs.add(klass.getId());
//		}
//		for(IAnoteClass klass:classes1st)
//		{
//			classes1stIDs.add(klass.getId());
//		}
//		updateContent(report, classesSndIDs, classes1stIDs);
//		IResourceElementSet<IResourceElement> terms = ruleSnd.getResourceElements();;	
////		updateTerms(terms, report);
//		long endTime = GregorianCalendar.getInstance().getTimeInMillis();
//		report.setTime(endTime-startTime);
		return null;
	}

	private void updateContent(IResourceMergeReport report,
			Set<Long> classesSnd, Set<Long> classes1st) throws ANoteException {
		for(long classID:classesSnd)
		{
			if(!classes1st.contains(classID))
			{
				report.addClassesAdding(1);
			}
		}
	}

//	private void updateTerms(IResourceElementSet<IResourceElement> terms, IResourceMergeReport report) throws ANoteException{
//		int step = 0;
//		int total = terms.getElements().size();
//		for(IResourceElement elem:terms.getElements())
//		{
//			
//			if(this.addResourceElement(elem))
//			{
//				report.addTermAdding(1);
//			}
//			else
//			{
//				IInsertConflits conflit;
//				IResourceElement elemValue = getResourceElementsByName(elem.getTerm()).getElementsOrder().get(0);
//				if(elem.getTermClassID()==elemValue.getTermClassID())
//				{
//					conflit = new ResourceMergeConflits(ConflitsType.AlreadyHaveTerm, elemValue, elem);
//				}
//				else
//				{
//					conflit = new ResourceMergeConflits(ConflitsType.TermInDiferentClasses, elemValue, elem) ;
//				}
//				report.addConflit(conflit);
//			}
//			if(step%10==0)
//			{
//				memoryAndProgress(step, total);
//			}
//			step++;
//		}
//
//	}
	
	@JsonIgnore
	public List<IResourceElement> getRules() throws ANoteException {
		if(rulesOrder==null)
		{
			rulesOrder = InitConfiguration.getDataAccess().getResourceElements(this);
		}
		return rulesOrder.getResourceElementsOrder();
	}

	public void reoderpriorities(int priorety) throws ANoteException {
		List<IResourceElement> rules = getRules();
		for(IResourceElement rule:rules)
		{
			if(priorety<rule.getPriority())
			{
				rule.setPriority(rule.getPriority()-1);
				InitConfiguration.getDataAccess().updateResourceElement(rule);
			}
		}
	}
	
	public void changePriorety(IResourceElement fst,IResourceElement snd) throws ANoteException
	{
		int prioretyfst = fst.getPriority();
		int prioretysnd = snd.getPriority();
		snd.setPriority(prioretyfst);
		fst.setPriority(prioretysnd);
		InitConfiguration.getDataAccess().updateResourceElement(fst);
		InitConfiguration.getDataAccess().updateResourceElement(snd);
	}

	public void cleanAndNotifyViewObservers() {
		rulesOrder = null;
	}
}
