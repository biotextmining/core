package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ResourcesClassStatsId generated by hbm2java
 */
@Embeddable
public class ResourcesClassStatsId implements java.io.Serializable {

	private long resResourceId;
	private Long resClassId;
	private long numberClasses;

	public ResourcesClassStatsId() {
	}

	public ResourcesClassStatsId(long resResourceId, long numberClasses) {
		this.resResourceId = resResourceId;
		this.numberClasses = numberClasses;
	}

	public ResourcesClassStatsId(long resResourceId, Long resClassId, long numberClasses) {
		this.resResourceId = resResourceId;
		this.resClassId = resClassId;
		this.numberClasses = numberClasses;
	}

	@Column(name = "res_resource_id", nullable = false)
	public long getResResourceId() {
		return this.resResourceId;
	}

	public void setResResourceId(long resResourceId) {
		this.resResourceId = resResourceId;
	}

	@Column(name = "res_class_id")
	public Long getResClassId() {
		return this.resClassId;
	}

	public void setResClassId(Long resClassId) {
		this.resClassId = resClassId;
	}

	@Column(name = "numberClasses", nullable = false)
	public long getNumberClasses() {
		return this.numberClasses;
	}

	public void setNumberClasses(long numberClasses) {
		this.numberClasses = numberClasses;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResourcesClassStatsId))
			return false;
		ResourcesClassStatsId castOther = (ResourcesClassStatsId) other;

		return (this.getResResourceId() == castOther.getResResourceId())
				&& ((this.getResClassId() == castOther.getResClassId()) || (this.getResClassId() != null && castOther.getResClassId() != null && this.getResClassId().equals(
						castOther.getResClassId()))) && (this.getNumberClasses() == castOther.getNumberClasses());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getResResourceId();
		result = 37 * result + (getResClassId() == null ? 0 : this.getResClassId().hashCode());
		result = 37 * result + (int) this.getNumberClasses();
		return result;
	}

}
