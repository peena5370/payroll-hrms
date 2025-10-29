package com.company.payroll.department.dto;

import java.time.LocalDateTime;

public record DepartmentInfoDTO(
        Long departmentId,
        LocalDateTime createdAt,
        DepartmentDTO detail) {
}
