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
public class StaffTraining {
    private Integer tId;
    private String trainingTitle;
    @Schema(description="Description may store a very long paragraph of text.<br>"
            + "Before submitting the text to back-end, please convert \\n(newline) into \\<br\\>")
    private String description;
    @JsonProperty("date_start")
    private LocalDateTime startDate;
    @JsonProperty("date_end")
    private LocalDateTime endDate;
    @Schema(description="Status: 0=ongoing, 1=completed, 2=canceled")
    @JsonProperty("status")
    private Byte trainingStatus;
    private Integer staffId;
    private StaffDetails staffDetails;
//    private Integer eId;
//    private Integer mId;
}