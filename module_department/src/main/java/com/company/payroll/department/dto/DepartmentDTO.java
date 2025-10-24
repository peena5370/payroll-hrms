package com.company.payroll.department.dto;

public record DepartmentDTO(
        String departmentName,
        String costCenterCode,
        String description,
        Long parentDepartmentId,
        String location,
        String phoneExtensionCode,
        String departmentEmail) {
}
