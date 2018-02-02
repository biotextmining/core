package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.bridges.AuthUserDataObjectsIdBridge;
import com.silicolife.textmining.core.interfaces.core.user.IUserDataObject;

/**
 * AuthUserDataObjects generated by hbm2java
 */
@Entity
@Indexed
@Table(name = "auth_user_data_objects")
public class AuthUserDataObjects implements java.io.Serializable, IUserDataObject {

	private AuthUserDataObjectsId id;
	private AuthUsers authUsers;
	private String audoPermission;

	public AuthUserDataObjects() {
	}

	public AuthUserDataObjects(AuthUserDataObjectsId id, AuthUsers authUsers, String audoPermission) {
		this.id = id;
		this.authUsers = authUsers;
		this.audoPermission = audoPermission;
	}
	
	
	@EmbeddedId
	@FieldBridge(impl = AuthUserDataObjectsIdBridge.class)
	@IndexedEmbedded
	@AttributeOverrides({ @AttributeOverride(name = "audoUserId", column = @Column(name = "audo_user_id", nullable = false)),
			@AttributeOverride(name = "audoUidResource", column = @Column(name = "audo_uid_resource", nullable = false)),
			@AttributeOverride(name = "audoTypeResource", column = @Column(name = "audo_type_resource", nullable = false, length = 9)) })
	public AuthUserDataObjectsId getId() {
		return this.id;
	}

	public void setId(AuthUserDataObjectsId id) {
		this.id = id;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@IndexedEmbedded
	@JoinColumn(name = "audo_user_id", nullable = false, insertable = false, updatable = false)
	public AuthUsers getAuthUsers() {
		return this.authUsers;
	}

	public void setAuthUsers(AuthUsers authUsers) {
		this.authUsers = authUsers;
	}

	@Field(name="audoPermission",index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name = "audo_permission", nullable = false, length = 10)
	@Override
	public String getAudoPermission() {
		return this.audoPermission;
	}

	public void setAudoPermission(String audoPermission) {
		this.audoPermission = audoPermission;
	}

}
