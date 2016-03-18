package com.silicolife.textmining.core.datastructures.textprocessing.documentStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DocumentStructuring {

	/* Fields in the database to structure in the text. */
	private List<String> dbPublicationFields;
	
	public DocumentStructuring(List<String> dbPublicationFields) {
		this.dbPublicationFields = dbPublicationFields;
	}
	
	/*
	 * 
	 * @param dbSession
	 * @param pub
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 
	public void structureTexts(IDatabase dbSession,IPublication pub) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException{
		DBPublicationInformation dbPubInfo = null;

		Map<String, String> pieces = dbPubInfo.getPieces(pmid, dbPublicationFields);
		docText = annotatePublicationFields(docText, pieces);

	}
	*/
	
	public List<String> dbPublicationFields(){
		List<String> pieces = new ArrayList<String>();
		pieces.add("title");
		pieces.add("authors");
		pieces.add("journal");
		pieces.add("abstract");
		return pieces;
	}
	
	public String annotatePublicationFields(String pubText, Map<String, String> fields){
		String resultText = pubText;
		
		for(String field : fields.keySet())
		{
			String token = field.toUpperCase();
			String replace = "<"+token+">" + fields.get(field) +"</"+token+">"; 
			resultText = resultText.replace(fields.get(field), replace);
		}
		
		return resultText;
	}
	
}
