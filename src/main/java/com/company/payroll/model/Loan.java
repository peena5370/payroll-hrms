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
public class Loan {
    private Integer loanId;

    @Schema(description="Reference number should store in below format:<br>"
						+ "yyyyMMdd-{8Characters-RandomString}")
    @JsonProperty("reference_number")
    private String refnum;

    private String reason;

    @JsonProperty("amount")
    private BigDecimal loanamount;
    
    @JsonProperty("application_date")
    private LocalDate applicationdate;

    @Schema(description="Loan status: 0=rejected, 1=approved, 2=rejected")
    @JsonProperty("status")
    private Byte loanstatus;

    @JsonProperty("approve_date")
    private LocalDate dateapproved;

    private Integer eId;

    private Integer mId;
}