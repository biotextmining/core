package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CorpusHasPublications generated by hbm2java
 */
@Entity
@Table(name = "corpus_has_publications")
public class CorpusHasPublications implements java.io.Serializable {

	private CorpusHasPublicationsId id;
	private Corpus corpus;
	private Publications publications;

	public CorpusHasPublications() {
	}

	public CorpusHasPublications(CorpusHasPublicationsId id, Corpus corpus, Publications publications) {
		this.id = id;
		this.corpus = corpus;
		this.publications = publications;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "chpCorpusId", column = @Column(name = "chp_corpus_id", nullable = false)),
			@AttributeOverride(name = "chpPublicationId", column = @Column(name = "chp_publication_id", nullable = false)) })
	public CorpusHasPublicationsId getId() {
		return this.id;
	}

	public void setId(CorpusHasPublicationsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chp_corpus_id", nullable = false, insertable = false, updatable = false)
	public Corpus getCorpus() {
		return this.corpus;
	}

	public void setCorpus(Corpus corpus) {
		this.corpus = corpus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chp_publication_id", nullable = false, insertable = false, updatable = false)
	public Publications getPublications() {
		return this.publications;
	}

	public void setPublications(Publications publications) {
		this.publications = publications;
	}

}
