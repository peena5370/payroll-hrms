package com.company.payroll.department.dto;

public record DepartmentDTO(String departmentName, String description, long managerId,
                         Long parentDepartmentId, String location, String phoneExtensionCode,
                         String departmentEmail) {
}
