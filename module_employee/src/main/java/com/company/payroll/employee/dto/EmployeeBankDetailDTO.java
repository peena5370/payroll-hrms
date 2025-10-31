package com.company.payroll.employee.dto;

public record EmployeeBankDetailDTO(
        String bankName,
        String accountNumber,
        String bicCode,
        String accountType
) {
}
