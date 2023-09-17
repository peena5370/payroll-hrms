CREATE DATABASE `payroll_schema`;

USE `payroll_schema`;

SET FOREIGN_KEY_CHECKS=0;

-- payroll_schema.ams_loan definition

DROP TABLE IF EXISTS `ams_loan`;
CREATE TABLE `ams_loan` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'loan ID',
  `ref_num` varchar(25) NOT NULL COMMENT 'Reference number for loan application',
  `reason` varchar(255) NOT NULL COMMENT 'Reason for loan application',
  `amount` decimal(8,2) NOT NULL COMMENT 'Loan amount',
  `date_repay_from` date NOT NULL COMMENT 'Loan repayment from date',
  `date_repay_to` date NOT NULL COMMENT 'Loan repayment to date',
  `repay_balance` decimal(8,2) NOT NULL COMMENT 'Balance loan need to pay by the staff',
  `date_apply` date NOT NULL COMMENT 'Date for loan application',
  `date_approved` date NULL COMMENT 'Loan application approved date',
  `status` tinyint(4) NOT NULL COMMENT 'Loan application status: 0=pending, 1=approved, 2=rejected',
  `staff_id` bigint NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.ams_resignation definition

DROP TABLE IF EXISTS `ams_resignation`;
CREATE TABLE `ams_resignation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Resignation table ID',
  `reason` varchar(500) NOT NULL COMMENT 'Reason',
  `date_resign` date NOT NULL COMMENT 'Resign date',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT 'Resignation status: 0=pending, 1=approved, 2=rejected',
  `file_name` varchar(255) NULL,
  `file_size` bigint NULL,
  `file_path` varchar(255) NULL,
  `staff_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.ams_time_off definition

DROP TABLE IF EXISTS `ams_time_off`;
CREATE TABLE `ams_time_off` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'leave ID',
  `ref_num` varchar(25) NOT NULL COMMENT 'Reference number',
  `type` varchar(25) NOT NULL COMMENT 'Leave type',
  `reason` varchar(255) NOT NULL COMMENT 'Reason',
  `date_apply` date NOT NULL COMMENT 'Application date',
  `date_approved` date NULL COMMENT 'Approved date',
  `date_start` datetime NOT NULL COMMENT 'Starts from',
  `date_end` datetime NOT NULL COMMENT 'Ends at',
  `status` tinyint(4) NOT NULL COMMENT 'Application status: 0=pending, 1=approved, 2=rejected',
  `staff_id` bigint NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.atms_staff_attendance definition

DROP TABLE IF EXISTS `atms_staff_attendance`;
CREATE TABLE `atms_staff_attendance` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_working` date NOT NULL COMMENT 'Staff check in date',
  `in_time_1` datetime NULL COMMENT 'Check in time 1',
  `out_time_1` datetime NULL COMMENT 'Check out time 1',
  `in_time_2` datetime NULL COMMENT 'Check in time 2',
  `out_time_2` datetime NULL COMMENT 'Check out time 2',
  `in_time_3` datetime NULL COMMENT 'Check in time 3',
  `out_time_3` datetime NULL COMMENT 'Check out time 3',
  `staff_id` bigint NULL COMMENT 'Staff id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.hms_file_attachment definition

DROP TABLE IF EXISTS `hms_file_attachment`;
CREATE TABLE `hms_file_attachment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'table id',
  `file_name` varchar(255) NOT NULL COMMENT 'File original name',
  `file_size` bigint NOT NULL COMMENT 'File size',
  `file_path` varchar(255) NOT NULL COMMENT 'Attachment file path',
  `staff_id` bigint NULL COMMENT 'Staff id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.hms_staff_appraisal definition

