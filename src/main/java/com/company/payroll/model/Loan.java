package com.company.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Loan {
    private Integer loanId;

    @JsonProperty("reference_number")
    private String refnum;

    private String reason;

    @JsonProperty("amount")
    private BigDecimal loanamount;
    
    @JsonProperty("application_date")
    private LocalDate applicationdate;

    @JsonProperty("status")
    private Byte loanstatus;

    @JsonProperty("approve_date")
    private LocalDate dateapproved;

    private Integer eId;

    private Integer mId;
}