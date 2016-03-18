package com.silicolife.textmining.core.datastructures.report.corpora;

import java.util.HashSet;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.core.document.corpus.CorpusTextType;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.report.corpora.ICorpusCreateReport;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public class CorpusCreateReportImpl extends ReportImpl implements ICorpusCreateReport{

	private ICorpus corpus;
	private Set<IIEProcess> processes;
	private CorpusTextType corpusTextType;
	private int documentsNumber;
	
	public CorpusCreateReportImpl(ICorpus corps,CorpusTextType corpusTextType,int documentNUmber) {
		super("Create Corpus Report");
		this.corpus = corps;
		this.processes = new HashSet<IIEProcess>();
		this.corpusTextType = corpusTextType;
		this.documentsNumber = documentNUmber;
	}

	@Override
	public ICorpus getCorpus() {
		return corpus;
	}


	@Override
	public Set<IIEProcess> getProcesses() {
		return processes;
	}

	@Override
	public CorpusTextType getCorpusTextType() {
		return corpusTextType;
	}

	@Override
	public int getDocumentNumber() {
		return documentsNumber;
	}


	@Override
	public void addProcess(IIEProcess process) {
		this.processes.add(process);
	}

//	@Override
//	public void setCorpus(ICorpus corpus) {
//		this.corpus = corpus;
//	}
//
//	@Override
//	public String getName() {
//		return name;
//	}

}
