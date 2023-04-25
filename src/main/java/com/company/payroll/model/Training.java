package com.company.payroll.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Training {
    private Integer tId;

    private String trainingtitle;
    
    @JsonProperty("date_start")
    private LocalDateTime startdate;

    @JsonProperty("date_end")
    private LocalDateTime enddate;
    
    @JsonProperty("status")
    private Byte sessionstatus;

    private Integer eId;

    private Integer mId;

    private String description;
}