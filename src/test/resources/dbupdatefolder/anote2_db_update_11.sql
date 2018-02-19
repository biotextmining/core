ALTER TABLE `data_process_status` 
ADD COLUMN `dps_id` INT(11) NOT NULL AUTO_INCREMENT FIRST,
ADD COLUMN `dps_finish_date` DATETIME NULL DEFAULT NULL AFTER `dps_update_date`,
ADD COLUMN `dps_users_id` BIGINT(20) NULL DEFAULT NULL AFTER `dps_finish_date`,
ADD INDEX `fk_data_process_status_auth_users1_idx` (`dps_users_id` ASC),
DROP PRIMARY KEY,
ADD PRIMARY KEY (`dps_id`),
ADD CONSTRAINT `fk_data_process_status_auth_users1`
  FOREIGN KEY (`dps_users_id`)
  REFERENCES `auth_users` (`au_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
;


