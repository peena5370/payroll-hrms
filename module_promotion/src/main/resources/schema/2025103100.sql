CREATE TABLE `employee_promotion` (
  `promotion_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Employee promotion table id',
  `employee_id` bigint NOT NULL COMMENT 'Employee table id',
  `old_job_title` varchar(200) NOT NULL COMMENT 'Job title before promotion',
  `new_job_title` varchar(200) NOT NULL COMMENT 'Job title after promotion',
  `old_department_id` bigint NOT NULL COMMENT 'Department table id before promotion',
  `new_department_id` bigint NOT NULL COMMENT 'Department table id after promotion',
  `promotion_date` DATE NULL COMMENT 'Employee promote date',
  `salary_increment_amount` DECIMAL(10, 2) NULL COMMENT 'Incremented salary',
  `promotion_reason` TEXT NULL COMMENT 'Promote reason',
  `approved_by_id` bigint NULL COMMENT 'Promotion approved by manager id',
  `created_at` datetime NULL COMMENT 'Data created date',
  `updated_at` datetime NULL COMMENT 'Data updated date',
  PRIMARY KEY (`promotion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;