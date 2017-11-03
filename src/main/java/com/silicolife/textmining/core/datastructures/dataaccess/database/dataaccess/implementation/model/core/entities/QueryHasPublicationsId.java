package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.bridge.builtin.LongBridge;

/**
 * QueryHasPublicationsId generated by hbm2java
 */
@Embeddable
public class QueryHasPublicationsId implements java.io.Serializable {

	private long qhbQueryId;
	private long qhbPublicationId;

	public QueryHasPublicationsId() {
	}

	public QueryHasPublicationsId(long qhbQueryId, long qhbPublicationId) {
		this.qhbQueryId = qhbQueryId;
		this.qhbPublicationId = qhbPublicationId;
	}

	@FieldBridge(impl = LongBridge.class)
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.NO)
	@Column(name = "qhb_query_id", nullable = false)
	public long getQhbQueryId() {
		return this.qhbQueryId;
	}

	public void setQhbQueryId(long qhbQueryId) {
		this.qhbQueryId = qhbQueryId;
	}

	@FieldBridge(impl = LongBridge.class)
	@Field(index=Index.YES, analyze=Analyze.NO, store=Store.NO)
	@Column(name = "qhb_publication_id", nullable = false)
	public long getQhbPublicationId() {
		return this.qhbPublicationId;
	}

	public void setQhbPublicationId(long qhbPublicationId) {
		this.qhbPublicationId = qhbPublicationId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof QueryHasPublicationsId))
			return false;
		QueryHasPublicationsId castOther = (QueryHasPublicationsId) other;

		return (this.getQhbQueryId() == castOther.getQhbQueryId()) && (this.getQhbPublicationId() == castOther.getQhbPublicationId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getQhbQueryId();
		result = 37 * result + (int) this.getQhbPublicationId();
		return result;
	}

}
