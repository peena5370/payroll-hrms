CREATE TABLE `department_employee` (
  `department_eid` bigint NOT NULL COMMENT 'Department employee table id',
  `department_id` bigint NOT NULL COMMENT 'Department table id',
  `department_fuid` bigint NOT NULL COMMENT 'Department facility unit table id',
  `employee_id` bigint NOT NULL COMMENT 'Employee table id',
  `is_primary` tinyint(4) NOT NULL COMMENT 'Is employee primary department',
  `is_manager` tinyint(4) NOT NULL COMMENT 'Is employee is manager department',
  `joined_at` datetime NOT NULL COMMENT 'Employee joined department date',
  `leaved_at` datetime NULL COMMENT 'Employee leave department date',
  PRIMARY KEY (`department_eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;