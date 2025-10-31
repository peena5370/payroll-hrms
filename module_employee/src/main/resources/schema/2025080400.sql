CREATE DATABASE `payroll_schema`;

USE `payroll_schema`;

SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `employee` (
  `employee_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Employee table id',
  `first_name` varchar(100) NOT NULL COMMENT 'Employee first name',
  `last_name` varchar(100) NOT NULL COMMENT 'Employee last name',
  `date_of_birth` DATE NULL COMMENT 'Employee date of birth',
  `ic_number` varchar(50) NOT NULL COMMENT 'Employee identity number',
  `gender` varchar(20) NULL COMMENT 'Gender',
  `email` varchar(100) NULL COMMENT 'Employee email',
  `phone_number` varchar(20) NULL COMMENT 'Employee phone number',
  `address_line_1` varchar(255) NULL COMMENT 'Address 1',
  `address_line_2` varchar(255) NULL COMMENT 'Address 2',
  `city` varchar(100) NULL COMMENT 'City',
  `state_province` varchar(100) NULL COMMENT 'State of province',
  `postal_code` varchar(20) NULL COMMENT 'Postal code',
  `country` varchar(100) NULL COMMENT 'Country',
  `hire_date` DATE NULL COMMENT 'Employee hire date',
  `employment_status` varchar(20) NULL COMMENT 'Employment status',
  `job_title` varchar(100) NULL COMMENT 'Job title',
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

SET FOREIGN_KEY_CHECKS=1;