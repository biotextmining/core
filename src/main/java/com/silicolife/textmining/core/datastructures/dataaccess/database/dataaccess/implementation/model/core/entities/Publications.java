package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 12/Mai/2015 15:04:11 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.AnalyzerDefs;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.bridge.builtin.IntegerBridge;

/**
 * Publications generated by hbm2java
 */
@Entity
@Indexed
@AnalyzerDefs({ @AnalyzerDef(name = "KeywordsSplitter",

// Split input into keywords
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
	    @TokenFilterDef(factory = StopFilterFactory.class)
	    })
		,
	
	@AnalyzerDef(name = "toLowerCase",
	 tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class)
	
	,
	
			filters = {
					@TokenFilterDef(factory = LowerCaseFilterFactory.class)
	}
	),
	
		@AnalyzerDef(name = "keywordEdgeAnalyzerCS",
	
		// Split input into keywords
	tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),
	
	filters = {
			@TokenFilterDef(factory = StopFilterFactory.class),
			@TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
					@Parameter(name = "minGramSize", value = "3"),
					@Parameter(name = "maxGramSize", value = "30") 
			}) 
	}),
	
	@AnalyzerDef(name = "tokenEdgeAnalyzerCS",

	// Split input into tokens
	tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),

	filters = {
			@TokenFilterDef(factory = StopFilterFactory.class),
			@TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
					@Parameter(name = "minGramSize", value = "4"),
					@Parameter(name = "maxGramSize", value = "8") 
			})
	})
	
})


@Table(name = "publications")
public class Publications implements java.io.Serializable {

	private long pubId;
	private String pubTitle;
	private String pubAuthors;
	private String pubCategory;
	private Integer pubYeardate;
	private String pubFulldate;
	private String pubStatus;
	private String pubJournal;
	private String pubVolume;
	private String pubIssue;
	private String pubPages;
	private String pubAbstract;
	private String pubExternalLinks;
	private Boolean pubFreeFullText;
	private String pubFullcontent;
	private String pubNotes;
	private String pubRelativePath;
	private String pubType;
	private Set<Annotations> annotationses = new HashSet<Annotations>(0);
	private Set<PublicationFields> publicationFieldses = new HashSet<PublicationFields>(0);
	private Set<PublicationHasLabels> publicationHasLabelses = new HashSet<PublicationHasLabels>(0);
	private Set<PublicationHasSources> publicationHasSourceses = new HashSet<PublicationHasSources>(0);
	private Set<AnnotationLogs> annotationLogses = new HashSet<AnnotationLogs>(0);
	private Set<CorpusHasPublications> corpusHasPublicationses = new HashSet<CorpusHasPublications>(0);
	private Set<QueryHasPublications> queryHasPublicationses = new HashSet<QueryHasPublications>(0);

	public Publications() {
	}

	public Publications(long pubId) {
		this.pubId = pubId;
	}

	public Publications(long pubId, String pubTitle, String pubAuthors, String pubCategory, Integer pubYeardate, String pubFulldate, String pubStatus, String pubJournal,
			String pubVolume, String pubIssue, String pubPages, String pubAbstract, String pubExternalLinks, Boolean pubFreeFullText, String pubFullcontent, String pubNotes,
			String pubRelativePath,String pubType, Set<Annotations> annotationses, Set<PublicationFields> publicationFieldses, Set<PublicationHasLabels> publicationHasLabelses,
			Set<PublicationHasSources> publicationHasSourceses, Set<AnnotationLogs> annotationLogses, Set<CorpusHasPublications> corpusHasPublicationses,
			Set<QueryHasPublications> queryHasPublicationses) {
		this.pubId = pubId;
		this.pubTitle = pubTitle;
		this.pubAuthors = pubAuthors;
		this.pubCategory = pubCategory;
		this.pubYeardate = pubYeardate;
		this.pubFulldate = pubFulldate;
		this.pubStatus = pubStatus;
		this.pubJournal = pubJournal;
		this.pubVolume = pubVolume;
		this.pubIssue = pubIssue;
		this.pubPages = pubPages;
		this.pubAbstract = pubAbstract;
		this.pubExternalLinks = pubExternalLinks;
		this.pubFreeFullText = pubFreeFullText;
		this.pubFullcontent = pubFullcontent;
		this.pubNotes = pubNotes;
		this.pubRelativePath = pubRelativePath;
		this.pubType=pubType;
		this.annotationses = annotationses;
		this.publicationFieldses = publicationFieldses;
		this.publicationHasLabelses = publicationHasLabelses;
		this.publicationHasSourceses = publicationHasSourceses;
		this.annotationLogses = annotationLogses;
		this.corpusHasPublicationses = corpusHasPublicationses;
		this.queryHasPublicationses = queryHasPublicationses;
	}

	@Id
	@Column(name = "pub_id", unique = true, nullable = false)
	public long getPubId() {
		return this.pubId;
	}

	public void setPubId(long pubId) {
		this.pubId = pubId;
	}
	

