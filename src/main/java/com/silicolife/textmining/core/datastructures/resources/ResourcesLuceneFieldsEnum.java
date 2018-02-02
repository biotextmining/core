package com.silicolife.textmining.core.datastructures.resources;

import org.apache.lucene.search.SortField;

import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public enum ResourcesLuceneFieldsEnum {
	
	name ("reso_resource_nameNCS","reso_resource_nameCS", "resoNameSort",SortField.Type.STRING ),
	info("reso_notesNCS","reso_notesCS", "resoNotesSort",SortField.Type.STRING),
	type("resourceTypes.rtyResourceType", "resourceTypes.rtyResourceType", "", null),
	none("","","none",null);
	
	private final String NCS;
	private final String CS;
	private final String SORT;
	private final SortField.Type SORTTYPE;
	
	ResourcesLuceneFieldsEnum(String NCS,String CS, String SORT, SortField.Type SORTTYPE) {
		this.NCS = NCS;
		this.CS = CS;
		this.SORT = SORT;
		this.SORTTYPE = SORTTYPE;
	}
	 
	 public static String getLuceneField(ISearchProperties searchProperties, String field){
		 ResourcesLuceneFieldsEnum resourcesLuceneFieldsEnum = ResourcesLuceneFieldsEnum.valueOf(field);

				if(searchProperties.isCaseSensitive())
					return resourcesLuceneFieldsEnum.CS;
				else
					return resourcesLuceneFieldsEnum.NCS;
		}
	 
	 public static String getSortField(String field) {
		 ResourcesLuceneFieldsEnum rLuceneField = ResourcesLuceneFieldsEnum.valueOf(field);
			return rLuceneField.SORT;
		}
		
		public static SortField.Type getSortType(String field) {
			ResourcesLuceneFieldsEnum rLuceneField = ResourcesLuceneFieldsEnum.valueOf(field);
			return rLuceneField.SORTTYPE;
		}

}
