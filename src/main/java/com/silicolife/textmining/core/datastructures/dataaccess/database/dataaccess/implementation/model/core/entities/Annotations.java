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
 * Annotations generated by hbm2java
 */
@Entity
@Table(name = "annotations")
public class Annotations implements java.io.Serializable {

	private long annId;
	private Classes classes;
	private Corpus corpus;
	private Processes processes;
	private Publications publications;
	private ResourceElements resourceElements;
	private long annAnnotStart;
	private long annAnnotEnd;
	private String annElement;
	private String annAnnotType;
	private String annClue;
	private String annClassificationRe;
	private boolean annActive;
	private Long annStartSentenceOffset;
	private Long annEndSentenceOffset;
	private String annNotes;
	private Set<AnnotationProperties> annotationPropertieses = new HashSet<AnnotationProperties>(0);
	private Set<AnnotationLogs> annotationLogses = new HashSet<AnnotationLogs>(0);
	private Set<AnnotationSides> annotationSidesesForAsAnnotationId = new HashSet<AnnotationSides>(0);
	private Set<AnnotationSides> annotationSidesesForAsAnnotationSubId = new HashSet<AnnotationSides>(0);
	private boolean annAbbreviation;


	public Annotations() {
	}

	public Annotations(long annId, Corpus corpus, Processes processes, Publications publications, long annAnnotStart, long annAnnotEnd, String annAnnotType, boolean annActive) {
		this.annId = annId;
		this.corpus = corpus;
		this.processes = processes;
		this.publications = publications;
		this.annAnnotStart = annAnnotStart;
		this.annAnnotEnd = annAnnotEnd;
		this.annAnnotType = annAnnotType;
		this.annActive = annActive;
	}

	public Annotations(long annId, Classes classes, Corpus corpus, Processes processes, Publications publications, ResourceElements resourceElements, long annAnnotStart,
			long annAnnotEnd, String annElement, String annAnnotType, String annClue, String annClassificationRe, boolean annActive,
			Long annStartSentenceOffset, Long annEndSentenceOffset, String annNotes, Set<AnnotationProperties> annotationPropertieses, Set<AnnotationLogs> annotationLogses,
			Set<AnnotationSides> annotationSidesesForAsAnnotationId, Set<AnnotationSides> annotationSidesesForAsAnnotationSubId,boolean annAbbreviation) {
		this.annId = annId;
		this.classes = classes;
		this.corpus = corpus;
		this.processes = processes;
		this.publications = publications;
		this.resourceElements = resourceElements;
		this.annAnnotStart = annAnnotStart;
		this.annAnnotEnd = annAnnotEnd;
		this.annElement = annElement;
		this.annAnnotType = annAnnotType;
		this.annClue = annClue;
		this.annClassificationRe = annClassificationRe;
		this.annActive = annActive;
		this.annStartSentenceOffset = annStartSentenceOffset;
		this.annEndSentenceOffset = annEndSentenceOffset;
		this.annNotes = annNotes;
		this.annotationPropertieses = annotationPropertieses;
		this.annotationLogses = annotationLogses;
		this.annotationSidesesForAsAnnotationId = annotationSidesesForAsAnnotationId;
		this.annotationSidesesForAsAnnotationSubId = annotationSidesesForAsAnnotationSubId;
		this.annAbbreviation=annAbbreviation;
	}

	@Id
	@Column(name = "ann_id", unique = true, nullable = false)
	public long getAnnId() {
		return this.annId;
	}

