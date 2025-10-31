package com.company.payroll.employee.dto;

import java.time.LocalDateTime;

public record EmployeeInfoDTO(
        Long employeeId,
        LocalDateTime createdAt,
        EmployeeDTO detail
) {
}
