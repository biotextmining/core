package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.silicolife.textmining.core.interfaces.core.user.IRole;

/**
 * AuthRoles generated by hbm2java
 */
@Entity
@Table(name = "auth_roles", uniqueConstraints = @UniqueConstraint(columnNames = "ar_role_code"))
public class AuthRoles implements java.io.Serializable , IRole{

	private Long arId;
	private String arRoleCode;
	private String arRoleDescription;
	private Set<AuthGroupHasRoles> authGroupHasRoleses = new HashSet<AuthGroupHasRoles>(0);

	public AuthRoles() {
	}

	public AuthRoles(String arRoleCode, String arRoleDescription) {
		this.arRoleCode = arRoleCode;
		this.arRoleDescription = arRoleDescription;
	}

	public AuthRoles(String arRoleCode, String arRoleDescription, Set<AuthGroupHasRoles> authGroupHasRoleses) {
		this.arRoleCode = arRoleCode;
		this.arRoleDescription = arRoleDescription;
		this.authGroupHasRoleses = authGroupHasRoleses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ar_id", unique = true, nullable = false)
	public Long getArId() {
		return this.arId;
	}

	public void setArId(Long arId) {
		this.arId = arId;
	}

	@Column(name = "ar_role_code", unique = true, nullable = false, length = 128)
	public String getArRoleCode() {
		return this.arRoleCode;
	}

	public void setArRoleCode(String arRoleCode) {
		this.arRoleCode = arRoleCode;
	}

	@Column(name = "ar_role_description", nullable = false)
	public String getArRoleDescription() {
		return this.arRoleDescription;
	}

	public void setArRoleDescription(String arRoleDescription) {
		this.arRoleDescription = arRoleDescription;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authRoles")
	public Set<AuthGroupHasRoles> getAuthGroupHasRoleses() {
		return this.authGroupHasRoleses;
	}

	public void setAuthGroupHasRoleses(Set<AuthGroupHasRoles> authGroupHasRoleses) {
		this.authGroupHasRoleses = authGroupHasRoleses;
	}

}
