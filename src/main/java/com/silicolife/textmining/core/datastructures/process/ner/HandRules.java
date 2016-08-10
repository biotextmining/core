package com.silicolife.textmining.core.datastructures.process.ner;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.silicolife.textmining.core.datastructures.annotation.AnnotationPosition;
import com.silicolife.textmining.core.datastructures.annotation.AnnotationPositions;
import com.silicolife.textmining.core.datastructures.annotation.ner.EntityAnnotationImpl;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;
import com.silicolife.textmining.core.interfaces.core.document.structure.ITextSegment;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;


public class HandRules {
	private ElementToNer elementsToNER;
	private HandRulesExtraRulesInformation extraInfo;
	
	public HandRules(ElementToNer elementsToNER) throws ANoteException{ 
		this.elementsToNER = elementsToNER;
		NERCaseSensativeEnum caseSensitive = elementsToNER.getResourceToNER().getCaseSensitive();
		if(caseSensitive.equals(NERCaseSensativeEnum.INALLWORDS)){
			this.extraInfo = new HandRulesExtraRulesInformation(elementsToNER.getResourcesForRulesInfo(),true);
		}else{
			this.extraInfo = new HandRulesExtraRulesInformation(elementsToNER.getResourcesForRulesInfo(),false);
		}	}
	
	/** annotations is the list of the already existing annotations 
	 * @throws DatabaseLoadDriverException 
	 * @throws SQLException 
	 * @throws DaemonException */
	public void applyRules(String text, AnnotationPositions annotations) throws ANoteException{
		if(elementsToNER.isUsingOtherResourceInfoToImproveRuleAnnotstions() && elementsToNER.getResourcesForRulesInfo().size() > 0)
		{
			for(IResourceElement rule : elementsToNER.getRules())
			{
				applyRule(extraInfo,rule, annotations, text);
			}
		}
		else
		{
			for(IResourceElement rule : elementsToNER.getRules())
			{
				applyRule(rule, annotations, text);
			}
		}
	}
	


	/** annotations is the list of the already existing annotations 
	 * @throws DatabaseLoadDriverException 
	 * @throws SQLException 
	 * @throws DaemonException */
	public void applyRules(ITextSegment text, AnnotationPositions annotations) throws ANoteException{
		if(elementsToNER.isUsingOtherResourceInfoToImproveRuleAnnotstions() && elementsToNER.getResourcesForRulesInfo().size() > 0)
		{
			HandRulesExtraRulesInformation extraInfo = null;
			NERCaseSensativeEnum caseSensitive = elementsToNER.getResourceToNER().getCaseSensitive();
			if(caseSensitive.equals(NERCaseSensativeEnum.INALLWORDS)){
				extraInfo = new HandRulesExtraRulesInformation(elementsToNER.getResourcesForRulesInfo(),true);
			}else{
				extraInfo = new HandRulesExtraRulesInformation(elementsToNER.getResourcesForRulesInfo(),false);
			}			for(IResourceElement rule : elementsToNER.getRules())
			{
				applyRule(extraInfo,rule, annotations, text);
			}
		}
		else
		{
			for(IResourceElement rule : elementsToNER.getRules())
			{
				applyRule(rule, annotations, text);
			}
		}
	}

	public void applyRule(IResourceElement handRule, AnnotationPositions annotations, ITextSegment segment){
		
		String rule = handRule.getTerm();
		String text = segment.getText();
		Pattern p = Pattern.compile("("+rule+")");
		Matcher m = p.matcher(text);
		
		while(m.find())
		{
			updateRule(handRule, annotations, segment, m);
		}
	}

