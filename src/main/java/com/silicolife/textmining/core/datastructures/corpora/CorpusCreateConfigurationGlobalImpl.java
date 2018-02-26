package com.silicolife.textmining.core.datastructures.corpora;

import java.util.Properties;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.interfaces.core.corpora.CorpusCreateSourceEnum;
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusCreateConfigurationGlobal;
import com.silicolife.textmining.core.interfaces.core.document.corpus.CorpusTextType;

public class CorpusCreateConfigurationGlobalImpl extends CorpusCreateConfigurationImpl implements ICorpusCreateConfigurationGlobal {
	
	private String searchString;
	public  static final String  configurationUID = "corpus.globalSearchCreation";
	private static final String OR = "OR";
	private static final String AND = "AND";
	private static final String CLOSE = "}";
	private static final String TERM = "TERM";
	
	
	public CorpusCreateConfigurationGlobalImpl() {
		super();
	}
	
	public CorpusCreateConfigurationGlobalImpl( String corpusName,String notes,
			CorpusTextType corpusTextType, boolean processJournalRetrievalBeforeNeeded,Properties properties, CorpusCreateSourceEnum corpusSource, String searchString) {
		super(corpusName, notes, corpusTextType, processJournalRetrievalBeforeNeeded,properties,corpusSource );
		this.searchString = searchString;
	}
	
	@Override
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	@JsonIgnore
	public void setDocumentsIDs(Set<Long> documentsIDs) {
		super.setDocumentsIDs(documentsIDs);
	}

	@Override
	public String toString() {
		return "CorpusCreateConfigurationGlobalImpl [searchString=" + searchString + ", getCorpusName()="
				+ getCorpusName() + ", getDocuments()=" + getDocuments() + ", getCorpusTextType()="
				+ getCorpusTextType() + ", isProcessJournalRetrievalBeforeNeeded()="
				+ isProcessJournalRetrievalBeforeNeeded() + ", getProperties()=" + getProperties()
				+ ", getCorpusNotes()=" + getCorpusNotes() + ", getConfigurationUID()=" + getConfigurationUID()
				+ ", getDocumentsIDs()=" + getDocumentsIDs() + ", getCorpusSource()=" + getCorpusSource()
				+ ", toString()=" + super.toString() + "]";
	}
	
	@JsonIgnore
	@Override
	public String htmlReport(int documentsAdded) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>"+this.getCorpusName()+" creation "+documentsAdded+" documents added</h3>");
		sb.append("<p><b>Global search query:</b></p>");
		sb.append(this.prettySearchQuery());
		return sb.toString();
	}
	
	private String prettySearchQuery() {
		String trimmed = this.searchString.trim().replaceAll(" +", " ");
		StringBuilder sb = new StringBuilder();
		String[] tokens = trimmed.split(" ",-1);
		int count = 1;
		int tabs = 1;
		int size = tokens.length;
		sb.append("<span style='white-space:pre'>");
		sb.append("<p>"+tokens[0]+"</p>");
		while(count< size-1) {
			String token = tokens[count];
			switch(token) {
				case OR:
					count++;
					sb.append(this.buildWithNTabs(tabs, token+" "+tokens[count]));
					tabs++;
					break;
				case AND:
					count++;
					sb.append(this.buildWithNTabs(tabs, token+" "+tokens[count]));
					tabs++;
					break;
				case CLOSE:
					tabs--;
					sb.append(this.buildWithNTabs(tabs, token));
					break;
				default:
					count++;
					sb.append(this.buildWithNTabs(tabs, token+" "+tokens[count]));
					break;
			}
			count++;
		}
		sb.append("<p>"+tokens[size-1]+"</p>");
		sb.append("</span>");
		return sb.toString();
	}
	
	private String buildWithNTabs(int nTabs, String content) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		sb.append("<p>");
		while(count < nTabs) {
			sb.append("&#9;");
			count++;
		}
		sb.append(content);
		sb.append("</p>");
		return sb.toString();
	}
	
}
