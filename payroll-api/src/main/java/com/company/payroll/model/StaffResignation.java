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
public class StaffResignation {
    private Integer resignId;
    private String reason;
    private LocalDate resignDate;
    @Schema(description="Resign status: 0=ongoing, 1=completed, 2=rejected")
    private Byte resignStatus;
    @JsonProperty("filename")
    private String fileName;
    @JsonProperty("size")
    private long fileSize;
//    private String attachment;
    private String filePath;
    private Integer staffId;
    private StaffDetails staffDetails;
//    private Integer eId;
//    private Integer mId;
}