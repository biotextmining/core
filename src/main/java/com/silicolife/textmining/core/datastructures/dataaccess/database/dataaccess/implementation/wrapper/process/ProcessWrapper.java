package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.process;

import java.util.Date;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusHasProcesses;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessOrigins;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessProperties;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.ProcessTypes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.corpora.CorpusWrapper;
import com.silicolife.textmining.core.datastructures.process.IEProcessImpl;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IProcessOrigin;
import com.silicolife.textmining.core.interfaces.process.IProcessType;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

/**
 * Class to transform anote2 Processes structures to daemon Processes structures
 * and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ProcessWrapper {

	public static IIEProcess convertToAnoteStructure(Processes processes) {
		Long id = processes.getProId();
		String description = processes.getProName();
		String notes = processes.getProNotes();
		ProcessOrigins processOrigin = processes.getProcessOrigins();
		ProcessTypes processType = processes.getProcessTypes();
		Integer version = processes.getProVersion();
		Date createDate = processes.getProCreateDate();
		Date update = processes.getProUpdateDate();

		Set<ProcessProperties> processProperties = processes.getProcessPropertieses();
		Set<CorpusHasProcesses> corpusHasProcesses = processes.getCorpusHasProcesseses();
		/*
		 * anote structures
		 */
		IProcessOrigin processOrigin_ = ProcessOriginWrapper.convertToAnoteStructure(processOrigin);
		IProcessType processType_ = ProcessTypeWrapper.convertToAnoteStructure(processType);
		Properties properties = ProcessPropertiesWrapper.convertToAnoteStructure(processProperties);
		ICorpus corpus_ = null;
		if (corpusHasProcesses.size() > 0) {
			CorpusHasProcesses corpusRecord = corpusHasProcesses.iterator().next();
			corpus_ = CorpusWrapper.convertToAnoteStructure(corpusRecord.getCorpus());
		}
		IIEProcess process_ = new IEProcessImpl(id, corpus_, description, notes, processType_, processOrigin_,version, properties,createDate,update);
		return process_;
	}

	public static Processes convertToDaemonStructure(IIEProcess processes_) {
		Long id = processes_.getId();
		String name = processes_.getName();
		String notes = processes_.getNotes();
		IProcessOrigin processOrigin_ = processes_.getProcessOrigin();
		IProcessType processType_ = processes_.getType();
		Properties properties = processes_.getProperties();
		/*
		 * daemon structures
		 */
		Processes processes = new Processes();
		processes.setProId(id);
		processes.setProName(name);
		processes.setProNotes(notes);
		ProcessTypes processType = ProcessTypeWrapper.convertToDaemonStructure(processType_);
		ProcessOrigins processOrigin = ProcessOriginWrapper.convertToDaemonStructure(processOrigin_);
		Set<ProcessProperties> processesProperties = ProcessPropertiesWrapper.convertToDaemonStructure(properties, processes);
		processes.setProcessTypes(processType);
		processes.setProcessOrigins(processOrigin);
		processes.setProcessPropertieses(processesProperties);
		processes.setProVersion(processes_.getVersion());
		processes.setProCreateDate(processes_.getCreateDate());
		processes.setProUpdateDate(processes_.getUpdateDate());
		processes.setProActive(true);
		return processes;
	}
}