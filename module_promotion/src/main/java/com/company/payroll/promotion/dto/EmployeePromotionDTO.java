package com.company.payroll.promotion.dto;

import java.time.LocalDate;

public record EmployeePromotionDTO(
        Long employeeId,
        String oldJobTitle,
        String newJobTitle,
        Long oldDepartmentId,
        Long newDepartmentId,
        LocalDate promoteDate,
        Double incrementAmount,
        String reason,
        Long approverId
) {
}
