-- H2 Script
-- 19/05/17

-- -----------------------------------------------------
-- Table `corpus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `corpus` (
  `crp_id` BIGINT NOT NULL,
  `crp_corpus_name` VARCHAR(500) NOT NULL DEFAULT '\\"\\"',
  `crp_active` TINYINT(1) NOT NULL DEFAULT 1,
  `crp_notes` TEXT NULL,
  PRIMARY KEY (`crp_id`));

-- -----------------------------------------------------
-- Table `process_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `process_types` (
  `pt_id` BIGINT NOT NULL,
  `pt_process_type` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`pt_id`));

CREATE UNIQUE INDEX IF NOT EXISTS `idx_process_types_uk`
	ON `process_types` (`pt_process_type` ASC);

-- -----------------------------------------------------
-- Table `process_origins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `process_origins` (
  `po_id` BIGINT NOT NULL,
  `po_description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`po_id`));

CREATE UNIQUE INDEX IF NOT EXISTS `idx_processes_origin_uk` 
  ON `process_origins` (`po_description` ASC);

-- -----------------------------------------------------
-- Table `processes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `processes` (
  `pro_id` BIGINT NOT NULL,
  `pro_process_type_id` BIGINT NOT NULL,
  `pro_process_origin_id` BIGINT NOT NULL,
  `pro_name` VARCHAR(500) NULL,
  `pro_notes` TEXT NULL,
  `pro_active` TINYINT(1) NOT NULL DEFAULT 1,
  `pro_version` INT(3) NOT NULL DEFAULT 1,
  `pro_create_date` DATETIME NULL DEFAULT NULL,
  `pro_update_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`pro_id`));
  
CREATE INDEX IF NOT EXISTS `idx_processes_01`
	ON `processes` (`pro_active` ASC);
CREATE INDEX IF NOT EXISTS `idx_processes_02`
	ON `processes` (`pro_process_type_id` ASC);
CREATE INDEX IF NOT EXISTS `idx_processes_03`
	ON `processes` (`pro_process_origin_id` ASC);
	
ALTER TABLE `processes` ADD
  CONSTRAINT `fk_processes_01`
    FOREIGN KEY (`pro_process_type_id`)
    REFERENCES `process_types` (`pt_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
ALTER TABLE `processes` ADD
  CONSTRAINT `fk_processes_02`
    FOREIGN KEY (`pro_process_origin_id`)
    REFERENCES `process_origins` (`po_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `publications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publications` (
  `pub_id` BIGINT NOT NULL,
  `pub_title` TEXT NULL,
  `pub_authors` TEXT NULL DEFAULT NULL,
  `pub_category` VARCHAR(255) NULL DEFAULT NULL,
  `pub_yeardate` INT(11) UNSIGNED NULL DEFAULT NULL,
  `pub_fulldate` VARCHAR(25) NULL DEFAULT NULL,
  `pub_status` VARCHAR(25) NULL DEFAULT NULL,
  `pub_journal` VARCHAR(500) NULL DEFAULT NULL,
  `pub_volume` VARCHAR(128) NULL DEFAULT NULL,
  `pub_issue` VARCHAR(128) NULL DEFAULT NULL,
  `pub_pages` VARCHAR(128) NULL DEFAULT NULL,
  `pub_abstract` LONGTEXT NULL DEFAULT NULL,
  `pub_external_links` TEXT NULL DEFAULT NULL,
  `pub_free_full_text` TINYINT(1) NULL DEFAULT '0',
  `pub_fullcontent` LONGTEXT NULL DEFAULT NULL,
  `pub_notes` TEXT NULL DEFAULT NULL,
  `pub_relative_path` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`pub_id`));

CREATE INDEX IF NOT EXISTS `idx_publications_yn`
  ON `publications`(`pub_free_full_text` ASC);

-- -----------------------------------------------------
-- Table `resource_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resource_types` (
  `rty_id` BIGINT NOT NULL,
  `rty_resource_type` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`rty_id`));
  
CREATE INDEX IF NOT EXISTS `idx_resource_types_01`
  ON `resource_types` (`rty_resource_type` ASC);

CREATE UNIQUE INDEX IF NOT EXISTS `idx_resource_types_02`
  ON `resource_types` (`rty_resource_type` ASC);

-- -----------------------------------------------------
-- Table `resources`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resources` (
  `reso_id` BIGINT NOT NULL,
  `reso_resource_name` VARCHAR(500) NOT NULL,
  `reso_resource_type_id` BIGINT NOT NULL,
  `reso_notes` VARCHAR(500) NULL,
  `reso_active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`reso_id`));
  
CREATE INDEX IF NOT EXISTS `idx_resources_01`
  ON `resources` (`reso_active` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_resources_02`
  ON `resources` (`reso_resource_type_id` ASC);
  
ALTER TABLE `resources` ADD 
  CONSTRAINT `fk_resources`
    FOREIGN KEY (`reso_resource_type_id`)
    REFERENCES `resource_types` (`rty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `classes` (
  `cla_id` BIGINT NOT NULL,
  `cla_name` VARCHAR(255) NOT NULL,
  `cla_color` VARCHAR(10) NULL,
  PRIMARY KEY (`cla_id`));
  
CREATE UNIQUE INDEX IF NOT EXISTS `idx_classes_uk` 
  ON `classes` (`cla_name` ASC);

-- -----------------------------------------------------
-- Table `resource_elements`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resource_elements` (
  `res_id` BIGINT NOT NULL,
  `res_resource_id` BIGINT NOT NULL,
  `res_class_id` BIGINT NULL,
  `res_element` VARCHAR(500) NOT NULL,
  `res_priorety` INT NOT NULL DEFAULT 0,
  `res_active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`res_id`));
  
