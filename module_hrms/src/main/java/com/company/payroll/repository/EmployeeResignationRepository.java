package com.company.payroll.repository;

import com.company.payroll.model.EmployeeResignation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeResignationRepository extends JpaRepository<EmployeeResignation, Long> {
}
