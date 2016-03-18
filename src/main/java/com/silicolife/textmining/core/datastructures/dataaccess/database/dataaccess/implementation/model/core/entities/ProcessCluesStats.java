package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities;

// Generated 23/Mar/2015 16:36:00 by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ProcessCluesStats generated by hbm2java
 */
@Entity
@Table(name = "process_clues_stats")
public class ProcessCluesStats implements java.io.Serializable {

	private ProcessCluesStatsId id;

	public ProcessCluesStats() {
	}

	public ProcessCluesStats(ProcessCluesStatsId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "annProcessId", column = @Column(name = "ann_process_id", nullable = false)),
			@AttributeOverride(name = "annClue", column = @Column(name = "ann_clue", length = 250)),
			@AttributeOverride(name = "numberClues", column = @Column(name = "numberClues", nullable = false)),
			@AttributeOverride(name = "annClassificationRe", column = @Column(name = "ann_classification_re", length = 250)) })
	public ProcessCluesStatsId getId() {
		return this.id;
	}

	public void setId(ProcessCluesStatsId id) {
		this.id = id;
	}

}
