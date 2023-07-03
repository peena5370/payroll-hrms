package com.company.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class StaffLoan {
    private Integer loanId;
    @Schema(description="Reference number should store in below format:<br>"
						+ "yyyyMMdd-{8Characters-RandomString}")
    @JsonProperty("reference_number")
    private String refNum;
    private String reason;
    @JsonProperty("amount")
    private BigDecimal loanAmount;
    @JsonProperty("application_date")
    private LocalDate applicationDate;
    @Schema(description="StaffLoan status: 0=rejected, 1=approved, 2=rejected")
    @JsonProperty("status")
    private Byte loanStatus;
    @JsonProperty("approve_date")
//    private LocalDate dateapproved;
    private LocalDate approvedDate;
    private Integer staffId;
//    private Integer eId;
//
//    private Integer mId;
}