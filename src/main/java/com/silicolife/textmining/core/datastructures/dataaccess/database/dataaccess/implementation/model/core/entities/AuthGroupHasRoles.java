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

import com.silicolife.textmining.core.interfaces.core.user.IGroupHasRole;

/**
 * AuthGroupHasRoles generated by hbm2java
 */
@Entity
@Table(name = "auth_group_has_roles")
public class AuthGroupHasRoles implements java.io.Serializable, IGroupHasRole{

	private AuthGroupHasRolesId id;
	private AuthGroups authGroups;
	private AuthRoles authRoles;

	public AuthGroupHasRoles() {
	}

	public AuthGroupHasRoles(AuthGroupHasRolesId id, AuthGroups authGroups, AuthRoles authRoles) {
		this.id = id;
		this.authGroups = authGroups;
		this.authRoles = authRoles;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "agrGroupId", column = @Column(name = "agr_group_id", nullable = false)),
			@AttributeOverride(name = "agrRoleId", column = @Column(name = "agr_role_id", nullable = false)) })
	public AuthGroupHasRolesId getId() {
		return this.id;
	}

	public void setId(AuthGroupHasRolesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agr_group_id", nullable = false, insertable = false, updatable = false)
	public AuthGroups getAuthGroups() {
		return this.authGroups;
	}

	public void setAuthGroups(AuthGroups authGroups) {
		this.authGroups = authGroups;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agr_role_id", nullable = false, insertable = false, updatable = false)
	public AuthRoles getAuthRoles() {
		return this.authRoles;
	}

	public void setAuthRoles(AuthRoles authRoles) {
		this.authRoles = authRoles;
	}

}
