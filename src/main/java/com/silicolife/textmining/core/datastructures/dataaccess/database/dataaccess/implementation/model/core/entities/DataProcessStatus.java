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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DataProcessStatus generated by hbm2java
 */
@Entity
@Table(name = "data_process_status")
public class DataProcessStatus implements java.io.Serializable {

	private DataProcessStatusId id;
	private AuthUsers authUsers;
	private String dpsStatus;
	private String dpsReport;
	private Float dpsProgress;
	private Date dpsCreateDate;
	private Date dpsUpdateDate;
	private Date dpsFinishDate;

	public DataProcessStatus() {
	}

	public DataProcessStatus(DataProcessStatusId id, String dpsStatus) {
		this.id = id;
		this.dpsStatus = dpsStatus;
	}
	public DataProcessStatus(DataProcessStatusId id, AuthUsers authUsers, String dpsStatus, String dpsReport,
			Float dpsProgress, Date dpsCreateDate, Date dpsUpdateDate, Date dpsFinishDate) {
		this.id = id;
		this.authUsers = authUsers;
		this.dpsStatus = dpsStatus;
		this.dpsReport = dpsReport;
		this.dpsProgress = dpsProgress;
		this.dpsCreateDate = dpsCreateDate;
		this.dpsUpdateDate = dpsUpdateDate;
		this.dpsFinishDate = dpsFinishDate;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "dpsDataObjectId", column = @Column(name = "dps_data_object_id", nullable = false)),
			@AttributeOverride(name = "dpsTypeResource", column = @Column(name = "dps_type_resource", nullable = false, length = 9))})
	public DataProcessStatusId getId() {
		return this.id;
	}

	public void setId(DataProcessStatusId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dps_users_id")
	public AuthUsers getAuthUsers() {
		return this.authUsers;
	}

	public void setAuthUsers(AuthUsers authUsers) {
		this.authUsers = authUsers;
	}

	@Column(name = "dps_status", nullable = false, length = 8)
	public String getDpsStatus() {
		return this.dpsStatus;
	}

	public void setDpsStatus(String dpsStatus) {
		this.dpsStatus = dpsStatus;
	}

	@Column(name = "dps_report")
	public String getDpsReport() {
		return this.dpsReport;
	}

	public void setDpsReport(String dpsReport) {
		this.dpsReport = dpsReport;
	}

	@Column(name = "dps_progress", precision = 12, scale = 0)
	public Float getDpsProgress() {
		return this.dpsProgress;
	}

	public void setDpsProgress(Float dpsProgress) {
		this.dpsProgress = dpsProgress;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dps_create_date", length = 10)
	public Date getDpsCreateDate() {
		return this.dpsCreateDate;
	}

	public void setDpsCreateDate(Date dpsCreateDate) {
		this.dpsCreateDate = dpsCreateDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dps_update_date", length = 10)
	public Date getDpsUpdateDate() {
		return this.dpsUpdateDate;
	}

	public void setDpsUpdateDate(Date dpsUpdateDate) {
		this.dpsUpdateDate = dpsUpdateDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dps_finish_date", length = 10)
	public Date getDpsFinishDate() {
		return this.dpsFinishDate;
	}

	public void setDpsFinishDate(Date dpsFinishDate) {
		this.dpsFinishDate = dpsFinishDate;
	}

}
