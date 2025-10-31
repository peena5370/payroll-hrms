package com.company.payroll.employee.repository;

import com.company.payroll.employee.model.EmployeeEmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeEmergencyContactRepository extends JpaRepository<EmployeeEmergencyContact, Long> {

    @Query("SELECT eec FROM EmployeeEmergencyContact eec WHERE eec.employeeId = :employeeId")
    List<EmployeeEmergencyContact> getAllByEmployeeId(@Param("employeeId") Long employeeId);
}
