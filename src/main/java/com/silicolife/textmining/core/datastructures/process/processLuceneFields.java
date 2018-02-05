package com.silicolife.textmining.core.datastructures.process;

import org.apache.lucene.search.SortField;

import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum processLuceneFields {
	
	processOrigin("processOrigins.pro_po_descriptionNCS", "processOrigins.pro_po_descriptionCS", "processOrigins.pro_po_descriptionSort", SortField.Type.STRING ),
	notes ("pro_notesNCS","pro_notesCS", "", null),
	name("pro_nameNCS","pro_nameCS","proNameSort",SortField.Type.STRING),
	//publicationsSize("", "", "quMatchingPublicationsSort", SortField.Type.INT),
	//date ("","","quDateSort",SortField.Type.LONG ),
	none("","","none",null);
	
	
	private final String NCS;
	private final String CS;
	private final String SORT;
	private final SortField.Type SORTTYPE;
	
	
	processLuceneFields(String NCS, String CS, String SORT, SortField.Type SORTTYPE ){
		this.NCS = NCS;
		this.CS = CS;
		this.SORT = SORT;
		this.SORTTYPE = SORTTYPE;
	}
	
	
	
	public static String getLuceneField(ISearchProperties searchProperties, String field){
		processLuceneFields pLuceneField = processLuceneFields.valueOf(field);

			if(searchProperties.isCaseSensitive())
				return pLuceneField.CS;
			else
				return pLuceneField.NCS;
		
		
	}
	
	public static String getSortField(String field) {
		processLuceneFields pLuceneField = processLuceneFields.valueOf(field);
		return pLuceneField.SORT;
	}
	
	public static SortField.Type getSortType(String field) {
		processLuceneFields pLuceneField = processLuceneFields.valueOf(field);
		return pLuceneField.SORTTYPE;
	}


}
