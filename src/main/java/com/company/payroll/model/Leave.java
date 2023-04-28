package com.company.payroll.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Leave {
    private Integer lId;
    
    @Schema(description="Reference number should store in below format:<br>"
    					+ "yyyyMMdd-{8Characters-RandomString}")
    @JsonProperty("reference_number")
    private String refnum;

    private String leavetype;

    private String reason;

    private LocalDate applicationdate;
    
    @JsonProperty("date_start")
    private LocalDateTime datefrom;
    
    @JsonProperty("date_end")
    private LocalDateTime dateto;
    
    @Schema(description="Leave status: 0=rejected, 1=approved, 2=rejected")
    @JsonProperty("status")
    private Byte leavestatus;

    private LocalDate approveddate;

    private Integer eId;

    private Integer mId;
}