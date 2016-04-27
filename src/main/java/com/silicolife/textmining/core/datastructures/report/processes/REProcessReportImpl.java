package com.silicolife.textmining.core.datastructures.report.processes;

import java.util.Properties;

import com.silicolife.textmining.core.datastructures.report.processes.manualcuration.NERSchemaWithManualCurationReportImpl;
import com.silicolife.textmining.core.datastructures.report.processes.manualcuration.RESchemaWithManualCurationReportImpl;
import com.silicolife.textmining.core.interfaces.core.report.processes.IREProcessReport;
import com.silicolife.textmining.core.interfaces.core.report.processes.manualcuration.INERSchemaWithManualCurationReport;
import com.silicolife.textmining.core.interfaces.core.report.processes.manualcuration.IRESchemaWithManualCurationReport;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.IREProcess;

public class REProcessReportImpl extends NERProcessReportImpl implements IREProcessReport{

	private int numberOfRElations;
	private IIEProcess nerProcess;
	private IREProcess reProcess;
	private IRESchemaWithManualCurationReport reSchemaManualCurationReport;
	private INERSchemaWithManualCurationReport nerSchemaManualCurationReport;
	private boolean useMCAnnoations;
	
	public REProcessReportImpl(String title,IIEProcess ieProcess,IREProcess reProcess,boolean useMCAnnoations) {
		super(title,null);
		this.nerProcess = ieProcess;
		this.numberOfRElations = 0;
		this.reProcess = reProcess;
		this.nerSchemaManualCurationReport = new NERSchemaWithManualCurationReportImpl(reProcess, ieProcess);
		this.reSchemaManualCurationReport = new RESchemaWithManualCurationReportImpl(reProcess, ieProcess);
		this.useMCAnnoations = useMCAnnoations;
	}
	
	public REProcessReportImpl(String title,Properties prop,IIEProcess process)
	{
		super(title,prop,null);
		this.nerProcess = process;
		this.numberOfRElations = 0;
	}

	public int getNumberOfRelations() {
		return numberOfRElations;
	}

	public IIEProcess nerBasedProcesss() {
		return nerProcess;
	}

	public void increaseRelations(int newRelations) {
		this.numberOfRElations +=newRelations;
	}

	public IREProcess getREProcess() {
		return reProcess;
	}

	public IRESchemaWithManualCurationReport getRESchemachemaWithManualCurationReport() {
		return reSchemaManualCurationReport;
	}

	public INERSchemaWithManualCurationReport getNERSchemachemaWithManualCurationReport() {
		return nerSchemaManualCurationReport;
	}

	@Override
	public boolean useMCAnnoations() {
		return useMCAnnoations;
	}
}
