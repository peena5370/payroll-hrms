CREATE TABLE `company_facility` (
  `facility_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Company facility table id',
  `facility_name` varchar(100) NOT NULL UNIQUE COMMENT 'Company facility name',
  `address_line_1` varchar(255) NULL COMMENT 'Address 1',
  `address_line_2` varchar(255) NULL COMMENT 'Address 2',
  `city` varchar(100) NULL COMMENT 'City',
  `state_province` varchar(100) NULL COMMENT 'State of province',
  `postal_code` varchar(20) NULL COMMENT 'Postal code',
  `country` varchar(100) NULL COMMENT 'Country',
  `contact_person_id` bigint NULL COMMENT 'Contact person id which came from employee table',
  `facility_phone_number` varchar(20) NULL COMMENT 'Phone number',
  `created_at` datetime NULL COMMENT 'Data created date',
  `updated_at` datetime NULL COMMENT 'Data updated date',
  PRIMARY KEY (`facility_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;