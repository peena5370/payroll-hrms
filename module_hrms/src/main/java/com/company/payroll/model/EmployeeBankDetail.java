package com.company.payroll.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "employee_bank_detail")
public class EmployeeBankDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_detail_id")
    private long bankDetailId;

    @Column(name = "employee_id", nullable = false)
    private long employeeId;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "account_number", nullable = false)
    private String encryptedAccountNumber;

    @Column(name = "bic_code", nullable = false)
    private String bicCode;

    @Column(name = "account_type", nullable = false)
    private String accountType;
}