	private void applyRule(HandRulesExtraRulesInformation extraInfo,IResourceElement handRule, AnnotationPositions annotations,ITextSegment segment) throws ANoteException {
		String rule = handRule.getTerm();
		String text = segment.getText();
		Pattern p = Pattern.compile("("+rule+")");
		Matcher m = p.matcher(text);	
		while(m.find())
		{
			if(m.groupCount() > 1)
			{
				for(int i=2;i<=m.groupCount();i++)
				{
					if(m.start(i) > 0 && m.end(i) > 0)
					{
						AnnotationPosition pos = new AnnotationPosition(m.start(i)+(int)segment.getStartOffset(), m.end(i)+ (int)segment.getStartOffset());
						String term = segment.getText().substring(m.start(i),m.end(i));	
						// Empty terms are not considered
						if(!term.isEmpty())
						{
							IEntityAnnotation annotation = getEntityAnnotationTextSegment(extraInfo,handRule, segment, pos, term);
							annotations.addAnnotationWhitConflitsAndReplaceIfRangeIsMoreRule(pos, annotation);
						}
					}
				}
			}
			else
			{
				AnnotationPosition pos = new AnnotationPosition(m.start(1)+(int)segment.getStartOffset(), m.end(1)+ (int)segment.getStartOffset());
				String term = segment.getText().substring(m.start(1),m.end(1));
				// Empty terms are not considered
				if(!term.isEmpty())
				{
					IEntityAnnotation annotation = getEntityAnnotationTextSegment(extraInfo,handRule, segment, pos, term);
					annotations.addAnnotationWhitConflitsAndReplaceIfRangeIsMoreRule(pos, annotation);
				}
			}
		}
	}

	private void updateRule(IResourceElement handRule,AnnotationPositions annotations, ITextSegment segment, Matcher m) {
		if(m.groupCount() > 1)
		{
			for(int i=2;i<=m.groupCount();i++)
			{
				if(m.start(i) > 0 && m.end(i) > 0)
				{
					AnnotationPosition pos = new AnnotationPosition(m.start(i)+(int)segment.getStartOffset(), m.end(i)+ (int)segment.getStartOffset());
					String term = segment.getText().substring(m.start(i),m.end(i));
					// Empty terms are not considered
					if(!term.isEmpty())
					{
						IEntityAnnotation annotation = new EntityAnnotationImpl(pos.getStart()+segment.getStartOffset(), pos.getEnd()+segment.getStartOffset(),
								handRule.getTermClass(),handRule, term,false,null);
						annotations.addAnnotationWhitConflitsAndReplaceIfRangeIsMoreRule(pos, annotation);
					}
				}
			}
		}
		else
		{
			AnnotationPosition pos = new AnnotationPosition(m.start(1)+(int)segment.getStartOffset(), m.end(1)+ (int)segment.getStartOffset());
			String term = segment.getText().substring(m.start(1),m.end(1));
			// Empty terms are not considered
			if(!term.isEmpty())
			{
				IEntityAnnotation annotation = new EntityAnnotationImpl(pos.getStart()+segment.getStartOffset(), pos.getEnd()+segment.getStartOffset(),
						handRule.getTermClass(),handRule, term,false,null);
				annotations.addAnnotationWhitConflitsAndReplaceIfRangeIsMoreRule(pos, annotation);
			}
		}
	}
	
	public void applyRule(IResourceElement handRule, AnnotationPositions annotations, String text){

		String rule = handRule.getTerm();
		Pattern p = Pattern.compile("("+rule+")");
		Matcher m = p.matcher(text);
		while(m.find())
		{
			if(m.groupCount() > 1)
			{
				for(int i=2;i<=m.groupCount();i++)
				{
					if(m.start(i) > 0 && m.end(i) > 0)
					{
						AnnotationPosition pos = new AnnotationPosition(m.start(i), m.end(i));
						String term = text.substring(m.start(i),m.end(i));
						// Empty terms are not considered
						if(!term.isEmpty())
						{
							IEntityAnnotation annotation = new EntityAnnotationImpl(pos.getStart(), pos.getEnd(),handRule.getTermClass(),handRule, term,false,null);
							annotations.addAnnotationWhitConflitsAndReplaceIfRangeIsMoreRule(pos, annotation);
						}
					}
				}
			}
			else
			{
				AnnotationPosition pos = new AnnotationPosition(m.start(1), m.end(1));
				String term = text.substring(m.start(1),m.end(1));
				// Empty terms are not considered
				if(!term.isEmpty())
				{
					IEntityAnnotation annotation = new EntityAnnotationImpl( pos.getStart(), pos.getEnd(),handRule.getTermClass(),handRule, term,false,null);
					annotations.addAnnotationWhitConflitsAndReplaceIfRangeIsMoreRule(pos, annotation);
				}
			}
		}
	}
	
