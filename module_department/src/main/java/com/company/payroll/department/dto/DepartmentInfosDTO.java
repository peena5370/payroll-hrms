package com.company.payroll.department.dto;

public record DepartmentInfosDTO(Long departmentId, String departmentName, String description, Long managerId,
                                 Long parentDepartmentId, String location, String phoneExtensionCode,
                                 String departmentEmail) {
}
