package com.company.payroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class HmsStaffBanking {
    private Long id;

    private Long accountNumber;

    private String bankName;

    private Integer epfAccount;

    private String incomeTaxAccount;

    private Long staffId;
}