package com.company.payroll.model;

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
public class Training {
    private Integer tId;

    private String trainingtitle;
    
    @JsonProperty("date_start")
    private LocalDateTime startdate;

    @JsonProperty("date_end")
    private LocalDateTime enddate;
    
    @Schema(description="Status: 0=ongoing, 1=completed, 2=canceled")
    @JsonProperty("status")
    private Byte sessionstatus;

    private Integer eId;

    private Integer mId;

    @Schema(description="Description may store a very long paragraph of text.<br>"
    				  + "Before submitting the text to back-end, please convert \\n(newline) into \\<br\\>")
    private String description;
}