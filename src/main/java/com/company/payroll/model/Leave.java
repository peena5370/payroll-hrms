package com.company.payroll.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Leave {
    private Integer lId;

    @JsonProperty("reference_number")
    private String refnum;

    private String leavetype;

    private String reason;

    private LocalDate applicationdate;
    
    @JsonProperty("date_start")
    private LocalDateTime datefrom;
    
    @JsonProperty("date_end")
    private LocalDateTime dateto;
    
    @JsonProperty("status")
    private Byte leavestatus;

    private LocalDate approveddate;

    private Integer eId;

    private Integer mId;
}