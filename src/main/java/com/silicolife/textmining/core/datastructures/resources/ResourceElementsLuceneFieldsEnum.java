package com.silicolife.textmining.core.datastructures.resources;

import org.apache.lucene.search.SortField;

import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum ResourceElementsLuceneFieldsEnum {
	
	name ("res_elementNCS","res_elementCS","", null, "keywordEdgeNGram_res_element", "tokenEdgeNGram_res_element", null),
	resourceId("resources", "resources", "", null, "", "", null),
	synonyms("synonymses.id.syn_synonymNCS", "synonymses.id.syn_synonymCS", "",
			null,"synonymses.id.keywordEdgeNGram_syn_synonym",
			"synonymses.id.tokenEdgeNGram_syn_synonym", "synonymses.id.synActive"),
	externalIDs("resourceElementExtenalIdses.id.rele_externalNCS",
			"resourceElementExtenalIdses.id.rele_externalCS", 
			"", null,"resourceElementExtenalIdses.id.keywordEdgeNGram_rele_external",
			"resourceElementExtenalIdses.id.tokenEdgeNGram_rele_external", "resourceElementExtenalIdses.releActive"),
	none("","","none",null, "", "", null);
	
	private final String NCS;
	private final String CS;
	private final String SORT;
	private final SortField.Type SORTTYPE;
	private final String KEYWORDEDGE;
	private final String TOKENEDGE;
	private final String ACTIVE;
	
	ResourceElementsLuceneFieldsEnum(String NCS,String CS, String SORT, 
			SortField.Type SORTTYPE, String KEYWORDEDGE, String TOKENEDGE, String ACTIVE) {
		this.NCS = NCS;
		this.CS = CS;
		this.SORT = SORT;
		this.SORTTYPE = SORTTYPE;
		this.KEYWORDEDGE = KEYWORDEDGE;
		this.TOKENEDGE = TOKENEDGE;
		this.ACTIVE = ACTIVE;
	}
	 
	 public static String getLuceneField(ISearchProperties searchProperties, String field){
		 ResourceElementsLuceneFieldsEnum resourceElementsLuceneFieldsEnum = ResourceElementsLuceneFieldsEnum.valueOf(field);

				if(searchProperties.isCaseSensitive())
					return resourceElementsLuceneFieldsEnum.CS;
				else
					return resourceElementsLuceneFieldsEnum.NCS;
		}
	 
	 public static String getSortField(String field) {
		 ResourceElementsLuceneFieldsEnum rLuceneField = ResourceElementsLuceneFieldsEnum.valueOf(field);
			return rLuceneField.SORT;
		}
		
		public static SortField.Type getSortType(String field) {
			ResourceElementsLuceneFieldsEnum rLuceneField = ResourceElementsLuceneFieldsEnum.valueOf(field);
			return rLuceneField.SORTTYPE;
		}
		
		public static String getKeywordEdge(String field) {
			ResourceElementsLuceneFieldsEnum rLuceneField = ResourceElementsLuceneFieldsEnum.valueOf(field);
			return rLuceneField.KEYWORDEDGE;
		}
		
		public static String getTokenEdge(String field) {
			ResourceElementsLuceneFieldsEnum rLuceneField = ResourceElementsLuceneFieldsEnum.valueOf(field);
			return rLuceneField.TOKENEDGE;
		}
		
		public static String getActive(String field) {
			ResourceElementsLuceneFieldsEnum rLuceneField = ResourceElementsLuceneFieldsEnum.valueOf(field);
			return rLuceneField.ACTIVE;
		}

}
