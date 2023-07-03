package com.company.payroll.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class StaffLeave {
    private Integer lId;
    @Schema(description="Reference number should store in below format:<br>"
    					+ "yyyyMMdd-{8Characters-RandomString}")
    @JsonProperty("reference_number")
    private String refNum;
    private String reason;
    private String leaveType;
    private LocalDate applicationDate;
    @JsonProperty("date_start")
    private LocalDateTime dateFrom;
    @JsonProperty("date_end")
    private LocalDateTime dateTo;
    @Schema(description="StaffLeave status: 0=rejected, 1=approved, 2=rejected")
    @JsonProperty("status")
    private Byte leaveStatus;
    private LocalDate approvedDate;
    private Integer staffId;
//    private Integer eId;
//    private Integer mId;
}