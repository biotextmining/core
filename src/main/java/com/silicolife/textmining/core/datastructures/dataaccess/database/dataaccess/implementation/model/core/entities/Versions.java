package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Versions generated by hbm2java
 */
@Entity
@Table(name = "versions")
public class Versions implements java.io.Serializable {

	private long verVersion;
	private Date verVersionDate;
	private String verNotes;

	public Versions() {
	}

	public Versions(long verVersion, Date verVersionDate) {
		this.verVersion = verVersion;
		this.verVersionDate = verVersionDate;
	}

	public Versions(long verVersion, Date verVersionDate, String verNotes) {
		this.verVersion = verVersion;
		this.verVersionDate = verVersionDate;
		this.verNotes = verNotes;
	}

	@Id
	@Column(name = "ver_version", unique = true, nullable = false)
	public long getVerVersion() {
		return this.verVersion;
	}

	public void setVerVersion(long verVersion) {
		this.verVersion = verVersion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ver_version_date", nullable = false, length = 10)
	public Date getVerVersionDate() {
		return this.verVersionDate;
	}

	public void setVerVersionDate(Date verVersionDate) {
		this.verVersionDate = verVersionDate;
	}

	@Column(name = "ver_notes", length = 500)
	public String getVerNotes() {
		return this.verNotes;
	}

	public void setVerNotes(String verNotes) {
		this.verNotes = verNotes;
	}

}
