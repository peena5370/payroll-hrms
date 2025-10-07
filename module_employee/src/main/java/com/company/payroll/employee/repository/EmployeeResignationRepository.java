package com.company.payroll.employee.repository;

import com.company.payroll.employee.model.EmployeeResignation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeResignationRepository extends JpaRepository<EmployeeResignation, Long> {
}
