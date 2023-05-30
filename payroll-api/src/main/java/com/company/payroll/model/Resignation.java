package com.company.payroll.model;

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
public class Resignation {
    private Integer resignId;

    private String reason;

    private LocalDate resigndate;

    @Schema(description="Resign status: 0=ongoing, 1=completed, 2=rejected")
    private Byte resignstatus;
    
    @JsonProperty("filename")
    private String fileName;
    
    @JsonProperty("size")
    private long fileSize;
    
    private String attachment;
    
    private Integer eId;
    
    private Integer mId;
}