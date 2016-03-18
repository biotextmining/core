package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Processes generated by hbm2java
 */
@Entity
@Table(name = "processes")
public class Processes implements java.io.Serializable {

	private long proId;
	private ProcessOrigins processOrigins;
	private ProcessTypes processTypes;
	private String proName;
	private String proNotes;
	private boolean proActive;
	private Set<AnnotationLogs> annotationLogses = new HashSet<AnnotationLogs>(0);
	private Set<CorpusHasProcesses> corpusHasProcesseses = new HashSet<CorpusHasProcesses>(0);
	private Set<ProcessProperties> processPropertieses = new HashSet<ProcessProperties>(0);
	private Set<Annotations> annotationses = new HashSet<Annotations>(0);

	public Processes() {
	}

	public Processes(long proId, ProcessOrigins processOrigins, ProcessTypes processTypes, boolean proActive) {
		this.proId = proId;
		this.processOrigins = processOrigins;
		this.processTypes = processTypes;
		this.proActive = proActive;
	}

	public Processes(long proId, ProcessOrigins processOrigins, ProcessTypes processTypes, String proName, String proNotes, boolean proActive,
			Set<AnnotationLogs> annotationLogses, Set<CorpusHasProcesses> corpusHasProcesseses, Set<ProcessProperties> processPropertieses, Set<Annotations> annotationses) {
		this.proId = proId;
		this.processOrigins = processOrigins;
		this.processTypes = processTypes;
		this.proName = proName;
		this.proNotes = proNotes;
		this.proActive = proActive;
		this.annotationLogses = annotationLogses;
		this.corpusHasProcesseses = corpusHasProcesseses;
		this.processPropertieses = processPropertieses;
		this.annotationses = annotationses;
	}

	@Id
	@Column(name = "pro_id", unique = true, nullable = false)
	public long getProId() {
		return this.proId;
	}

	public void setProId(long proId) {
		this.proId = proId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pro_process_origin_id", nullable = false)
	public ProcessOrigins getProcessOrigins() {
		return this.processOrigins;
	}

	public void setProcessOrigins(ProcessOrigins processOrigins) {
		this.processOrigins = processOrigins;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pro_process_type_id", nullable = false)
	public ProcessTypes getProcessTypes() {
		return this.processTypes;
	}

	public void setProcessTypes(ProcessTypes processTypes) {
		this.processTypes = processTypes;
	}

	@Column(name = "pro_name", length = 500)
	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	@Column(name = "pro_notes", length = 65535)
	public String getProNotes() {
		return this.proNotes;
	}

	public void setProNotes(String proNotes) {
		this.proNotes = proNotes;
	}

	@Column(name = "pro_active", nullable = false)
	public boolean isProActive() {
		return this.proActive;
	}

	public void setProActive(boolean proActive) {
		this.proActive = proActive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "processes")
	public Set<AnnotationLogs> getAnnotationLogses() {
		return this.annotationLogses;
	}

	public void setAnnotationLogses(Set<AnnotationLogs> annotationLogses) {
		this.annotationLogses = annotationLogses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "processes")
	public Set<CorpusHasProcesses> getCorpusHasProcesseses() {
		return this.corpusHasProcesseses;
	}

	public void setCorpusHasProcesseses(Set<CorpusHasProcesses> corpusHasProcesseses) {
		this.corpusHasProcesseses = corpusHasProcesseses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "processes")
	public Set<ProcessProperties> getProcessPropertieses() {
		return this.processPropertieses;
	}

	public void setProcessPropertieses(Set<ProcessProperties> processPropertieses) {
		this.processPropertieses = processPropertieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "processes")
	public Set<Annotations> getAnnotationses() {
		return this.annotationses;
	}

	public void setAnnotationses(Set<Annotations> annotationses) {
		this.annotationses = annotationses;
	}

}