	public void setAnnId(long annId) {
		this.annId = annId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ann_class_id")
	public Classes getClasses() {
		return this.classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ann_corpus_id", nullable = false)
	public Corpus getCorpus() {
		return this.corpus;
	}

	public void setCorpus(Corpus corpus) {
		this.corpus = corpus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ann_process_id", nullable = false)
	public Processes getProcesses() {
		return this.processes;
	}

	public void setProcesses(Processes processes) {
		this.processes = processes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ann_publication_id", nullable = false)
	public Publications getPublications() {
		return this.publications;
	}

	public void setPublications(Publications publications) {
		this.publications = publications;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ann_resource_element_id")
	public ResourceElements getResourceElements() {
		return this.resourceElements;
	}

	public void setResourceElements(ResourceElements resourceElements) {
		this.resourceElements = resourceElements;
	}

	@Column(name = "ann_annot_start", nullable = false)
	public long getAnnAnnotStart() {
		return this.annAnnotStart;
	}

	public void setAnnAnnotStart(long annAnnotStart) {
		this.annAnnotStart = annAnnotStart;
	}

	@Column(name = "ann_annot_end", nullable = false)
	public long getAnnAnnotEnd() {
		return this.annAnnotEnd;
	}

	public void setAnnAnnotEnd(long annAnnotEnd) {
		this.annAnnotEnd = annAnnotEnd;
	}

	@Column(name = "ann_element", length = 500)
	public String getAnnElement() {
		return this.annElement;
	}

	public void setAnnElement(String annElement) {
		this.annElement = annElement;
	}

	@Column(name = "ann_abbreviation")
	public boolean isAnnAbbreviation() {
		return annAbbreviation;
	}

	public void setAnnAbbreviation(boolean annAbbreviation) {
		this.annAbbreviation = annAbbreviation;
	}

	@Column(name = "ann_annot_type", nullable = false, length = 4)
	public String getAnnAnnotType() {
		return this.annAnnotType;
	}

	public void setAnnAnnotType(String annAnnotType) {
		this.annAnnotType = annAnnotType;
	}

	@Column(name = "ann_clue", length = 250)
	public String getAnnClue() {
		return this.annClue;
	}

	public void setAnnClue(String annClue) {
		this.annClue = annClue;
	}

	@Column(name = "ann_classification_re", length = 250)
	public String getAnnClassificationRe() {
		return this.annClassificationRe;
	}

	public void setAnnClassificationRe(String annClassificationRe) {
		this.annClassificationRe = annClassificationRe;
	}

	@Column(name = "ann_active", nullable = false)
	public boolean isAnnActive() {
		return this.annActive;
	}

	public void setAnnActive(boolean annActive) {
		this.annActive = annActive;
	}

	@Column(name = "ann_start_sentence_offset")
	public Long getAnnStartSentenceOffset() {
		return this.annStartSentenceOffset;
	}

	public void setAnnStartSentenceOffset(Long annStartSentenceOffset) {
		this.annStartSentenceOffset = annStartSentenceOffset;
	}

	@Column(name = "ann_end_sentence_offset")
	public Long getAnnEndSentenceOffset() {
		return this.annEndSentenceOffset;
	}

	public void setAnnEndSentenceOffset(Long annEndSentenceOffset) {
		this.annEndSentenceOffset = annEndSentenceOffset;
	}

	@Column(name = "ann_notes", length = 500)
	public String getAnnNotes() {
		return this.annNotes;
	}

	public void setAnnNotes(String annNotes) {
		this.annNotes = annNotes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "annotations")
	public Set<AnnotationProperties> getAnnotationPropertieses() {
		return this.annotationPropertieses;
	}

	public void setAnnotationPropertieses(Set<AnnotationProperties> annotationPropertieses) {
		this.annotationPropertieses = annotationPropertieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "annotations")
	public Set<AnnotationLogs> getAnnotationLogses() {
		return this.annotationLogses;
	}

	public void setAnnotationLogses(Set<AnnotationLogs> annotationLogses) {
		this.annotationLogses = annotationLogses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "annotationsByAsAnnotationId")
	public Set<AnnotationSides> getAnnotationSidesesForAsAnnotationId() {
		return this.annotationSidesesForAsAnnotationId;
	}

	public void setAnnotationSidesesForAsAnnotationId(Set<AnnotationSides> annotationSidesesForAsAnnotationId) {
		this.annotationSidesesForAsAnnotationId = annotationSidesesForAsAnnotationId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "annotationsByAsAnnotationSubId")
	public Set<AnnotationSides> getAnnotationSidesesForAsAnnotationSubId() {
		return this.annotationSidesesForAsAnnotationSubId;
	}

	public void setAnnotationSidesesForAsAnnotationSubId(Set<AnnotationSides> annotationSidesesForAsAnnotationSubId) {
		this.annotationSidesesForAsAnnotationSubId = annotationSidesesForAsAnnotationSubId;
	}

}
