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
public class PayrollManager {
    private Integer prMgrId;

    private BigDecimal basicpay;

    private BigDecimal overtimepay;

    private BigDecimal allowance;

    private BigDecimal transport;

    private BigDecimal otherdeduction;
    
    @JsonProperty("manager_epf")
    private BigDecimal managerEpf;
    
    @JsonProperty("manager_socso")
    private BigDecimal managerSocso;
    
    @JsonProperty("manager_eis")
    private BigDecimal managerEis;
    
    @JsonProperty("employer_epf")
    private BigDecimal employerEpf;
    
    @JsonProperty("employer_socso")
    private BigDecimal employerSocso;
    
    @JsonProperty("employer_eis")
    private BigDecimal employerEis;
    
    @JsonProperty("mtd_pcb")
    private BigDecimal mtdPcb;

    private BigDecimal totalpay;
    
    @Schema(description="Pay period string format: 'yyyy-MM-dd to yyyy-MM-dd'")
    private String payperiod;

    private LocalDate paymentdate;

    private Integer mId;
}