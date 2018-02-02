package com.silicolife.textmining.core.datastructures.documents.corpus;

import org.apache.lucene.search.SortField;

import com.silicolife.textmining.core.datastructures.documents.query.QueriesLuceneFields;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum CorpusLuceneFieldsEnum {
	
	name ("crp_nameNCS","crp_nameCS", "crpNameSort", SortField.Type.STRING),
	notes("crp_notesNCS","crp_notesCS", "crpNotesSort", SortField.Type.STRING),
	none("","","none",null);
	
	private final String NCS;
	private final String CS;
	private final String SORT;
	private final SortField.Type SORTTYPE;
	
	 CorpusLuceneFieldsEnum(String NCS,String CS, String SORT, SortField.Type SORTTYPE) {
		this.NCS = NCS;
		this.CS = CS;
		this.SORT = SORT;
		this.SORTTYPE = SORTTYPE;
	}
	 
	 public static String getLuceneField(ISearchProperties searchProperties, String field){
		 CorpusLuceneFieldsEnum copusLuceneField = CorpusLuceneFieldsEnum.valueOf(field);

				if(searchProperties.isCaseSensitive())
					return copusLuceneField.CS;
				else
					return copusLuceneField.NCS;
		}
	 
	 public static String getSortField(String field) {
		 CorpusLuceneFieldsEnum cLuceneField = CorpusLuceneFieldsEnum.valueOf(field);
			return cLuceneField.SORT;
		}
		
		public static SortField.Type getSortType(String field) {
			CorpusLuceneFieldsEnum cLuceneField = CorpusLuceneFieldsEnum.valueOf(field);
			return cLuceneField.SORTTYPE;
		}

}
