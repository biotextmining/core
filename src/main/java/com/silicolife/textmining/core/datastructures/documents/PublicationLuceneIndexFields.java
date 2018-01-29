package com.silicolife.textmining.core.datastructures.documents;

import org.apache.lucene.search.SortField;

import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum PublicationLuceneIndexFields {

	title("titleCS", "titleNCS", "pubTitleSort", SortField.Type.STRING),
	authors ("authorsCS","authorsNCS", "pubAuthorsSort", SortField.Type.STRING),
	journal ("journalCS","journalNCS", "", null ),
	abstractSection("abstractCS", "abstractNCS", "", null ),
	fullContent("fullContentCS", "fullContentNCS", "", null),
	notes("notesCS","notesNCS", "", null),
	queryId("queryHasPublicationses.id.qhbQueryId","queryHasPublicationses.id.qhbQueryId", "", null),
	corpusId("corpusHasPublicationses.id.chpCorpusId", "corpusHasPublicationses.id.chpCorpusId", "", null),
	yeardate("lucYearDate","lucYearDate","pubYeardateSort", SortField.Type.STRING),
	category("categoryCS","categoryNCS","pubCategorySort", SortField.Type.STRING),
	type("typeCS", "typeNCS", "pubTypeSort", SortField.Type.STRING),
	none("","","none",null);
	
	
	private final String cS;
	private final String nCS;
	private final String SORT;
	private final SortField.Type SORTTYPE;
	
	
	private PublicationLuceneIndexFields(String cS, String nCS, String SORT, SortField.Type SORTTYPE) {
		this.cS = cS;
		this.nCS = nCS;
		this.SORT = SORT;
		this.SORTTYPE = SORTTYPE;
	}
	
	public static String getLuceneField(ISearchProperties searchProperties, String field){
		PublicationLuceneIndexFields pubLuceneField = PublicationLuceneIndexFields.valueOf(field);
		
		if(searchProperties.isCaseSensitive())
			return pubLuceneField.cS;
		else
			return pubLuceneField.nCS;
		
	}
	
	public static String getSortField(String field) {
		PublicationLuceneIndexFields pubLuceneField = PublicationLuceneIndexFields.valueOf(field);
		return pubLuceneField.SORT;
	}
	
	public static SortField.Type getSortType(String field) {
		PublicationLuceneIndexFields pubLuceneField = PublicationLuceneIndexFields.valueOf(field);
		return pubLuceneField.SORTTYPE;
	}
	
}
