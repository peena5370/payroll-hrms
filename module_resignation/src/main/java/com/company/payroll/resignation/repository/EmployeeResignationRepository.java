package com.company.payroll.resignation.repository;

import com.company.payroll.resignation.model.EmployeeResignation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeResignationRepository extends JpaRepository<EmployeeResignation, Long> {
}
