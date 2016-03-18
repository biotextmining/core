package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.specialdao;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Processes;

public interface CorpusAuxDao {

	public List<Processes> findProcessesByCorpusId(Long corpusId, Long userID, String corpusstr);

	public Integer getCorpusDocumentNumber(Long corpusID);

	public Integer getCorpusProcessesNumber(Long corpusID, Long userID, String corpusstr);

	public List<Corpus> findQueriesByAttributes(Long auId, String corpusstr);

	public List<Corpus> findCorpusByAttributes(long auId, String corpusstr,String string);
}
