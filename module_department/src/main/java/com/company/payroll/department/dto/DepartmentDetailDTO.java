package com.company.payroll.department.dto;

public record DepartmentDetailDTO(
        Long departmentId,
        Long facilityId,
        Long managerId,
        DepartmentDTO detail
) {
}