	/** For getting resource aditional infomation 
	 * @throws DatabaseLoadDriverException 
	 * @throws SQLException 
	 * @throws DaemonException */
	private void applyRule(HandRulesExtraRulesInformation extraInfo,IResourceElement handRule, AnnotationPositions annotations,String text) throws ANoteException {
		// TODO Auto-generated method stub
		String rule = handRule.getTerm();
		
		Pattern p = Pattern.compile("("+rule+")");
		Matcher m = p.matcher(text);

		while(m.find())
		{
			if(m.groupCount() > 1)
			{

				for(int i=2;i<=m.groupCount();i++)
				{
					if(m.start(i) > 0 && m.end(i) > 0)
					{
						AnnotationPosition pos = new AnnotationPosition(m.start(i), m.end(i));
						String term = text.substring(m.start(i),m.end(i));
						// Empty terms are not considered
						if(!term.isEmpty())
						{
							IEntityAnnotation annotation = getEntityAnnotation(extraInfo,handRule, pos, term);
							annotations.addAnnotationWhitConflitsAndReplaceIfRangeIsMoreRule(pos, annotation);
						}
					}
				}
			}
			else
			{
				AnnotationPosition pos = new AnnotationPosition(m.start(1), m.end(1));
				String term = text.substring(m.start(1),m.end(1));
				// Empty terms are not considered
				if(!term.isEmpty())
				{
					IEntityAnnotation annotation = getEntityAnnotation(extraInfo,handRule,pos, term);
					annotations.addAnnotationWhitConflitsAndReplaceIfRangeIsMoreRule(pos, annotation);
				}
			}
		}
	}

	private IEntityAnnotation getEntityAnnotation(HandRulesExtraRulesInformation extraInfo, IResourceElement handRule,AnnotationPosition pos, String term) throws ANoteException {
		long resourceInfoNumber = extraInfo.getResourceExtraInfoID(handRule,term);
		if(resourceInfoNumber == -1)
		{
			return new EntityAnnotationImpl(pos.getStart(), pos.getEnd(),handRule.getTermClass(),handRule, term,false,null);
		}
		else
		{
			return new EntityAnnotationImpl(pos.getStart(), pos.getEnd(),handRule.getTermClass(),InitConfiguration.getDataAccess().getResourceElementByID(resourceInfoNumber), term,false,null);
		}
	}
	
	private IEntityAnnotation getEntityAnnotationTextSegment(HandRulesExtraRulesInformation extraInfo, IResourceElement handRule, ITextSegment segment,AnnotationPosition pos, String term) throws ANoteException {
		long resourceInfoNumber = extraInfo.getResourceExtraInfoID(handRule,term);
		if(resourceInfoNumber == -1)
		{
			return new EntityAnnotationImpl(pos.getStart()+segment.getStartOffset(), pos.getEnd()+segment.getStartOffset(),
					handRule.getTermClass(),handRule, term,false,null);
		}
		else
		{
			return new EntityAnnotationImpl( pos.getStart()+segment.getStartOffset(), pos.getEnd()+segment.getStartOffset(),
					handRule.getTermClass(),InitConfiguration.getDataAccess().getResourceElementByID(resourceInfoNumber), term,false,null);
		}
	}
}
