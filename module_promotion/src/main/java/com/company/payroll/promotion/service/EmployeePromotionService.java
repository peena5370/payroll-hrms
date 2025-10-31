package com.company.payroll.promotion.service;

import com.company.payroll.promotion.dto.EmployeePromotionDTO;
import com.company.payroll.promotion.dto.EmployeePromotionDetailDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeePromotionService {

    int createPromotionDetail(EmployeePromotionDTO employeePromotionDTO);

    List<EmployeePromotionDetailDTO> getAllPromotionsByOffsetAndLimitOrEmployeeId(Long employeeId, int offset, int limit);

    Optional<EmployeePromotionDetailDTO> getPromotionDetailById(long promotionId);

    int updatePromotionDetailById(long promotionId, EmployeePromotionDTO employeePromotionDTO);

    int deletePromotionDetailById(long promotionId);
}
