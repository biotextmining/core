package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ClusterLabelPublications generated by hbm2java
 */
@Entity
@Table(name = "cluster_label_publications")
public class ClusterLabelPublications implements java.io.Serializable {

	private ClusterLabelPublicationsId id;
	private ClusterLabels clusterLabels;
	private QueryHasPublications queryHasPublications;

	public ClusterLabelPublications() {
	}

	public ClusterLabelPublications(ClusterLabelPublicationsId id, ClusterLabels clusterLabels, QueryHasPublications queryHasPublications) {
		this.id = id;
		this.clusterLabels = clusterLabels;
		this.queryHasPublications = queryHasPublications;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "clpClusterLabelId", column = @Column(name = "clp_cluster_label_id", nullable = false)),
			@AttributeOverride(name = "clpQueryId", column = @Column(name = "clp_query_id", nullable = false)),
			@AttributeOverride(name = "clpPublicationId", column = @Column(name = "clp_publication_id", nullable = false)) })
	public ClusterLabelPublicationsId getId() {
		return this.id;
	}

	public void setId(ClusterLabelPublicationsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clp_cluster_label_id", nullable = false, insertable = false, updatable = false)
	public ClusterLabels getClusterLabels() {
		return this.clusterLabels;
	}

	public void setClusterLabels(ClusterLabels clusterLabels) {
		this.clusterLabels = clusterLabels;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "clp_query_id", referencedColumnName = "qhb_query_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "clp_publication_id", referencedColumnName = "qhb_publication_id", nullable = false, insertable = false, updatable = false) })
	public QueryHasPublications getQueryHasPublications() {
		return this.queryHasPublications;
	}

	public void setQueryHasPublications(QueryHasPublications queryHasPublications) {
		this.queryHasPublications = queryHasPublications;
	}

}
