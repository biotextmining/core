package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ClusterProcesses generated by hbm2java
 */
@Entity
@Table(name = "cluster_processes")
public class ClusterProcesses implements java.io.Serializable {

	private long clpId;
	private String clpDescription;
	private boolean clpActive;
	private Set<ClusterProcessHasLabels> clusterProcessHasLabelses = new HashSet<ClusterProcessHasLabels>(0);
	private Set<QueryHasClusterProcesses> queryHasClusterProcesseses = new HashSet<QueryHasClusterProcesses>(0);
	private Set<ClusterProperties> clusterPropertieses = new HashSet<ClusterProperties>(0);

	public ClusterProcesses() {
	}

	public ClusterProcesses(long clpId, boolean clpActive) {
		this.clpId = clpId;
		this.clpActive = clpActive;
	}

	public ClusterProcesses(long clpId, String clpDescription, boolean clpActive, Set<ClusterProcessHasLabels> clusterProcessHasLabelses,
			Set<QueryHasClusterProcesses> queryHasClusterProcesseses, Set<ClusterProperties> clusterPropertieses) {
		this.clpId = clpId;
		this.clpDescription = clpDescription;
		this.clpActive = clpActive;
		this.clusterProcessHasLabelses = clusterProcessHasLabelses;
		this.queryHasClusterProcesseses = queryHasClusterProcesseses;
		this.clusterPropertieses = clusterPropertieses;
	}

	@Id
	@Column(name = "clp_id", unique = true, nullable = false)
	public long getClpId() {
		return this.clpId;
	}

	public void setClpId(long clpId) {
		this.clpId = clpId;
	}

	@Column(name = "clp_description")
	public String getClpDescription() {
		return this.clpDescription;
	}

	public void setClpDescription(String clpDescription) {
		this.clpDescription = clpDescription;
	}

	@Column(name = "clp_active", nullable = false)
	public boolean isClpActive() {
		return this.clpActive;
	}

	public void setClpActive(boolean clpActive) {
		this.clpActive = clpActive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clusterProcesses")
	public Set<ClusterProcessHasLabels> getClusterProcessHasLabelses() {
		return this.clusterProcessHasLabelses;
	}

	public void setClusterProcessHasLabelses(Set<ClusterProcessHasLabels> clusterProcessHasLabelses) {
		this.clusterProcessHasLabelses = clusterProcessHasLabelses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clusterProcesses")
	public Set<QueryHasClusterProcesses> getQueryHasClusterProcesseses() {
		return this.queryHasClusterProcesseses;
	}

	public void setQueryHasClusterProcesseses(Set<QueryHasClusterProcesses> queryHasClusterProcesseses) {
		this.queryHasClusterProcesseses = queryHasClusterProcesseses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clusterProcesses")
	public Set<ClusterProperties> getClusterPropertieses() {
		return this.clusterPropertieses;
	}

	public void setClusterPropertieses(Set<ClusterProperties> clusterPropertieses) {
		this.clusterPropertieses = clusterPropertieses;
	}

}
