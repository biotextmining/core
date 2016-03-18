package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CorpusHasPublicationsId generated by hbm2java
 */
@Embeddable
public class CorpusHasPublicationsId implements java.io.Serializable {

	private long chpCorpusId;
	private long chpPublicationId;

	public CorpusHasPublicationsId() {
	}

	public CorpusHasPublicationsId(long chpCorpusId, long chpPublicationId) {
		this.chpCorpusId = chpCorpusId;
		this.chpPublicationId = chpPublicationId;
	}

	@Column(name = "chp_corpus_id", nullable = false)
	public long getChpCorpusId() {
		return this.chpCorpusId;
	}

	public void setChpCorpusId(long chpCorpusId) {
		this.chpCorpusId = chpCorpusId;
	}

	@Column(name = "chp_publication_id", nullable = false)
	public long getChpPublicationId() {
		return this.chpPublicationId;
	}

	public void setChpPublicationId(long chpPublicationId) {
		this.chpPublicationId = chpPublicationId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CorpusHasPublicationsId))
			return false;
		CorpusHasPublicationsId castOther = (CorpusHasPublicationsId) other;

		return (this.getChpCorpusId() == castOther.getChpCorpusId()) && (this.getChpPublicationId() == castOther.getChpPublicationId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getChpCorpusId();
		result = 37 * result + (int) this.getChpPublicationId();
		return result;
	}

}
