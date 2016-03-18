package com.silicolife.textmining.core.datastructures.documents.corpus;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpusStatistics;

public class CorpusStatisticsImpl implements ICorpusStatistics{
	
	private int documents;
	private int processes;
	
	
	public CorpusStatisticsImpl(int documents,int processes)
	{
		this.documents=documents;
		this.processes=processes;
	}
	
	public CorpusStatisticsImpl()
	{
	}
	
	@JsonGetter("documents")
	@Override
	public int getDocumentNumber() {
		return documents;
	}

	@JsonGetter("processes")
	@Override
	public int getProcessesNumber() {
		return processes;
	}

	@JsonSetter("documents")
	public void setDocumentsNumber(int documents) {
		this.documents = documents;
	}

	@JsonSetter("processes")
	public void setProcessesNumber(int processes) {
		this.processes = processes;
	}

}
