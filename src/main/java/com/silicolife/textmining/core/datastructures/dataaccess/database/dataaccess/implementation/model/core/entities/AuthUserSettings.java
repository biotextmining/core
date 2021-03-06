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

/**
 * AuthUserSettings generated by hbm2java
 */
@Entity
@Table(name = "auth_user_settings")
public class AuthUserSettings implements java.io.Serializable {

	private AuthUserSettingsId id;
	private AuthUsers authUsers;
	private String ausPropValue;

	public AuthUserSettings() {
	}

	public AuthUserSettings(AuthUserSettingsId id, AuthUsers authUsers, String ausPropValue) {
		this.id = id;
		this.authUsers = authUsers;
		this.ausPropValue = ausPropValue;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "ausUserId", column = @Column(name = "aus_user_id", nullable = false)),
			@AttributeOverride(name = "ausPropKey", column = @Column(name = "aus_prop_key", nullable = false)) })
	public AuthUserSettingsId getId() {
		return this.id;
	}

	public void setId(AuthUserSettingsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aus_user_id", nullable = false, insertable = false, updatable = false)
	public AuthUsers getAuthUsers() {
		return this.authUsers;
	}

	public void setAuthUsers(AuthUsers authUsers) {
		this.authUsers = authUsers;
	}

	@Column(name = "aus_prop_value", nullable = false)
	public String getAusPropValue() {
		return this.ausPropValue;
	}

	public void setAusPropValue(String ausPropValue) {
		this.ausPropValue = ausPropValue;
	}

}
