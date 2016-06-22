ALTER TABLE `annotation_logs` 
CHANGE COLUMN `alo_annot_log_type` `alo_annot_log_type` ENUM('ENTITYADD','ENTITYREMOVE','ENTITYUPDATE','RELATIONADD','RELATIONREMOVE','RELATIONUPDATE','ENTITYVALIDATED','RELATIONVALIDATED') NOT NULL ;

-- -----------------------------------------------------
-- Table `data_process_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `data_process_status` (
  `dps_data_object_id` BIGINT NOT NULL,
  `dps_type_resource` ENUM('queries','resources','corpus','ner','re') NOT NULL,
  `dps_status` ENUM('start','running','finished','stop','error') NOT NULL,
  `dps_report` LONGTEXT NULL,
  `dps_progress` FLOAT NULL,
  `dps_create_date` DATE NULL,
  `dps_update_date` DATE NULL,
  PRIMARY KEY (`dps_data_object_id`, `dps_type_resource`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `data_process_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `corpus_has_publications_has_processes` (
  `chphp_corpus_id` BIGINT NOT NULL,
  `chphp_publication_id` BIGINT NOT NULL,
  `chphp_processes_id` BIGINT NOT NULL,
  `chphp_create_date` DATE NULL,
  `chphp_update_date` DATE NULL,
  PRIMARY KEY (`chphp_corpus_id`, `chphp_publication_id`, `chphp_processes_id`),
  INDEX `fk_corpus_has_publications_has_processes_processes1_idx` (`chphp_processes_id` ASC),
  INDEX `fk_corpus_has_publications_has_processes_corpus_has_publica_idx` (`chphp_corpus_id` ASC, `chphp_publication_id` ASC),
  CONSTRAINT `fk_corpus_has_publications_has_processes_corpus_has_publicati1`
    FOREIGN KEY (`chphp_corpus_id` , `chphp_publication_id`)
    REFERENCES `corpus_has_publications` (`chp_corpus_id` , `chp_publication_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_corpus_has_publications_has_processes_processes1`
    FOREIGN KEY (`chphp_processes_id`)
    REFERENCES `processes` (`pro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

