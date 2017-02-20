package com.silicolife.textmining.core.datastructures.schemas;

import java.util.Date;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.process.IEProcessImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IProcessOrigin;
import com.silicolife.textmining.core.interfaces.process.IProcessType;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.INERSchema;

public class NERSchemaImpl extends IEProcessImpl implements INERSchema{

	public NERSchemaImpl(long id,ICorpus corpus, String description, String notes,IProcessType type, IProcessOrigin origin,int version, Properties properties,Date createDate,Date updateDate) {
		super(id,corpus, description, notes, type, origin,version, properties,createDate,updateDate);
	}

	public NERSchemaImpl(IIEProcess newProcess) {
		this(newProcess.getId(),newProcess.getCorpus(), newProcess.getName(), newProcess.getNotes(),newProcess.getType(),newProcess.getProcessOrigin(),newProcess.getVersion(), newProcess.getProperties(),newProcess.getCreateDate(),newProcess.getUpdateDate());
	}

	@Override
	public Set<Long> getEntityAnnotatedClasses() throws ANoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
