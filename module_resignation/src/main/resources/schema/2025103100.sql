CREATE TABLE `employee_resignation` (
  `resignation_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Employee resignation table id',
  `employee_id` bigint NOT NULL COMMENT 'Employee table id',
  `resignation_date` DATE NOT NULL COMMENT 'Resignation start date',
  `last_working_day` DATE NOT NULL COMMENT 'Last working day',
  `resignation_reason` TEXT NOT NULL COMMENT 'Reason of resign',
  `notice_period_day` INT NOT NULL COMMENT 'Notice period in days',
  `exit_interview_conducted` TINYINT(1) NOT NULL COMMENT 'Status of conducted exit interview',
  `exit_interview_note` TEXT NULL COMMENT 'Comment on the exit interview',
  `status` varchar(50) NOT NULL COMMENT 'Resignation application status',
  `approved_by_id` bigint NULL COMMENT 'Resignation application approved by manager id',
  `created_at` datetime NULL COMMENT 'Data created date',
  `updated_at` datetime NULL COMMENT 'Data updated date',
  PRIMARY KEY (`resignation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;