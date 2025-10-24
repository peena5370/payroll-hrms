CREATE TABLE `department_facility_unit` (
  `department_fuid` bigint NOT NULL COMMENT 'Department facility unit id',
  `department_id` bigint NOT NULL COMMENT 'Department table id',
  `facility_id` bigint NOT NULL COMMENT 'Facility table id',
  PRIMARY KEY (`department_fuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;