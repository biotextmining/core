ALTER TABLE `publications` 
ADD COLUMN `pub_type` VARCHAR(45) NULL DEFAULT NULL AFTER `pub_relative_path`;
