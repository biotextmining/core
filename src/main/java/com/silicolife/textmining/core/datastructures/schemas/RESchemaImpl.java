package com.silicolife.textmining.core.datastructures.schemas;

import java.util.Properties;

import com.silicolife.textmining.core.datastructures.process.IEProcessImpl;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IProcessOrigin;
import com.silicolife.textmining.core.interfaces.process.IProcessType;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.IRESchema;

public class RESchemaImpl extends IEProcessImpl implements IRESchema{
	
	public RESchemaImpl(long id,ICorpus corpus, String description, String notes,IProcessType type, IProcessOrigin origin, Properties properties) {
		super(id,corpus, description, notes, type, origin, properties);
	}

	public RESchemaImpl(IIEProcess newProcess) {
		this(newProcess.getID(),newProcess.getCorpus(), newProcess.getName(), newProcess.getNotes(),newProcess.getType(),newProcess.getProcessOrigin(), newProcess.getProperties());
	}

}
