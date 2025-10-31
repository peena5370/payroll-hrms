package com.company.payroll.promotion.dto;

import java.time.LocalDateTime;

public record EmployeePromotionDetailDTO(
        Long promotionId,
        LocalDateTime createdAt,
        EmployeePromotionDTO detail
) {
}
