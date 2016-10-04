package com.silicolife.textmining.core.datastructures.documents.corpus;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpusStatistics;

public class CorpusStatisticsImpl implements ICorpusStatistics{
	
	private int documentsNumber;
	private int processesNumber;
	
	
	public CorpusStatisticsImpl(int documentsNumber,int processesNumber)
	{
		this.processesNumber=processesNumber;
		this.processesNumber=processesNumber;
	}
	
	public CorpusStatisticsImpl()
	{
	}
	
	@Override
	public int getDocumentNumber() {
		return documentsNumber;
	}

	@Override
	public int getProcessesNumber() {
		return processesNumber;
	}

	public void setDocumentsNumber(int documentsNumber) {
		this.documentsNumber = documentsNumber;
	}

	public void setProcessesNumber(int processesNumber) {
		this.processesNumber = processesNumber;
	}

}
