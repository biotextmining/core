package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.bridges.ResourceTypesBridge;

/**
 * ResourceTypes generated by hbm2java
 */


@Entity
@Indexed
@Table(name = "resource_types", uniqueConstraints = @UniqueConstraint(columnNames = "rty_resource_type"))
public class ResourceTypes implements java.io.Serializable {

	private long rtyId;
	private String rtyResourceType;
	private Set<Resources> resourceses = new HashSet<Resources>(0);

	public ResourceTypes() {
	}
	
	
	public ResourceTypes(long rtyId, String rtyResourceType) {
		this.rtyId = rtyId;
		this.rtyResourceType = rtyResourceType;
	}

	
	public ResourceTypes(long rtyId, String rtyResourceType, Set<Resources> resourceses) {
		this.rtyId = rtyId;
		this.rtyResourceType = rtyResourceType;
		this.resourceses = resourceses;
	}
	
	@Id
	@Column(name = "rty_id", unique = true, nullable = false)
	public long getRtyId() {
		return this.rtyId;
	}

	public void setRtyId(long rtyId) {
		this.rtyId = rtyId;
	}
	
	@IndexedEmbedded
	@Field
	@Column(name = "rty_resource_type", unique = true, nullable = false)
	public String getRtyResourceType() {
		return this.rtyResourceType;
	}

	public void setRtyResourceType(String rtyResourceType) {
		this.rtyResourceType = rtyResourceType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resourceTypes")
	public Set<Resources> getResourceses() {
		return this.resourceses;
	}

	public void setResourceses(Set<Resources> resourceses) {
		this.resourceses = resourceses;
	}

}
