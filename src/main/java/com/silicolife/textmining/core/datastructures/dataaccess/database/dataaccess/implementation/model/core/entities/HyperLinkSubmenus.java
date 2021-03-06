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
 * HyperLinkSubmenus generated by hbm2java
 */
@Entity
@Table(name = "hyper_link_submenus")
public class HyperLinkSubmenus implements java.io.Serializable {

	private HyperLinkSubmenusId id;
	private HyperLinkMenus hyperLinkMenusByHyliHyperLinkMenuId;
	private HyperLinkMenus hyperLinkMenusByHyliHyperLinkSubmenuId;

	public HyperLinkSubmenus() {
	}

	public HyperLinkSubmenus(HyperLinkSubmenusId id, HyperLinkMenus hyperLinkMenusByHyliHyperLinkMenuId, HyperLinkMenus hyperLinkMenusByHyliHyperLinkSubmenuId) {
		this.id = id;
		this.hyperLinkMenusByHyliHyperLinkMenuId = hyperLinkMenusByHyliHyperLinkMenuId;
		this.hyperLinkMenusByHyliHyperLinkSubmenuId = hyperLinkMenusByHyliHyperLinkSubmenuId;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "hyliHyperLinkMenuId", column = @Column(name = "hyli_hyper_link_menu_id", nullable = false)),
			@AttributeOverride(name = "hyliHyperLinkSubmenuId", column = @Column(name = "hyli_hyper_link_submenu_id", nullable = false)) })
	public HyperLinkSubmenusId getId() {
		return this.id;
	}

	public void setId(HyperLinkSubmenusId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hyli_hyper_link_menu_id", nullable = false, insertable = false, updatable = false)
	public HyperLinkMenus getHyperLinkMenusByHyliHyperLinkMenuId() {
		return this.hyperLinkMenusByHyliHyperLinkMenuId;
	}

	public void setHyperLinkMenusByHyliHyperLinkMenuId(HyperLinkMenus hyperLinkMenusByHyliHyperLinkMenuId) {
		this.hyperLinkMenusByHyliHyperLinkMenuId = hyperLinkMenusByHyliHyperLinkMenuId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hyli_hyper_link_submenu_id", nullable = false, insertable = false, updatable = false)
	public HyperLinkMenus getHyperLinkMenusByHyliHyperLinkSubmenuId() {
		return this.hyperLinkMenusByHyliHyperLinkSubmenuId;
	}

	public void setHyperLinkMenusByHyliHyperLinkSubmenuId(HyperLinkMenus hyperLinkMenusByHyliHyperLinkSubmenuId) {
		this.hyperLinkMenusByHyliHyperLinkSubmenuId = hyperLinkMenusByHyliHyperLinkSubmenuId;
	}

}
