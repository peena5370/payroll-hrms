package com.company.payroll.repository;

import com.company.payroll.model.EmployeePromotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePromotionRepository extends JpaRepository<EmployeePromotion, Long> {
}
