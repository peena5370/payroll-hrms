package com.company.payroll.resignation.dto;

import java.time.LocalDateTime;

public record EmployeeResignationDetailDTO(
        Long resignationId,
        LocalDateTime createdAt,
        EmployeeResignationDTO detail
) {
}
