package com.company.payroll.employee.repository;

import com.company.payroll.employee.model.EmployeeBankDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeBankDetailRepository extends JpaRepository<EmployeeBankDetail, Long> {
}
