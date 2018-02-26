package com.silicolife.textmining.core.interfaces.core.corpora;

import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;

public interface ICorpusCreateConfigurationLuceneSearch {

	public ISearchProperties getSearchProperties();

	public String htmlReport(int documentsAdded);

}
