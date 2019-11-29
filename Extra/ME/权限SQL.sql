DROP TABLE
IF EXISTS `user`;

CREATE TABLE `user` (
	`id` INT (20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL,
	`password` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL,
	`status` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL,
	`update_time` datetime DEFAULT NULL,
	`create_time` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

DROP TABLE
IF EXISTS `user_role`;

CREATE TABLE `user_role` (
	`id` INT (20) NOT NULL AUTO_INCREMENT,
	`user_id` INT (32) NOT NULL,
	`role_id` INT (32) NOT NULL,
	`update_time` datetime DEFAULT NULL,
	`create_time` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

DROP TABLE
IF EXISTS `role`;

CREATE TABLE `role` (
	`id` INT (32) NOT NULL,
	`description` VARCHAR (255) DEFAULT NULL,
	`role` VARCHAR (255) DEFAULT NULL,
	`status` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL,
	`update_time` datetime DEFAULT NULL,
	`create_time` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

DROP TABLE
IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
	`id` INT (20) NOT NULL AUTO_INCREMENT,
	`permission_id` INT (32) NOT NULL,
	`role_id` INT (32) NOT NULL,
	`update_time` datetime DEFAULT NULL,
	`create_time` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

DROP TABLE
IF EXISTS `user_permission`;

CREATE TABLE `user_permission` (
	`id` INT (20) NOT NULL AUTO_INCREMENT,
	`permission_id` INT (32) NOT NULL,
	`user_id` INT (32) NOT NULL,
	`update_time` datetime DEFAULT NULL,
	`create_time` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;

DROP TABLE
IF EXISTS `permission`;

CREATE TABLE `permission` (
	`id` INT (32) NOT NULL,
	`name` VARCHAR (255) DEFAULT NULL,
	`description` VARCHAR (255) DEFAULT NULL,
	`status` VARCHAR (255) COLLATE utf8mb4_bin DEFAULT NULL,
	`update_time` datetime DEFAULT NULL,
	`create_time` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;