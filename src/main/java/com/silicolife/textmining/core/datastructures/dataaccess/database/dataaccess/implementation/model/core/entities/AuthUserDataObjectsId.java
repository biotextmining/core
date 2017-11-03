package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.bridge.builtin.LongBridge;

/**
 * AuthUserDataObjectsId generated by hbm2java
 */
@Embeddable
public class AuthUserDataObjectsId implements java.io.Serializable {

	private long audoUserId;
	private long audoUidResource;
	private String audoTypeResource;

	public AuthUserDataObjectsId() {
	}

	public AuthUserDataObjectsId(long audoUserId, long audoUidResource, String audoTypeResource) {
		this.audoUserId = audoUserId;
		this.audoUidResource = audoUidResource;
		this.audoTypeResource = audoTypeResource;
	}
	
	@FieldBridge(impl = LongBridge.class)
	@Field(name="Auth_audo_user_id",index=Index.YES, analyze=Analyze.NO, store=Store.NO)
	@Column(name = "audo_user_id", nullable = false)
	public long getAudoUserId() {
		return this.audoUserId;
	}

	public void setAudoUserId(long audoUserId) {
		this.audoUserId = audoUserId;
	}
	
	@FieldBridge(impl = LongBridge.class)
	@Field(name="Auth_audo_uid_resource", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
	@Column(name = "audo_uid_resource", nullable = false)
	public long getAudoUidResource() {
		return this.audoUidResource;
	}

	public void setAudoUidResource(long audoUidResource) {
		this.audoUidResource = audoUidResource;
	}

	@Column(name = "audo_type_resource", nullable = false, length = 9)
	public String getAudoTypeResource() {
		return this.audoTypeResource;
	}

	public void setAudoTypeResource(String audoTypeResource) {
		this.audoTypeResource = audoTypeResource;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AuthUserDataObjectsId))
			return false;
		AuthUserDataObjectsId castOther = (AuthUserDataObjectsId) other;

		return (this.getAudoUserId() == castOther.getAudoUserId())
				&& (this.getAudoUidResource() == castOther.getAudoUidResource())
				&& ((this.getAudoTypeResource() == castOther.getAudoTypeResource()) || (this.getAudoTypeResource() != null && castOther.getAudoTypeResource() != null && this
						.getAudoTypeResource().equals(castOther.getAudoTypeResource())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getAudoUserId();
		result = 37 * result + (int) this.getAudoUidResource();
		result = 37 * result + (getAudoTypeResource() == null ? 0 : this.getAudoTypeResource().hashCode());
		return result;
	}

}
