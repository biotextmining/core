package com.silicolife.textmining.core.datastructures.process.ner;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class HandRulesExtraRulesInformation {
	
	private List<IResource<IResourceElement>> resources;
	private boolean casesensitive;
//	private PreparedStatement getRulesAditionalInfoOnDictionariesTerm;
//	private PreparedStatement getRulesAditionalInfoOnDictionariesTermSynonyms;
	private Map<String,Long> quickelementsaccess;

	public HandRulesExtraRulesInformation(List<IResource<IResourceElement>> resources,boolean caseSensitive) throws ANoteException	
	{
		this.resources = resources;
		this.casesensitive = caseSensitive;
		if(this.casesensitive)
		{
//			getRulesAditionalInfoOnDictionariesTerm = GlobalOptions.database.getConnection().prepareStatement(QueriesNERHandRules.findDictionaryTermCaseSensitive);
//			getRulesAditionalInfoOnDictionariesTermSynonyms = GlobalOptions.database.getConnection().prepareStatement(QueriesNERHandRules.findDictionaryTermSynonymCaseSensitive);
		}
		else
		{
//			getRulesAditionalInfoOnDictionariesTerm = GlobalOptions.database.getConnection().prepareStatement(QueriesNERHandRules.findDictionaryTerm);
//			getRulesAditionalInfoOnDictionariesTermSynonyms = GlobalOptions.database.getConnection().prepareStatement(QueriesNERHandRules.findDictionaryTermSynonym);
		}
		quickelementsaccess = new TreeMap<String, Long>();
	}
	
	public Long getResourceExtraInfoID(IResourceElement handRule, String text) throws ANoteException
	{
		if(quickelementsaccess.containsKey(text))
		{
			return quickelementsaccess.get(text);
		}
		for(IResource<IResourceElement> resource:resources)
		{
			long result = -1;
			result = InitConfiguration.getDataAccess().getResourceElementIDMathingByText(resource,handRule,text,casesensitive);
			if( result != -1)
			{
				quickelementsaccess.put(text, result);
				return result;
			}
		}
		quickelementsaccess.put(text, new Long(-1));
		return null;
	}

//	private int findTextInResource(IResourceElement handRule, IResource<IResourceElement> resource,String text) throws SQLException, DatabaseLoadDriverException {
//
//		getRulesAditionalInfoOnDictionariesTerm.setInt(1, resource.getID());
//		getRulesAditionalInfoOnDictionariesTerm.setString(2, text);
//		getRulesAditionalInfoOnDictionariesTerm.setInt(3, handRule.getTermClassID());
//		ResultSet rs = getRulesAditionalInfoOnDictionariesTerm.executeQuery();
//		if(rs.next())
//		{
//			int result = rs.getInt(1);
////			getRulesAditionalInfoOnDictionariesTerm.close();
////			getRulesAditionalInfoOnDictionariesTermSynonyms.close();
//			rs.close();
//			return result;
//		}
//		rs.close();
////		getRulesAditionalInfoOnDictionariesTerm.close();
//		getRulesAditionalInfoOnDictionariesTermSynonyms.setInt(1, resource.getID());
//		getRulesAditionalInfoOnDictionariesTermSynonyms.setString(2, text);
//		getRulesAditionalInfoOnDictionariesTermSynonyms.setInt(3, handRule.getTermClassID());
//		ResultSet rs2 = getRulesAditionalInfoOnDictionariesTermSynonyms.executeQuery();
//		if(rs2.next())
//		{
//			int result = rs2.getInt(1);
//			rs2.close();
////			getRulesAditionalInfoOnDictionariesTermSynonyms.close();
//			return result;
//		}
//		rs2.close();
////		getRulesAditionalInfoOnDictionariesTermSynonyms.close();
//		return -1;
//	}

//	public void clean() throws SQLException
//	{
//		if(getRulesAditionalInfoOnDictionariesTerm!=null)
//			getRulesAditionalInfoOnDictionariesTerm.close();
//		if(getRulesAditionalInfoOnDictionariesTermSynonyms!=null)
//			getRulesAditionalInfoOnDictionariesTermSynonyms.close();
//		quickelementsaccess.clear();
//	}


}
