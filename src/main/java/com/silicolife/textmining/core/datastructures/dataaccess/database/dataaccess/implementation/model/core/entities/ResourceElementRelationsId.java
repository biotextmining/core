package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ResourceElementRelationsId generated by hbm2java
 */
@Embeddable
public class ResourceElementRelationsId implements java.io.Serializable {

	private long relResourceElementIdB;
	private long relResourceElementIdA;

	public ResourceElementRelationsId() {
	}

	public ResourceElementRelationsId(long relResourceElementIdB, long relResourceElementIdA) {
		this.relResourceElementIdB = relResourceElementIdB;
		this.relResourceElementIdA = relResourceElementIdA;
	}

	@Column(name = "rel_resource_element_id_b", nullable = false)
	public long getRelResourceElementIdB() {
		return this.relResourceElementIdB;
	}

	public void setRelResourceElementIdB(long relResourceElementIdB) {
		this.relResourceElementIdB = relResourceElementIdB;
	}

	@Column(name = "rel_resource_element_id_a", nullable = false)
	public long getRelResourceElementIdA() {
		return this.relResourceElementIdA;
	}

	public void setRelResourceElementIdA(long relResourceElementIdA) {
		this.relResourceElementIdA = relResourceElementIdA;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResourceElementRelationsId))
			return false;
		ResourceElementRelationsId castOther = (ResourceElementRelationsId) other;

		return (this.getRelResourceElementIdB() == castOther.getRelResourceElementIdB()) && (this.getRelResourceElementIdA() == castOther.getRelResourceElementIdA());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getRelResourceElementIdB();
		result = 37 * result + (int) this.getRelResourceElementIdA();
		return result;
	}

}
