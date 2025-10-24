package com.company.payroll.department.dto;

import java.time.LocalDateTime;

public record DepartmentInfoDTO(
        Long departmentId,
        String departmentName,
        String costCenterCode,
        String description,
        Long parentDepartmentId,
        String location,
        String phoneExtensionCode,
        String departmentEmail,
        LocalDateTime createdAt) {
}
