package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 12/Mai/2015 15:02:03 by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PublicationHasSources generated by hbm2java
 */
@Entity
@Table(name = "publication_has_sources")
public class PublicationHasSources implements java.io.Serializable {

	private PublicationHasSourcesId id;
	private PublicationSources publicationSources;
	private Publications publications;

	public PublicationHasSources() {
	}

	public PublicationHasSources(PublicationHasSourcesId id, PublicationSources publicationSources, Publications publications) {
		this.id = id;
		this.publicationSources = publicationSources;
		this.publications = publications;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "phpsPublicationSourceId", column = @Column(name = "phps_publication_source_id", nullable = false)),
			@AttributeOverride(name = "phpsPublicationSourceInternalId", column = @Column(name = "phps_publication_source_internal_id", nullable = false)),
			@AttributeOverride(name = "phpsPublicationId", column = @Column(name = "phps_publication_id", nullable = false)) })
	public PublicationHasSourcesId getId() {
		return this.id;
	}

	public void setId(PublicationHasSourcesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "phps_publication_source_id", nullable = false, insertable = false, updatable = false)
	public PublicationSources getPublicationSources() {
		return this.publicationSources;
	}

	public void setPublicationSources(PublicationSources publicationSources) {
		this.publicationSources = publicationSources;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "phps_publication_id", nullable = false, insertable = false, updatable = false)
	public Publications getPublications() {
		return this.publications;
	}

	public void setPublications(Publications publications) {
		this.publications = publications;
	}

}
