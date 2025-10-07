CREATE TABLE `department` (
  `department_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Department table id',
  `department_name` varchar(100) NOT NULL COMMENT 'Department name',
  `description` TEXT NULL COMMENT 'Description',
  `parent_department_id` bigint NULL COMMENT 'Parent department id from department table',
  `location` varchar(255) NULL COMMENT 'Department location',
  `phone_extension_code` varchar(20) NULL COMMENT 'Department phone extension code',
  `department_email` varchar(100) NULL COMMENT 'Department general email',
  `created_at` datetime NULL COMMENT 'Data created date',
  `updated_at` datetime NULL COMMENT 'Data updated date',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;