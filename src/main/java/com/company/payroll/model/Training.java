package com.company.payroll.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Training {
    private Integer tId;

    private String trainingtitle;

    private LocalDateTime startdate;

    private LocalDateTime enddate;

    private Byte sessionstatus;

    private Integer eId;

    private Integer mId;

    private String description;
}