package com.company.payroll.employee.dto;

import com.company.payroll.employee.constant.EmploymentStatus;
import com.company.payroll.employee.constant.Gender;

import java.time.LocalDate;
import java.util.List;

public record EmployeeDTO(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String icNumber,
        Gender gender,
        String email,
        String phoneNumber,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String postalCode,
        String country,
        LocalDate hireDate,
        EmploymentStatus employmentStatus,
        String jobTitle,
        Long managerId,
        List<EmployeeBankDetailDTO> bankDetails,
        List<EmployeeEmergencyContactDTO> emergencyContacts
) {
}
