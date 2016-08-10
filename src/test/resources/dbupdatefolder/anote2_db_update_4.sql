ALTER TABLE `annotations` 
DROP COLUMN `ann_normalization_form`,
ADD COLUMN `ann_abbreviation` TINYINT(1) NULL DEFAULT 0 AFTER `ann_notes`;
