package com.company.payroll.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author leeshengxian
 * Updated at 22 Apr 2023
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class StaffBankingInfo {
    private Integer bId;
    
    @JsonProperty("bank_name")
    private String bankName;
    
    @JsonProperty("account_bank")
//    private Long accountnum;
    private Long bankAccount;
    
    @JsonProperty("account_income_tax")
//    private String incometaxno;
    private String incomeTaxNo;

    @JsonProperty("account_epf")
//    private Integer epfno;
    private String epfNo;
}