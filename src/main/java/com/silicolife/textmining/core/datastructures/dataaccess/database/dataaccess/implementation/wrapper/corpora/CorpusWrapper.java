package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.wrapper.corpora;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.corpora.CorpusImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Corpus;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.CorpusProperties;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;

/**
 * Class to transform anote2 Corpus structures to daemon Corpus structures and
 * vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class CorpusWrapper {

	public static ICorpus convertToAnoteStructure(Corpus corpus) {
		Long id = corpus.getCrpId();
		String name = corpus.getCrpCorpusName();
		String notes = corpus.getCrpNotes();
		Set<CorpusProperties> corpusProperties = corpus.getCorpusPropertieses();
		/**
		 * create properties
		 */
		Properties properties = CorpusPropertiesWrapper.convertToAnoteStructure(corpusProperties);
		ICorpus corpus_ = new CorpusImpl(id, name, notes, properties);

		return corpus_;
	}

	public static Corpus convertToDaemonStructure(ICorpus corpus_) {
		Long id = corpus_.getId();
		String name = corpus_.getDescription();
		String notes = corpus_.getNotes();
		Set<CorpusProperties> corpusProperties = new HashSet<CorpusProperties>(0);
		Properties properties = corpus_.getProperties();
		Boolean active = true;
		Corpus corpus = new Corpus(id, name, active);
		if (properties != null) {
			corpusProperties = CorpusPropertiesWrapper.convertToDaemonStructure(properties, corpus);
		}
		corpus.setCrpNotes(notes);
		corpus.setCorpusPropertieses(corpusProperties);
		corpus.setCrpActive(true);
		return corpus;
	}
}
