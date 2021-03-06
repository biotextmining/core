package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AnnotationLogsId generated by hbm2java
 */
@Embeddable
public class AnnotationLogsId implements java.io.Serializable {

	private long aloId;
	private long aloUserId;

	public AnnotationLogsId() {
	}

	public AnnotationLogsId(long aloId, long aloUserId) {
		this.aloId = aloId;
		this.aloUserId = aloUserId;
	}

	@Column(name = "alo_id", nullable = false)
	public long getAloId() {
		return this.aloId;
	}

	public void setAloId(long aloId) {
		this.aloId = aloId;
	}

	@Column(name = "alo_user_id", nullable = false)
	public long getAloUserId() {
		return this.aloUserId;
	}

	public void setAloUserId(long aloUserId) {
		this.aloUserId = aloUserId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AnnotationLogsId))
			return false;
		AnnotationLogsId castOther = (AnnotationLogsId) other;

		return (this.getAloId() == castOther.getAloId()) && (this.getAloUserId() == castOther.getAloUserId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getAloId();
		result = 37 * result + (int) this.getAloUserId();
		return result;
	}

}
