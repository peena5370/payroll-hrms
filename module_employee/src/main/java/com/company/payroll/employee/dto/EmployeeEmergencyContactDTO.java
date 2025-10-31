package com.company.payroll.employee.dto;

public record EmployeeEmergencyContactDTO(
        String contactPersonName,
        String relationship,
        String phoneNumber,
        String email
) {
}
