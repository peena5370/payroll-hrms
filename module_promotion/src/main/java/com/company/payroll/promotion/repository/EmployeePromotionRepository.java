package com.company.payroll.promotion.repository;

import com.company.payroll.promotion.model.EmployeePromotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePromotionRepository extends JpaRepository<EmployeePromotion, Long> {
}
