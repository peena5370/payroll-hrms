package com.company.payroll.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AmsTimeOff {
    private Long id;

    private String refNum;

    private String type;

    private String reason;

    private LocalDate dateApply;

    private LocalDate dateApproved;

    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;

    private Byte status;

    private Long staffId;
}