CREATE INDEX IF NOT EXISTS `idx_resource_elements_01`
  ON `resource_elements` (`res_active` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_resource_elements_02`
  ON `resource_elements` (`res_resource_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_resource_elements_03`
  ON `resource_elements` (`res_class_id` ASC);

ALTER TABLE `resource_elements` ADD
  CONSTRAINT `fk_resource_elements_01`
    FOREIGN KEY (`res_resource_id`)
    REFERENCES `resources` (`reso_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `resource_elements` ADD    
  CONSTRAINT `fk_resource_elements_02`
    FOREIGN KEY (`res_class_id`)
    REFERENCES `classes` (`cla_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `annotations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `annotations` (
  `ann_id` BIGINT NOT NULL,
  `ann_process_id` BIGINT NOT NULL,
  `ann_corpus_id` BIGINT NOT NULL,
  `ann_publication_id` BIGINT NOT NULL,
  `ann_annot_start` BIGINT NOT NULL,
  `ann_annot_end` BIGINT NOT NULL,
  `ann_resource_element_id` BIGINT NULL,
  `ann_class_id` BIGINT NULL DEFAULT NULL,
  `ann_element` VARCHAR(500) NULL DEFAULT NULL,
  `ann_annot_type` VARCHAR(6) NOT NULL DEFAULT 'ner',
  `ann_clue` VARCHAR(250) NULL,
  `ann_active` TINYINT(1) NOT NULL DEFAULT 1,
  `ann_notes` VARCHAR(500) NULL DEFAULT NULL,
  `ann_abbreviation` TINYINT(1)  NULL DEFAULT 0,
  `ann_validated` TINYINT(1)  NULL DEFAULT 0,
  PRIMARY KEY (`ann_id`));
  
CREATE INDEX IF NOT EXISTS `idx_annotations_01`
  ON `annotations` (`ann_annot_type` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_annotations_02`
  ON `annotations` (`ann_active` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_annotations_03`
  ON `annotations` (`ann_clue` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_annotations_04`
  ON `annotations` (`ann_corpus_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_annotations_05`
  ON `annotations` (`ann_process_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_annotations_06`
 ON `annotations` (`ann_publication_id` ASC);
 
CREATE INDEX IF NOT EXISTS `idx_annotations_07`
  ON `annotations` (`ann_resource_element_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_annotations_08`
  ON `annotations` (`ann_class_id` ASC);

ALTER TABLE `annotations` ADD
  CONSTRAINT `ck_ann_annot_type`
  	CHECK ( `ann_annot_type` in ('ner','re')); 
  
ALTER TABLE `annotations` ADD
  CONSTRAINT `fk_annotations_01`
    FOREIGN KEY (`ann_corpus_id`)
    REFERENCES `corpus` (`crp_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
   
ALTER TABLE `annotations` ADD    
  CONSTRAINT `fk_annotations_02`
    FOREIGN KEY (`ann_process_id`)
    REFERENCES `processes` (`pro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `annotations` ADD    
  CONSTRAINT `fk_annotations_03`
    FOREIGN KEY (`ann_publication_id`)
    REFERENCES `publications` (`pub_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `annotations` ADD    
  CONSTRAINT `fk_annotations_04`
    FOREIGN KEY (`ann_resource_element_id`)
    REFERENCES `resource_elements` (`res_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `annotations` ADD
  CONSTRAINT `fk_annotations_05`
    FOREIGN KEY (`ann_class_id`)
    REFERENCES `classes` (`cla_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `class_hierarchies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `class_hierarchies` (
  `clh_class_id` BIGINT NOT NULL,
  `clh_super_class_id` BIGINT NOT NULL);

CREATE INDEX IF NOT EXISTS `idx_class_hierarchies_01`
  ON `class_hierarchies` (`clh_class_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_class_hierarchies_02`
  ON `class_hierarchies` (`clh_super_class_id` ASC);
  
ALTER TABLE `class_hierarchies` ADD
  CONSTRAINT `fk_class_hierarchies_01`
    FOREIGN KEY (`clh_class_id`)
    REFERENCES `classes` (`cla_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `class_hierarchies` ADD    
  CONSTRAINT `fk_class_hierarchies_02`
    FOREIGN KEY (`clh_super_class_id`)
    REFERENCES `classes` (`cla_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `corpus_has_processes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `corpus_has_processes` (
  `chp_corpus_id` BIGINT NOT NULL,
  `chp_process_id` BIGINT NOT NULL,
  PRIMARY KEY (`chp_corpus_id`, `chp_process_id`));
  
CREATE INDEX IF NOT EXISTS `idx_corpus_has_processes_01`
  ON `corpus_has_processes` (`chp_corpus_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_corpus_has_processes_02`
  ON `corpus_has_processes` (`chp_process_id` ASC);
  
ALTER TABLE `corpus_has_processes` ADD
  CONSTRAINT `fk_corpus_has_processes_01`
    FOREIGN KEY (`chp_corpus_id`)
    REFERENCES `corpus` (`crp_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `corpus_has_processes` ADD 
  CONSTRAINT `fk_corpus_has_processes_02`
    FOREIGN KEY (`chp_process_id`)
    REFERENCES `processes` (`pro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `corpus_has_publications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `corpus_has_publications` (
  `chp_corpus_id` BIGINT NOT NULL,
  `chp_publication_id` BIGINT NOT NULL,
  PRIMARY KEY (`chp_corpus_id`, `chp_publication_id`));
  
CREATE INDEX IF NOT EXISTS `idx_corpus_has_publications_01`
  ON `corpus_has_publications` (`chp_corpus_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_corpus_has_publications_02`
  ON `corpus_has_publications` (`chp_publication_id` ASC);
  
ALTER TABLE `corpus_has_publications` ADD
  CONSTRAINT `fk_corpus_has_publications_01`
    FOREIGN KEY (`chp_corpus_id`)
    REFERENCES `corpus` (`crp_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `corpus_has_publications` ADD    
  CONSTRAINT `fk_corpus_has_publications_02`
    FOREIGN KEY (`chp_publication_id`)
    REFERENCES `publications` (`pub_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `corpus_properties`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `corpus_properties` (
  `cop_corpus_id` BIGINT NOT NULL,
  `cop_prop_key` VARCHAR(255) NOT NULL,
  `cop_prop_value` LONGTEXT NOT NULL,
  PRIMARY KEY (`cop_corpus_id`, `cop_prop_key`));
  
CREATE INDEX IF NOT EXISTS `idx_corpus_properties_fk`
  ON `corpus_properties` (`cop_corpus_id` ASC);
  
ALTER TABLE `corpus_properties` ADD
  CONSTRAINT `fk_corpus_properties`
    FOREIGN KEY (`cop_corpus_id`)
    REFERENCES `corpus` (`crp_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `process_properties`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `process_properties` (
  `ppr_process_id` BIGINT NOT NULL,
  `ppr_prop_key` VARCHAR(255) NOT NULL,
  `ppr_prop_value` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ppr_process_id`, `ppr_prop_key`));
  
CREATE INDEX IF NOT EXISTS `idx_process_properties_fk`
  ON `process_properties` (`ppr_process_id` ASC);
  
ALTER TABLE `process_properties` ADD
  CONSTRAINT `fk_process_properties`
    FOREIGN KEY (`ppr_process_id`)
    REFERENCES `processes` (`pro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `query_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `query_types` (
  `qt_id` BIGINT NOT NULL,
  `qt_description` VARCHAR(255) NULL,
  PRIMARY KEY (`qt_id`));
  
CREATE UNIQUE INDEX IF NOT EXISTS `idx_query_types_uk`
  ON `query_types` (`qt_description` ASC);

-- -----------------------------------------------------
-- Table `queries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `queries` (
  `qu_id` BIGINT NOT NULL,
  `qu_query_type_id` BIGINT NOT NULL,
  `qu_query_date` DATETIME NOT NULL,
  `qu_keywords` MEDIUMTEXT NOT NULL,
  `qu_organism` VARCHAR(500) NULL,
  `qu_complete_query` TEXT NULL,
  `qu_matching_publications` INT(11) NULL,
  `qu_available_abstracts` INT(11) NULL DEFAULT NULL,
  `qu_query_name` VARCHAR(500) NULL DEFAULT NULL,
  `qu_active` TINYINT(1) NOT NULL DEFAULT 1,
  `qu_notes` TEXT NULL,
  PRIMARY KEY (`qu_id`));
  
CREATE INDEX IF NOT EXISTS `idx_queries_01`
  ON `queries` (`qu_active` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_queries_02` 
  ON `queries` (`qu_query_type_id` ASC);

ALTER TABLE `queries` ADD
  CONSTRAINT `fk_queries`
    FOREIGN KEY (`qu_query_type_id`)
    REFERENCES `query_types` (`qt_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `query_has_publications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `query_has_publications` (
  `qhb_query_id` BIGINT NOT NULL,
  `qhb_publication_id` BIGINT NOT NULL,
  `qhb_relevance` VARCHAR(20) NULL,
  PRIMARY KEY (`qhb_query_id`, `qhb_publication_id`));
  
CREATE INDEX IF NOT EXISTS `idx_query_has_publications_01`
  ON `query_has_publications` (`qhb_query_id` ASC);
 
CREATE INDEX IF NOT EXISTS `idx_query_has_publications_02`
  ON `query_has_publications` (`qhb_publication_id` ASC);

ALTER TABLE `query_has_publications` ADD
  CONSTRAINT `ck_qhb_relevance`
  	 CHECK ( `qhb_relevance` in ('Relevant','Related','Irrelevant'));
  
ALTER TABLE `query_has_publications` ADD
  CONSTRAINT `fk_query_has_publications_01`
    FOREIGN KEY (`qhb_publication_id`)
    REFERENCES `publications` (`pub_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `query_has_publications` ADD
  CONSTRAINT `fk_query_has_publications_02`
    FOREIGN KEY (`qhb_query_id`)
    REFERENCES `queries` (`qu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `query_properties`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `query_properties` (
  `qpr_query_id` BIGINT NOT NULL,
  `qpr_prop_key` VARCHAR(255) NOT NULL,
  `qpr_prop_value` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`qpr_query_id`, `qpr_prop_key`));
  
CREATE INDEX IF NOT EXISTS `idx_query_properties_fk`
 ON `query_properties` (`qpr_query_id` ASC);
 
ALTER TABLE `query_properties` ADD
  CONSTRAINT `fk_query_properties`
    FOREIGN KEY (`qpr_query_id`)
    REFERENCES `queries` (`qu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `resource_element_relation_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resource_element_relation_types` (
  `rer_id` BIGINT NOT NULL,
  `rer_relation_type` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`rer_id`));
  
CREATE UNIQUE INDEX IF NOT EXISTS `idx_resource_element_relation_types_uk` 
  ON `resource_element_relation_types` (`rer_relation_type` ASC);

-- -----------------------------------------------------
-- Table `sources`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sources` (
  `sou_id` BIGINT NOT NULL,
  `sou_description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`sou_id`));

CREATE UNIQUE INDEX IF NOT EXISTS `idx_sources_uk` 
  ON `sources` (`sou_description` ASC);

-- -----------------------------------------------------
-- Table `resource_element_extenal_ids`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resource_element_extenal_ids` (
  `rele_resource_element_id` BIGINT NOT NULL,
  `rele_source_id` BIGINT NOT NULL,
  `rele_external_id` VARCHAR(200) NOT NULL,
  `rele_active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`rele_resource_element_id`, `rele_source_id`, `rele_external_id`));
  
CREATE INDEX IF NOT EXISTS `idx_resource_element_extenal_ids_01`
  ON `resource_element_extenal_ids` (`rele_resource_element_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_resource_element_extenal_ids_02`
  ON `resource_element_extenal_ids` (`rele_active` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_resource_element_extenal_ids_03`
  ON `resource_element_extenal_ids` (`rele_source_id` ASC);
  
ALTER TABLE `resource_element_extenal_ids` ADD
  CONSTRAINT `fk_resource_element_extenal_ids_01`
    FOREIGN KEY (`rele_resource_element_id`)
    REFERENCES `resource_elements` (`res_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `resource_element_extenal_ids` ADD
  CONSTRAINT `fk_resource_element_extenal_ids_02`
    FOREIGN KEY (`rele_source_id`)
    REFERENCES `sources` (`sou_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `resource_element_relations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resource_element_relations` (
  `rel_resource_element_id_a` BIGINT NOT NULL,
  `rel_resource_element_id_b` BIGINT NOT NULL,
  `rel_relation_type_id` BIGINT NOT NULL,
  PRIMARY KEY (`rel_resource_element_id_b`, `rel_resource_element_id_a`));
  
CREATE INDEX IF NOT EXISTS `idx_resource_element_relations_01`
  ON `resource_element_relations` (`rel_relation_type_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_resource_element_relations_02`
  ON `resource_element_relations` (`rel_resource_element_id_b` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_resource_element_relations_03`
  ON `resource_element_relations` (`rel_resource_element_id_a` ASC);
  
ALTER TABLE `resource_element_relations` ADD
  CONSTRAINT `fk_resource_element_relations_01`
    FOREIGN KEY (`rel_relation_type_id`)
    REFERENCES `resource_element_relation_types` (`rer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `resource_element_relations` ADD
  CONSTRAINT `fk_resource_element_relations_02`
    FOREIGN KEY (`rel_resource_element_id_a`)
    REFERENCES `resource_elements` (`res_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `resource_element_relations` ADD
  CONSTRAINT `fk_resource_element_relations_03`
    FOREIGN KEY (`rel_resource_element_id_b`)
    REFERENCES `resource_elements` (`res_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `synonyms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `synonyms` (
  `syn_resource_element_id` BIGINT NOT NULL,
  `syn_synonym` VARCHAR(500) NOT NULL,
  `syn_active` TINYINT(1) NOT NULL DEFAULT 1);
  
CREATE INDEX IF NOT EXISTS `idx_synonyms_01` 
  ON `synonyms` (`syn_active` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_synonyms_02`
  ON `synonyms` (`syn_resource_element_id` ASC);

ALTER TABLE `synonyms` ADD
  CONSTRAINT `fk_synonyms`
    FOREIGN KEY (`syn_resource_element_id`)
    REFERENCES `resource_elements` (`res_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `annotation_properties`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `annotation_properties` (
  `anp_id` BIGINT NOT NULL,
  `anp_prop_key` VARCHAR(255) NOT NULL,
  `anp_prop_value` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`anp_prop_key`, `anp_id`));
  
CREATE INDEX IF NOT EXISTS `idx_annotation_properties_fk`
  ON `annotation_properties` (`anp_id` ASC);

ALTER TABLE `annotation_properties` ADD
  CONSTRAINT `fk_annotation_properties`
    FOREIGN KEY (`anp_id`)
    REFERENCES `annotations` (`ann_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `publication_fields`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publication_fields` (
  `pfl_publication_id` BIGINT NOT NULL,
  `pfl_field` VARCHAR(255) NOT NULL,
  `pfl_field_start` BIGINT NOT NULL,
  `pfl_field_end` BIGINT NOT NULL,
  `pfl_text` VARCHAR(20) NOT NULL DEFAULT 'abstracttext',
  PRIMARY KEY (`pfl_field`, `pfl_publication_id`));
  
CREATE INDEX IF NOT EXISTS `idx_publication_fields_fk` 
  ON `publication_fields` (`pfl_publication_id` ASC);

ALTER TABLE `publication_fields` ADD
  CONSTRAINT `ck_pfl_text`
  	CHECK ( `pfl_text` in ('abstracttext', 'fulltext'));
  
ALTER TABLE `publication_fields` ADD
  CONSTRAINT `fk_publication_fields`
    FOREIGN KEY (`pfl_publication_id`)
    REFERENCES `publications` (`pub_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `annotation_sides`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `annotation_sides` (
  `as_annotation_id` BIGINT NOT NULL,
  `as_annotation_sub_id` BIGINT NOT NULL,
  `as_annot_side_type` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`as_annotation_id`, `as_annotation_sub_id`));
  
CREATE INDEX IF NOT EXISTS `idx_annotation_sides_01`
  ON `annotation_sides` (`as_annotation_id` ASC);

CREATE INDEX IF NOT EXISTS `idx_annotation_sides_02`
  ON `annotation_sides` (`as_annotation_sub_id` ASC);

ALTER TABLE `annotation_sides` ADD
  CONSTRAINT `ck_as_annot_side_type`
  	CHECK ( `as_annot_side_type` in ('left','right'));  
  
ALTER TABLE `annotation_sides` ADD
  CONSTRAINT `fk_annotation_sides_01`
    FOREIGN KEY (`as_annotation_id`)
    REFERENCES `annotations` (`ann_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
   
ALTER TABLE `annotation_sides` ADD
  CONSTRAINT `fk_annotation_sides_02`
    FOREIGN KEY (`as_annotation_sub_id`)
    REFERENCES `annotations` (`ann_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `hyper_link_menus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hyper_link_menus` (
  `hyl_id` BIGINT NOT NULL,
  `hyl_menu_name` VARCHAR(45) NOT NULL,
  `hyl_hyper_link_menu` VARCHAR(250) NOT NULL,
  `hyl_icon` BLOB NULL,
  `hyl_menu_level` VARCHAR(2) NOT NULL DEFAULT 1,
  PRIMARY KEY (`hyl_id`));

-- -----------------------------------------------------
-- Table `hyper_link_submenus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hyper_link_submenus` (
  `hyli_hyper_link_menu_id` BIGINT NOT NULL,
  `hyli_hyper_link_submenu_id` BIGINT NOT NULL,
  PRIMARY KEY (`hyli_hyper_link_menu_id`, `hyli_hyper_link_submenu_id`));
  
CREATE INDEX IF NOT EXISTS `idx_hyper_link_submenus_01`
  ON `hyper_link_submenus` (`hyli_hyper_link_submenu_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_hyper_link_submenus_02`
  ON `hyper_link_submenus` (`hyli_hyper_link_menu_id` ASC);

ALTER TABLE `hyper_link_menus` ADD
  CONSTRAINT `ck_hyl_menu_level`
  	CHECK ( `hyl_menu_level` in ('1','2'));   
  
ALTER TABLE `hyper_link_submenus` ADD
  CONSTRAINT `fk_hyper_link_submenus_01`
    FOREIGN KEY (`hyli_hyper_link_submenu_id`)
    REFERENCES `hyper_link_menus` (`hyl_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `hyper_link_submenus` ADD
  CONSTRAINT `fk_hyper_link_submenus_02`
    FOREIGN KEY (`hyli_hyper_link_menu_id`)
    REFERENCES `hyper_link_menus` (`hyl_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `hyper_link_menu_source_association`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hyper_link_menu_source_association` (
  `hlm_hyper_links_menu_id` BIGINT NOT NULL,
  `hlm_source_id` BIGINT NOT NULL,
  PRIMARY KEY (`hlm_hyper_links_menu_id`, `hlm_source_id`));
  
CREATE INDEX IF NOT EXISTS `idx_hyper_link_menu_source_association_01`
  ON `hyper_link_menu_source_association` (`hlm_source_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_hyper_link_menu_source_association_02`
  ON `hyper_link_menu_source_association` (`hlm_hyper_links_menu_id` ASC);

ALTER TABLE `hyper_link_menu_source_association` ADD
  CONSTRAINT `fk_hyper_link_menu_source_association_01`
    FOREIGN KEY (`hlm_source_id`)
    REFERENCES `sources` (`sou_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `hyper_link_menu_source_association` ADD
  CONSTRAINT `fk_hyper_link_menu_source_association_02`
    FOREIGN KEY (`hlm_hyper_links_menu_id`)
    REFERENCES `hyper_link_menus` (`hyl_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `cluster_processes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cluster_processes` (
  `clp_id` BIGINT NOT NULL,
  `clp_description` VARCHAR(255) NULL,
  `clp_active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`clp_id`));


-- -----------------------------------------------------
-- Table `query_has_cluster_processes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `query_has_cluster_processes` (
  `qhc_query_id` BIGINT NOT NULL,
  `qhc_cluster_process_id` BIGINT NOT NULL,
  PRIMARY KEY (`qhc_query_id`, `qhc_cluster_process_id`));
  
CREATE INDEX IF NOT EXISTS `idx_query_has_cluster_processes_01`
  ON `query_has_cluster_processes` (`qhc_cluster_process_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_query_has_cluster_processes_02`
  ON `query_has_cluster_processes` (`qhc_query_id` ASC);

ALTER TABLE `query_has_cluster_processes` ADD
  CONSTRAINT `fk_query_has_cluster_processes_01`
    FOREIGN KEY (`qhc_query_id`)
    REFERENCES `queries` (`qu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `query_has_cluster_processes` ADD
  CONSTRAINT `fk_query_has_cluster_processes_02`
    FOREIGN KEY (`qhc_cluster_process_id`)
    REFERENCES `cluster_processes` (`clp_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `cluster_properties`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cluster_properties` (
  `clpr_cluster_process_id` BIGINT NOT NULL,
  `clpr_prop_key` VARCHAR(255) NOT NULL,
  `clpr_prop_value` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`clpr_cluster_process_id`, `clpr_prop_key`));
  
CREATE INDEX IF NOT EXISTS `idx_cluster_properties_fk` 
  ON `cluster_properties` (`clpr_cluster_process_id` ASC);

ALTER TABLE `cluster_properties` ADD
  CONSTRAINT `fk_cluster_properties`
    FOREIGN KEY (`clpr_cluster_process_id`)
    REFERENCES `cluster_processes` (`clp_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `cluster_labels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cluster_labels` (
  `cll_cluster_label_id` BIGINT NOT NULL,
  `cll_cluster_label_name` VARCHAR(255) NOT NULL,
  `cll_score` DOUBLE NULL DEFAULT 0.0,
  PRIMARY KEY (`cll_cluster_label_id`));

-- -----------------------------------------------------
-- Table `cluster_process_has_labels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cluster_process_has_labels` (
  `cph_cluster_process_id` BIGINT NOT NULL,
  `cph_cluster_label_id` BIGINT NOT NULL,
  PRIMARY KEY (`cph_cluster_process_id`, `cph_cluster_label_id`));
  
CREATE INDEX IF NOT EXISTS `idx_cluster_process_has_labels_01`
  ON `cluster_process_has_labels` (`cph_cluster_label_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_cluster_process_has_labels_02`
  ON `cluster_process_has_labels` (`cph_cluster_process_id` ASC);

ALTER TABLE `cluster_process_has_labels` ADD
  CONSTRAINT `fk_cluster_process_has_labels_01`
    FOREIGN KEY (`cph_cluster_label_id`)
    REFERENCES `cluster_labels` (`cll_cluster_label_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `cluster_process_has_labels` ADD
  CONSTRAINT `fk_cluster_process_has_labels_02`
    FOREIGN KEY (`cph_cluster_process_id`)
    REFERENCES `cluster_processes` (`clp_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `cluster_label_publications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cluster_label_publications` (
  `clp_cluster_label_id` BIGINT NOT NULL,
  `clp_query_id` BIGINT NOT NULL,
  `clp_publication_id` BIGINT NOT NULL,
  PRIMARY KEY (`clp_cluster_label_id`, `clp_query_id`, `clp_publication_id`));
  
CREATE INDEX IF NOT EXISTS `idx_cluster_label_publications_01`
  ON `cluster_label_publications` (`clp_query_id` ASC, `clp_publication_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_cluster_label_publications_02`
  ON `cluster_label_publications` (`clp_cluster_label_id` ASC);
  
ALTER TABLE `cluster_label_publications` ADD
  CONSTRAINT `fk_cluster_label_publications_01`
    FOREIGN KEY (`clp_cluster_label_id`)
    REFERENCES `cluster_labels` (`cll_cluster_label_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `cluster_label_publications` ADD
  CONSTRAINT `fk_cluster_label_publications_02`
    FOREIGN KEY (`clp_query_id` , `clp_publication_id`)
    REFERENCES `query_has_publications` (`qhb_query_id` , `qhb_publication_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `versions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `versions` (
  `ver_version` BIGINT NOT NULL,
  `ver_version_date` TIMESTAMP NOT NULL,
  `ver_notes` VARCHAR(500) NULL,
  PRIMARY KEY (`ver_version`));

-- -----------------------------------------------------
-- Table `auth_groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auth_groups` (
  `ag_id` BIGINT NOT NULL AUTO_INCREMENT,
  `ag_description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ag_id`));
  
CREATE UNIQUE INDEX IF NOT EXISTS `idx_auth_groups_uk` 
  ON `auth_groups` (`ag_description` ASC);

-- -----------------------------------------------------
-- Table `auth_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auth_users` (
  `au_id` BIGINT NOT NULL,
  `au_group_id` BIGINT NOT NULL,
  `au_username` VARCHAR(255) NOT NULL,
  `au_password` VARCHAR(255) NOT NULL,
  `au_fullname` VARCHAR(255) NOT NULL,
  `au_email` VARCHAR(255) NOT NULL,
  `au_phone` INT NULL,
  `au_address` VARCHAR(255) NULL,
  `au_zip_code` VARCHAR(16) NULL,
  `au_location` VARCHAR(255) NULL,
   `au_prefer_language` VARCHAR(10) DEFAULT 'en',
  `au_avatar` BLOB,
  `au_active` TINYINT(1) DEFAULT 1,
  PRIMARY KEY (`au_id`));
  
CREATE INDEX IF NOT EXISTS `idx_auth_users_01` 
  ON `auth_users` (`au_group_id` ASC);
 
CREATE UNIQUE INDEX IF NOT EXISTS `idx_auth_users_02`
  ON `auth_users` (`au_username` ASC);

CREATE UNIQUE INDEX IF NOT EXISTS `idx_auth_users_03` 
  ON `auth_users` (`au_email` ASC);

ALTER TABLE `auth_users` ADD
  CONSTRAINT `fk_auth_users`
    FOREIGN KEY (`au_group_id`)
    REFERENCES `auth_groups` (`ag_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `annotation_logs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `annotation_logs` (
  `alo_id` BIGINT NOT NULL,
  `alo_user_id` BIGINT NOT NULL,
  `alo_annotation_id` BIGINT NULL,
  `alo_corpus_id` BIGINT NOT NULL,
  `alo_process_id` BIGINT NOT NULL,
  `alo_publication_id` BIGINT NOT NULL,
  `alo_annot_log_type` VARCHAR(20) NOT NULL,
  `alo_annot_old` VARCHAR(300) NOT NULL DEFAULT '\"\"',
  `alo_annot_new` VARCHAR(300) NOT NULL,
  `alo_notes` TEXT NULL,
  `alo_date` DATETIME NOT NULL,
  PRIMARY KEY (`alo_id`, `alo_user_id`));
  
CREATE INDEX IF NOT EXISTS `idx_annotation_logs_01`
  ON `annotation_logs` (`alo_publication_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_annotation_logs_02`
  ON `annotation_logs` (`alo_process_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_annotation_logs_03`
  ON `annotation_logs` (`alo_corpus_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_annotation_logs_04`
  ON `annotation_logs` (`alo_annotation_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_annotation_logs_05`
  ON `annotation_logs` (`alo_user_id` ASC);
  
ALTER TABLE `annotation_logs` ADD
  CONSTRAINT `ck_alo_annot_log_type`
  	CHECK ( `alo_annot_log_type` in ('ENTITYADD','ENTITYREMOVE','ENTITYUPDATE','RELATIONADD','RELATIONREMOVE','RELATIONUPDATE','ENTITYVALIDATED','RELATIONVALIDATED'));  
  
ALTER TABLE `annotation_logs` ADD
  CONSTRAINT `fk_annotation_logs_01`
    FOREIGN KEY (`alo_publication_id`)
    REFERENCES `publications` (`pub_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `annotation_logs` ADD
  CONSTRAINT `fk_annotation_logs_02`
    FOREIGN KEY (`alo_process_id`)
    REFERENCES `processes` (`pro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `annotation_logs` ADD
  CONSTRAINT `fk_annotation_logs_03`
    FOREIGN KEY (`alo_corpus_id`)
    REFERENCES `corpus` (`crp_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `annotation_logs` ADD
  CONSTRAINT `fk_annotation_logs_04`
    FOREIGN KEY (`alo_annotation_id`)
    REFERENCES `annotations` (`ann_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `annotation_logs` ADD
  CONSTRAINT `fk_annotation_logs_05`
    FOREIGN KEY (`alo_user_id`)
    REFERENCES `auth_users` (`au_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `auth_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auth_roles` (
  `ar_id` BIGINT NOT NULL AUTO_INCREMENT,
  `ar_role_code` VARCHAR(128) NOT NULL,
  `ar_role_description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ar_id`));
  
CREATE UNIQUE INDEX IF NOT EXISTS `idx_auth_roles_uk`
  ON `auth_roles` (`ar_role_code` ASC);

-- -----------------------------------------------------
-- Table `auth_group_has_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auth_group_has_roles` (
  `agr_group_id` BIGINT NOT NULL,
  `agr_role_id` BIGINT NOT NULL,
  PRIMARY KEY (`agr_group_id`, `agr_role_id`));
  
CREATE INDEX IF NOT EXISTS `idx_auth_group_has_roles_01`
  ON `auth_group_has_roles` (`agr_role_id` ASC);
  
CREATE INDEX IF NOT EXISTS `idx_auth_group_has_roles_02`
  ON `auth_group_has_roles` (`agr_group_id` ASC);
  
ALTER TABLE `auth_group_has_roles` ADD
  CONSTRAINT `fk_auth_group_has_roles_01`
    FOREIGN KEY (`agr_group_id`)
    REFERENCES `auth_groups` (`ag_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `auth_group_has_roles` ADD
  CONSTRAINT `fk_auth_group_has_roles_02`
    FOREIGN KEY (`agr_role_id`)
    REFERENCES `auth_roles` (`ar_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `auth_user_logs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auth_user_logs` (
  `aul_id` BIGINT NOT NULL AUTO_INCREMENT,
  `aul_user_id` BIGINT NOT NULL,
  `aul_create_date` DATETIME NOT NULL,
  `aul_operation` VARCHAR(10) NOT NULL,
  `aul_table_changed` VARCHAR(128) NOT NULL,
  `aul_sql_command` MEDIUMTEXT NULL,
  `aul_notes` MEDIUMTEXT NULL,
  PRIMARY KEY (`aul_id`));
  
CREATE INDEX IF NOT EXISTS `idx_auth_user_logs_fk`
  ON `auth_user_logs` (`aul_user_id` ASC);

ALTER TABLE `auth_user_logs` ADD
  CONSTRAINT `ck_aul_operation`
  	CHECK ( `aul_operation` in ('create','update','delete'));  
  
ALTER TABLE `auth_user_logs` ADD
  CONSTRAINT `fk_auth_user_logs`
    FOREIGN KEY (`aul_user_id`)
    REFERENCES `auth_users` (`au_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `auth_user_data_objects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auth_user_data_objects` (
  `audo_user_id` BIGINT NOT NULL,
  `audo_uid_resource` BIGINT NOT NULL,
  `audo_type_resource` VARCHAR(20) NOT NULL,
  `audo_permission` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`audo_user_id`, `audo_uid_resource`, `audo_type_resource`));
  
CREATE INDEX IF NOT EXISTS `idx_auth_user_data_objects_fk`
  ON `auth_user_data_objects` (`audo_user_id` ASC);

ALTER TABLE `auth_user_data_objects` ADD
  CONSTRAINT `ck_audo_type_resource`
  	CHECK ( `audo_type_resource` in ('queries','resources','corpus','processes')); 

ALTER TABLE `auth_user_data_objects` ADD
  CONSTRAINT `ck_audo_permission`
  	CHECK ( `audo_permission` in ('owner','read_write','read')); 
  
ALTER TABLE `auth_user_data_objects` ADD
  CONSTRAINT `fk_auth_user_data_objects`
    FOREIGN KEY (`audo_user_id`)
    REFERENCES `auth_users` (`au_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `publication_labels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publication_labels` (
  `pla_id` BIGINT NOT NULL,
  `pla_description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`pla_id`));

-- -----------------------------------------------------
-- Table `publication_has_labels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publication_has_labels` (
  `php_publication_id` BIGINT NOT NULL,
  `php_publication_label_id` BIGINT NOT NULL,
  PRIMARY KEY (`php_publication_id`, `php_publication_label_id`));
  
CREATE INDEX IF NOT EXISTS `idx_publications_has_publication_labels_01`
  ON `publication_has_labels` (`php_publication_label_id` ASC);
	
CREATE INDEX IF NOT EXISTS `idx_publications_has_publication_labels_02`
  ON `publication_has_labels` (`php_publication_id` ASC);
  
ALTER TABLE `publication_has_labels` ADD
  CONSTRAINT `fk_publications_has_publication_labels_01`
    FOREIGN KEY (`php_publication_id`)
    REFERENCES `publications` (`pub_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `publication_has_labels` ADD
  CONSTRAINT `fk_publications_has_publication_labels_02`
    FOREIGN KEY (`php_publication_label_id`)
    REFERENCES `publication_labels` (`pla_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `publication_sources`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publication_sources` (
  `pss_id` BIGINT NOT NULL,
  `pss_description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`pss_id`));
  
CREATE UNIQUE INDEX IF NOT EXISTS `idx_publication_sources_uk`
  ON `publication_sources` (`pss_description` ASC);

-- -----------------------------------------------------
-- Table `publication_has_sources`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `publication_has_sources` (
  `phps_publication_id` BIGINT NOT NULL,
  `phps_publication_source_id` BIGINT NOT NULL,
  `phps_publication_source_internal_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`phps_publication_source_id`, `phps_publication_source_internal_id`, `phps_publication_id`));
  
CREATE INDEX IF NOT EXISTS `idx_publications_has_publications_source_01` 
  ON `publication_has_sources` (`phps_publication_source_id` ASC);
  
CREATE INDEX  IF NOT EXISTS `idx_publications_has_publications_source_02` 
  ON `publication_has_sources` (`phps_publication_id` ASC);
  
ALTER TABLE `publication_has_sources` ADD
  CONSTRAINT `fk_publications_has_publications_source_01`
    FOREIGN KEY (`phps_publication_id`)
    REFERENCES `publications` (`pub_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `publication_has_sources` ADD
  CONSTRAINT `fk_publications_has_publications_source_02`
    FOREIGN KEY (`phps_publication_source_id`)
    REFERENCES `publication_sources` (`pss_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `auth_user_settings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `auth_user_settings` (
  `aus_user_id` BIGINT NOT NULL,
  `aus_prop_key` VARCHAR(255) NOT NULL,
  `aus_prop_value` LONGTEXT NOT NULL,
  PRIMARY KEY (`aus_user_id`, `aus_prop_key`));
  
CREATE INDEX IF NOT EXISTS `idx_auth_user_settings_fk`
  ON `auth_user_settings` (`aus_user_id` ASC);
  
ALTER TABLE `auth_user_settings` ADD
  CONSTRAINT `fk_auth_user_settings`
    FOREIGN KEY (`aus_user_id`)
    REFERENCES `auth_users` (`au_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Table `data_process_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `data_process_status` (
  `dps_data_object_id` BIGINT NOT NULL,
  `dps_type_resource` VARCHAR(20) NOT NULL,
  `dps_status` VARCHAR(20) NOT NULL,
  `dps_report` LONGTEXT NULL,
  `dps_progress` FLOAT NULL,
  `dps_create_date` DATETIME NULL,
  `dps_update_date` DATETIME NULL,
  PRIMARY KEY (`dps_data_object_id`, `dps_type_resource`));

ALTER TABLE `data_process_status` ADD
  CONSTRAINT `ck_dps_type_resource`
  	CHECK ( `dps_type_resource` in ('queries','resources','corpus','ner','re')); 
  	
ALTER TABLE `data_process_status` ADD
  CONSTRAINT `ck_dps_status`
  	CHECK ( `dps_status` in ('start','running','finished','stop','error')); 
  
-- -----------------------------------------------------
-- Table `data_process_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `corpus_has_publications_has_processes` (
  `chphp_corpus_id` BIGINT NOT NULL,
  `chphp_publication_id` BIGINT NOT NULL,
  `chphp_processes_id` BIGINT NOT NULL,
  `chphp_create_date` DATETIME NULL,
  `chphp_update_date` DATETIME NULL,
  `chphp_processes_version` INT(3) NOT NULL DEFAULT 1,
  PRIMARY KEY (`chphp_corpus_id`, `chphp_publication_id`, `chphp_processes_id`));
  
CREATE INDEX IF NOT EXISTS `fk_corpus_has_publications_has_processes_processes1_idx`
  ON `corpus_has_publications_has_processes` (`chphp_processes_id` ASC);
  
CREATE INDEX IF NOT EXISTS `fk_corpus_has_publications_has_processes_corpus_has_publica_idx`
  ON `corpus_has_publications_has_processes` (`chphp_corpus_id` ASC, `chphp_publication_id` ASC);
  
ALTER TABLE `corpus_has_publications_has_processes` ADD
  CONSTRAINT `fk_corpus_has_publications_has_processes_corpus_has_publicati1`
    FOREIGN KEY (`chphp_corpus_id` , `chphp_publication_id`)
    REFERENCES `corpus_has_publications` (`chp_corpus_id` , `chp_publication_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE `corpus_has_publications_has_processes` ADD
  CONSTRAINT `fk_corpus_has_publications_has_processes_processes1`
    FOREIGN KEY (`chphp_processes_id`)
    REFERENCES `processes` (`pro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- -----------------------------------------------------
-- Placeholder table for view `resources_class_stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resources_class_stats` (`res_resource_id` INT, `res_class_id` INT, `numberClasses` INT);

-- -----------------------------------------------------
-- Placeholder table for view `resources_class_synonyms_count`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resources_class_synonyms_count` (`res_resource_id` INT, `res_class_id` INT, `numberSynonyms` INT);

-- -----------------------------------------------------
-- Placeholder table for view `resources_total_elements`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resources_total_elements` (`res_resource_id` INT, `numberElements` INT);

-- -----------------------------------------------------
-- Placeholder table for view `resources_total_elements_synonyms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resources_total_elements_synonyms` (`res_resource_id` INT, `numberSynonyms` INT);

-- -----------------------------------------------------
-- Placeholder table for view `process_general_resume_stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `process_general_resume_stats` (`ann_process_id` INT, `ann_annot_type` INT, `numberType` INT);

-- -----------------------------------------------------
-- Placeholder table for view `process_entities_Class_stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `process_entities_Class_stats` (`ann_process_id` INT, `cla_name` INT, `numberClasses` INT);

-- -----------------------------------------------------
-- Placeholder table for view `process_entities_stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `process_entities_stats` (`ann_process_id` INT, `ann_element` INT, `numberElements` INT, `cla_name` INT);

-- -----------------------------------------------------
-- Placeholder table for view `process_clues_stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `process_clues_stats` (`ann_process_id` INT, `ann_clue` INT, `numberClues` INT, `ann_classification_re` INT);

-- -----------------------------------------------------
-- View `resources_class_stats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resources_class_stats`;

CREATE  OR REPLACE VIEW resources_class_stats AS SELECT res_resource_id,res_class_id,COUNT(res_class_id) as numberClasses FROM resource_elements WHERE res_active=1
GROUP BY res_resource_id,res_class_id
;

-- -----------------------------------------------------
-- View `resources_class_synonyms_count`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resources_class_synonyms_count`;

CREATE  OR REPLACE VIEW resources_class_synonyms_count AS SELECT t.res_resource_id,t.res_class_id,COUNT(s.syn_resource_element_id) as numberSynonyms
FROM resource_elements t JOIN synonyms s ON t.res_id = s.syn_resource_element_id
WHERE t.res_active=1 AND s.syn_active=1
GROUP BY t.res_resource_id,t.res_class_id

;

-- -----------------------------------------------------
-- View `resources_total_elements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resources_total_elements`;

CREATE  OR REPLACE VIEW resources_total_elements AS SELECT res_resource_id,COUNT(res_id) as numberElements FROM resource_elements WHERE res_active=1
GROUP BY res_resource_id

;

-- -----------------------------------------------------
-- View `resources_total_elements_synonyms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resources_total_elements_synonyms`;

CREATE  OR REPLACE VIEW resources_total_elements_synonyms AS SELECT t.res_resource_id,COUNT(s.syn_resource_element_id) as numberSynonyms
FROM resource_elements t JOIN synonyms s ON t.res_id = s.syn_resource_element_id
WHERE t.res_active=1 AND s.syn_active=1
GROUP BY t.res_resource_id;

-- -----------------------------------------------------
-- View `process_general_resume_stats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `process_general_resume_stats`;

CREATE  OR REPLACE VIEW process_general_resume_stats AS SELECT ann_process_id,ann_annot_type,COUNT(ann_annot_type) as numberType FROM annotations 
WHERE ann_active=1
GROUP BY ann_process_id,ann_annot_type 
;

-- -----------------------------------------------------
-- View `process_entities_Class_stats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `process_entities_Class_stats`;

CREATE  OR REPLACE VIEW process_entities_Class_stats AS SELECT ann_process_id,cl.cla_name,COUNT(ann_class_id) numberClasses FROM annotations 
JOIN classes AS cl ON cl.cla_id=annotations.ann_class_id
WHERE ann_active=1 AND ann_annot_type='ner'
GROUP BY ann_process_id,ann_class_id
;

-- -----------------------------------------------------
-- View `process_entities_stats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `process_entities_stats`;

CREATE  OR REPLACE VIEW process_entities_stats AS SELECT ann_process_id,ann_element,COUNT(ann_element) as numberElements,cl.cla_name FROM annotations 
JOIN classes AS cl ON cl.cla_id=annotations.ann_class_id
WHERE ann_active=1 AND ann_annot_type='ner'
GROUP BY ann_process_id,ann_element,ann_class_id

;

-- -----------------------------------------------------
-- View `process_clues_stats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `process_clues_stats`;

CREATE  OR REPLACE VIEW process_clues_stats AS SELECT ann_process_id,ann_clue,COUNT(ann_clue) as numberClues FROM annotations 
WHERE ann_active=1 AND ann_annot_type='re'
GROUP BY ann_process_id,ann_clue

;

-- -----------------------------------------------------
-- Data for table `sources`
-- -----------------------------------------------------
INSERT INTO `sources` (`sou_id`, `sou_description`) VALUES (1, 'Chebi');
INSERT INTO `sources` (`sou_id`, `sou_description`) VALUES (2, 'Entrez Gene');
INSERT INTO `sources` (`sou_id`, `sou_description`) VALUES (3, 'BioCyc');
INSERT INTO `sources` (`sou_id`, `sou_description`) VALUES (4, 'Gene Ontology');
INSERT INTO `sources` (`sou_id`, `sou_description`) VALUES (5, 'UniProt');
INSERT INTO `sources` (`sou_id`, `sou_description`) VALUES (6, 'Ncbi Taxonomy');
INSERT INTO `sources` (`sou_id`, `sou_description`) VALUES (7, 'Brenda');
INSERT INTO `sources` (`sou_id`, `sou_description`) VALUES (8, 'Kegg');
INSERT INTO `sources` (`sou_id`, `sou_description`) VALUES (9, 'Chemical Abstracts Service');
INSERT INTO `sources` (`sou_id`, `sou_description`) VALUES (10, 'GO');

-- -----------------------------------------------------
-- Data for table `hyper_link_menus`
-- -----------------------------------------------------

INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (1, 'ChEBI', 'http://www.ebi.ac.uk/chebi/searchFreeText.do?searchString=#', x'89504E470D0A1A0A0000000D4948445200000033000000220803000000C5F51126000000017352474200AECE1CE9000000C0504C5445AC295576879BB0446AC26E8CBAC7D5B4B4B4D4E2F36D5F6BE9F3FA898D96C8D7E9899EB531559DD293ABE2EBF747668AFBFCFE777682DEB6C8C8C7C7E6CAD7AEBBCA9F093BD7A7BB9AADC3CDDCEF072C9AEBE5E915161798A0ABD9E5F416396BF1F5FA34486AD5D5D5AAA8AAF5EBF4EBD4E05171C3DDE3E9733A52F5F9FC7894D6A0ABB87C1C3EC8D4DFA6B2C0F1F1F293ACE1DDE8F5DFDEDFBB587BDFEEFFFEF6FDBCBCBCB3B3B0BECBDCC9809BC0CFE3565656555555003061D0DFF2FFFFFFC69F75AA000000097048597300000B1300000B1301009A9C180000000774494D4507DC030D0B240591352E310000002169545874436F6D6D656E740000000000437265617465642077697468205468652047494D506DB097000000026E4944415438CB95956977AA30108641B6443410D7A24029D60511BD544BA54AF8FFFFAA1350E1F4523DBE1F38789827B3646614ECF3B3B285535E574048AC32352628C8FF92269CAB1F2698578A91D9CC9C6E8C895CF64BAA6BDE6748E543DC16048898F718B33A7E78B01C40BCA5BC24F11DC62415237B72085E5EB22C0B50C6BF1AF02AE7F0107339BB31A896BDE5509E4BC60D7F31598D392E1D84C4768988EE7A110751267B01F2C0EE8D33469E17AF37E623500F16197E174CA85BEB8E8608301E30106223E323765051488B6CE8FADFA2A31DBE8089022FCB3963E82F10A1FE5A8B8D442E56656372640C6FC5CE6231790BA3EC05AC8292D179027AAE578CE7B248A4C6A233D9B2B08D9D644687650D96A864FEAF01823BD1BF21A289C3B499FFDE57121ADD6A5D635EAB5AC78C45C60290897DDEED72D34FBE9299E31242DC42A4E14E55268BF604246F357F3A9DFA8AA22418EEE8D2766A03C38E8A688B86011D83FBBEEFF715E58B3370BB61413531E2E180CB3369D2EFF793D24F1B63A9A5FDC1A8D8BACE01056B20B89F231E0CBA92003DDE145B1C39D82999829871CAFEC8777360461A8B1B18549B338A315ECD122AB3F960D04B811931D4C078B5A6C6B3F1B8AB5168D75E6F3C96F6C0585ED3FC54C3E378D33964B15F0D992449FB16201BB771E6BC1BF3FE090C3F5EB35AFB564B00867A8DCC6DB0E5CFDD74DE033FC2E648054E8C5691D93CDBC1A5D64E8F6791F2988AC1F86EB3E0CF7D7099ED700C154825086A74B964F5E10E91BB69372DDC6C8625431EEE2A2B4DA15C3CF5D52543F3F14E74F6BC5AA36B64F777E275F7AE04EE6553228F76EF333BFEF9FF9293609F7EE97C3EDD97FD0366DF48E7EC632F8B0000000049454E44AE426082', '1');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (2, 'Ensembl', 'http://www.ensembl.org/Multi/Search/Results?q=#', x'89504E470D0A1A0A0000000D49484452000000120000001008030000002CD8DF6E000000017352474200AECE1CE9000001E9504C5445F7BDBDD62929BD0808BD4A4AF7CECED60808A50101940000840000A55A5AE742428C0000630808F7DEDEC60000520808EF9C9CAD0000730000633939D6E7F7B5D6FF9CBDFF7BADFF73A5F76BA5F784ADF7A5BDEFDFE8F7E76363630000A58C8CDEEFFF9CC6FF5A94F75A8CDE5284D64A7BCE4A7BC64A73C6527BD68CADDED631319C00007B00004A0808E7E7E7C6DEFF5A94EF5284DE4A73B5426BB54263AD4263A5426BAD4A73BD4273BD94A5C6C610106B0000846363BDDEFF73ADFF5A8CE739639C3152842942733952735A6B94637BA54A638CB500004A0000DED6D684B5FF395A9429426B5A6B84A5ADB5DEDEE794B5F7395A9C52637B7B5252639CF76B738CD6D6DE314A7B9CA5ADE7B5B5ADD6FF42526BC6C6D6E7EFFF7BB5FFEFA5A5BDD6FF31528C394A63E78484520000D6CECE4A6BB5DE6B6B6B4242425A8CE7E7EF94BDFF425273B5B5BDD64A4A5A0000C6B5B55273BD6A94DC6B9CF73963A5314A737B849CC6CECECE29295A29294A5A8463738C8494A5ADB5BDAD9C9C8C94A5CECED65A1010E7BDBD9C7B7B426BBD5A6B8CE79494DEDEDE6B7B9CCE6B6B8C4A4A526B9CAD3939CEADADC6CEDEADBDD6BD84848CB5F7B5CEEFBCCEEF638CDEA5C6F76B7B8CE7A5A55A84CE636B84CED6DEE75A5AA51010D6DEEFEF7B7B7B0808EFDEDE5A73A5294A73F1C91F190000000174524E530040E6D86600000001624B47440088051D48000000097048597300000B1300000B1301009A9C180000000774494D4507DC030D0B250F68FBF66E0000002169545874436F6D6D656E740000000000437265617465642077697468205468652047494D506DB097000000007B4944415418D363602003B071630871C385F40DD48DCD8C8C8142D650112F73335F7F06B54006168E608848887910903434F667484E81AB6150085173CA6560C82B8008A999D7FB434D1084D2E62D700B85E0427D204AB41FE116730B0BA9A9212AD31818DA604273BDCD1C16E90119DADC42E84E67E7CE27DEEB004F9512F7DFFC5CDA0000000049454E44AE426082', '1');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (3, 'NCBI', '', x'89504E470D0A1A0A0000000D4948445200000037000000120806000000FFBA46C3000004E54944415478DAD5575B535B5514CE7FF2416774D43A5E5A75D46A3BA2ED8CF532DAB19691D2D252CAAD4829D28252B4A24542C8050897D200095002840A13D39642842201420BE1D294040850C2CB727F2B3D674E9A70C2A33C2C48F665EDF5ADFDAD6FED689EFBBA9C243B985D4BDF5EB6D0CF357D641BB84FC78A2DA49CDF6DA6C19FE3252D64E973D3D2F22A6D6D6DC97641D7BDBBC1E9DA5CB4125A6730F3FE651A18F632D07AFB3D4ACA35F3A2FD19267A3545B7FBC085C35B34E70F527645277D78AE26EEA277D20D745EDB4559D73A123A7CF7AC91BE2ABC4E472E34D091FC8698F9D74EE8E8E8A566FABCA091BE29BA1E338F3D1705630A7476FE9FFC530B8F7F9C534B5F1636516A591B8F5FD4F7529ED6CE8997F6C2EFD1A2665E73F807336900CCDA3FBAA34CE454DCA4C6EE213A74DEACBA2EBFAA9B82AB6B3436BD107538ECF963957449EF60A69CFAC52A8FC3A7DD354EF7BDF3D4E3F2D0C8E41CFB58DF78C2EB001ACC1A9B9EA7DF9B06985960DCEC62406618921A10A5E59EF0D181AC5AD2A0DEBA84D3CC3F3A7604B0BCB19FBCBEC77430AB4E75DDCC42800134F50CC7CC9D2CB3121883DBC5F7A4DC3A065354ED885AF7597E3D9FF5DE59131B3ED7DD1C94E74BEBFAF80C245D2A9FA9593F0DB8BDB4EF8C212228A05DB1C141D9D73AE9935CB32AE5508F9B9BE198409EB5B1070BE4FCC7CB873FBB16F4BA373E434939910481399DCE7FE3FAF9AEC4422F2457D207191170D556973CF77D690BFB4FFFCD16892FDD489E878BD4EC700B8DA88A80930C35870CED11E28100CE5CB5D18FD5BD5471C3C9B73B31F348161EB524C0DC933EBEDDD1A9790AAD6D708B91E6506FAED169FA28B386F69F33D16638CCC955F387750FE696A8B2C5298FE9ADB7A97F788ADE38A997933F31B348A6F6417AE58402DCE1BC7ADE38343E4B738F02B41C5A1337B4C9F4915AC313F1BDA97B382125F9E6446DBC785CCB40908CC7C1553A9059CB735F1446C02199B83DF84E54162FA768A9F7B6870136D88738D9A839948992590057D3F1141C1A370A59020030A1F50DF20757182482041571F8BED3861DCBF0B46F895E4F8D6434F54A1B270B54850FB0E286F089CF50419C7BD9D0ABEA0F89FA6B70926381E22229DAD6BFF902EE8E3DE4356F8B3A8B02076581730C1ADBEF526E65972CBF92BD95A6E7205E4AD6EE181C0445120C18EA0EE7A08FA28E4029F8053531DE205458CD1FCA0531029072DC2480603F5E534816D35202870914B72ADF4531438ECBCCB712D69A64FEC00ABF7C94631516672491A2E8A1A2A815D0CD71C7A34A4D9C0F4199F2F9592C9473E879525BD92BC04150D02618DC9FE240C8F0DEB4C494439344205055B57568F6E8514A1191CCDC15C9348440A22D6E32B8B246ABA10DBE41B40AF42EA860A7739CCF83122229D8B747F15A32D8EEF03ED41B44077DEF96A02F806A8030ED572B3B3D94A77E2B68C0B83D344ABC04B65B076142DD161B631510809C235ECE6E54E2843F24193584FAF40B01820F49FA51A718C33C82471C88197BA432929AF890E76913571E9051DE4EA7AFDAE206FCBEA0454A692BAB1C44074F9F78EB902CDC18824DB9D21A3741C8F0A7DBBC72106041959D0AAB7BA262C10B056378FDE06986168579E52D622F9418E500FF9AEDB20F9A9618FB58D5F01C927A1CACAD7F44A6D4FFFE274F3C4306D00A244068C4A8B7ED6E6C578103672136BA5697A06B07BFFFDE3C65D8553F79FE03FD62ED576721BB0F0000000049454E44AE426082', '1');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (4, 'Gene', 'http://www.ncbi.nlm.nih.gov/sites/entrez?db=gene&cmd=Search&term=#', x'89504E470D0A1A0A0000000D494844520000001B0000001B0803000000BA0A0467000000017352474200AECE1CE900000180504C5445000000E0FF65E0FF63D9FF43D0FF18D0FF12CEFF11FFFFFDFDFFF9FBFFEAF7FFD9E9FF91DBFF48CFFF12CFFF0FCBFF00CDFF07CAFF00CCFF00CCFF02CDFF06D9FF41CCFF01F7FFD7E3FF72D5FF2CDDFF55F9FFE2FEFFF9D7FF39CEFF0ACDFF04CCFF03F6FFD0E8FF8CF1FFB7FEFEFFF7FFD6FDFFF4CAFD07DBFF4BEAFF93EBFF9BCDFF05F9FFE4F8FAFCF5FFCCF6FFD3E9FF8FD7FF36D1FF17EAFF98D2FF1FF8FFDBCDFF08EDFFA4CCFF05F4FFC8FBFFECD7FF37FEFFFBD5FF2BD7FF38EFFFAED9FF44D5FF2FFAFFE6D5FF30E5FF80FFFFFBF7F9FBD0FF15E2FF6DD2F366D0F35BE8FF8BCCFF06D9FF3FD4FF26D9FF40FDFEFED1FF1AF2FFBCEFFFB1CDFF0BD7FF3ACBFF01D6FF2EE4FF73FDFFF6E6FF80E4FF7AE5FF7FCCFF04FFFFFEFEFEFEDCFF4AFEFFFFE4FF77FAFFE5DDFF58CBFF02D3FF23D3FF25FCFFF0FDFFF3DDFF53F3FFC4E6FF82F5FFCEFEFFFAD3FF29F7FFD5F0FFB6E8FF8EFEFFF8F5FFCBECFF9BD0FF16D0FF14DEFF5CD1FF14E2FF72FDFFF599CCFF6699CC336699FFFFFF9956AAB20000000174524E530040E6D86600000001624B47440088051D48000000097048597300000B1300000B1301009A9C180000000774494D4507DC030D0B252B54F812BF000001A14944415428CF6D526757E33010DC24841E24D94E482FF4DE42E89D3B8E5EAFD03B57E8BDC6DEBF8E645904DEB11F66353B9EF53E6901EC586B29999B258C31E20B77570FC0BB28DFF4D55A96555AE7E7581B6A8BBC29D1E57646A77815F790E3749284CB1DE9A6218F5C7CF96B9A265621C7DBA097785252BBF225177A11B3D92C3A98EE24431921656264A498572DD1D406C4E125CD1D05A8D960BFB90B4537B34A8020F571BD12E0ECFEFBA9601F7CB85D97F00394B0FEB1FF353C89272EE187EEB289DD13554FC4D164107A9E1A24B14794C38A784E84808D672499707C13920E1B3E609E02E74BFB6796224D94022BC54FB5C22E1D88FF5C5133370962AB4181FC4A7DEA4B1B1D90EFAD976411ED397147D2433D06E1A3C79C0D73C625AD02BAC9608B3837A3F30EFCC4E38E6A7D501DEA09AA49DE105FE6036E808136E24DAB960A71D2682CE2EF1739269D2BA86E59A6FD323D2D17C9F3F3DB8CBA6591BEAE9669AE1AB914A9762DBEFBCEF7C7D05D076AD12293812D516C2A6C15490B5CFFCBED67B4D21FB02C8376D1462EB98B0AE04314AFF73F509D76C42AFA54E91517FBEBD920A994D50000000049454E44AE426082', '2');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (5, 'Protein', 'http://www.ncbi.nlm.nih.gov/sites/entrez?db=protein&cmd=Search&term=#', x'89504E470D0A1A0A0000000D494844520000001B0000001B0803000000BA0A0467000000017352474200AECE1CE9000000E1504C5445D6FF19336699CCFF00E5ECF27396B9E3FF72A5BCD2D7FF3A92C53A4D79A68DA9C6DCECC2A4C590FFFFFF7EB24DB9EC153D708FCCD9E5DCFF4AF7FFD695C548EAFF9948798E8AB286D0FF18E0FF63CDFF08F9FFE4EAFF93F3FFC4BFCFDFB2D968D4FF2899CC33668CB35F8C8F99B2CCBCD995BAE254E8FF8CAEE21D80A0BFF2F5F9D9E2ECF0FFB6BEEC2E3E7097B2C5D9DDFF53FBFFECC0D9AB5983ACCEFF0F8FB29AE6FF82D9FF42C5F518F8FFDBEDFFA5E1FF6C9FCF3EDEFF5CF6FFCFFFFFE6E4FF7A81B255BCEC236B968E40709FD5FF2FECFF9BF2FFBCD3FF23FDFFF595C545FCC7B2330000000174524E530040E6D86600000001624B47440088051D48000000097048597300000B1300000B1301009A9C180000000774494D4507DC030D0B281929813D72000001864944415428CF6D926B5B8240108597F1B2656D8964C1A0664411688A4A929A3E5DC0B4FFFF83DAD955AC9ECE0738ECCB326799614C6BD54A1148493CB7D84F59D304C0E68D469A4B9307EE017D7200BE98BB4288556B9903C4C5D6B5FD70691835A134CC8CC117A42B8DFA0948641843423D728347E03342330EF7B4609489F9CA8E6D7088BDC2E6442D84C4AACA9A2D84BE0C6FE354786A81582993EE588806A68CB580BB220A3DAFA3B3F4CA5E5812A28D68310E8EF84F395418BE3CF925FD1855FD6AA46DC9BFBAC9D999FC7EA6E144DA8928AABEB143C48EB2AA6CA8AC66DEE1683E594FB33A5DD5FF328DE224357275764445748270FF751151E90B96BCDF99FBDC3DB3B7B7E6F53967312C7727DEC5AF7A1ED5B4216073E8AE74661D7FA8EBAF1146CCCAB1B28B681611B36D57352280A4F997190BC491646E0C9BB17A392AE27F24D0D48394827D2A916E442437DE3EEBD6D2FC71C046BBE8C07A83E81493E62E00B0EBCCDDADD58C6D39A7C1CF01EDA7726C9124EFCE88FDD66CCAE50EB47950906FBB4B2F6C43C617630000000049454E44AE426082', '2');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (6, 'PubMed', 'http://www.ncbi.nlm.nih.gov/sites/entrez?db=pubmed&cmd=Search&term=#', x'89504E470D0A1A0A0000000D494844520000001C0000001B080300000058D61F1E000000017352474200AECE1CE9000000BD504C544500000099B2CCD9FF404C79A5E6FF80E5EBF2F2F5F8FCFFF0CFFF0F7F9FBF5982AC7295B8D6FF30BFCFDFE5FF7FDCFF50B2C5D8D8FF3FF9FFE0D5FF2F3F6F9FD2FF20DFFF60A5BCD28CA8C5EFFFAFF8FFDFF6FFD0E2FF70D2FF1FCFFF10D8E2EBEBFF9FE2FF6FEFFFB0F2FFC0E9FF90DFFF5FDBFF4FF5FFCFEFF5E8FBFFEFE2EBE2ECFFA0C8D8D5BCD895A8D836B2E22C5C8C8279A866AFE21CE8FF8F729F7F7FB24CA5D826598C72D8EBB2CCF539CCD8E5668CB2FFFFFF336699CCFF006A995F060000000174524E530040E6D86600000001624B47440088051D48000000097048597300000B1300000B1301009A9C180000000774494D4507DC030912082E982DB377000001944944415428CF6592E796E2300C85539D464880D03BB3D3B7F78DAFF4FE8F3592131838AB1F3E963E5B96ACEB79BD8D370376369DDD79B7360EF8CAA637F843171C1D87C791DBCD920B9BA9BF3D3CC3027819A6E20DCE74234EFAA3D843619961359140D0B113F3AFCF8FD8E746A0090DF0F4ED3B73A42C9932FF211BC35F08CC6A18BFA1DF7277DC17F39588B2D8B6796B5187E27CD2A2044A7BF7567C6AB230CFE78A885A297A271DCA9995830B5FD7D2EDDBA5844FDE2BF3045DC0C892C31D69916A4911F3B18340413447DBC107E6CAAB98870E4A8F35510C7470A8AD0667B8008C6405F2772837470E96285148562CCE69037D33753046883A8EAD2617B8D582A45A5E0A6C1053693027EC153E4AF8CE4B7454020B949401A1646804DEBB4F70F3FA49E46B83A6D5B72D857F25B811B8D6817D0C335F3ECE974EAD5FF84FDBFEE3DD3827AB7943BD65F1B30E34EA06AACA4A97C6FD1BD9164B27855E0989D3DDE8F0525058AF0E7A8D77171125BDF6B65FFEA5BDFED6D7F2DB5D4B93ABE446B84934BDA0C1C9FBCFD65115044115BD677C033C996CA95757709F0000000049454E44AE426082', '2');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (7, 'BioCyc', 'http://biocyc.org/substring-search?type=NIL&object=#', x'89504E470D0A1A0A0000000D4948445200000065000000200802000000848B2147000000097048597300000B1300000B1301009A9C180000000774494D4507D807120B382D249C788900000F3C4944415478DAED5979781455B63FB7B6EEAAEEF496A5B3104216B3B2440324208A107DC8E082038E3088F014061C1706719D99CF119C4F671C549ECEE8F004471915898AA304216034094948584C20A0D997CED6E9EE74A7F7AAEAAABAEF8F6E82C688A833BEEFCDE37CF54775D5AD7BEBFCFADCF3FB9D5308630C97ECA28DB804C17732EA22C789A2D8D1D13134640B491200D034C507F93973AED46AB597F0FA8AD9ED8E8ACA4A2924656767656767A9194AC13824291F7D74C0EDF6BCF1C69B03FDFD5B9EDC72092F50303E5476C8E3F55E77ED7C03F2B4DB46469AF62BBC7720A48A4A9FB1A0283541230403814020E0F678B41A2D49FEFBEF6EF44DF95E92A4B7DF2E2928B83C3B51AB34BF07EEDE3E22B9C61A1B83065DBA4959AC338FE8211451894A4199375946E4DADAA34B7E7A0BCBB2FF8BCE88A218E47902116AB58AA6E91F0FAFCACA2A4591737272CD52A7F2C55E2069A48A5268EED340860BA2A6919D991A01CB0284782C78012B44EE528B603A525DBBE2E7CB1042E149B0D7AA34EEC2C111CC8F9C5F8F35A0D81C32FD5A884AF867F9200842797979F9C7E5ED6D6D1E8F8724499D4E979595BD62E58AC99327FFCBF1C218DF71FB1D8840AF3F7DB772760FA8F5486DC02A5D63D05CDEE4578603A913629292A4FC68418378CCBB31EF06C1434C5BD534846C4343D75E5B1C9967F094587A2F28D238ABD21C55740F91B318D00FDDC27575F54F3CFE78475BDBD71DD9BAEDF9C58B17FF1878D96C3622E830B5FD0D6816B13188330E61D3C1369840F02A8652131064D900A2F20D7E33E9C541170E0E83229385BFDAFDC1C7D72FB8CE68348EC10BB126141587FDC338E080F08A34CBDCF867149B0BB288FD360040240D1AF3975F04822EEC68C5120F0445C46402170BE782376C1515950F6CD8E071BB01C068325D35776E46460642A8B3A3E3F8B1E3BB4BF610041192249AA2CCE6B8D1A742923434640300AD5663D0EBC317799E6F6969B55AAD8A2C1B4DA6ACAC2CBD5EE718761208E9F5BA5048E238769C7C2F49D233CFFC69CDDA35D1AE3A0C08A90D883580CAA0C8BA41BB43910904720A4B759DEDF7CA92B4286DD12402010645C2FE21A5A36CEED573AB6B6A6FBC61D1585AB9E63162E21CC08AF2F9DE50CD36C03284824A4F2D199B8B875BC5F7D70200119D4E2F7D230295DF2E9F78456E2B03593CA7144972D2D564E13D489714BE303030F8E8430F85C1BA6DF9CF1F7EF411BD5EF7654708927CEC9147DF2B2989D2E9FEB16F5F4ACAC4F0ADBDEFEDFDCD238FD034FD97EDDBE7CF9FE7F57AB7FF757BC9DB7B9CC38ED1C7598EABAAA93E79F224C7B2164B2FCB714B97DC320E5E18E3E8E8682DF8B1FD2CA8F588D10213052A9D91D4A466129C3F040A66347476ACDE2D8B9C5E83540CC60A0AF120FAF1E067099937BA5C23DF1CCD0491361FEA5F86500000F02816632C6097CA1E56ECCD000004052403B2008A2C777EAA0CB7D28B5E40518900F0EACE9D0EBB1D00AEBBFEFADF6DFE1DC3305FF987280A00EEB9F7DE8F4A4BBD1ECF3BEFBCF3E0839B0020180CBEFDE65B0030E7EAABE7CEBDDAE1706CB87F437D6D2D228884C4A49CDC5C96657B7B2D18C06030A4A5A66AB51AAFD7977159C6F87A627878382B3B870D5A14AC204A0D941A6816D11CABD2CC4ED71D38D19B48B32449D92931C491739274400441E281668152036070B69AE3629C4E97C9641C0708AC28ED8740E20100489A9C5838EE18E9E88B61B008531A39EB7EC294A10CB74847FE84BD56ECEE976BB751D73DE50F0AE5870F0300CB72F7DE77DF18B0462D3979C28A952B776CDF5EB27BF7EAFF5C1D131D5D595975F64C9346ABDDB869132288CD4F6CAEAFAD55B3EC868D1B6F5F797B98DF31C61E8F17219495950900494949DF580F0D0F3B63A24DD8D307040D240D24030403040D049D1C63289E993180F966B79B8BD7FD647AA64EAB05F2DC308A0192C6233D69E9193D16CB9869E5133BA48F7E157A7765A8EE2F8015A05454FE4A64CE1F07AE911EB9B30200805253C59B890985C04513C9B3E96B7E0B24030072DF31ECEAEAEDEDB3DB6C0090989418F66AFC8046E88ED5ABE2CC66E7F0F0DE77DF5314E5D51D3B1545B96DF9F29C9CECA6D34D61D0EF5ABB76CDDA35A3620821F4E5AD7DA1FA510C85380D07120F88881C44F8849431720778529124B75F455334450122004884C808CD2102040FC77182C08F95BEF616B9B75E717686D33F11974BE62C06821C4724F7D58312020022F10A644C3BEF79C2E544F46500009280071B7C5E2FCFF3003061E2C4F0D6FB264B4C48B863F56A0078B7A4A4AAEA48E36727A36362D6AD5F8F10AAF8F45351101895EA8E55ABD05799E462EB6D8C0170645F447E280A000EC9CA870D1D569B677AC6841B8B8B0467E093B3DD1863008C318E501EC6E3420000481B8FF4C9883586094E196810FFB1063B9AC78DAFC89B99D2BFA2361081622E8B60EA1DFA4E2260D9F2E5C92929DD5D5DBF7DECD718E37BEEBB2F3ADA04001DEDED0090929A6A341ABE677F42C3A9455104260AB0048A0C72087008E4D0C088B7A7D9D676BCB7F158E7C9C30D7CD3A06BD8E7F07840099D3B245024501B3D6EB786D38CE5C7AB3631B7BEC12C799DBEF201202800C03E9B74F4C5F144BA3F72427FAD54A0CF4D8B6535ABA66906007A2D1649922EECA4C1A05FBB6E1D00580707B273737FBA6449F87A301000009AFA0E95C058BC323232FC7E9F978AC39200120FB2009200329FC02A3113B51E45F108A2ACA7450ED34AC8EDF3412808127FFE30A6B7B4B4A4A6A68EB314C9802696C85B4A4E98110913DB5910DC632351131BC1C4333076064F5F640C179D909068301A0060A0AFFFCC99B3DFEAE792254B743A1D002C5DBA54AB8DE06E309900C0661B9265F97BE235303070E7AAD56F1F3C81180D16FD58F483E8C3829791FD37E7E8A7CF8A4FCE89C9CB8ACE9F1A2312848996B0E883900F8B7E1C0A00A3F5D3E6B2B23282B8402EC058097D295D2963F18AC98ADC196C88306924185CCA501300002289D81C93C938B3A80800783EF8C2B66DA2285ED84F958A2108020028FA7CB2CBCDCD050087CD76B4AEEE7BE2151F1FFFF41FFF78CBB29528F94A2C78E05CB983058F0E7C0B279166D15B5EDFD32AC254B362443E10DCC0BB41F080E02352E747E98D8F3FFE38C7715F4BE30A6005FC36F9F46E65A031024D5422B0636507915C84B86800C09E7EF9C47F47F4AA2C48C75EC601270010C614149B8310FAC5FA756146ABAAA8D8F4C0A6AEAEAE3144FFADADE3FF58B0406F3000C0535B7EDFD5D5FD7DFA39344D2FFCC9C2975EFAEBB2A53719D8CF70D0851089012325846541468C75D8CF6835019F07510A049D2078303F82834ED09A8F0DD06F3E77FFE62D9BC7E976543E0D940AA42016BC1172205554C15DE345828E9ABE2654BD1514593ABD47196840D197E1E156C5D11A566D54E12F81D100406E4ECEA6871F7EE60F7F1005E1406969755555DE942993522649B2DCDFDFD7F2C517A5070FC4C5C65EC0F9A4A4C47577DFFDEC33CFB4B7B5DEBE7CF9925B6F9D3A2D9FA2A921ABD5E974DE7DF7FA8BEA7F2184CCF1E6539FB7CD2BF8857CF43908D81196B024380252BB64B693DA687B8FADA64FBA725AC284119DECC2BC0B481579F95DDEDAD3FD7DFD619A1FBB09F99131498A9AF94B226DDEF8319F750315744A0DBB4012147B33D823348A547A6AF67D2879F6E87BAE5ABDCA60343EBF756B7F5F9FD7E3A9ABA9A9ABA9199DA7E9745371F1FC0BC7CB9D77DD4510E48BDB9E1FB25A5F7AF13CFFC4C6C5AD5FBFEEEB2263FC7E0EC678DFBED2B4F4B4BC64BDD2F82AF65987E9C4F75C795D4D67533869663A99CCE13E64D65142BAD286F4C964FE9D4D1D569665274E4C1E95DAD8D32F9FFA3BF01ECCBB415180A4905A877449286E3291900F2ADDB9B6CF807C7A3700204D0C99BF6A54E5635797D27650B1B7801C420C8792AE2052E7852BA1B134E0F1947F5C5E5B536BB1F4381D8EE8D8D8B838F3B4FC69372DBE3936266674D873CF3E170804162E5C5830BD608CB3164BEFC103071A3E6BF007FC006036C75F5170C5F2E5CB2E162F005014655FE97EBD4E77CD9C42A5EB137F4FDDA140764BB72349E88A574B9E3EC1AF4B983E2B392F3D03928A8E1C3DEE728DDCB068D1BF7D8B155D2029628C1B4F9D6A3A7D66F6EC5969A929B2DBD2172047BCDE4CB915511C69CE439AB8EAEADABEFE812953F2F2A7E55F9C42FEBF6DE4134F3C7181E22B213E3E2323FDF3CF3FAFAAAEED1974716ACE1C1D8B8CE96B363EC9E963351A6DF9C71FAF58B162524ACAB860EDD95352595141334C427CFC98E01D13EAF5C78E0B3C6F32992EFED53D1ECF5B6FBE75AAF1546E5E2E499EAF2BF6EE7D3F2D2DFD934F3ED1EB0DE33075B89E551402A1E62F9A0551D4E9A2FE99DF1F351ACDFCF9F356AF5AB970E1F56AB5BAA7BB1B00E2E3CD3ABD7ED2A494071F7AF002EBB5B6B6FDECB6DB3E78FF83E6E6169FCF5776B0ACBBBB4714C5C71E7DEC48D5118C714D4D6D7DFD3100B00D0D1DABAFAFAEAE9165D9E7F31D3C58D6D3630180CECEAEAAAA23922479BDDEB2B24356AB7534F6B76E7D7646E1CC9B16DF4C5194C3317CE0C041BBDD0100ED6D6DB222F7F4F404795E96E5CACACAB0A0F5FBFD870F1FB6582C1D1D1D1B376C1CB45AC550481004003871FC444D4D0DC678C4EDAEACACACAAAAFAA69AE13BA41B9665B3B33267CD2A32994C2FFCF9C579F3AEF976B54253B1B13173E6CC696969114321499677ECD88101244932188DE154F04E4989CD66078081416B7D7D7D63E329511431C6AFBCF28AA2283B76EC54ABD500F0DA6BAF070381D75E7B3DFC94DF1FA0697A725E9EC9682408E2E5975E3618F4DBB66D1BA3D4ABAB6BDADB3B76EDDAC5F3C2CE9D7F4388200852AD56D30CA3625443838356EB507373CB912347CE349DA9ABABEBEBEDFBF0837D3535B56D6D6D3FE87BED0FB1CECECEE9330A4E9E38D9DEDE2E0A2249100683312727DBE7F39796EEA7292AC8F30070F9E5F900C86EB73B9DCEB6D6562924618C972DFBD9DF77BD91997959AFC5C2715C414184DA1886710E3B795E50AB550020C9F28C1933CA0E1E1A13175D9D9D2EA76B7A41012070D8ED575DB556A5528542A184C484D10E5D6F6F6FDEE4C93A9DAEBBBB6BF2E4298545852CCB05FC811F1A5FDFC3465CAE2D5B7EAF62D553A74E55ABD54EA78BE5588450764EF65B6FED06047C3028634C5124CBB2FB3E2CADACAC9A397326C33076BB5DA556618C1B1A1A753A1D4DD30B165C6FE9B1204484131FC3D08B6FB9F937BFFECDF3CFFF9720083939D94F3FF5F4942953542A955EAF2710D26AB51449CE2F2E76B95C1EAF4FAD5215CD2ADAB2F9C9E3C74F204438ECF6EEEE1E35AB56A954854585159F56ECDFBFBFB8B89866688D86E338965131DF991F2FD98F1D5F97F0FAFF6EFF037F5A6794A90DB8510000000049454E44AE426082', '1');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (8, 'Gene Ontology', 'http://amigo.geneontology.org/amigo/medial_search?q=#', x'89504E470D0A1A0A0000000D49484452000000680000001A0802000000D4948E3C000000097048597300000B1300000B1301009A9C180000000774494D4507D807120C1336DDC65A8900000B1F4944415478DAED597B6C5C5799FFBE73CE7DCCCC9D3B0FDBE3F1D84EECD871EA84C449DDFA41D32614026DD30DA50854608556A80581840A7F01615556ABD582C44A7405D26A251005D15258D152D2F20A4955256ADA466D12374EEBA4B1E3F895B13D0FCFDCB93373EF3DE7DB3F6CA73869A142412B6DF2FD7B9FE777BEDFE37E170B8E84772F225044F0DEEADD4E544AC1FFBB626B574E70A3DE5B898A53464429A5224220C61822226332908C21171A0068BA7903A92B8193810F8081940C9109018041100000671C80292919E33760BABAF086C6FD8D1DF76E0794524A4900544A2122225E86051111D9F54ED5773BE0D5EBB59ACB18670C011088345D97322022A11942D36F00F78EED269552866132C609088894528C31520C394300520A19BB01DC55C0F9C1CC628508105932AE6B2019A20F0480A8A452BE8E786D81F33D997315000067CD1647BC86F7A6632FE55F77C567EE8C45AE3570A414AD0899A2E9C9F2D3AF968F8CD7802008A8AF2FFEA50F26E3DCE7E2EF424F229ABEE83C7EB4782A1B30004FF07B07E21FEFB72DED2F5F068EEB9D9E91433DA1BFF2802038FE86733E615FDB4825960A7922E5799E101A903AFD86FCCF17AA77DEDEF8C3FB5B630C26C60A8F8D069C13FDDD9CF1EC99C2BF1F2C0D6C4FFCF703319BC3D848EE9BBF5BA809EDA15B427FA1A5BDAAFFE81333D1CDA9A19EBF869B2BA76AB4B5DDBCB6A94AE886414AE98649A4CE8F95FEEB48EDB30FACFBE8C695E6EAE8493CB29188945CC56D6AD2393EE32B8075EBAD81560D149D182BE58571673B3F70CAA90790CA84EFE8302E3FE0E2A4737CDA53001DEB23FD992BBBC8C9B93F3ABCD4BDA5E10BBBEDE5E6DFB4ADE1A36F965F39E72CF585A8583D7AD1BF6D4B7476B27C36AF3024EE7E5F2424B0E6FA075FC98FE56853B1F6AB63FED65EBB27CE00C0AF0547479DBC07DCD4EED911595E433657CF57B02DF9366E6EB17E68ACEA2948369A776C7C1BD0D75E2F4E38A087455F8A677DBE4EF8C7A6E5AE9BEDC48A9EA993AF3B5912BBB6854D00110AAF10DF77BDDF9F584C6F4DDEDBAD5FCD0B22529EFFB3DF640F4C05C35D21A807BF7EB9F8C9BD997D1BB5A973E59FCF2E1D62AC39A32F66DD91974ADAA75A875B84ACFB8F1FC81E980EDEDF155235FF99E34B0FEC6DB9A76BCDCD4F8C9666B9F68D7EEBCFB5B631AE15A782AA4FE4D47FFD7CE1E8C9523265D8323834569D28A51EBEC3F23C796EA256653CC454B608B7E8080017CF2FFDC7C162BCD14C87F0CDC9E2A133EEBF7DAA29C62057F0EA266F8D89E5859C7A6DE1BB47AB7D5D6644A9675F2EBCB2A5E12B7B6C51F7BFFF3F73675CBEB54DBF7466E96725DA35D0984CD47FFA4229DD191D6A42002865AB3F3A9CEB1E487DF80A735858A89E5EA4FB7745F83B0933297AEE70F65713FED73EDD3E941110F88F3E363D32E3EDDBA8E91CBD2AFBDC832D1B2C9E9F29EFFFC5FC857C309862CF1ECC3E75C1FFFA67DA6F493108FCEFFD647664D65B039CF48F9DA9A5D37647620D2903499C214320814C425F7FF213DBC23A103E31793E5BAF4ACB8EEB9B9BB59328BE7477D3B2C295E72B8F1E2C766C497E71D80A099C18CD7DE599A513F30DBBD32C9BF3AC8896B61900CCBD55FCCE0B954FEC4DEFEB3105D11F0FCFFD64AC7461D02A8EE45FCAB1473EDBD29B6033E34BFFF2746143ABDE10A298A48B797FA84907A0E3A74B794DFF608FB90C8F202244048052D173046B8B6B0020A584C0FBC12F17DFAA52C8D6BE705753D297CF8E54B76D6F1C483324393EEE9EAFC1CE268D029A2CF9EB3BED0D164700C1194760084EB1FADBD3D5BEFED4608B2025CF8FBBE375F840E35A1377830B1E6D6E37236BB68AB2B9201931C33ACE16FC5A58F46F08EB0C00D060B4ECB6E4A9897CD0DD6E5DF685E3A3A51CD3BEBC3D121208006D69230C345B0C20C5DE9C0AAC64D81640817CEEB5929DB1EEED31050220AE6F36E4E94A6E293831594BAF8FDE14670800048A00008C889E0E41A92201C077EA87DFACF57425BB57292F2AE512E39C735E2A7BC808838A92E1627E516362E866DD3B561E2D022A7761CA9995A0CD96BFF5781900168BAA6373626F8FE107C17C31686BD311979D2EA8016FB679FE923327D1982E3FF2B843A016F3B27373FC235DC6DA08A27C4551634DBBF94EFDCC826A1F0C45385C5AF474436BB49615462E9629DEAD9B1CDC5270A124B7F65D6E5E7576B29E49C7D2165BB50EE90266E24255BC71576DEA330D809A178C5F0AFA86C3DAEA3EB955C93596083300283BD2036035FF4FAF9582A8BE212E744D35C658B6E0138446464BE33E7FB82F62AE5E2BACA8AD4829A52261E17BB5F932413398A190A69B7D61EFC5234E3265A622FA1CE39CF37F188E852511B24442EF4D0B8EE454EAF325B83DA52300014C67ABAEC69B235CE64908B6EFB606534A522A91346E6AD6AE70494D673AC3E9824F70F97DE8E5578B334CFBA74D262739B320234DE6328F55A9767A097635691A40BEE29503B63175D96A64B14CA299AD880CD1C9B7AA3C62F4247069D1777CB6A1912FD3A8EC82A1B355F191A7CED7E289706B9C7F686BF4C53F94BEF3B402C71B2FC0FD1F4976C71993908989E773BE0AFCC3A7DCD6F6686F93F8B3791C22635C08ADB5CD5AC7D5F3E7645D62BD5E93815F9C5B3A5751EB531C7D8F6981A9D4A2A7FABBF850B7E86D42E5FB32F09CC5CA3CF12DAD2B9B3FB7E05921110FB388AD994A1514BFADD7BAADD7BA2925F0EA1140D41868E29393EE94A39603DDEC64F9B1E3EEEEA1646F82434D4E38EA7DAB31229FF76B849D0D0200B2976A0544EB6D3F6456189DB25F0900004AB9EA5323D5C11D765AC742C9AF01C64D06008CB1B001B982BF1C10C6CF968FCCD3EE1D96CD71D3D6C4FE7BEC73672BC9F6E8FE7FCCDC7793C9008063674264676A27C7CAAF1669F70E3B26DEE9CB21DE18FAE4B0FDBD430B0F67DDA1750640F5F41BD5A266EEEC899961DEB6C1DCD93AFDF33F144B8B56085137C5DDB7C6133A144B2569EA2DD68AACCFCC4BDB8EC40CD05B22C32D859FFE66B63063E940BA29EEEA8FC5AFB46BB6EFCEE4C8930BDF7E6A7EB85DF3ABFE1F4F56863F907EF0D688C6A0E2CA8207DB57B3C0DCA2A7B868B51900C4E21AAB384F1E9ADF9814DBB6C4B637F13D03F6B1DF2D7DFF39EAB070F45C45CB441F7A7F9421C4A39A41F2F7274A999DF1B6B071D78ED0A3C77221F2624A1E39E5F60F26F7F6ACE4E2B3136E85731EC857468A6E6FECE6B400C0AE0E33FC62EE5F9FA9C532D670668D40F3AFEFFFD6E5A1C7FA75D6BEBE70D15DC96C03B7367DF5AE643A8C00C004BF755BBC5D978E8F00D0B739D6613300725C65A543832D3A0040A08A1E7675477A9362F9FC365D3A3E00C0F65E7B5DF41DF26CC8D2776FB59817D42468A6F6C58F67F674EACB8CF37DE573BEA33314331000CAAE8CA4CCC17643E7184F9A3D31EE110062675BA8C1C4C6E6D060BB56701520F6DF9C7C68A7BD9C9E43B6B1A355733CD89831233A767658BD0DB8E4123076DF9EE67B7B432BF281D8DB630FA679B61C8C8C9647F278FBE6900610B68D66F08E5DF4F7EE4A0DB60ABC318F5BFB612E3DC65779AF1E7B62EA4263E29F3F6C0B80A0E2FDF8B7F37F9AC3EF7E3EB3CEC4F73456BA5E8AD40B47169E3CAB3EB6CB8E024C5D748E3BDAE7F684A1EAFFE0C0A55359395FA64F7FACA5CDC0AB46E752FA9E47A490310444C6881422030420528A4849C60500328604A0A4E49C0311E302AEE910E3FF6A06BEAD2F31E7175F3C5902808EF5D1FD0F84333657B52011D5DA417FF0BEC48E76FD6A95C18542ADE2949707709C732104111100E71C91F99E07089C732595A6EB882865C018678C09CDB802B8EB6B748E084213410086692222200291107A10F88C3130742040C684860848009A6E2222E3D73BC7855387AA2B1511D5EA08C08586C8B890F56A5568FAF2AF56C68021102091629C33261857D7F9CF9AFF050C346306B260F8220000000049454E44AE426082', '1');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (9, 'Reactome', 'http://www.reactome.org/cgi-bin/search2?DB=gk_current&OPERATOR=ALL&QUERY=#', x'89504E470D0A1A0A0000000D494844520000001C000000190802000000ADA2D97000000006624B474400FF00FF00FFA0BDA793000000097048597300000B1300000B1301009A9C180000000774494D4507D807150C1B3BF6799485000004BF4944415478DAA595CD6F5CE519C5CF79DF7BE7CE77C671C61E83EDB163C247484A026A2865114AA96490588320FD27D8564251BB4B37FC11EC11816E60810BB5E4D20AE1D421891CE2C8C2E3786C8FC733CCD7FD780F0B27C17682D2AA677B757FBAE79CE73E0F25E1A024C4B1FA7D743A6AB7D568606B0BAD167A5D747B88223A47CF5310B87C9E4343181DC5E8284B2566B3B4160078081A47AAD7555BD79D3BD8D941ABC5C10012F1CB3246C522C62A7AEA694E4ED2F7E9ED7F1C865A59D1D5AB8A22349BEC76E91C1E29E7D86CA2DDE6EEAE2351ADEE8346916A35D56A0078E4083319351A6A3691247B9FA9284226A35C8ED6820484C4210CD1EFC339260936EA5CBEA94A45DEFD1C775BD8DC24C95209248280E9B4D26907E8E851485A5AD2CC0C4E9DB29E474308700E5184765BB76FBB1F7E60BFCF6613BD1E7E86C691007A1E9CC39EEB20E0D414272694CBF18B2FD0ED606686D5AAE1E184F9ECB3FAEE3B373F0F4318730F6A0C8686680C7A3D75BB6A34E81C0A058C8EA254324B4BEECAA2A6A759A93C48BC1B5DA70300A59282605FA641C07219CE69771771AC7C1EC3C3CC66B9B2E2E6FF814291BF3E8742E1302E8ED16AB96BD774E3067D1F8F3DC603500024AC652E876A15D92C3C8F3B3B6E6E4EDB0DCECE627ADA781EEFC5A5C100DBDB5A5FC7EA2AD6D628E1E4494D4E1A6BE13DE8254831950209E7F4F5D7EEC60D3EFFBCCE9C31E9340184A1DBD8D0DA1AB6B6D868A0DD4618D25ACDCCE8B9E798CFF321C30FEC15A530C4952BC9E58F393CEC7EFF1A3DCBAD6DD4D6B1BD8530347B35F83E32191D3B8653A73439697CFFAE8FC3D0C140EB353576D46CE2E64DD4EB2C14944EB3DF471C93542A854C16D90C8A0595CB2C8F606484B9DC81FA0E409344DF7CE39696D8ED328E25A15040268320403A8D2040368B7C1E85220A79A4D30C02781E1F9C870399DE5977DF7ECB568B000406298D8DA95A45B9CC6C96BE0FDFA7318FFE71BD7DCB491B1BEAF5EEBE44208A78FB369A4D542A7AF249542AFF15F1B07DE7F4D557F1FCBC4DA7652DE3F8BE31598B63659D7C06E3E32C95E8A748404214CA7A7737DEC3EDB7DBBA7A959B9BC9CB2FA35834B59A9A3B4C1C01260936EE70675BE5B28E1FD7D4710E95E81CFEFDAF38717CE105BBBF2B7BF1E2C57ADDDDBA959098FBBBFBDBA79A9E766FBE694F9C302365F829F5FBDA5BA92412C71F7FE4461D9B7575BA724E5F7E890F3F44B3A92050B1C8548A003818B84F3E893FFAC84D4C70610171AC4B97CCB973FE9EA35E4FABABC9E2A2AE5F67A7436368CCDE698024DFD7F7DFE3CA7F4C10E8E9A7343B8B575FE5F8B8F59A4D7DF6B92E5FB6A4FA7DBEFF7E72F6ACBD9F5126C3279EB0C3C31A19717373F1C282E976AD139244618828621C3349D0ED727191ABAB5A58706FBFE5EC8B2FFEE9830FD068984188DFBC14FFF5927FE4883D782D98CD726ACA9C7FC53B7F5E43475D3840AFCBC10051CC38FEF9B20D422631AA55790BFF74B99C397142D66A6C8C51A45F1C146072C2FBE305BD3EABE565DD5AD1F2B2AE5FD7B56B6630A0B53AF98CDEBD803FBCE6F3B3CFC37E8FBE4FDF5790C6AF4E9B62F1D1D328210CD5E9606929FEF35FB8B5C93367DD8577F9D26F6D2ECB872C94FF496B6BC97BEF697C5CEFBCC3D3A7ED5EFB1EFE3F158A7CE3F5E895DFA51E7FDC78F7603F01C6C87B5C17722AA30000000049454E44AE426082', '1');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (10, 'Uniprot', 'http://www.uniprot.org/uniprot/?query=#&sort=score', x'89504E470D0A1A0A0000000D49484452000000670000002108020000004B2222DF000000017352474200AECE1CE9000000097048597300000B1300000B1301009A9C180000000774494D4507DC030D0B29092D2D1C5700001AAD4944415468DEB57A79905D6775E7EF5BEEF6D67EBDA8A5EED6D2922CCB926C61191B1B2FF1D860335326432043082109504CA698CC6496641626294F4866A69801C6155214A4CC4C0D506C01CC24C4887803E34578912DC9966DB925B9DDEA45DDFD5EBFE5EEF75BCEFC71BB1B9B1153953FE6D4AD57DFBDEFBE7EB7CFFB9DDFF99D733EF6179FF9C4C1ABAFEE4551E006C610F3FDB8D0420837A7AAE10E78E6F1816F7358AE9453580F3272A139AA0586323042E6C03054990C2CCFC9F4A8308279AE4B40AA8B61C546525ECB1179986FE0620DB9E0D2A269659058966B2BB9F55DDFB2AD5D33D6D52F4DEA7A818A4221D0F7A1382A0AF51C8CB19E8F585896EBA10C0197A9C7FB3E148131C618C386111111596B85109C732232C61011E75C08C1B5DDBCBFBCADFC5479B1BC7FF33A632C9715C07266A173214CCD97CFFCF427F2861B6FBAF2BA6BB552D2AB4003D2498DE65CBA8A98E200874BCA610A24011700413368C007A0000204C0814C03020280B18C38E7100200012C073280637F1509E71AF0008FF8FAC75D963130C02B8008BF349C43030690800000684003027090031E801CB080879CC303C7DFC99401E7788397D7AD28C0F9FAF10633CC638C382C4C0E14E056A090837E4885625C00DC0AE2E000E7C499140060098C0B580B0E5802A0480B180EAD21354000811898E3C11038877038596B0CE7028CCD211E95A872C00219E70E5CC0531C39813804B70CA14B0A68BAB25A61A9D160302E3483015C824F101C99400818A00E566700A390A10F6C2B78899ACDFFB3C49152AAC41A63AC84CF3A9A84BC84CB00783EDE00D7CDA5B5967380313046969852711C4B03C66420A400381837E08EF02438B401010C309640C4B565D0441527F01908600E20371F14B004ADAD319C4910B43250C6711C72022D803AA0010E9F03253A08300C0E980B06CE8002903E042A25D42C200187813BEB68E31BB036EEFAF7BA80702F0D29E1FABF006B9A683DFAD661652D1131C781B5E5FA8D91CE1C8F3100069C33EE80484A298BDC5A6D884092F7B3C4F302A609CC91C2010708801580E0D2008A19A5485A00D00C96C30282C008C2758C2FC918CE250037F0CA5FCC4562016D202D403C63D0CCFAA56BAD258D81E59A0B014EB085B51E638C41020E200046EBA0F32004296E884B5100DA1ACED830738CD697C41AE7DC5A6B4B8F30C6392F7107E9FC3CD2B860D6020C5C808BCD77CB459A2B682BB86526E75C0B524551C84AB5C6BD2A1834B8089844E0B96096436BCDC118138660190C91630B86BAE330055848898C03800418212A0AED7001D842B94C30C96199516A2C100C901C200E0E62C8012B58A3CA606139674000788003294A4AA10DB00190281824E01A78858425309609704E55C39001D55FC86B824394DEDC0C492230668DB1D672CEB91000AC315A1BD793D618A5140029659916ACB5BEEF0160D07004A00011F855992405726D38CF1CA4DAB80E37DACA5C2390E0D080280C320546A872E3F0302D1A19A0A13DF42B20A091435A2849DC9542401852492E038F31667223B22897C8183CCD39217711FB1096C98257A4479E6B604C9EB2CC30EEDA8A17657945C1CB010D48243E061EA4C5682ED05710025B2AA940AA52AF6764EA4446F10D2B69A8B492C58410524AC6608CC9B2AC288A5AA35EA654218403CE39B7C42C3163486BAB0D71CEC104E382015CC05810110331AB38D32095248964106052702E99231C6900C9391837B16AB37C100FDCB5A8D9CB00AC35D86A53D455655B887A81D50A161A00309AC0D7E8085D9DDE5693DEDA858B61A7CB3C47385227D9C890137A208646865A012B39B96016692F776AF562A4D2878A3B3D11E5A25EA1D17A34486B059A393C8D5C622DC0C08363A052DE1C6837F0E3A05821446158AC643B533917E5A56B8410008C315A6B634CA5529152BAAEEB791EE73CCFF3300C9324D14B178D3125A07CDF1742586BB5D64AA91280954AA5D96CD6EB75D775185B4F0C9C73269C323D388E233917E092880C18C121009905E39CB16AADB69687FB2EDB971C3B55A9565A7BB7AF8573D2F1870ACEEB0DD69B9F9CDA9D66F1B4AA640B2BAC51EB6B5D71ABD2F16A4323EE7023D5C5D054ADD09D44149956235E5DACA415E157075D6F642B1A00C8091ABEAB17D7C2E1F1F1A8EE3DD59DBB76FB15592F0C9C8AB420322D5FD824B485E241104CD40BAD82E15A9D7215C61134DBBB7D240F2B958A3126CF73C771ACB54AA9750A034A5F54AB552184EFFB8D46431BB3197DA5882B112AA5DC94698EE39408DD086EC618832DA98E015C32E220CE604A32A192562C98901AA88F0C23555FF8F43D63CDE6073FFB1FB66ED9D6448D0FDA2F7EFD2FBF7AEA27B7DEFDF12BF71F8015FEF8B6118ABD4A15A0D6F8180CA2FEDAC8D870D2E9BDEBB65B3EF047FFF243BFFE61D12BBEFE9FFEF8B5333303973A73172759E5DA1B6FB8EB9E4F8AADB5ED3B76C3C0ABB01D8D3DB6178D0F8F1960AEBBB4A535EA2B7D60782B8172501F10F0A451ADA898684DE8B1C91018568EE30863280802CF7300580BCE51AAD7527F48C9012855731C411BFC668C658C6DB8775367C018238428B3AB52A68430DEAC0A25A75273715BBA6CC36B7041C0F98559DBD15EA68AA23B73FA74E4EFE899EE9543E3C352F69716E3B0DB5D59DABA64A4A85D4C2FD6AE3FE841CE3FFB1C57165B1A052B7492FDC5BFF9E3BFBD70E6C11FFEF5FB0EBEBD4EAC02F6DEDFF8E0DE5DD34B4F9DFECCE73F977C71F4FD77FFBBD71E7E6CDCADAD8E38BD49EF30DBC6AC7EF4C453B5C92D59E1F75F3837148CB26663766D2EDBD1F27DFF0A557DF0B3F71E3C7870B06764B685A9ACEAFBBE524A2955469CD69A73AEB5F63CCF719C6AB55AA954E238EE76BBD6DA200836736B99794B60AEACAC388EE3795E1004434343CD668331388ED8A81DDE649201B000B38030EB7218B0C02036A3D5D16D63533E265B23FD95956C30A8D42BCBB34B0886EB041386691256049764B1DADD7E68773B098F9F7AE9BAE6041B1D3B3BFB7265ACA14D7EC5BE2313FBA7F9F4388296508A096A4E6FC3587DC77BEFBC76E6C48F9E7EFC8EF9F74FEF3B805CEE1CF10ABDB474F2D4C4657BB62BB17B7CC7E2F2523D55989FC7F8C415FBF63C1FCED51B75CC774E7DF9BE911B568EDCFD8FF51659EBA25EAF13519EE7A5DA3046388E932489E30ACEC138314E5C403A1CE08283019C31C678C968466BC6D8E848AB54C5524ACEC81ABDFE03ACD319400CAC441C979CEC3ABE00820538C8C272D4AA031B06BC22EAA273615E166AFFEEDD0BCCB97C7A1FDABCCEF8F6B191C9B191F1A1C6CAF71EFADA9FDEF3F1AFDEF3B13FBBBBBFB832D1B31FF9ADDF7EC7BFFDA7854E8686777CE9C8916BFFE3EF1CBEE21FA1C88B2476AB9E375A75A646C1AAAB793FE5A6D369CBD7CEFFD9C77EFF5F7CEE53BF77EF9F1E08463FF9CFFF60F7F5373CFB956F7FF24B7F5ECBE95ADBAAB4935FFDF7FFECEA8FDD757661F6D37FF8A929EE3EF1ADFBBEF9E2A3C31F7BD7EF7CF8F75A43AD8D20A192C8FC0DA19FA40900D771DDA65BAFD705175AA992C5CAD02B41CA39F77DBF24B8F2544AB929FDDE64C437B046161B9043796A018236C56B4BE727CC485D3AC395BA2D8A95FE72E07A931754BCBCDC595C786DE6CC91C99D5B5AC3137EE5131FFFDDDB3E7AD775070F2F3DF8F4B7BEF2653E3DB2F7862315262747C7E62FCC39CF3F77606467AB56094D9A8621861BE923CFBC78F2C4BBFED547A368B04DA9ADCDA1CF7FEE9E0F7CECD7EAD5AAC9533CF8E4D1CF7EF1AA6B2FBBF1AE3B6E905B5EFCC277BEF1A97BDEB7BD3EFAB6FDFFE45FFFEEFD1FFF93F7DFF8FECEDED6D2D4D899B367C6C7C77DDF2F8AA24CA000AAD56A494FD65AC7710C4C5114711C0B216C5618635CD7AD542A9CF3344DCB3BF5865A164254ABD566B309C6A4E4C66CE8DD37EA62B6CE6B6FA63B027ADDA6D36C8D0E73D840387914F5DA9D5AA3BAB0B28820A84EEFDA3D35D5AC578B28CAE717D38BABEF79E79D87F6ECBDFEA69BEEBCF556AE94E78868D01BDD39353F585DC907D5C911543D1B67B61B3E75FF03DFFF93FFF6E9DFFFC40D971FECCC2D1E3E74F0C2E2859541E7573EFAC1A8C676FFDAEDCD4397D9E32F4E2AB1FFF0017B70A276EBA19BFFF31FE5CBED0B4F9FECAEAE34DEBAFFE1C597FA4D7EE02D876EBFE9965AAD56A6CE1241BEEFFBBEEF384E188659961191EBBA8EE300C8B22C8A2222524A655996A669922449926459A6B516426C2A18C69831064465E171095EE3EB29C002C2AE47284040B399221EF5C6DC71D66BAF8EFAD5C95D3BFB4CBE65E755580116E6175E9FBDB2D5DCB27D1243E7EA9EB76FD7CEADB7DC0C4756865B799670D0BE7D7BF36EA7BE737CDE6672A8066FC4E75CA6F960763149920FDDF9EE6DBB77A9EB76A351BFECF27D895579D579F76F7E680E165BD8D1EFDCB7636A6CCBF5D7E0B223F3B63FBDA571E4AA2B8F3FF6F85DBF79BBE676EC6D074EAECCEE3BF061D46B7B47B7B8D22D7491E7B9E77925BEA490A3C3C300D23CE59CBB52BACD66A351134C08028C31C608D785B569920028C373B3F614528273809835242EE5B5814FA8405B5460A7401C3A6DE84032283DAD25DA6D0495995DC26E6B15AFBF76201E3BB13B9AEAB550381395512DDD471767AE99ACF70287AC50F5EAC0170BD9EA5865787C364DCE2CE1D0F6EB9E8F6A374DCEB870E28EAE344573ECD0AFBFCFDF37557473FED6C3730DB603F5E0A5DE8DE1F07432FC58BFBFBD2611FA43B5E6AAC386A50C104D9F4DB1EC38915EDB537B3C5EBA2B1CDFF152EF1D571DC285F917F74C8EB515E75C2995E779195C9CF3A2289ACDE6FCFCFCE4E4E4A037A88C8FE7797EEEDCB9E9E9E92CD3511495D86CB55A51948C8D8D5D585AF67D3FCBB26AB5DAED76ABD5EAB66DE300B82BCDA572E87AE1C7E8E7AE33480962AFBFFE3AB2BC353A72FCE449B75AA36E7FADDD46BDAA9301F7DDD3A75ED83DB94B919D5BBD48AE9C5B987720A6A6A6827A0DAE0CA3A8DFEDB98D910BEDE52C2BB68C6F530E5FEE7515D9E6D0F0F6DDBB9E3F79928167C812664626772DAEB58BA258995B82E3B4F6EC5C0AFBD3DB77CDBD7A0E5C22707B3041A5E61468D45BA855DAB6B8D8E9F42E769697973B9D4E5114AEEB32C6D6D6D6E6E6E62E5CB870EEDCB95EAF1786619EE7699A763A9D388ECF9F3FBFB4B4546600ADF5EAEAEAF2F2F2850B17A2285A5E5EEE76BB61186AAD4BAD9BA6799EAB4B47E886BF88FDCC8F9C802C8A834A7562C7341A8D5FBAED8E271F7D6275AD3776E34DD70C3B7862E6A1E34FB5766FE3A35B3830C8D289FD7B79A3DA1A1EE1602BEDCECB17664DE06EDBB923AE574E887C68E794CE1571A9EA7E1238476E7CFBC017D5D696CB6A9504351F6CE0B2D3FDA57F303DB9736CC768A50F55D973DBDB3FFF99878FFDF889DB7EF5BD8831F3F93F7F6CF6955B3E7A7BC3A99B306D333D4BE93BEF7867630862D4618C95F5B6D67A93DD1DC7298AA2D1686459E6FB3EC08787478D3159568C8CB4E238AD560363A8288A92CECA3ACCF3BC344D5DD70510041E804B624D0216A5F8C01BF1C6837A03D65A2E6D6770CD8D371E79FBCD7FF087776F9F987875CFD0F4A9CEE5D61D9A9CB8FA6D3731C5124D4FBF76EE9A383A541BB2907152F8E35B2EAA7C87701CEE3FD65F18E1981E1AED26D18BEDC5B361E7E5F3E7F75C77A4BBBC221BD5B269BBE6D005DF5E349935C510F9C853EF03BFFCF77B73FFFDBF7CE61BDFBD6F38B2FE85DE1D1FFF482A9C5B0EDF80303BF2EE773D72F4C1073EF2DB57FECA3BDFFBDEDF628CE5B92A8AA2EC7A4B2989C0B9D43AEB74BA9C7329DDC1201A1A1A32460B21AC45BBDD666C4C0811045E18C65996054140445114E579AE946A341A9B45E825BA291FFE8DF74FEFBD8C9105172080716D4970992489EBF873E75F5F59591DDDB1EBE67FF89E2DB566921762726C4765F8B65B6FC3C448B0772AEA8507F75F75D5DE7D2B948FEEDF1D67C98E91ADD3BBA60B57642E2747DE79ED8D334E16EC184762AEDC75F975B7DE321B7547B76D5D5D5EB9B8B6D6DABAB53F581B15D57D070ECC22A1C9E1A16EDE6D77DA0B170EDDF0B6FD571CDCB56D7B34886F7FCF2FC7AEB8FCAAABC2B5FED0E8F8BE5B6E9E5F591ADF3D9D46A95B6BE55911C7C9DA5A378E12A3EDA01F2ECC2FE679D15EED4461CCC003BFB2B0B0B8B6D65DEB74AD354992CCCFCF2BA5565757FBFD41BBDD6EB7DBDD6EB7DFEFAFADAD154551F6E33CCFFD59F5CE00326016B0AF9F7D953DFC83FB6EBBF34EB2C484630062B2D0DA952E030461F9C2F248BD7EF1F50B537BF64048A4C912EBFBF3BDD6E8246A4E5CB1ED767B676D0B523D88D6AAD393852E9CA89095DADAC545590B1CDF0B5692EE9058957ACCADB6B4EC2EB7074CB7C6C78421E67AC6F51CC04F3448AE7696D2C9E6968BB1BF75ACB3D61E191E452F42AE500B2E7656A494A3E31388529DA772CB3038FACBABB23059A552ABD500C4714C444110004892A456AB29A54A0DD16C56D254876158AFD7B33C9652E6795EABD53A9D0EE7BCD56AE5799E2489EBBA4AA97ABD5EA9049B4DB9324205074C01AE41EAD11F7E1F8FDCFF1D3221A901D95CDB5C13855AE5448A284C5438C84853D14929A5F9E75EA598ACB514E59412151BB59C224A3559CAB422A293CF1C2FD79A882C695D6464DA2A0A754A44648988565757CBC5FCCA8A227AE185D326D39A2826A29C06FD24265244C54A4403224539514ED45F5CA395947252440B617876669672CA52A3159125B2640D25B12A4FD3449717E75E5F2A17EDD57EF9A56118970F1E86F1DA5A8F88E2383586CA45F9565168224A924C1BD2868888744E3626D3FBF1FD5FE5D84C9FEC4DBC9716F00319D4BD2FFD8FAF3DF1ECB3DD4EEF9E2F7CF195175F66C41E7EF2F1A74E3E13E629233CF9E8134F3FF594F1C5532F9CFCF297BFAA72DD8BE2856EE78599579F3979627E6171368FBFFCAD6F0EC9EA6BAFCE1EFDF18F3A51F89DBFFC6ED28F4DAE7FF4C02359BB970DE2C79E7D2AF5443F4B3D8B1FDC7FF4B9175FD0C093CF3E77EADCABE074EFFFFC5FAB619458FAE4A7FF2B86FC93A75EF8E973279AB5DAE9995741387EFCF913275E6CB74300C6606E6EFECC9973BD5EFAC413C73A9D08C0ECEC5CBB1D8661F1F2CB6766679700ACAEAE763ADD7E3F6CB7DB0B0B0B834174ECD8B1C16010C769A7D309C3586B5B960A6542B884F2E0E5988B7380F18D661200E1405910E056835BDF71D3FD0F3D74CB1DEF7C6D6161FEDCEC0F1E79F0C4EC8C57AB3CFBD36716E6E78FBFF2620CFADB871EECAF751D571E3BFE4C7DB8F5CAD999AF7FE31BDB2626BEFEFDFB8E1F3F2E2CFEE67FFFD5C14387825AF591071E2CA24430FED39F3C3EF7F24C6F6965726AAA000CD9EEC595B5223BFEFC499BDBEFDDF75757BEEDEAAFFCF57D4A88C71EF949E762FB2DD75F1F59FAEA77BF73FCD9E725F0D22BAF6A0F4F3FFDF4D1A3471DC7310652E29BDFFC66A3D1989999F9F6B7BF7DECD8B138D6478F1E5D5D5D3D7FFE7CA7D3F9DEF7BE4784071E78E0E1871F6E36EBF7DE7B6FA3D1387DFA74BBDD06F0FCF3CFDF7DF7DD67CF9E554A956934CFD5CFA6808C95072F9B042082B544B66C729236000403E7E80E0A3F0896DAE1D6C989A95D3B0FBCE52A46BC393676C36D7F6FA9D3991A9F5C596E6FDDB1A39FA76F7DEB75870F1D4E92E28AC387D3BC98D8B2EDE61B6F210EDFAB5CBE7B5FD14FF7ECD8EDBB41AFDDDB3E3119767B33A75F66C68EB586B328DE3E3565A01A41350FC3879E7C6CCFF4B41EC4375C736D3BC982AD230BEDE543FBF6476BBDB57890BAAC9D84571FBCF2F4F1D3BB77ED7A75E1E29E3DD3575F7D18B044C83273F9E597799EE3794EBD5E3D7CF8CAF3E7CFB6DB2B41E029953FFBECD3070F5EB1B8B8D46EB7C3305C59691F3A74A852A92449F2D24B2F9582637A7ABA24C4D2158C3163CAD195C5C6618CC1C347BF5DF29AB589D2A9260AF3BCE4919C28B5B4D88915519C5198186B8914CD2C2FAF126544A4285E8BE7E2708D2833548445AA6C976C4CD4E947A9B229D1325166881465FDAC64BAFE629B72A282FA734B941165A61B0EDA26ED25D1E30F3C78ECDC8C36440591A13ED10AD1CCEA2A1922430B71BC42746A71810C514E4A538FA8D31E6CF21A595A59EE92A528CC2F2E75CA2B675E395F2E4E9D7CB9E4B5975E7AA5E4AF284A88C8183A7F7EB63C3D71E2146D5849DB4A93D2648C2195918948777FF4375FC18F7E781F51442622CA2C159628555A1175D362D3778A4811658A7243A428223A13751451B8D8214B33BDD535B2648914ADA549AF74684164A9638A84C812C5BD240B73A3280B532A8814A5AB5D4A0A8A12D2268E069A8C25A5D2A84F94A7BA580E29A3D7E3708928264A7B69111631D1996CD027CA0739E55464A64DD668224B7966361DB7795C5C6A978BD5956E992B8ADC16852E5D16C769B7DB4F924C29B3E9A93C579BAF834154662F53663D53102564FB3F39FA356ED9665DC537CA7800A8F98E35B0065AD9BC3085D6BCDC80A00D03C6ABC33A2B6AA3C3C430D41CF1C0102B101CDF4BA0DCB2B59969C529C8A10B12B5C0ABB99CC3F37D7080915FAFC0130934842555204BAD5699B046E5AE2B9C6A00D8D14ACD0738E0D77DA7E2A4793AEED525E0565CC4A9E3700F64C98241E93CCB0B30E48552DA804169D31A6E82010CA36343E5C2711963AC52F1CB69CBD0502308BCB27E2A0A9DE7CA7525803215949D924B678392D7C882EC06AF19C50006480129C0057C57B8527066052708A1AD96401EC620F4E29840D668700716049E9A82594003C41CB82028A58C8046B91704D628E2D64AA66C2EAA4106536D3444AE85E4E4B8BED6200B1470009506B01CDA42835B9B253E6C1EF5C0ACE11A642BD69633816A35F07D17006364AD0660CCBA0B565757CBEEA3D66A73BC9265C5E6E0596B5D14DA75653976D86C81947FF092BCC67FAEB1C63650172729038CB10EE78065A0348FB52D20E071591449B3D52AFB7F025C1A83BC80A5C82681F06D9C224CC199064143489603832223A390E590CC30C6042B6073500205D872CED14512385E3EE829A6C1EC6079C58375806ED453D023CD866354AB5ACB92503402CA126E61AD562A374601B62832CEA175A175E1FB6E9625806DB59A691A6B5D388E48D3B81CA3388E53325A1CA741E0BBAE2C7DA8B5D5DA9641AAB54DD3FCFFA93C04679C974DE1F2B54CBD4AE5803564004B46BB5C645104A0EA5640409AC64502C0735C080760CA1A06C1398770005E4041038C19C0777D160410823857B00660522890849374BBF02BE070E123CDBD6AD5A904591C6F9D9884459624F5C650AA0B0EC69980B17EB50A06E6BA508631E6384EA996E238965256ABD5B27F5DB68CA494524ACFF336832ECB0A2198EBBA42AC6FCAD2DA2AA589608C91920B518E9FB9E7794294FB8CF8E68E2321845C3E77EC891F2CAE4FF0B92C7B7B5A59BEBE2189FD5F9D257349F7BF71A7C5D9375C7FFD921B78DE7CFF1BED2CFEFF1AFB05CFF38BACB0108291D19E14BAC87C4F2E9E79E5FF005B8230056D6DFC960000000049454E44AE426082', '1');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (11, 'BioGrid', 'http://www.thebiogrid.org/search.php?keywords=#&organismid=0', x'89504E470D0A1A0A0000000D494844520000004E0000001008000000009D451646000000097048597300000B1300000B1301009A9C180000000774494D4507D807120A3933C64A1E9C0000047A4944415478DA2DD0DB6F53751C00F0EFEF777EE79CDE69E9D6765D5BB6EEC6600C5C80019BF1C1404C042406D427494C7C20FAA22824FA60A2890F1A5EBC248647146F89898A26263ABC2CCE00BBE0C6D8C6D8D5B55DBBAED7D3F6DC7EBFAF0FFA0F7C1E3E84034C4D0606A2A418C65CB113805AF3FB08E07CDC49EAE34392243659D34292DE1E142B494930B118751304829642EC875ACF9C5F672B2718002001600076B57F47024808CD521228E07A8C4B60A293503D4C088293A0DF76C485DC04C01062AB5D324142245B22DD5988F979631705400A00C00030309229C44E8E657DA72CBBF275CB933E414439402817CDDF8C468FCBCAAEA563E5A54F6A6C3876DD31704AD5723F3DB0A47307EDD9DCF6F8B9D068EC981385C4290000E79C5FD92D3D5E1A1FF6ED9FD97AD7132D344A456DAAC66D33FB72EB91FB46F1F6850B7AA37AC2EDF8B4F68ACF7DA9F89BF6B1DFD9552866332F0C256E553E8B1EFAB15EAA9B9C734E0110690630D3EEB767EFB1C7FACFA85B0F33DA0E15D17EEB7AA1AB3D332EF5EB76D928832FD81834AD2FADCC7D2752624FDFA2D9544D5B3BDE5B7DE7AFFAF2560D011800014B4730E06F2F899BE40D21D622625962515C9DD8B372243F3BE53A9B839A93370F39751D3D5EBE519305529C28EC592C8461593BFA55EAA30FA79BDBC22E60000802E4067E1E3AF06CAF3676ADEF70DB2FEFBDED7EE9E29935EB81D52AB5A8AAEB39B2431C5EB8391459186EBC9ACAB3788F19B11E6C198695863C77229D48A7967459650C8080A749E9683DFAFEEF716FC4F3D40D31FF45B94F6D7F1D4AE9BDBE84BBFC53D5D735D8A34D4D42E5D6F581370F8E08545C9D36B52CC7E9F1459518BEB4F0EA8D154730E0A10028340FDF2E494FA47FFD208C93EB78C394285B2EFF39F0C8C6D8FC5264B832269255B441099D748C9CF9A1FD7822971D9D23018FF9DDB4178952B241719B95745E17148000A6723629B7E0C6317DA12AA18F73D00A352959C8D9EB4B0B8B2C6214260CD61F139E361BAF74752442944A4808A0D814109FF3E0FEA6BA55AB1B40018088289348783570A8EE665E14E7FDC4E0D0FACCEA65973A1D97E3A9913F8CF95ABC3F6B17FAC058FC362FE783EE03B4B9DD15478A8950CE1BBF9CE294510214806FE6CD80A210E96CE4E95A3D164EFB5E1B5CBFBBF7EAF648DFD5735E739F7CE95A5BE445D239DD3B5D7B7EBF2AFF9CD8B91279D4DE3E7FF15E3ED25C8CDF514F7FDFB3662BFE800A84032FF01A20E3267371C83B6CE7E6FC41872C46471DBBFBF66CB9B0B8D1192A516AD90CA0AA985228279B415540CDE2AA8292C96D172DDF9D61BB07DB1D0C400A218AC2EC5C25D8EDDAD814C99D95ED9BBDE12D4D0D36E5B75AAA5393D463CC6D0703CEB03C77C7D79B589DC97ADB7B3CE93BC5D0BE0ECFC6CC3AEFE8365D497F574821840310445EDED8D4DD1156AAA8214F31439B9BCDAAA6A86AC06DA7EE62B73FCFFD0EC5D9F8272F8261C86F9270442DA4355734CA8AA9020D05EC060F34B965F89F1396DE40A6A010AA0C561D9D6E340597A943E266DDA68EFF966DDD10B283990DCBE9415B034971124307A13001922233C47F010F12547A934E8ECC0000000049454E44AE426082', '1');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (12, 'iHop', 'http://www.ihop-net.org/UniPub/iHOP/index.html?field=all&search=#&organism_id=0', x'89504E470D0A1A0A0000000D494844520000002F0000001008020000000969B183000000097048597300000B1300000B1301009A9C180000000774494D4507D807120A2538B7EF9A49000005A84944415478DAAD556B889455187ECF39DFF9AE739FDD9DD9D999317776575D5757A124C4C83442B01F41461441D40F7FF627A8880A22080AFB9195DD2388240C84A00B76D1D456CB7451736D2F5E765DF73A3BFBCDCC37F3DDBF73FA31E38A5121E1F9F9BECFFB9CE73DE739EF41F0FE49B8758B2040807CCEFF5FB9704B4408186DC9841F29C457271401A139CB3B32637C3AB23063FA80D0CDF3A07F3B1B8CA0272A538C6C9F5DA83A1C8020D41B9701C0F08271C35D42C644F2DE5DF9873AE3F8C68DAFD69C87BF1B3AB6E002A137A906FF5B2224E01FB7779FDDD1BB6F4B9E586508FC9848CEEEE83DBBA3F7ED0D2930CBC082C6D5ECDE987DB890C0088D4DCF3EBE7BCF636FEE397D790200B221E9C3CD9DAA5984C0BF6537855D0BCD0E437A15A8523364EA303F06EDBD20696B13CAA3DD4900A859F683AFED3A57AA42247D7AEFB7A79EDD2909A4B73576BB621FB12BA025332A5DDFA23608E64C6F48B7AC80FF979AEDF9C8D36BD38CF37BBF3A07446C223C5BB54A22E55D9166A46694A1B600CC07806DB9284108004E5EB83854AAF2DC3A682D5C12F095AAD59D0801408FC68F944AA0C436B7C73FDFDAD960601CAC807D3E5A7CE6F844C5C74BDEBA414D8CE2956121F05D981B85F4F24670C5B2DCFCAEE745E13AD2F2AE7BB34D26CD0D0078A8051279D0924840E41ADEB56AE004C0D952F9A7078F645B93F7AE59BDB337A597F5E74E5C0525D620BC41CDA979E3A543835C9F46955948669A3E47A8663BB65DB57CBF906E03004230200208238446CB5603D697CF6512C979594392B42621E5421200D8AEFBDBF09810E9E284A06B0D7C7FE6CCD77F5ED6DFDB45302E881E94C621BD0AA8FC77357765621FDCBD2E60ECE3F3EF006E363D3639B5F595576B1E971475EEDD370020A1A922C1545168347AA8E44F1A762E2CB745235F3CF9D0275341A425FDD47289620400FB7F3CE89A76B67739E4F26AB84948A8B475C3468C30E7FCC0A993C82CF3C0FB07354B4BB3CB3145240800C0F37D8D2852A13F99CE35B22D00DB928ABA223B4D63B315E389037F7CB865C5F2446453577E53175C7306FFE6E8C0AE2FF7CBCBFAE554479508166FBEDF171EB87F59AA0D21D8F7CBF1CF8E0EF0EC7A20C2DF7D43291569537ECAAF0B047340004010922289D6EEFE7426B7348A300793A19571E5BEB4ACD4F05B078E766B785536938E476DCF1F9E9E3E3570FCC2E58964B6E076ADA953857B36454AA3BCD09E1ABA72F5A31F7EDE3F704C6A2BE074A74365B6A48610422915455192E46BC386A414F2FAAF63F5D193A5F9298209158842C9CB87CFA1D1C1DAFC4C4A8BA63456756A67EAE462852C883DC1F88873EC27439F751D0B10A29226A6FA5A32DDA968A6B325AA89749D1634C85F7CF783BD27061D2506CBFA85548F90CC091C5996E5FBBE8031C618534A39E77ED02C98B6BDE1C9E24FB375BEE0812DCBF176B2E80E2C8E2791DB61874DE668727BBE0EB6C72E970D8B21AA86596E75B4BDA09A86EF98B6EF3B48E05268068B530BE6E9C5C990AAB475C71BE40EA0506E6567DF9D53245A26AA1770E6798410001038E71863DBB613F1789D93C1A96260D74ABECF830085C242B64F9144450B0902ED6B09EB8C8C4C4855D4C219191C9B8B68B53BF2A93045E77567657BD265DC03ACDB9E088CB1C06140A88810AA98B684E1F705F3D1BD83F18933BE51DDB876D39FA99E7BDAA28B1E1F5AB40CC328962BD7FF29411030C6AE69C0EC089A390F4A14776E08B777465545C1DC747D45146E8BC833361B2FEA46BD0E009C7384902449FDB95421113E7C694692A462C5088280058124D244488DC9A2CB817B5ECDB2ABF5BAAF4F07A5C9A82C6F5EBFAEAF7BC589629D505A635877D962DD9C9A2BDEF86B72065615AC0A08220E25452DE2792E633CA2CA0A41738685110A186B588D73DE688373AEA9AA40A9AEEBE170382191F99A65D90EA55453D572A5C2180380D688D69F54C177874AF539D3EBE8E850A990200C114177FCB2EB572AD5BF0049F083A9FABF08290000000049454E44AE426082', '1');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (13, 'Pfarm', 'http://pfam.xfam.org/search/keyword?query=#&submit=Submit', x'89504E470D0A1A0A0000000D49484452000000270000001008020000001ABEF177000000017352474200AECE1CE9000000097048597300000B1300000B1301009A9C180000000774494D4507DC030D0B2708C4A9014F000006084944415438CB05C1CB6F5C570107E0DF79DFB977DEE3B13D7EE55D42129A9246A12D6251094C511AA54D41229BC086052CA844FF13760836AC4A10E2212045A5A8224A489A544DEB36CF2671DCD8E389C79EF1BD33735FE7DC73F83E625CBC35967E993907AE0725EEE5C6771CCE8C0211639C83941154436B0915AC404074C68A1836802F1C122025E080A7C1AD33CA25480378481C2BB1118AB21951C600386B09A52014008FB22C2897B6623C7CB81AF51FC683A8D63A78F49BC7A7EB956114D65940BCC6EAB3E1C35EB7C8DDA9E3C7823C52151F60D46A18A62475802E62C93C9894168E124D002625602508E581759612471801906531A5942B555FDDC5C57FDCBC72ED837EF75E1C4E16968E1F5D797AE1FC6B5F9F59C8818F3E59FFE7E5CBF7D71E5495DABFE758B3C10147419CC98971A09C73E1318122864978C92F43D92C032736B3505E98114A7949B024894B4A804821241F26D91FFE72F98FEFDFEA0EFB1E0F64D0B8BF95AE5CBA3272E6973F7B83A5F8F5BBEFDFBCB3328EFBDFFEC6E15A030049AD04E58C51879C32915BE6E0182170069089719EF40197139A58C6148C430638E15B0ACE0560F97892DC7BF06490D831CAADCE8270B4BB1E3A8A074FB73686E86F84D7EEAD0F5256F69B7B0F7E6DB0835AA7FAB86F9EF6B6B6D7EF9748D2593C589BDDD769719719153472884F1F6D85C39E9EEC34E70FB6F62D0EFBF8F2DE5D67B2C3FB979E7FAE691C444EF878146E6C6C6E0FB2606EE6FC850B93FEE0CF17FFBEBB3BD8EC76D7BBCFD69E6C0F2705F56B2F9E3AF4C3736FCDB4706788772FDDB876E3F3A877DB23E14CE7C0F32797DF7CFDA5D9C0AB30ACAC26BFFFDBB5FB776E8EA38D7D474E1E7AE107B73FFE6F776D4DC7D1B75E387C76F9D5574E1E528A70A70DA7AC5CA968AD37BE5AFBE27F5777D71F371BD5F95A30D7AA3CF8E20900AD8BA228B6B77A13D5F9DD5FFFFDA7F76EF5FBE36640952BEE76EF7FBE2172C85FFCE4447782DF5E7CEFD2879FA6F196EFD92F6FDCBDF499F6D2CD4AE08D06E3F8FA2D021CD8BF54E9282E556934199BDC2F957D3DDA8D361FCFFA4EE8C1CB478F1C59F43F180F03C5134E5756567ED37B20DE3AFB9F2BD786233ABD7868B6160B1DF477B2E128BF7AFDD6E9EF1E11DCADADF72082DAD4DCDC82F76433D94E95F4CACDF905CA581CF56E3F7ABCD67D363FBD441343A9F095F2AC4EB81E2F35833DADD2B9E557CE9F79B5AD5051C4E4934267711C532ED77BCFD264C2886BD5EBEFBCFDF3777EF5B65F529C62B0B3350A4352180A9834919C9F5E5E6E4F3519855F6FBC7EF68DD9A5454B191532774541C19D5729A84CB32C90ECE513C78E9FFE4E9DF2C5799F30A4056C1A290643E9CC74676EEFDEC4523D191327DB4D9F11CC34A77C295D91C2420996C6E37067CB1669BB39DB694F799C14E3416BEF62AD1E50628B42E7796A8C61047CAC89769412A26851F5E8D1FD55243019BC322450AFF8CE244CF13367DEFCF1F78EEFAEEEECFFE8939547E1C737AEF656AF9F7BED254248B91A64F93889F399A95A6BAAF1D54E2F8927599A4AC29447E3DD9E2299C78A927452400A6201CA846414CCA63EC9DCA82F80B287A00C0A84BBA1C9C61E34CD470279BB8AE70EB64E9D787166AAA6F3DD703048277961EC781C06D5B22322CD5D96A5D1A85FE499A23250AAC8A3E9000D617D9ABA382CE288DA5C02DC6776A95DCE27C99E366F2AED03798E91862CA35EAF2CCD34A6CBB4445C536A0930879FFEE8FBA5A0F2AF0F2FE7E1003ADF33371DC56B8AEB46E0136316671BA3B433DF6E4A93B74A74BEC1E7CACC84DD2985039DFAC2EC94A29600A49BD8CD01F17D48ABDB6A58911E50CD040C40A087DB8941D90514B4682A5675D8C9E114B633EC7477676B75781866A8FA080C6A1ED60669E20A0F6CDFB4B71922F540A26CA1AD86919D8CA39260AD5A593142729358E669030F86B31D580AB426863A85123218E29CB40280962E419C435473E12685F149A008260609B73EA5BE03B473D2E5B0DC81199E689D7A9044140642C01A280E6BE18A9C331D09EAB91C5CC4D01132405694F40A80405342E0686134E309A021284C2839919CC202850C8ADC937046A31088223255522E4306B85249B21235056AE32C93422559C6B822AE00E8FF0166D82EC17534C0430000000049454E44AE426082', '1');
INSERT INTO `hyper_link_menus` (`hyl_id`, `hyl_menu_name`, `hyl_hyper_link_menu`, `hyl_icon`, `hyl_menu_level`) VALUES (14, 'Google', 'http://www.google.com/search?q=#', x'89504E470D0A1A0A0000000D49484452000000100000001008060000001FF3FF6100000006624B474400FF00FF00FFA0BDA793000000097048597300000B1300000B1301009A9C180000000774494D4507D80712092C1D2F6F4B1E000002EB4944415478DA9591494C137114C6DFFC67289DA1405BA0950106084BC13D8ACA26440F069784B81D8CDBD98301F562E2410E6E31115434213144AD78A9812828266A8A2088094184962260ED5EDA5228455ACA308B375391C4F0CEDFF77BEFFB1E06AB8CCD6AC5464CE379EBE88CCA65116D0C4638D537FB0CDCB9DF6698EEBD7B03C3B03F5A22DA5877B50EAFDC77F0B4C111B930B3A4CC1EEA359B5D4E5738BC1096F231547A22C7ED8B8B8BEB0000C33F806BB7EFA56E2EDAAD1B30FA322DA36F5B62C03FEEF178FC56ABD56BB7DB1711C2C9B2EA8BEF7E72859AC1C1C1BF01E76A2F2B733797EBDF74F6E1831F5BAECB64A49961987E9D4E178ABE90127F5D2A2E2E91545454404343030000200080E2BD871EF40F98F3F41D8FB46EB7C3C0B2EC07AD561B5AD9CDD3A6BAC75269AC3EBA03387BFE8AA6B1DDC89757D59848923CA152A910AC61902A67EB7EBB378CEC368B8BE3388BCFE713D6022066228801E06121BC24088210FC9FA1FAC87129CF94967D7EA79F9A357598085720BCA464280042AE1684D597DF6A7A9E1F44CA93B45A5DCA64D225371FBE47F3B6E1A372B9DC4158DDDE513C4B0A325A931FB0203588FCD84A80B6BD0F79272D2EC9B2AB7D7DED930A2E308FC4885BC161D230CE0A847F2963678D7A5D5AECD4D8E422449C9D2B01D39303FE44C98269D6E7F8AA283D56224F48CDB37FFB646443DE2EF4CBD8E9B69A465E0899B9A02C3F7506945B8A568BE1F1781659965D6494D093B95D03447265098661320200801F797DC94824546EAB3A40F349C9ADA189EEEA486FE350F4BF45518467BA3626B920B7BCB56B6E5926094D4B693AFB8F429273B4409AB3F7E5A643551A9E26386AD9F76A231EFEB0253166B6204D45CFE3F17B7C1E76CFF7515B8FF58B563FE59CB09124D98D459F999155183F47145F4ECBDF75386FC7063A3D432E519010E2424127EF710C8BC1E1CF73019F5F1004038EE33F9A9B9B456C65568AA20021A492C964D90A854291949484D46A75242525C54F5194A3BEBE3E10ADFF0D394244B6190CCC1E0000000049454E44AE426082', '1');

-- -----------------------------------------------------
-- Data for table `hyper_link_submenus`
-- -----------------------------------------------------

INSERT INTO `hyper_link_submenus` (`hyli_hyper_link_menu_id`, `hyli_hyper_link_submenu_id`) VALUES (3, 4);
INSERT INTO `hyper_link_submenus` (`hyli_hyper_link_menu_id`, `hyli_hyper_link_submenu_id`) VALUES (3, 5);
INSERT INTO `hyper_link_submenus` (`hyli_hyper_link_menu_id`, `hyli_hyper_link_submenu_id`) VALUES (3, 6);

-- -----------------------------------------------------
-- Data for table `hyper_link_menu_source_association`
-- -----------------------------------------------------

INSERT INTO `hyper_link_menu_source_association` (`hlm_hyper_links_menu_id`, `hlm_source_id`) VALUES (1, 1);
INSERT INTO `hyper_link_menu_source_association` (`hlm_hyper_links_menu_id`, `hlm_source_id`) VALUES (4, 2);
INSERT INTO `hyper_link_menu_source_association` (`hlm_hyper_links_menu_id`, `hlm_source_id`) VALUES (7, 3);
INSERT INTO `hyper_link_menu_source_association` (`hlm_hyper_links_menu_id`, `hlm_source_id`) VALUES (8, 4);
INSERT INTO `hyper_link_menu_source_association` (`hlm_hyper_links_menu_id`, `hlm_source_id`) VALUES (8, 10);
INSERT INTO `hyper_link_menu_source_association` (`hlm_hyper_links_menu_id`, `hlm_source_id`) VALUES (10, 5);

-- -----------------------------------------------------
-- Data for table `versions`
-- -----------------------------------------------------

INSERT INTO `versions` (`ver_version`, `ver_version_date`, `ver_notes`) VALUES (1, '2012-11-13 00:00:00', 'Base Database');
INSERT INTO `versions` (`ver_version`, `ver_version_date`, `ver_notes`) VALUES (2, '2016-06-21 20:00:00', 'Add Data Process Status Update AnnotationLog new fields to ENUMA dd Corpus Process Publication Table');
INSERT INTO `versions` (`ver_version`, `ver_version_date`, `ver_notes`) VALUES (3, '2016-07-31 20:00:00', 'Bug Fix :Alter Corpus Has Publication has Process foreign key reference');
INSERT INTO `versions` (`ver_version`, `ver_version_date`, `ver_notes`) VALUES (4, '2016-08-10 20:00:00', 'Alter Annotation table: remove element normalization and add abbreviation');
INSERT INTO `versions` (`ver_version`, `ver_version_date`, `ver_notes`) VALUES (5, '2017-02-07 20:00:00', 'Lucene Index');
INSERT INTO `versions` (`ver_version`, `ver_version_date`, `ver_notes`) VALUES (6, '2017-03-10 20:00:00', 'Alter Annotation table: remove start, end offset sentence, relation_classidfication and add manual curated field, Alter Processes table : add version, create and update date: Alter table corpus_has_publication_has_processes: Add version');
INSERT INTO `versions` (`ver_version`, `ver_version_date`, `ver_notes`) VALUES (7, '2017-05-12 20:00:00', 'Update some external id links and icons');
INSERT INTO `versions` (`ver_version`, `ver_version_date`, `ver_notes`) VALUES (8, '2017-05-25 20:00:00', 'Fix case sensitive search for old dbs');
INSERT INTO `versions` (`ver_version`, `ver_version_date`, `ver_notes`) VALUES (9, '2017-11-02 00:00:00', 'User Tabel Update : Add Active, avatar and default language fields');

-- -----------------------------------------------------
-- Data for table `auth_groups`
-- -----------------------------------------------------
INSERT INTO `auth_groups` (`ag_id`, `ag_description`) VALUES (1, 'Admin');
INSERT INTO `auth_groups` (`ag_id`, `ag_description`) VALUES (2, 'General');

-- -----------------------------------------------------
-- Data for table `auth_users`
-- -----------------------------------------------------

INSERT INTO `auth_users` (`au_id`, `au_group_id`, `au_username`, `au_password`, `au_fullname`, `au_email`, `au_phone`, `au_address`, `au_zip_code`, `au_location`) VALUES (1, 1, 'admin', '0bd2e8ab6e1f46f6e6093b4e9385ee01fd410df8723a8ebdaff85425ab8b2946', '@Note Administrator', 'anote@silicolife.com', NULL, NULL, NULL, 'Braga - Portugal');

-- -----------------------------------------------------
-- Data for table `auth_roles`
-- -----------------------------------------------------

INSERT INTO `auth_roles` (`ar_id`, `ar_role_code`, `ar_role_description`) VALUES (1, 'role_admin', 'admin access');
INSERT INTO `auth_roles` (`ar_id`, `ar_role_code`, `ar_role_description`) VALUES (2, 'general_role', 'general access');

-- -----------------------------------------------------
-- Data for table `auth_group_has_roles`
-- -----------------------------------------------------

INSERT INTO `auth_group_has_roles` (`agr_group_id`, `agr_role_id`) VALUES (1, 1);
INSERT INTO `auth_group_has_roles` (`agr_group_id`, `agr_role_id`) VALUES (2, 2);
