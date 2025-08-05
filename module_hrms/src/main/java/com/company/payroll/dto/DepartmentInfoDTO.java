package com.company.payroll.dto;

public record DepartmentInfoDTO(String departmentName, String description, String departmentManagerName,
                                String parentDepartmentName, String location, String phoneExtensionCode,
                                String departmentEmail) {
}
