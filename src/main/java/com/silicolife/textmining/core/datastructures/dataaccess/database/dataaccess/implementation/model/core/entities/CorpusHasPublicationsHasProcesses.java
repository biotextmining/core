package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;
// Generated 21/jun/2016 18:34:36 by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CorpusHasPublicationsHasProcesses generated by hbm2java
 */
@Entity
@Table(name = "corpus_has_publications_has_processes")
public class CorpusHasPublicationsHasProcesses implements java.io.Serializable {

	private CorpusHasPublicationsHasProcessesId id;
	private CorpusHasPublications corpusHasPublications;
	private Processes processes;
	private Date chphpCreateDate;
	private Date chphpUpdateDate;

	public CorpusHasPublicationsHasProcesses() {
	}

	public CorpusHasPublicationsHasProcesses(CorpusHasPublicationsHasProcessesId id,
			CorpusHasPublications corpusHasPublications, Processes processes) {
		this.id = id;
		this.corpusHasPublications = corpusHasPublications;
		this.processes = processes;
	}
	public CorpusHasPublicationsHasProcesses(CorpusHasPublicationsHasProcessesId id,
			CorpusHasPublications corpusHasPublications, Processes processes, Date chphpCreateDate,
			Date chphpUpdateDate) {
		this.id = id;
		this.corpusHasPublications = corpusHasPublications;
		this.processes = processes;
		this.chphpCreateDate = chphpCreateDate;
		this.chphpUpdateDate = chphpUpdateDate;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "chphpCorpusId", column = @Column(name = "chphp_corpus_id", nullable = false)),
			@AttributeOverride(name = "chphpPublicationId", column = @Column(name = "chphp_publication_id", nullable = false)),
			@AttributeOverride(name = "chphpProcessesId", column = @Column(name = "chphp_processes_id", nullable = false))})
	public CorpusHasPublicationsHasProcessesId getId() {
		return this.id;
	}

	public void setId(CorpusHasPublicationsHasProcessesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "chphp_corpus_id", referencedColumnName = "chp_corpus_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "chphp_publication_id", referencedColumnName = "chp_publication_id", nullable = false, insertable = false, updatable = false)})
	public CorpusHasPublications getCorpusHasPublications() {
		return this.corpusHasPublications;
	}

	public void setCorpusHasPublications(CorpusHasPublications corpusHasPublications) {
		this.corpusHasPublications = corpusHasPublications;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chphp_processes_id", nullable = false, insertable = false, updatable = false)
	public Processes getProcesses() {
		return this.processes;
	}

	public void setProcesses(Processes processes) {
		this.processes = processes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "chphp_create_date", length = 19)
	public Date getChphpCreateDate() {
		return this.chphpCreateDate;
	}

	public void setChphpCreateDate(Date chphpCreateDate) {
		this.chphpCreateDate = chphpCreateDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "chphp_update_date", length = 19)
	public Date getChphpUpdateDate() {
		return this.chphpUpdateDate;
	}

	public void setChphpUpdateDate(Date chphpUpdateDate) {
		this.chphpUpdateDate = chphpUpdateDate;
	}

}
