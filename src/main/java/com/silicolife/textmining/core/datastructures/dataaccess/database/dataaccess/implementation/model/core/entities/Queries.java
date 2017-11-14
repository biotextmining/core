package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

/**
 * Queries generated by hbm2java
 */
@Entity
@Indexed
@Table(name = "queries")
public class Queries implements java.io.Serializable {

	private long quId;
	private QueryTypes queryTypes;
	private Date quQueryDate;
	private String quKeywords;
	private String quOrganism;
	private String quCompleteQuery;
	private Integer quMatchingPublications;
	private Integer quAvailableAbstracts;
	private String quQueryName;
	private boolean quActive;
	private String quNotes;
	private Set<QueryProperties> queryPropertieses = new HashSet<QueryProperties>(0);
	private Set<QueryHasPublications> queryHasPublicationses = new HashSet<QueryHasPublications>(0);
	private Set<QueryHasClusterProcesses> queryHasClusterProcesseses = new HashSet<QueryHasClusterProcesses>(0);

	public Queries() {
	}

	public Queries(long quId, QueryTypes queryTypes, Date quQueryDate, String quKeywords, boolean quActive) {
		this.quId = quId;
		this.queryTypes = queryTypes;
		this.quQueryDate = quQueryDate;
		this.quKeywords = quKeywords;
		this.quActive = quActive;
	}

	public Queries(long quId, QueryTypes queryTypes, Date quQueryDate, String quKeywords, String quOrganism, String quCompleteQuery, Integer quMatchingPublications,
			Integer quAvailableAbstracts, String quQueryName, boolean quActive, String quNotes, Set<QueryProperties> queryPropertieses,
			Set<QueryHasPublications> queryHasPublicationses, Set<QueryHasClusterProcesses> queryHasClusterProcesseses) {
		this.quId = quId;
		this.queryTypes = queryTypes;
		this.quQueryDate = quQueryDate;
		this.quKeywords = quKeywords;
		this.quOrganism = quOrganism;
		this.quCompleteQuery = quCompleteQuery;
		this.quMatchingPublications = quMatchingPublications;
		this.quAvailableAbstracts = quAvailableAbstracts;
		this.quQueryName = quQueryName;
		this.quActive = quActive;
		this.quNotes = quNotes;
		this.queryPropertieses = queryPropertieses;
		this.queryHasPublicationses = queryHasPublicationses;
		this.queryHasClusterProcesseses = queryHasClusterProcesseses;
	}

	@Id
	@Column(name = "qu_id", unique = true, nullable = false)
	public long getQuId() {
		return this.quId;
	}

	public void setQuId(long quId) {
		this.quId = quId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qu_query_type_id", nullable = false)
	public QueryTypes getQueryTypes() {
		return this.queryTypes;
	}

	public void setQueryTypes(QueryTypes queryTypes) {
		this.queryTypes = queryTypes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "qu_query_date", nullable = false, length = 19)
	public Date getQuQueryDate() {
		return this.quQueryDate;
	}

	public void setQuQueryDate(Date quQueryDate) {
		this.quQueryDate = quQueryDate;
	}

	@Fields(value = { 
			@Field(name="q_keywordsCS",index=Index.YES, analyze=Analyze.YES, analyzer = @Analyzer(definition="KeywordsSplitter"), store=Store.NO),
			@Field(name="q_keywordsNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="toLowerCase"), store=Store.NO)
	})
	@Column(name = "qu_keywords", nullable = false, length = 16777215)
	public String getQuKeywords() {
		return this.quKeywords;
	}

	public void setQuKeywords(String quKeywords) {
		this.quKeywords = quKeywords;
	}

	@Fields(value = { 
			@Field(name="q_organismCS",index=Index.YES, analyze=Analyze.YES, analyzer = @Analyzer(definition="KeywordsSplitter"), store=Store.NO),
			@Field(name="q_organismNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="toLowerCase"), store=Store.NO)
	})
	@Column(name = "qu_organism", length = 500)
	public String getQuOrganism() {
		return this.quOrganism;
	}

	public void setQuOrganism(String quOrganism) {
		this.quOrganism = quOrganism;
	}

	@Column(name = "qu_complete_query", length = 65535)
	public String getQuCompleteQuery() {
		return this.quCompleteQuery;
	}

	public void setQuCompleteQuery(String quCompleteQuery) {
		this.quCompleteQuery = quCompleteQuery;
	}

	@Column(name = "qu_matching_publications")
	public Integer getQuMatchingPublications() {
		return this.quMatchingPublications;
	}

	public void setQuMatchingPublications(Integer quMatchingPublications) {
		this.quMatchingPublications = quMatchingPublications;
	}

	@Column(name = "qu_available_abstracts")
	public Integer getQuAvailableAbstracts() {
		return this.quAvailableAbstracts;
	}

	public void setQuAvailableAbstracts(Integer quAvailableAbstracts) {
		this.quAvailableAbstracts = quAvailableAbstracts;
	}

	@Fields(value = { 
			@Field(name="q_query_nameCS",index=Index.YES, analyze=Analyze.YES, analyzer = @Analyzer(definition="KeywordsSplitter"), store=Store.NO),
			@Field(name="q_query_nameNCS",index=Index.YES, analyze=Analyze.YES,analyzer = @Analyzer(definition="toLowerCase"), store=Store.NO)
	})
	@Column(name = "qu_query_name", length = 500)
	public String getQuQueryName() {
		return this.quQueryName;
	}

	public void setQuQueryName(String quQueryName) {
		this.quQueryName = quQueryName;
	}

	@Field
	@Column(name = "qu_active", nullable = false)
	public boolean isQuActive() {
		return this.quActive;
	}

	public void setQuActive(boolean quActive) {
		this.quActive = quActive;
	}

	@Column(name = "qu_notes", length = 65535)
	public String getQuNotes() {
		return this.quNotes;
	}

	public void setQuNotes(String quNotes) {
		this.quNotes = quNotes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "queries")
	public Set<QueryProperties> getQueryPropertieses() {
		return this.queryPropertieses;
	}

	public void setQueryPropertieses(Set<QueryProperties> queryPropertieses) {
		this.queryPropertieses = queryPropertieses;
	}
	
	//@IndexedEmbedded
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "queries")
	public Set<QueryHasPublications> getQueryHasPublicationses() {
		return this.queryHasPublicationses;
	}

	public void setQueryHasPublicationses(Set<QueryHasPublications> queryHasPublicationses) {
		this.queryHasPublicationses = queryHasPublicationses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "queries")
	public Set<QueryHasClusterProcesses> getQueryHasClusterProcesseses() {
		return this.queryHasClusterProcesseses;
	}

	public void setQueryHasClusterProcesseses(Set<QueryHasClusterProcesses> queryHasClusterProcesseses) {
		this.queryHasClusterProcesseses = queryHasClusterProcesseses;
	}

}
