package com.company.payroll.employee.repository;

import com.company.payroll.employee.model.EmployeePromotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePromotionRepository extends JpaRepository<EmployeePromotion, Long> {
}
