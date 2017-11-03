ALTER TABLE `auth_users` 
ADD COLUMN `au_prefer_language` VARCHAR(10) AFTER `au_location`,
ADD COLUMN `au_avatar` BLOB  AFTER `au_prefer_language`,
ADD COLUMN `au_active` TINYINT(1) DEFAULT 1 AFTER `au_avatar`;