DROP TABLE IF EXISTS `hms_staff_appraisal`;
CREATE TABLE `hms_staff_appraisal` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Promotion table id',
  `current_salary` decimal(10,2) NOT NULL COMMENT 'Current salary',
  `promote_salary` decimal(10,2) NOT NULL COMMENT 'Promote salary',
  `promote_date` date NOT NULL COMMENT 'Promote date',
  `comment` varchar(255) NULL COMMENT 'appraisal description',
  `status` tinyint not null COMMENT 'appraisal status',
  `title_id` bigint NULL COMMENT 'Title table id',
  `staff_id` bigint NULL COMMENT 'promoted staff id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.hms_staff_banking definition

DROP TABLE IF EXISTS `hms_staff_banking`;
CREATE TABLE `hms_staff_banking` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Banking info table id',
  `account_number` bigint NOT NULL COMMENT 'Bank account number',
  `bank_name` varchar(100) NOT NULL COMMENT 'Bank name',
  `epf_account` int(11) NOT NULL COMMENT 'EPF account number',
  `income_tax_account` varchar(15) NULL COMMENT 'Income tax number',
  `staff_id` bigint null COMMENT 'Staff id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.hms_staff_detail definition

DROP TABLE IF EXISTS `hms_staff_detail`;
CREATE TABLE `hms_staff_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Employee table ID',
  `fullname` varchar(100) NOT NULL COMMENT 'Full name',
  `gender` enum('female','male','other') NOT NULL COMMENT 'Gender',
  `date_of_birth` date NOT NULL COMMENT 'Date of birth',
  `age` int(11) NOT NULL COMMENT 'Age',
  `marital_status` enum('divorced','married','single', 'widowed') NOT NULL COMMENT 'Marital status',
  `education` varchar(50) NOT NULL COMMENT 'Education level',
  `home_address` varchar(120) NOT NULL COMMENT 'Home address',
  `phone` varchar(15) NOT NULL COMMENT 'Phone number',
  `email` varchar(50) NOT NULL COMMENT 'Company email address',
  `hired_date` date NOT NULL COMMENT 'Date hired',
  `resign_date` date NULL COMMENT 'Date Resign',
  `profile_image_path` varchar(255) NULL COMMENT 'Personal image path',
  `dept_id` bigint NULL COMMENT 'Department table id',
  `title_id` bigint NULL COMMENT 'Title table id',
  `manager_id` bigint NULL COMMENT 'Staff detail table manager id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.hms_staff_job_title definition

DROP TABLE IF EXISTS `hms_staff_job_title`;
CREATE TABLE `hms_staff_job_title` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL UNIQUE COMMENT 'Title name',
  `description` varchar(500) NOT NULL COMMENT 'Title description',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.hms_staff_leave_balance definition

DROP TABLE IF EXISTS `hms_staff_leave_balance`;
CREATE TABLE `hms_staff_leave_balance` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Staff leave balance table id',
  `annual_leave` int(11) NOT NULL DEFAULT 0 COMMENT 'Annual leave balance',
  `hospitality_leave` int(11) NOT NULL DEFAULT 0 COMMENT 'Hospitality leave balance',
  `maternity_leave` int(11) NOT NULL DEFAULT 0 COMMENT 'Maternity leave balance',
  `unpaid_leave` int(11) NOT NULL DEFAULT 0 COMMENT 'Unpaid leave taken',
  `staff_id` bigint null COMMENT 'Staff id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.hms_staff_learning definition

DROP TABLE IF EXISTS `hms_staff_learning`;
CREATE TABLE `hms_staff_learning` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Training table id',
  `date_start` datetime NOT NULL COMMENT 'Start date',
  `date_end` datetime NULL COMMENT 'End date',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT 'Completion status: 0=Pending, 1=Completed, 2=Canceled',
  `staff_id` bigint NULL COMMENT 'Staff id',
  `learning_detail_id` bigint NULL COMMENT 'Learning detail table id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.hms_staff_learning_detail definition

DROP TABLE IF EXISTS `hms_staff_learning_detail`;
CREATE TABLE `hms_staff_learning_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Learning detail table id',
  `title` varchar(255) NOT NULL COMMENT 'Learning detail title',
  `description` text NOT NULL COMMENT 'Description',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.hms_staff_salary definition

