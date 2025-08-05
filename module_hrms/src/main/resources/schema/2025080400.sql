CREATE DATABASE `payroll_schema`;

USE `payroll_schema`;

SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `company_facility` (
  `facility_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Company facility table id',
  `facility_name` varchar(100) NOT NULL COMMENT 'Company facility name',
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

CREATE TABLE `department` (
  `department_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Department table id',
  `department_name` varchar(100) NOT NULL COMMENT 'Department name',
  `description` TEXT NULL COMMENT 'Description',
  `manager_id` bigint NULL COMMENT 'Department manager id from employee table',
  `parent_department_id` bigint NULL COMMENT 'Parent department id from department table',
  `location` varchar(255) NULL COMMENT 'Department location',
  `phone_extension_code` varchar(20) NULL COMMENT 'Department phone extension code',
  `department_email` varchar(100) NULL COMMENT 'Department general email',
  `created_at` datetime NULL COMMENT 'Data created date',
  `updated_at` datetime NULL COMMENT 'Data updated date',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `employee` (
  `employee_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Employee table id',
  `first_name` varchar(100) NOT NULL COMMENT 'Employee first name',
  `last_name` varchar(100) NOT NULL COMMENT 'Employee last name',
  `date_of_birth` DATE NULL COMMENT 'Employee date of birth',
  `gender` varchar(20) NULL COMMENT 'Gender',
  `email` varchar(100) NULL COMMENT 'Employee email',
  `phone_number` TEXT NULL COMMENT 'Employee phone number',
  `address_line_1` varchar(255) NULL COMMENT 'Address 1',
  `address_line_2` varchar(255) NULL COMMENT 'Address 2',
  `city` varchar(100) NULL COMMENT 'City',
  `state_province` varchar(100) NULL COMMENT 'State of province',
  `postal_code` varchar(20) NULL COMMENT 'Postal code',
  `country` varchar(100) NULL COMMENT 'Country',
  `hire_date` DATE NULL COMMENT 'Employee hire date',
  `employment_status` varchar(20) NULL COMMENT 'Employment status',
  `job_title` varchar(20) NULL COMMENT 'Job title',
  `department_id` bigint NULL COMMENT 'Department id from department table',
  `manager_id` bigint NULL COMMENT 'Manager id from employee table',
  `created_at` datetime NULL COMMENT 'Data created date',
  `updated_at` datetime NULL COMMENT 'Data updated date',
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `employee_bank_detail` (
  `bank_detail_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Employee bank detail table id',
  `employee_id` bigint NOT NULL COMMENT 'Employee table id',
  `bank_name` varchar(100) NOT NULL COMMENT 'Bank name',
  `account_number` varchar(255) NULL COMMENT 'Bank account number',
  `bic_code` varchar(20) NOT NULL COMMENT 'Bank identifier code',
  `account_type` varchar(50) NOT NULL COMMENT 'Bank account type',
  PRIMARY KEY (`bank_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `employee_emergency_contact` (
  `contact_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Emergency contact table id',
  `employee_id` bigint NOT NULL COMMENT 'Employee table id',
  `contact_name` varchar(200) NOT NULL COMMENT 'Emergency contact name',
  `relationship` varchar(50) NOT NULL COMMENT 'Emergency contact relationship with employee',
  `phone_number` varchar(20) NOT NULL COMMENT 'Emergency contact phone number',
  `email` varchar(100) NULL COMMENT 'Emergency contact email address',
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

SET FOREIGN_KEY_CHECKS=1;