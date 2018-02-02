package com.silicolife.textmining.core.datastructures.general;

import org.apache.lucene.search.SortField;

import com.silicolife.textmining.core.datastructures.documents.PublicationLuceneFields;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum UsersLuceneFields {
	
	
	auUsername("auUsernameNCS", "auUsernameCS", "auUsernameSort", SortField.Type.STRING ),
	auFullname ("auFullNameNCS","auFullNameCS", "auFullNameSort", SortField.Type.STRING),
	auEmail("auEmailNCS","auEmailCS","auEmailSort",SortField.Type.STRING),
	auLocation("auLocationNCS", "auLocationCS", "auLocationSort", SortField.Type.STRING),
	none("","","none",null);
	
	
	private final String NCS;
	private final String CS;
	private final String SORT;
	private final SortField.Type SORTTYPE;
	
	
	UsersLuceneFields(String NCS, String CS, String SORT, SortField.Type SORTTYPE ){
		this.NCS = NCS;
		this.CS = CS;
		this.SORT = SORT;
		this.SORTTYPE = SORTTYPE;
	}
	
	
	
	public static String getLuceneField(ISearchProperties searchProperties, String field){
		UsersLuceneFields uLuceneField = UsersLuceneFields.valueOf(field);

			if(searchProperties.isCaseSensitive())
				return uLuceneField.CS;
			else
				return uLuceneField.NCS;
		
		
	}
	
	public static String getSortField(String field) {
		UsersLuceneFields uLuceneField = UsersLuceneFields.valueOf(field);
		return uLuceneField.SORT;
	}
	
	public static SortField.Type getSortType(String field) {
		UsersLuceneFields uLuceneField = UsersLuceneFields.valueOf(field);
		return uLuceneField.SORTTYPE;
	}


}
