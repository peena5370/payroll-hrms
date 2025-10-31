package com.company.payroll.employee.repository;

import com.company.payroll.employee.model.EmployeeBankDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeBankDetailRepository extends JpaRepository<EmployeeBankDetail, Long> {

    @Query("SELECT ebd FROM EmployeeBankDetail ebd WHERE ebd.employeeId = :employeeId")
    List<EmployeeBankDetail> getAllByEmployeeId(@Param("employeeId") Long employeeId);
}
