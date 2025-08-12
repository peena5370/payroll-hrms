package com.company.payroll.repository;

import com.company.payroll.model.EmployeeEmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeEmergencyContactRepository extends JpaRepository<EmployeeEmergencyContact, Long> {
}
