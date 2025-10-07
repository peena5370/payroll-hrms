package com.company.payroll.employee.repository;

import com.company.payroll.employee.model.EmployeeEmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeEmergencyContactRepository extends JpaRepository<EmployeeEmergencyContact, Long> {
}
