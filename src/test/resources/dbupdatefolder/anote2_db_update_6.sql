ALTER TABLE `annotations` 
DROP COLUMN `ann_end_sentence_offset`,
DROP COLUMN `ann_start_sentence_offset`,
DROP COLUMN `ann_classification_re`,
ADD COLUMN `ann_validated` TINYINT(1) NULL DEFAULT 0 ;

ALTER TABLE `processes` 
ADD COLUMN `pro_version` INT(3) NOT NULL DEFAULT 1,
ADD COLUMN `pro_create_date` DATETIME NULL DEFAULT NULL,
ADD COLUMN `pro_update_date` DATETIME NULL DEFAULT NULL;

ALTER TABLE `corpus_has_publications_has_processes` 
ADD COLUMN `chphp_processes_version` INT(3) NULL DEFAULT 1;