DROP TABLE IF EXISTS `hms_staff_salary`;
CREATE TABLE `hms_staff_salary` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Salary table id',
  `monthly_salary` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT 'Monthly salary',
  `annual_salary` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT 'Annual salary',
  `update_date` date NULL COMMENT 'Salary update date',
  `staff_id` bigint null COMMENT 'Staff id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.pms_payroll definition

DROP TABLE IF EXISTS `pms_payroll`;
CREATE TABLE `pms_payroll` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Payroll employee table id',
  `basic_pay` decimal(10,2) NOT NULL COMMENT 'Employee basic salary',
  `overtime_pay` decimal(8,2) NOT NULL COMMENT 'Overtime pay',
  `allowance` decimal(8,2) NOT NULL COMMENT 'Allowance',
  `transport` decimal(8,2) NOT NULL COMMENT 'Transport allowance',
  `loan_deduction` decimal(8,2) NOT NULL COMMENT 'Loan deduction for staff loan application',
  `other_deduction` decimal(8,2) NOT NULL COMMENT 'Other deduction',
  `employee_epf` decimal(8,2) NOT NULL COMMENT 'Employee EPF deduction',
  `employee_socso` decimal(8,2) NOT NULL COMMENT 'Employee SOCSO deduction 0.5%',
  `employee_eis` decimal(8,2) NOT NULL COMMENT 'Employee EIS deduction 0.2%',
  `employer_epf` decimal(8,2) NOT NULL COMMENT 'Employer EPF deduction',
  `employer_socso` decimal(8,2) NOT NULL COMMENT 'Employer SOCSO deduction 1.75%',
  `employer_eis` decimal(8,2) NOT NULL COMMENT 'Employer EIS deduction 0.2%',
  `mtd_pcb` decimal(8,2) NOT NULL COMMENT 'Monthly tax deduction',
  `total` decimal(10,2) NOT NULL COMMENT 'Net pay for the payslip',
  `pay_period` varchar(50) NULL COMMENT 'Payroll period for a certain range of date',
  `date_issue` date NOT NULL COMMENT 'Payroll issue date',
  `staff_id` bigint NULL COMMENT 'Employee table id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.sys_account definition

DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Account table id',
  `username` char(20) NOT NULL UNIQUE COMMENT 'Account username',
  `password` char(68) NOT NULL COMMENT 'Account hashed password',
  `secret_key` char(44) NOT NULL COMMENT 'Secret key',
  `roles` enum('common_user', 'manager_admin', 'manager_user', 'sys_admin', 'sys_user') NOT NULL DEFAULT 'common_user' COMMENT 'account user role',
  `date_created` datetime NOT NULL COMMENT 'Account created date time',
  `date_modified` datetime NOT NULL COMMENT 'Account modified date time',
  `last_login` datetime NULL COMMENT 'Account last login date time',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0=inactive, 1=active, 2=locked',
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.sys_department definition

DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Department table id',
  `division_name` varchar(100) NOT NULL COMMENT 'Department name',
  `location` varchar(250) NOT NULL COMMENT 'Department address',
  `manager_id` bigint null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- payroll_schema.sys_profile definition

DROP TABLE IF EXISTS `sys_profile`;
CREATE TABLE `sys_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'system profile id',
  `about_profile` varchar(200) NULL COMMENT 'profile about me',
  `image_path` varchar(255) NULL COMMENT 'Account profile image path',
  `date_created` datetime NOT NULL COMMENT 'Profile created date',
  `date_modified` datetime NOT NULL COMMENt 'Profile update date',
  `account_id` bigint NULL COMMENT 'account id',
  `staff_id` bigint NULL COMMENT 'staff id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS=1;

-- production management system --> to be updated
-- warehouse management system --> to be updated
-- business management system --> to be updated