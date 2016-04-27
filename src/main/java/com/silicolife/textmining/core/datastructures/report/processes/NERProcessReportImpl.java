package com.silicolife.textmining.core.datastructures.report.processes;

import java.util.Properties;

import com.silicolife.textmining.core.datastructures.report.ReportImpl;
import com.silicolife.textmining.core.interfaces.core.report.processes.INERProcessReport;
import com.silicolife.textmining.core.interfaces.process.ProcessTypeEnum;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public class NERProcessReportImpl extends ReportImpl implements INERProcessReport{

	private int numberOfDocuments;
	private int entitiesExtracted;
	private IIEProcess nerProcess;
	
	public NERProcessReportImpl(String title,IIEProcess process) {
		super(title);
		this.numberOfDocuments = 0;
		this.entitiesExtracted = 0;
		this.nerProcess = process;
	}
	
	public NERProcessReportImpl(String title,Properties prop,IIEProcess process) {
		super(title,prop);
		this.numberOfDocuments = 0;
		this.entitiesExtracted = 0;
		this.nerProcess = process;
	}

	public int getNumberOfDocuments() {
		return numberOfDocuments;
	}

	public ProcessTypeEnum getProcessType() {
		return ProcessTypeEnum.NER;
	}

	public int getNumberOFEntities() {
		return entitiesExtracted;
	}

	public void incrementDocument() {
		numberOfDocuments++;
	}

	public void incrementEntitiesAnnotated(int newEntities) {
		this.entitiesExtracted += newEntities;
	}

	@Override
	public IIEProcess getNERProcess() {
		return nerProcess;
	}
	
	

}
