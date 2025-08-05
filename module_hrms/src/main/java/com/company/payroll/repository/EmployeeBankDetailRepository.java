package com.company.payroll.repository;

import com.company.payroll.model.EmployeeBankDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeBankDetailRepository extends JpaRepository<EmployeeBankDetail, Long> {
}
