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
public class BankingInfo {
    private Integer bId;
    
    @JsonProperty("bank_name")
    private String bankname;
    
    @JsonProperty("account_bank")
    private Long accountnum;
    
    @JsonProperty("account_income_tax")
    private String incometaxno;

    @JsonProperty("account_epf")
    private Integer epfno;

}