	@Fields(value = { 
			@Field(name="titleCS",index=Index.YES, analyze=Analyze.YES, analyzer = @Analyzer(definition="KeywordsSplitter"), store=Store.NO),
			@Field(name="titleNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="toLowerCase"), store=Store.NO),
			//@Field(name="titleSNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="keywordEdgeAnalyzer"), store=Store.NO, boost = @Boost(2)),
			//@Field(name="titleSCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="keywordEdgeAnalyzerCS"), store=Store.NO, boost = @Boost(2)),
			//@Field(name="titleTNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="tokenEdgeAnalyzer"), store=Store.NO),
			//@Field(name="titleTCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="tokenEdgeAnalyzerCS"), store=Store.NO)
			@Field(name = "pubTitleSort", analyze = Analyze.NO, store = Store.YES)			
	})
	@SortableField(forField = "pubTitleSort")
	@Column(name = "pub_title", length = 65535)
	public String getPubTitle() {
		return this.pubTitle;
	}

	public void setPubTitle(String pubTitle) {
		this.pubTitle = pubTitle;
	}

	@Fields(value = { 
			@Field(name="authorsCS",index=Index.YES, analyze=Analyze.YES, analyzer = @Analyzer(definition="KeywordsSplitter"), store=Store.NO),
			@Field(name="authorsNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="toLowerCase"), store=Store.NO),
			//@Field(name="authorsSNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="tokenEdgeAnalyzer"), store=Store.NO),
			//@Field(name="authorsSCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="tokenEdgeAnalyzerCS"), store=Store.NO),
			@Field(name = "pubAuthorsSort", analyze = Analyze.NO, store = Store.YES)	
	})
	@SortableField(forField = "pubAuthorsSort")
	@Column(name = "pub_authors", length = 65535)
	public String getPubAuthors() {
		return this.pubAuthors;
	}

	public void setPubAuthors(String pubAuthors) {
		this.pubAuthors = pubAuthors;
	}

	@Fields(value = { 
			@Field(name="categoryCS",index=Index.YES, analyze=Analyze.YES, analyzer = @Analyzer(definition="KeywordsSplitter"), store=Store.NO),
			@Field(name="categoryNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="toLowerCase"), store=Store.NO),
			@Field(name = "pubCategorySort", analyze = Analyze.NO, store = Store.YES)
	})
	@SortableField(forField = "pubCategorySort")
	@Column(name = "pub_category")
	public String getPubCategory() {
		return this.pubCategory;
	}

	public void setPubCategory(String pubCategory) {
		this.pubCategory = pubCategory;
	}
	
	@Fields(value = { 
	@Field(name = "pubYeardateSort", analyze = Analyze.NO, store = Store.YES),
	@Field(name="lucYearDate")
	})
	@FieldBridge(impl = IntegerBridge.class)
	//@SortableField(forField = "pubYeardateSort")
	@Column(name = "pub_yeardate")
	public Integer getPubYeardate() {
		return this.pubYeardate;
	}

	public void setPubYeardate(Integer pubYeardate) {
		this.pubYeardate = pubYeardate;
	}

	@Column(name = "pub_fulldate", length = 25)
	public String getPubFulldate() {
		return this.pubFulldate;
	}

	public void setPubFulldate(String pubFulldate) {
		this.pubFulldate = pubFulldate;
	}

	@Column(name = "pub_status", length = 25)
	public String getPubStatus() {
		return this.pubStatus;
	}

	public void setPubStatus(String pubStatus) {
		this.pubStatus = pubStatus;
	}

	@Fields(value = { 
			@Field(name="journalCS",index=Index.YES, analyze=Analyze.YES, analyzer = @Analyzer(definition="KeywordsSplitter"), store=Store.NO),
			@Field(name="journalNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="toLowerCase"), store=Store.NO),
			//@Field(name="journalSNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="tokenEdgeAnalyzer"), store=Store.NO),
			//@Field(name="journalSCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="tokenEdgeAnalyzerCS"), store=Store.NO)
	})
	@Column(name = "pub_journal", length = 500)
	public String getPubJournal() {
		return this.pubJournal;
	}

	public void setPubJournal(String pubJournal) {
		this.pubJournal = pubJournal;
	}

	@Column(name = "pub_volume", length = 128)
	public String getPubVolume() {
		return this.pubVolume;
	}

	public void setPubVolume(String pubVolume) {
		this.pubVolume = pubVolume;
	}

	@Column(name = "pub_issue", length = 128)
	public String getPubIssue() {
		return this.pubIssue;
	}

	public void setPubIssue(String pubIssue) {
		this.pubIssue = pubIssue;
	}

	@Column(name = "pub_pages", length = 128)
	public String getPubPages() {
		return this.pubPages;
	}

	public void setPubPages(String pubPages) {
		this.pubPages = pubPages;
	}

	@Fields(value = { 
			@Field(name="abstractCS",index=Index.YES, analyze=Analyze.YES, analyzer = @Analyzer(definition="KeywordsSplitter"), store=Store.NO),
			@Field(name="abstractNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="toLowerCase"), store=Store.NO),
			//@Field(name="abstractSNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="tokenEdgeAnalyzer"), store=Store.NO),
			//@Field(name="abstractSCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="tokenEdgeAnalyzerCS"), store=Store.NO)
	})
	@Column(name = "pub_abstract")
	public String getPubAbstract() {
		return this.pubAbstract;
	}

	public void setPubAbstract(String pubAbstract) {
		this.pubAbstract = pubAbstract;
	}

	@Column(name = "pub_external_links", length = 65535)
	public String getPubExternalLinks() {
		return this.pubExternalLinks;
	}

	public void setPubExternalLinks(String pubExternalLinks) {
		this.pubExternalLinks = pubExternalLinks;
	}

	@Column(name = "pub_free_full_text")
	public Boolean getPubFreeFullText() {
		return this.pubFreeFullText;
	}

	public void setPubFreeFullText(Boolean pubFreeFullText) {
		this.pubFreeFullText = pubFreeFullText;
	}

	
	@Fields(value = { 
			@Field(name="fullContentCS",index=Index.YES, analyze=Analyze.YES, analyzer = @Analyzer(definition="KeywordsSplitter"), store=Store.NO),
			@Field(name="fullContentNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="toLowerCase"), store=Store.NO),
			//@Field(name="fullContentSNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="tokenEdgeAnalyzer"), store=Store.NO),
			//@Field(name="fullContentSCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="tokenEdgeAnalyzerCS"), store=Store.NO)
	})
	
	@Column(name = "pub_fullcontent")
	public String getPubFullcontent() {
		return this.pubFullcontent;
	}

	public void setPubFullcontent(String pubFullcontent) {
		this.pubFullcontent = pubFullcontent;
	}

	
	@Fields(value = { 
			@Field(name="notesCS",index=Index.YES, analyze=Analyze.YES, analyzer = @Analyzer(definition="KeywordsSplitter"), store=Store.NO),
			@Field(name="notesNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="toLowerCase"), store=Store.NO),
			//@Field(name="notesSNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="tokenEdgeAnalyzer"), store=Store.NO),
			//@Field(name="notesSCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="tokenEdgeAnalyzerCS"), store=Store.NO)
	})
	
	@Column(name = "pub_notes", length = 65535)
	public String getPubNotes() {
		return this.pubNotes;
	}

	public void setPubNotes(String pubNotes) {
		this.pubNotes = pubNotes;
	}

	@Column(name = "pub_relative_path", length = 200)
	public String getPubRelativePath() {
		return this.pubRelativePath;
	}

	public void setPubRelativePath(String pubRelativePath) {
		this.pubRelativePath = pubRelativePath;
	}
	
	@Fields(value = { 
			@Field(name="typeCS",index=Index.YES, analyze=Analyze.YES, analyzer = @Analyzer(definition="KeywordsSplitter"), store=Store.NO),
			@Field(name="typeNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="toLowerCase"), store=Store.NO),
			@Field(name = "pubTypeSort", analyze = Analyze.NO, store = Store.YES)
	})
	@SortableField(forField = "pubTypeSort")
	@Column(name = "pub_type", length = 45)
	public String getPubType() {
		return pubType;
	}

	public void setPubType(String pubType) {
		this.pubType = pubType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<Annotations> getAnnotationses() {
		return this.annotationses;
	}

	public void setAnnotationses(Set<Annotations> annotationses) {
		this.annotationses = annotationses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<PublicationFields> getPublicationFieldses() {
		return this.publicationFieldses;
	}

	public void setPublicationFieldses(Set<PublicationFields> publicationFieldses) {
		this.publicationFieldses = publicationFieldses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<PublicationHasLabels> getPublicationHasLabelses() {
		return this.publicationHasLabelses;
	}

	public void setPublicationHasLabelses(Set<PublicationHasLabels> publicationHasLabelses) {
		this.publicationHasLabelses = publicationHasLabelses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<PublicationHasSources> getPublicationHasSourceses() {
		return this.publicationHasSourceses;
	}

	public void setPublicationHasSourceses(Set<PublicationHasSources> publicationHasSourceses) {
		this.publicationHasSourceses = publicationHasSourceses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<AnnotationLogs> getAnnotationLogses() {
		return this.annotationLogses;
	}

	public void setAnnotationLogses(Set<AnnotationLogs> annotationLogses) {
		this.annotationLogses = annotationLogses;
	}
	
	@IndexedEmbedded
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<CorpusHasPublications> getCorpusHasPublicationses() {
		return this.corpusHasPublicationses;
	}

	public void setCorpusHasPublicationses(Set<CorpusHasPublications> corpusHasPublicationses) {
		this.corpusHasPublicationses = corpusHasPublicationses;
	}
	
	@IndexedEmbedded
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<QueryHasPublications> getQueryHasPublicationses() {
		return this.queryHasPublicationses;
	}

	public void setQueryHasPublicationses(Set<QueryHasPublications> queryHasPublicationses) {
		this.queryHasPublicationses = queryHasPublicationses;
	}

}
