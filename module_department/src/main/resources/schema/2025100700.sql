CREATE TABLE `department_employee` (
  `department_eid` bigint NOT NULL AUTO_INCREMENT COMMENT 'Department employee table id',
  `department_id` bigint NOT NULL COMMENT 'Department table id',
  `employee_id` bigint NOT NULL COMMENT 'Employee table id',
  `is_manager` tinyint(4) NOT NULL COMMENT 'Is employee is manager department',
  `joined_at` datetime NOT NULL COMMENT 'Employee joined department date',
  `leaved_at` datetime NULL COMMENT 'Employee leave department date',
  PRIMARY KEY (`facility_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;